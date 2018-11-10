package com.ml.sell.service.impl;

import com.ml.sell.config.WechatAccountConfig;
import com.ml.sell.dto.OrderDTO;
import com.ml.sell.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author
 * @date 2018/11/10
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        // mock string
        wxMpTemplateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲，记得收货."),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "18088889999"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5", "￥" + orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临!")
        );

        wxMpTemplateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("[微信模板消息] 发送失败，{}", e);
            log.info("[微信模板消息] Mock String，因为无法调试微信，所以在这里记录一下日志");
        }

    }
}
