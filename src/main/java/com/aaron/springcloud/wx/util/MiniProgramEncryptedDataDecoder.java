package com.aaron.springcloud.wx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 微信小程序数据解密
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/10/18
 */
public class MiniProgramEncryptedDataDecoder
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MiniProgramEncryptedDataDecoder.class);


    public static String decrypt(String appId, String encryptedData, String sessionKey, String iv) throws RuntimeException
    {
        String result;
        try
        {

            byte[] resultByte = decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));

            if (ArrayUtils.isEmpty(resultByte))
            {
                throw new RuntimeException("无数据，解密数据出错");
            }

            result = new String(decode(resultByte));

            JSONObject jsonObject = JSON.parseObject(result);
            String decryptAppId = jsonObject.getJSONObject("watermark").getString("appid");
            if (!appId.equals(decryptAppId))
            {
                LOGGER.error("数据不属于当前用户");
                throw new RuntimeException("数据不属于当前用户");
            }

            return result;
        }
        catch (Exception e)
        {
            LOGGER.error("解密失败，session可以:{}, iv:{}", sessionKey, iv, e);
            throw new RuntimeException("解密数据出错", e);
        }
    }


    private static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws Exception
    {

        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");

        Key sKeySpec = new SecretKeySpec(keyByte, "AES");

        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIv(ivByte));

        return cipher.doFinal(content);

    }


    private static AlgorithmParameters generateIv(byte[] iv) throws Exception
    {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");

        params.init(new IvParameterSpec(iv));

        return params;
    }


    private static byte[] decode(byte[] decrypted)
    {
        int pad = decrypted[decrypted.length - 1];
        if (pad < 1 || pad > 32)
        {
            pad = 0;
        }
        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }
}