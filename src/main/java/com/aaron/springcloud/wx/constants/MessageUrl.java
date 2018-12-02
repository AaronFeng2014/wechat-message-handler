package com.aaron.springcloud.wx.constants;

/**
 * 微信各种消息的url地址
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public interface MessageUrl
{
    String WX_DOMAIN = "api.weixin.qq.com";

    /**
     * 接口调用凭证获取地址
     */
    String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={}&secret={}";

    String SERVERIP_URL = "https://api.weixin.qq.com/cgi-bin/getcallbackip";

    /**
     * 创建菜单
     */
    String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    String SEARCHMEN_UURL = "https://api.weixin.qq.com/cgi-bin/menu/get";

    String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";

    String CREATECONDITIONALMENUURL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional";

    String DELETECONDITIONALMENUURL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional";

    String TRYMATCHCONDITIONALMENUURL = "https://api.weixin.qq.com/cgi-bin/menu/trymatch";

    String SEARCHALLMENUURL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";

    String ADDKFACCOUNTURL = "https://api.weixin.qq.com/customservice/kfaccount/add";

    String UPDATEKFACCOUNTURL = "https://api.weixin.qq.com/customservice/kfaccount/update";

    String DELETEKFACCOUNTURL = "https://api.weixin.qq.com/customservice/kfaccount/del";

    String SETKFHEADIMGURL = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg";

    String LISTALLKFURL = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist";

    /**
     * 普通客服消息发送地址
     */
    String COSTUMER_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    String UPLOADIMAGEURL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";

    String UPLOADNEWSURL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";

    String SENDGROUPBYTAG = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall";

    String SENDGROUPBYOPENID = "https://api.weixin.qq.com/cgi-bin/message/mass/send";

    String UPLOADVIDEOURL = "https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo";

    String DELETEGROUPMESSAGEURL = "https://api.weixin.qq.com/cgi-bin/message/mass/delete";

    String PREVIEWGROUPMESSAGEURL = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";

    String GROUPMESSAGESTATUSURL = "https://api.weixin.qq.com/cgi-bin/message/mass/get";

    String SETINDUSTRYURL = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry";

    String GETINDUSTRYURL = "https://api.weixin.qq.com/cgi-bin/template/get_industry";

    String GETTEMPLATEURL = "https://api.weixin.qq.com/cgi-bin/template/api_add_template";

    String LISTTEMPLATESURL = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template";

    String DELETETEMPLATEURL = "https://api.weixin.qq.com/cgi-bin/template/del_private_template";

    /**
     * 发送模板消息地址
     */
    String SEND_TEMPLATE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    String AUTOREPLYURL = "https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info";

    /**
     * 媒体资源上传接口
     */
    String UPLOAD_TEMP_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";

    String GETTEMPMEDIAURL = "https://api.weixin.qq.com/cgi-bin/media/get";

    String ADDNEWSMEDIAURL = "https://api.weixin.qq.com/cgi-bin/material/add_news";

    String ADDMEDIAURL = "https://api.weixin.qq.com/cgi-bin/material/add_material";

    String GETMEDIAURL = "https://api.weixin.qq.com/cgi-bin/material/get_material";

    String DELETEMEDIAURL = "https://api.weixin.qq.com/cgi-bin/material/del_material";

    String UPDATENEWSMEDIAURL = "https://api.weixin.qq.com/cgi-bin/material/update_news";

    String COUNTMEDIAURL = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount";

    String LISTMEDIAURL = "https://api.weixin.qq.comcgi-bin/material/batchget_material";

    String CREATETAGURL = "https://api.weixin.qq.com/cgi-bin/tags/create";

    String LISTTAGSURL = "https://api.weixin.qq.com/cgi-bin/tags/get";

    String UPDATETAGURL = "https://api.weixin.qq.com/cgi-bin/tags/update";

    String DELETETAGURL = "https://api.weixin.qq.com/cgi-bin/tags/delete";

    String LISTFANSBYTAGURL = "https://api.weixin.qq.com/cgi-bin/user/tag/get";

    String BATCHTAGURL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging";

    String BATCHCANCELTAGURL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging";

    String GET_TAGIDS_URL = "https://api.weixin.qq.com/cgi-bin/tags/getidlist";

    String UPDATE_USER_REMARK_URL = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark";

    String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";

    String BATCH_GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget";

    String LISTATTENTUSERURL = "https://api.weixin.qq.com/cgi-bin/user/get";

    String GETBLACKLISTURL = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist";

    String BATCHBLACKLISTURL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist";

    String CANCELBLACKLISTURL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist";

    /**
     * 服务号二维码生成ticket地址
     */
    String CREATE_QRCODE_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    String QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    /**
     * 小程序二维码生成地址
     */
    String MINIPROGRAM_QRCODE_URL = "http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";

    String CONVERTSHORTURL = "https://api.weixin.qq.com/cgi-bin/shorturl";

    String GETWEBACCESSTOKENURL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    String REFRESHWEBACCESSTOKENURL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    String GET_USER_INFO_BY_WEBACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/userinfo";

    String CHECK_WEB_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/auth";

    String GET_JS_API_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
}
