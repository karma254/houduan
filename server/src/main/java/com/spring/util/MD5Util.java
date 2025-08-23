package com.spring.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * 对字符串进行 MD5 哈希处理
     * @param input 要加密的字符串
     * @return 截取中间16位小写十六进制的 MD5 哈希值
     */
    public static String md5HashTo16(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x",b & 0xFF));
            }
            return hexString.toString().substring(8,24);//截取中间16位（9-24）

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 算法不可用", e);
        }
    }
}


