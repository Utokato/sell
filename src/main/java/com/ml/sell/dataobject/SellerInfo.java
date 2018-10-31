package com.ml.sell.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 买家表
 *
 * @author
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
