package com.fun.mall.puller;

import com.fun.mall.common.GetLatAndLngByTencent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 11:41 2018/12/20
 * @ Description：学校爬虫接口
 * @ Modified By：
 * @ Version    : 1.0$
 */

public class SchoolPuller {
    public static List getDataForJsoup(String url,String dataClass,String pageClass) throws IOException, InterruptedException {
        List dataList=new ArrayList();
        Document doc= Jsoup.connect(url).get();
        int page=Integer.parseInt(doc.body().getElementsByClass(pageClass).text());
        for(int i=1;i<page;i++){
            Document text= Jsoup.connect(String.format(url,i)).get();
            Elements skClass=text.getElementsByClass(dataClass);
            for (Element sk:skClass) {
                String data=sk.text().trim();
//                System.out.println(data.substring(0,data.trim().indexOf("电")));
                String schoolName=data.trim().split(",")[0].substring(0,data.trim().split(",")[0].length()-2);
                
                Object[] o= GetLatAndLngByTencent.getCoordinate(schoolName);
                String lng= (String) o[0];
                String lat= (String) o[1];
                StringBuilder dataAdvice=new StringBuilder().append(data).append(lng).append(lat);
                Thread.sleep(200);
                dataList.add(dataAdvice);
            }
        }
        return dataList;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String url="https://www.ruyile.com/xuexiao/?a=175&t=2&p=%s";
        String dataClass="sk";
        String pageClass="zys";
        List list=getDataForJsoup(url,dataClass,pageClass);
        System.out.println(list.size());
    }
}
