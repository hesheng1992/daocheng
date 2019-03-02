package com.almagway.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Aes 加密工具类
 * Created by leiyanxiang on 16-11-9.
 */

public class AesUtil {

    //    private static final String IV_STRING = "#dsd^6GER8@#dasX";
    private static final String IV_STRING = "t&j9*O@nxFq2E#g(";
    private static final String CHARSET = "UTF-8";
    private static final String AES_MODE = "AES/CBC/PKCS5Padding";
    private static final String AES_PASSWORD = "ajNGbzgjOHZTVEgjKjU3ZyRjRyp1IWR1KWZ1I2hlKEg=";

    /**
     * 描述：Aes加密
     *
     * @param content 加密前的字符串
     * @return 加密后的字符串
     */
    public static String encryptAES(String content) {


        byte[] encryptedBytes = new byte[0];
        try {
            byte[] byteContent = content.getBytes(CHARSET);
            // 注意，为了能与 iOS 统一
            // 这里的 PASSWORD 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
            String password = getStringKey();
            byte[] enCodeFormat = password.getBytes(CHARSET);
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");

            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

            // 指定加密的算法、工作模式和填充方式
            Cipher cipher = Cipher.getInstance(AES_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            encryptedBytes = cipher.doFinal(byteContent);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | UnsupportedEncodingException
                | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        // 同样对加密后数据进行 base64 编码
        return Base64Util.encode(encryptedBytes);
    }

    /**
     * 描述：Aes解密
     *
     * @param content 揭秘前的字符串
     * @return 解密后的字符串
     */
    public static String decryptAES(String content)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

        // base64 解码
        byte[] encryptedBytes = Base64Util.decode(content);
        String password = getStringKey();
        byte[] enCodeFormat = password.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");

        byte[] initParam = IV_STRING.getBytes(CHARSET);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        byte[] result = cipher.doFinal(encryptedBytes);

        return new String(result, CHARSET);
    }

    private static String getStringKey() {
        //进行base64加密避免特殊字符冲突的问题，获取时先进行解密
        byte[] bytes = Base64Util.decode(AES_PASSWORD);
        return bytes == null ? null : new String(bytes);
    }
}
