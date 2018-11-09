package com.ml.sell.service.impl;

import com.ml.sell.dataobject.SellerInfo;
import com.ml.sell.repository.SellerInfoRepository;
import com.ml.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2018/11/9
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }

    @Override
    public SellerInfo findSellerInfoByUsername(String username) {
        return sellerInfoRepository.findByUsername(username);
    }

}
