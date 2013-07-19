package rsv;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: Serebatos
 * Date: 29.05.13
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */


public class Encoder {
    Base64 b = new Base64();
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Encoder() {
    }

    public Encoder(String key) {
        this.key = key;
    }

    public static void main(String[] args) throws Exception {
        Encoder c = new Encoder();
//        c.setKey("1111");
//        String str = "Привет!";
//        String enc = c.encode(str);
//        System.out.println(enc);
//        System.out.println(c.decode(enc));



    }

    private byte[] encodeBase64(String s) {
        byte[] arr = new byte[0];
//        try {
        arr = s.getBytes();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }

        return b.encode(arr);
    }

    private byte[] decodeBase64(String s) {
        byte[] bytes = new byte[0];
//        try {
        bytes = s.getBytes();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        return b.decode(bytes);
    }

    private char[] xorItBaby(char[] toXor) {
        int j = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toXor.length; i++) {
            char c = toXor[i];
            j = i % key.length();
            char xc = key.toCharArray()[j];
            int xor = c ^ xc;
            char res = (char) xor;
            sb.append(res);
        }

        return sb.toString().toCharArray();
    }

    public String encode(String s) {
        try {
            System.out.println(s.toCharArray());
            byte[] bytes1 = s.getBytes();
            for (int i = 0; i < bytes1.length; i++) {
                byte b1 = bytes1[i];
                System.out.println(b1);
            }

            Charset c = Charset.defaultCharset();

            System.out.println(c);

            byte[] bytes = new byte[0];

            if (c.name().equals("windows-1251")) {
                bytes = new String(s.getBytes("UTF-8")).getBytes();
                System.out.println("in encoding");
                for (int i = 0; i < bytes1.length; i++) {
                    byte b1 = bytes1[i];
                    System.out.println(b1);
                }
            }


//            s = new String(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        char[] ca = s.toCharArray();
        String res = new String(xorItBaby(ca));
        res = new String(encodeBase64(res));
        return res;
    }

    public String decode(String s) {
        String res = new String(decodeBase64(s));
        try {
            String charsetName = "UTF-8";
            byte[] bytes = res.getBytes(charsetName);
            res = new String(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        char[] ca = res.toCharArray();
        res = new String(xorItBaby(ca));
        return res;
    }


}
