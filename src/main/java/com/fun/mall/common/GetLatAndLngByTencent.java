package com.fun.mall.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 15:43 2018/12/19
 * @ Description：
 * @ Modified By：
 * @ Version    : $
 */
public class GetLatAndLngByTencent {
    /**
     * @ param addr
     * 查询的地址
     * @ return
     * @ throws IOException
     */
    public static Object[] getCoordinate(String addr) throws IOException {
        //经度
        String lng = null;
        //纬度
        String lat = null;
        String address = null;
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String key = "5C7BZ-OWZR4-DDCUF-XWYNU-XTXGE-SIBZ3";
        String url = String.format("https://apis.map.qq.com/ws/geocoder/v1/?address=%s&key=%s", address, key);
        URL myURL = null;
        URLConnection httpsConn;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            // 不使用代理
            assert myURL != null;
            httpsConn = myURL.openConnection();
            if (httpsConn != null) {
                insr = new InputStreamReader(httpsConn.getInputStream(), StandardCharsets.UTF_8);
                br = new BufferedReader(insr);
                String data;
                int count = 1;
                while ((data = br.readLine()) != null) {
                    System.out.println(data);
                    if (count == 7) {
                        //经度
                        lng = (String) data.subSequence(data.indexOf(":") + 1, data.indexOf(","));
                        count++;
                    } else if (count == 8) {
                        //纬度
                        lat = data.substring(data.indexOf(":") + 1);
                        count++;
                    } else {
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (insr != null) {
                insr.close();
            }
            if (br != null) {
                br.close();
            }
        }
        return new Object[]{lng, lat};
    }


    public static void main(String[] args) {
        Object[] o;
        try {
            o = getCoordinate("北京天安门");
            //经度
            System.out.println(o[0]);
            //纬度
            System.out.println(o[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
