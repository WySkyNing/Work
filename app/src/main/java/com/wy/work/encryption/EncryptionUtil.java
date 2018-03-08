package com.wy.work.encryption;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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
    public static String base64ToCode(String express) {

        return Base64.encodeToString(express.getBytes(), Base64.DEFAULT);
    }

    /**
     * base64 解密 String
     *
     * @param cipherText 密文
     * @return 原文
     */
    @NonNull
    public static String base64ToDecode(String cipherText) {

        return new String(Base64.decode(cipherText, Base64.DEFAULT));
    }

    /**
     * md5 加密 String
     *
     * @param express 原文
     * @return 密文
     */
    public static String md5ToCode(String express) {

        if (TextUtils.isEmpty(express)) {

            return "";
        }

        MessageDigest messageDigest = null;

        try {
            // 获得密文完成哈希计算,产生128 位的长整数
            messageDigest = MessageDigest.getInstance("MD5");

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
}
