package com.example.limingyu20171123;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/11/23 0023.
 */

public class Utils {
     public static String getNetJson(String urlstring){

                  try {
                      //url对象封装接口字符串
                      URL url = new URL(urlstring);
                      //用url打开连接, 返回值我们用HttpURLConnection
                      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                      //设置链接超时时间
                      urlConnection.setConnectTimeout(8000);
                      //获取状态码
                      int responseCode = urlConnection.getResponseCode();
                      //判断  200为成功码
                      if (responseCode==200){
                          InputStream inputStream = urlConnection.getInputStream();
                          BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                          StringBuffer sb = new StringBuffer();
                          String line=null;
                          while ((line=br.readLine())!=null){
                              sb.append(line);
                          }
                          return sb.toString();
                      }else {
                          //啥也不干……
                      }
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
                  return null;
              }
}
