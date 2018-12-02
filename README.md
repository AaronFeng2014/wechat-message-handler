## 微信小程序和服务号消息处理

该工具包主要提供了与业务无关的，微信消息发送，资源文件上传等相关功能，并带有缓存接口，使用者可以自动以缓存实现

### 功能介绍

1. 微信资源上传与消息发送

    **缓存介绍**
    
       在二维码生成和媒体资源上传时，默认使用了简单的内存缓存
       二维码缓存接口QrCodeCache，媒体资源缓存接口MideaCache，
       如果用户不使用内存缓存，可以通过带缓存参数的接口，传入自定义的缓存实现（如redis，mysql等）
    **注意：** 微信消息发送和资源上传功能封装在WxResourceUtil类中
       
       
2. 小程序消息解密和token认证解密

       小程序数据解密：MiniProgramEncryptedDataDecoder
       微信token认证数据解密：WxSignatureUtil
3. endpoint暴露方式
      
       AbstractMessageCallBackController抽象类中自动暴露了2个endpoints
       
       a. GET：/{contextPath}/message/callback/{appId}，用于微信公众平台后台配置回调地址时，认证token使用
       b. POST：/{contextPath}/message/callback/{appId}，用户微信回调使用，开发者可以根据回调参数做不同的业务逻辑
       
      **注意**：以上2个endponits必须保证path一致，不要随意更改

### 接入指南

1. maven引入方式
       
       ......

2. 配置文件配置方式
    
       properties配置：
            wechat.config.appConfigList[0].appId=appid
            wechat.config.appConfigList[0].token=token
            wechat.config.appConfigList[0].encodingAesKey=encodingAesKey
       
       yml配置：
            wechat:
              config:
                appConfigList:
                - appId: appId1
                  token: token1
                  encodingAesKey: encodingAesKey1
                - appId: appId2
                  token: token2
                  encodingAesKey: encodingAesKey2           
      **提示：** 需要包com.aaron.springcloud.wx被spring扫描到
      
3. Controller实现
      
       接入的应用需要有一个controller实现AbstractMessageCallBackController（该controller会自动暴露endpoints） 并实现一个需要唯一实现的方法 registerHandler

4. 关心的回调事件与对应的处理器注册
        
       在第3步实现的方法中注册相应的MessageHandlerContext 和 MessageHandlerAdapterContext，形式如下
            
        //申明一个MessageHandlerAdapterContext，对应一个appId下的事件处理，同一个appId可以注册多个MessageHandlerAdapterContext
        MessageHandlerAdapterContext context = new MessageHandlerAdapterContext("appId");
            
        //这里是注册一个事件对应的处理器
        context.addMessageHandlerAdapter(WxCallBackTypeEnum.SUBSCRIBE_EVENT, MessageHandlerAdapter ... MessageHandlerAdapter);
        
        //这里是注册一个小程序或者公众号对应的处理器
        handlerContext.addMessageHandler(context);
            
5. 自定义功能逻辑实现

       在自定义的逻辑处理器中需要用户自行实现MessageHandlerAdapter接口，并实现messageHandle方法，在该方法中实现业务逻辑，该方法用户可以不用关注异常处理
       如果异常时，有特殊的逻辑，可以实现exceptionCaught方法，并在此方法中完成异常逻辑
       
       For example：
       
       public class WelcomeHandler implements MessageHandlerAdapter
       {
       
           @Override
           public void messageHandle(Map<String, String> params)
           {
               System.out.println("在这里实现你的业务");
           }
           
           
           @Override
           public void exceptionCaught(Map<String, String> params， Exception e)
           {
               System.out.println("在这里实现你的异常逻辑");
           }
       }
       
**环境需求：**

    jdk 1.8+
    spring 4+
