package com.yinfeixing.video.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinfeiixng.video.enums.SortEnum;
import com.yinfeiixng.video.model.PageModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseMongoRepository<T> {

    /**
     * 保存实体</br>
     * id自动赋值
     *
     * @param entity
     */
    void save(T entity);

    /**
     * 修改实体
     *
     * @param entity
     */
    void update(T entity);

    /**
     * 删除记录
     *
     * @param ids _id 数组
     */
    void delete(Serializable... ids);

    /**
     * 根据_id 查询
     *
     * @param _id
     * @return
     */
    T find(Serializable _id);

    /**
     * 查询所有记录<br>
     * 不分页<br>
     *
     * @return
     */
    List<T> findAll();

    /**
     * 查询所有记录并排序<br>(ps：不分页)
     *
     * @param sort 排序字段，例如：_id或_id asc或 _id asc，name desc<br>
     *             为空则不排序，不指定排序方式则默认升序排序
     * @return
     */
    List<T> findAllWithSort(List<SortEnum> sort);

    /**
     * 根据单一参数查询记录<br>(ps：不分页)
     *
     * @param propName  属性名称，对应实体类字段名称
     * @param propValue 属性值
     * @return
     */
    List<T> findByProp(String propName, ObjectMapper propValue);

    /**
     * 根据单一参数查询记录并排序<br>(ps：不分页)
     *
     * @param propName  属性名称，对应实体类字段名称
     * @param propValue 属性值
     * @param sort      排序字段，_id或_id asc或 _id asc，name desc<br>
     *                  为空则不排序，不指定排序方式则默认升序排序
     * @return
     */
    List<T> findByPropWithSort(String propName, ObjectMapper propValue, List<SortEnum> sort);

    /**
     * 根据多个参数查询记录<br>(ps：不分页)
     *
     * @param propMaps 参数key-value
     * @return
     */
    List<T> findByProps(Map<String, Object> propMaps);

    /**
     * 根据多个参数查询记录 并排序<br>(ps：不分页)
     *
     * @param propMaps 参数key-value
     * @param sort     排序字段，_id或_id asc或 _id asc，name desc<br>
     *                 为空则不排序，不指定排序方式则默认升序排序
     * @return 结果集合 或 null
     */
    List<T> findByPropsWithSort(Map<String, Object> propMaps, List<SortEnum> sort);

    /**
     * 根据单一参数查询唯一结果<br>
     * [HQL]
     *
     * @param propName  属性名称，对应实体类字段名
     * @param propValue 属性值
     * @return 唯一结果 或 null
     */
    T uniqueByProp(String propName, Object propValue);

    /**
     * 根据多个参数查询唯一结果<br>
     * [HQL]
     *
     * @param propMaps 参数key-value
     * @return 唯一结果 或 null
     */
    T uniqueByProps(Map<String, Object> propMaps);

    /**
     * 分页查询所有结果集合<br>
     * [分页]
     *
     * @param pageNo   当前页码
     * @param pageSize 页容量
     * @return 分页模型对象（不会为null）
     */
    PageModel<T> pageAll(int pageNo, int pageSize);

    /**
     * 分页查询所有结果集合 并排序<br>
     * [分页]
     *
     * @param pageNo   当前页码
     * @param pageSize 页容量
     * @param sort     排序字段，_id或_id asc或 _id asc，name desc<br>
     *                 为空则不排序，不指定排序方式则默认升序排序
     * @return 分页模型对象（不会为null）
     */
    PageModel<T> pageAllWithSort(int pageNo, int pageSize, List<SortEnum> sort);

    /**
     * 根据参数分页查询结果集合<br>
     * [分页]
     *
     * @param pageNo   当前页码
     * @param pageSize 页容量
     * @param propName  参数key-value
     * @return 分页模型对象（不会为null）
     */
    PageModel<T> pageByProp(int pageNo, int pageSize, String propName,Object propValue);

    /**
     * 根据参数分页查询结果集合并排序<br>
     * [分页]
     *
     * @param pageNo   当前页码
     * @param pageSize 页容量
     * @param propName  参数key-value
     * @param sort     排序字段，_id或_id asc或 _id asc，name desc<br>
     *                 为空则不排序，不指定排序方式则默认升序排序
     * @return 分页模型对象（不会为null）
     */
    PageModel<T> pageByPropWithSort(int pageNo, int pageSize, String propName, Object propValue, List<SortEnum> sort);

    /**
     * 根据参数分页查询结果集合<br>
     * [分页]
     *
     * @param pageNo   当前页码
     * @param pageSize 页容量
     * @param propMaps 参数key-value数组
     * @return 分页模型对象（不会为null）
     */
    PageModel<T> pageByProps(int pageNo, int pageSize, Map<String, Object> propMaps);

    /**
     * 根据参数分页查询结果集合 并排序<br>
     * [分页]
     *
     * @param pageNo   当前页码
     * @param pageSize 页容量
     * @param propMaps 参数key-value
     * @param sort     排序字段，_id或_id asc或 _id asc，name desc<br>
     *                 为空则不排序，不指定排序方式则默认升序排序
     * @return 分页模型对象（不会为null）
     */
    PageModel<T> pageByPropsWithSort(int pageNo, int pageSize, Map<String, Object> propMaps, List<SortEnum> sort);

    /**
     * 根据条件查询总记录数
     *
     * @param propMaps 参数key-value
     * @return 总记录数
     */
    int countByCondition(Map<String, Object> propMaps);

}
