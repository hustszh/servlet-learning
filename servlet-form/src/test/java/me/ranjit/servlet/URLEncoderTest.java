package me.ranjit.servlet;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by suzh on 7/25/2017.
 */
public class URLEncoderTest {

    @Test
    public void testURLEncoderDecoder() throws UnsupportedEncodingException {
        String cnStr = "中国";
        String encodeStr = URLEncoder.encode(cnStr, "utf-8");
        System.out.println(encodeStr);
        String decodeStr = URLDecoder.decode(encodeStr, "utf-8");
        System.out.println(decodeStr);
    }
}
