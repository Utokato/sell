package com.ml.sell.repository;

import com.ml.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单主表DAO
 *
 * Data Access Object 数据访问对象
 *
 * @author
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 根据买家openid和分页信息，返回订单主表的分页列表信息
     *
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
