package com.ml.sell.converter;

import com.ml.sell.dataobject.OrderMaster;
import com.ml.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换工具类
 *
 * @author
 */
public class OrderMaster2OrderDTOConverter {

    /**
     * 将orderMaster转换成OrderDTO
     *
     * @param orderMaster
     * @return
     */
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    /**
     * 将orderMaster列表对象转换成OrderDTO列表对象
     *
     * @param orderMasterList
     * @return
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
