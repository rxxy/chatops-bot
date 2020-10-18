package io.github.rxxy.adapter.dingtalk;

import cn.hutool.core.codec.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DingUtil {

    public static final String sign = "SECdccd2b24af7057eb826d8cdfdc1f90610fa5b47588f82da9475eaf09675a4a93";
    public static final String appSecret = "hCQQTRdIUfdkyJ5obKr9ktPerzwTXRPl9Bamw6oidOhlcmQ04zrZ0u0EC4-muRTz";


    /**
     * 生成签名
     * @param timestamp 时间戳
     */
    public static String generateSign(long timestamp) {
        String stringToSign = timestamp + "\n" + sign;
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(sign.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        } catch (NoSuchAlgorithmException|InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        return Base64.encode(signData);
    }

    /**
     * 生成签名
     * @param timestamp 时间戳
     */
    public static String generateHandlerSign(long timestamp) {
        String stringToSign = timestamp + "\n" + appSecret;
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(appSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        } catch (NoSuchAlgorithmException|InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        return Base64.encode(signData);
    }

}
