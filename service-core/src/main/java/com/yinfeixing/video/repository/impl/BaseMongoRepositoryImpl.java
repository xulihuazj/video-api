package com.yinfeixing.video.repository.impl;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinfeiixng.video.enums.SortEnum;
import com.yinfeiixng.video.model.OrderSortParse;
import com.yinfeiixng.video.model.PageModel;
import com.yinfeixing.video.repository.BaseMongoRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * MongoDB数据库基本操作接口
 *
 * @param <T>
 */
public abstract class BaseMongoRepositoryImpl<T> implements BaseMongoRepository<T> {

    // 子类必须实现
    protected abstract Class<T> getEntityClass();

    @Resource
    protected MongoTemplate mongoTemplate;

    @Override
    public void save(T entity) {
        this.mongoTemplate.save(entity);
    }

    @Override
    public void update(T entity) {
        String jsonStr = JSON.toJSONString(entity);
        Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
        String idName = null;
        Object idValue = null;
        Update update = new Update();
        for (Map.Entry mapInter : map.entrySet()) {
            String key = (String) mapInter.getKey();
            if (key.indexOf("{") != -1) {
                idName = key.substring(key.indexOf("{") + 1, key.indexOf("}"));
                idValue = mapInter.getValue();
            } else {
                update.set(key, mapInter.getValue());
            }
        }
        Query query = new Query().addCriteria(where(idName).is(idValue));
        this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
    }

    @Override
    public void delete(Serializable... ids) {
        if (ArrayUtils.isNotEmpty(ids)) {
            for (Serializable _id : ids) {
                this.mongoTemplate.remove(mongoTemplate.findById(_id, this.getEntityClass()));
            }
        }
    }

    @Override
    public T find(Serializable _id) {
        return this.mongoTemplate.findById(_id, this.getEntityClass());
    }

    @Override
    public List<T> findAll() {
        return this.mongoTemplate.findAll(this.getEntityClass());
    }

    @Override
    public List<T> findAllWithSort(List<SortEnum> sort) {
        if (CollectionUtils.isEmpty(sort)) {
            return this.findAll();
        } else {
            List<Sort.Order> orderList = OrderSortParse.parseOrder(sort);
            return this.mongoTemplate.find(new Query().with(new Sort(orderList)), this.getEntityClass());
        }
    }

    @Override
    public List<T> findByProp(String propName, ObjectMapper propValue) {
        return this.findByPropWithSort(propName, propValue, null);
    }

    @Override
    public List<T> findByPropWithSort(String propName, ObjectMapper propValue, List<SortEnum> sort) {
        Query query = new Query();
        // 查询器参数
        query.addCriteria(where(propName).is(propValue));
        // 排序参数
        List<Sort.Order> orderList = OrderSortParse.parseOrder(sort);
        if (CollectionUtils.isEmpty(orderList)) {
            query.with(new Sort(orderList));
        }
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    @Override
    public List<T> findByProps(Map<String, Object> propMaps) {
        return this.findByPropsWithSort(propMaps, null);
    }

    @Override
    public List<T> findByPropsWithSort(Map<String, Object> propMaps, List<SortEnum> sort) {
        Query query = this.createQueryWithSort(propMaps, sort);
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    @Override
    public T uniqueByProp(String propName, Object propValue) {
        Query query = new Query(where(propName).is(propValue));
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    @Override
    public T uniqueByProps(Map<String, Object> propMaps) {
        Query query = this.createQueryWithSort(propMaps, null);
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    @Override
    public PageModel<T> pageAll(int pageNo, int pageSize) {
        return this.pageAllWithSort(pageNo, pageSize, null);
    }

    @Override
    public PageModel<T> pageAllWithSort(int pageNo, int pageSize, List<SortEnum> sort) {
        return this.pageByPropWithSort(pageNo, pageSize, null, null, sort);
    }

    @Override
    public PageModel<T> pageByProp(int pageNo, int pageSize, String propName, Object propValue) {
        return this.pageByPropWithSort(pageNo, pageSize, propName, propValue, null);
    }

    @Override
    public PageModel<T> pageByPropWithSort(int pageNo, int pageSize, String propName, Object propValue, List<SortEnum> sort) {
        Map<String, Object> propMaps = new HashMap<>();
        propMaps.put(propName, propValue);
        return this.pageByPropsWithSort(pageNo, pageSize, propMaps, sort);
    }

    @Override
    public PageModel<T> pageByProps(int pageNo, int pageSize, Map<String, Object> propMaps) {
        return this.pageByPropsWithSort(pageNo, pageSize, propMaps, null);
    }

    @Override
    public PageModel<T> pageByPropsWithSort(int pageNo, int pageSize, Map<String, Object> propMaps, List<SortEnum> sort) {
        PageModel<T> pageModel = new PageModel<>(pageNo, pageSize);
        // 查询总记录数
        int count = this.countByCondition(propMaps);
        pageModel.setTotalCount(count);
        // 查询数据列表
        Query query = this.createQueryWithSort(propMaps, sort);
        // 设置分页
        query.skip(pageModel.getFirstResult());
        query.limit(pageModel.getPageSize());
        // 结构集
        List<T> resultList = this.mongoTemplate.find(query, this.getEntityClass());
        pageModel.setResult(resultList);
        return pageModel;
    }

    @Override
    public int countByCondition(Map<String, Object> propMaps) {
        Query query = this.createQueryWithSort(propMaps, null);
        Long count = this.mongoTemplate.count(query, this.getEntityClass());
        return count.intValue();
    }

    /**
     * 创建带有where条件（只支持等值）和排序的Query对象
     *
     * @param propMaps 参数key-value
     * @param sort     排序规则
     * @return
     */
    private Query createQueryWithSort(Map<String, Object> propMaps, List<SortEnum> sort) {
        Query query = new Query();
        // where 条件
        if (null != propMaps) {
            for (Map.Entry<String, Object> entry : propMaps.entrySet()) {
                query.addCriteria(where(entry.getKey()).is(entry.getValue()));
            }
        }
        // 排序
        List<Sort.Order> orderList = OrderSortParse.parseOrder(sort);
        if (CollectionUtils.isNotEmpty(orderList)) {
            query.with(new Sort(orderList));
        }
        return query;
    }
}