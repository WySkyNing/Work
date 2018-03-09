package com.wy.work.encryption;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

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


    //常量介绍
    private final static String HEX = "0123456789ABCDEF";
    //AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
    private static final String CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
    //AES 加密
    private static final String AES = "AES";
    // SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法
    private static final String SHA1PRNG = "SHA1PRNG";

    public static String generateKey() {

        try {

            SecureRandom localSecureRandom = SecureRandom.getInstance(SHA1PRNG);

            byte[] bytesKey = new byte[20];

            localSecureRandom.nextBytes(bytesKey);

            return toHex(bytesKey);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    //二进制转字符
    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    public static byte[] getRawKey(byte[] seed) {

        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

            //for android // 在4.2以上版本中，SecureRandom获取方式发生了改变
            SecureRandom secureRandom =   secureRandom = SecureRandom.getInstance(SHA1PRNG);

            //设置随机生成器的种子
            secureRandom.setSeed(seed);

            //AES中128位密钥版本有10个加密循环，192比特密钥版本有12个加密循环，256比特密钥版本则有14个加密循环。
            keyGenerator.init(128, secureRandom);

            //生成一个密钥
            SecretKey secretKey = keyGenerator.generateKey();

            //获得密钥的字节数组,以其主编码格式返回
            return secretKey.getEncoded();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String codeToAES(String key, String cleartext)  {

        if (TextUtils.isEmpty(cleartext)) {

            return "";
        }

        byte[] raw = getRawKey(key.getBytes());

        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");

        Cipher cipher = null;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance(CBC_PKCS5_PADDING);

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));

            encrypted = cipher.doFinal(cleartext.getBytes());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }finally {

            return Base64Encoder.encode(encrypted);
        }



    }

    public static String decodeToAes(String key, String encrypted)  {

        if (TextUtils.isEmpty(encrypted)) {
            return encrypted;
        }

        byte[] enc = Base64Decoder.decodeToBytes(encrypted);
        byte[] result = new byte[0];
        try {

            result = decrypt(key, enc);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    private static byte[] decrypt(String key, byte[] encrypted) {
        byte[] raw = getRawKey(key.getBytes());
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = null;
        byte[] decrypted = null;
        try {
            cipher = Cipher.getInstance(CBC_PKCS5_PADDING);

            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            decrypted = cipher.doFinal(encrypted);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } finally {

            return decrypted;
        }


    }
}
