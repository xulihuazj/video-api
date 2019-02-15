package com.yinfeiixng.video.model;


import com.yinfeiixng.video.enums.SortEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class OrderSortParse {

    /**
     * 解析排序参数
     *
     * @param sorts
     * @return
     */
    public static List<Sort.Order> parseOrder(List<SortEnum> sorts) {
        List<Sort.Order> orderList = null;
        if (CollectionUtils.isNotEmpty(sorts)) {
            orderList = new ArrayList<>();
            for (SortEnum sortEnum : sorts) {
                Sort.Direction direction;
                switch (sortEnum.getDesc()) {
                    case "DESC":
                        direction = Sort.Direction.DESC;
                        break;
                    default:
                        direction = Sort.Direction.ASC;
                        break;
                }
                Sort.Order order = new Sort.Order(direction, sortEnum.getCode());
                orderList.add(order);
            }
        }
        return orderList;
    }
}
