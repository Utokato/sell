package com.ml.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * @author
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * 公众平台id
     */
    private String mpAppId;

    /**
     * 公众平台秘钥
     */
    private String mpAppSecret;

    /**
     * 开放平台id
     */
    private String openAppId;

    /**
     * 开放平台秘钥
     */
    private String openAppSecret;


    /**
     * 微信模板消息id
     */
    private Map<String,String> templateId;

}
