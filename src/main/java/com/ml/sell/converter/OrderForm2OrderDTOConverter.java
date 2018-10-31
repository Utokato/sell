package com.ml.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ml.sell.dataobject.OrderDetail;
import com.ml.sell.dto.OrderDTO;
import com.ml.sell.enums.ResultEnum;
import com.ml.sell.exception.SellException;
import com.ml.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 转换工具类
 *
 * @author
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    /**
     * 将前端表单中的数据转换到OrderDTO对象中
     *
     * @param orderForm
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("[对象转换] 错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
