package com.ml.sell.repository;

import com.ml.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情DAO
 *
 * Data Access Object 数据访问对象
 *
 * @author
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 根据orderId返回对应的订单详情列表
     *
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
