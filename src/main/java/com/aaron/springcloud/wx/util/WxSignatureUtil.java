package com.aaron.springcloud.wx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * 微信token认证
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class WxSignatureUtil
{

    public static boolean checkSignature(String token, String signature, String timestamp, String nonce)
    {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce))
        {
            return false;
        }

        String[] arr = new String[] {token, timestamp, nonce};

        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);

        StringBuilder content = new StringBuilder();
        for (String anArr : arr)
        {
            content.append(anArr);
        }

        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());

            String tmpStr = byteToStr(digest);

            // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
            return signature.toUpperCase().equals(tmpStr);
        }
        catch (NoSuchAlgorithmException e)
        {
            return false;
        }

    }


    private static String byteToStr(byte[] byteArray)
    {
        StringBuilder strDigest = new StringBuilder();

        for (byte aByteArray : byteArray)
        {
            strDigest.append(byteToHexStr(aByteArray));
        }

        return strDigest.toString();
    }


    private static String byteToHexStr(byte mByte)
    {
        char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];
        return new String(tempArr);
    }
}