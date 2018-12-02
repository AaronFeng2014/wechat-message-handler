package com.aaron.springcloud.wx.menu;

import com.aaron.springcloud.wx.constants.MessageUrl;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 创建菜单内容
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
@Setter
@Getter
public class MenuButton
{

    @JSONField (serialize = false)
    private String url;

    @JSONField (name = "button")
    private List<ButtonBean> buttons;


    public MenuButton(String accessToken)
    {
        this.url = MessageUrl.CREATE_MENU_URL + accessToken;
    }


    @Setter
    @Getter
    public static class ButtonBean
    {
        /**
         * type : click
         * name : 今日歌曲
         * key : V1001_TODAY_MUSIC
         * sub_button : [{"type":"view","name":"搜索","url":"http://www.soso.com/"},{"type":"miniprogram","name":"wxa","url":"http://mp.weixin.qq.com","appid":"wx286b93c14bbf93aa","pagepath":"pages/lunar/index"},{"type":"click","name":"赞一下我们","key":"V1001_GOOD"}]
         */

        private String type;

        private String name;

        private String url;

        private String key;

        @JSONField (name = "pagepath")
        private String pagePath;

        @JSONField (name = "appid")
        private String appId;

        @JSONField (name = "sub_button")
        private List<ButtonBean> subButton;
    }
}