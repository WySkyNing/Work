package com.wy.work.encryption;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by wy
 */

public class EncryptionUtil {

    /**
     * base64 加密 String
     *
     * @param express 原文
     * @return 密文
     */
    public static String codeToBase64(String express) {

        return Base64.encodeToString(express.getBytes(), Base64.DEFAULT);
    }

    /**
     * base64 解密 String
     *
     * @param cipherText 密文
     * @return 原文
     */
    @NonNull
    public static String decodeToBase64(String cipherText) {

        return new String(Base64.decode(cipherText, Base64.DEFAULT));
    }

    /**
     * md5 加密 String
     *
     * @param express 原文
     * @return 密文
     */
    public static String codeToMD5(String express) {

        if (TextUtils.isEmpty(express)) {

            return "";
        }

        MessageDigest messageDigest = null;

        try {

            messageDigest = MessageDigest.getInstance("MD5");

            // 产生的散列值的字节数组。
            byte[] bytes = messageDigest.digest(express.getBytes());

            StringBuilder result = new StringBuilder();

            for (byte b : bytes) {

                String temp = Integer.toHexString(b & 0xff);

                if (temp.length() == 1) {

                    temp = "0" + temp;
                }

                result.append(temp);

            }

            return result.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

            return "";
        }
    }


    /**
     * 生成秘钥
     * @param seed
     * @return
     */
    public static SecretKey generateKey(String seed) {

        try {
            //获取秘钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

            //通过种子初始化
            SecureRandom secureRandom = new SecureRandom();

            secureRandom.setSeed(seed.getBytes("UTF-8"));

            keyGenerator.init(128,secureRandom);

            return keyGenerator.generateKey();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 使用秘钥进行加密
     * @param content
     * @param secretKey
     * @return
     */
    public static String encrypt(String content,SecretKey secretKey){

        //秘钥
        byte[] enCodeFormat = secretKey.getEncoded();

        //创建 AES 秘钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat,"AES");


        try {
            //创建密码器
            Cipher cipher = Cipher.getInstance("AES");

            //初始化加密器
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);

            //加密
            return new String(cipher.doFinal(content.getBytes("UTF-8")));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String decrypt(String content, SecretKey secretKey)  {

        try {


            // 秘钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 创建AES秘钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = null;

            cipher = Cipher.getInstance("AES");
            // 初始化解密器
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 解密
            return new String(cipher.doFinal(content.getBytes("UTF-8")));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }


}
