package com.tangshengbo.util;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Util {
    /**
     * Title: MD5加密 生成32位md5码
     * Description: TestDemo
     * @author lu
     * @date 2016年6月23日 下午2:36:07
     * @param inStr
     * @return 返回32位md5码
     * @throws Exception
     */
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes(StandardCharsets.UTF_8);
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        toHexString(md5Bytes, hexValue);
        return hexValue.toString();
    }
    /**
     * Title: MD5加密
     * Description: TestDemo
     * @author lu
     * @date 2016年6月23日 下午2:43:31
     * @param inStr
     * @return
     */
    public static String md5(String inStr) {
        MessageDigest md5 = null;
        Md5 md51 = new Md5().invoke();
        if (md51.is()) return "";
        md5 = md51.getMd5();
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        toHexString(md5Bytes, hexValue);
        return hexValue.toString();

    }

    private static void toHexString(byte[] md5Bytes, StringBuffer hexValue) {
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
    }

    /**
     * Title: 加密解密算法 执行一次加密，两次解密
     * Description: TestDemo
     * @author lu
     * @date 2016年6月23日 下午2:37:29
     * @param inStr
     * @return
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }
    public static String md5Decode(String str) {
        return convertMD5(convertMD5(str));
    }

    public static void main(String[] args) {
        String s = new String("13917114404");
        System.out.println(md5Decode("a6aeb3ffa55fc7d664406af9c3bd0f1b"));
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + md5(s));
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));
        System.out.println(md5("13917114404"));
    }

    private static class Md5 {
        private boolean myResult;
        private MessageDigest md5;

        boolean is() {
            return myResult;
        }

        public MessageDigest getMd5() {
            return md5;
        }

        public Md5 invoke() {
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
                myResult = true;
                return this;
            }
            myResult = false;
            return this;
        }
    }
}