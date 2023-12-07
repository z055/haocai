package com.haocai.module.system.controller.Token;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.haocai.module.system.vo.TokenData;
import com.haocai.module.system.vo.User;
import com.haocai.module.system.vo.UserData;
import com.haocai.utils.ValidateUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import com.haocai.module.system.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


import java.util.HashMap;


@Controller
public class Tock {
    private String grant_type="authorization_code";
    private String client_id="haocaiguanli";
    private String client_secret="haocaiguanli";
    private String redirect_uri="http://172.17.6.121:8080/getCode";
    @Autowired
    private UserService userService;
    @RequestMapping("/getCode")
   public String get(@RequestParam("code")String code, HttpServletRequest request, RedirectAttributes attributes) throws IOException {
       HashMap map=new HashMap();
       map.put("grant_type",grant_type);
       map.put("client_id",client_id);
       map.put("client_secret",client_secret);
       map.put("redirect_uri",redirect_uri);
       map.put("code",code);
       String result=  doPost("http://172.16.10.119:82/sso-auth-server/oauth/token",map);
       TokenData currentUser = JSON.parseObject(result, TokenData.class);
       String access_token=currentUser.getAccess_token();
       String userDataStr= doGet("http://172.16.10.119:82/sso-auth-server/api/user/token/"+access_token);
//        String userDataStr="{\"code\":0,\"data\":{\"password\":null,\"username\":\"912020110012\",\"authorities\":[],\"accountNonExpired\":true,\"accountNonLocked\":true,\n" +
//                "\"credentialsNonExpired\":true,\"enabled\":false,\"type\":1,\"info\":{\"workNum\":\"912020110012\",\"name\":\"赵志成\",\"sex\":\"男\",\"password\":\"\"\n" +
//                ",\"department\":\"140\",\"category\":\"专业技术岗位\",\"status\":null,\"type\":null,\"attribute\":\"在编人员\",\"attributeType\":null,\"post\":null,\"postGrade\":null}}}";
       UserData userData = JSON.parseObject(userDataStr,UserData.class);

       attributes.addFlashAttribute("userData",userData);
       return "redirect:/user/singleLogin";
    }
    @RequestMapping("/getCodeTest")
    public String gettest( HttpServletRequest request, RedirectAttributes attributes) throws IOException {
        HashMap map=new HashMap();
        map.put("grant_type",grant_type);
        map.put("client_id",client_id);
        map.put("client_secret",client_secret);
        map.put("redirect_uri",redirect_uri);
        map.put("code","A3NPUn");
        String result=  doPost("http://172.16.10.119:82/sso-auth-server/oauth/token",map);
        TokenData currentUser = JSON.parseObject(result, TokenData.class);
        String access_token=currentUser.getAccess_token();
//        String userDataStr= doGet("http://172.16.10.119:82/sso-auth-server/api/user/token/"+access_token);
        String userDataStr="{\"code\":0,\"data\":{\"password\":null,\"username\":\"991000210053\",\"authorities\":[],\"accountNonExpired\":true,\"accountNonLocked\":true,\n" +
                "\"credentialsNonExpired\":true,\"enabled\":false,\"type\":1,\"info\":{\"workNum\":\"9120201100112\",\"name\":\"李玲\",\"sex\":\"女\",\"password\":\"\"\n" +
                ",\"department\":\"147\",\"category\":\"专业技术岗位\",\"status\":null,\"type\":null,\"attribute\":\"在编人员\",\"attributeType\":null,\"post\":null,\"postGrade\":null}}}";
        UserData userData = JSON.parseObject(userDataStr,UserData.class);
        attributes.addFlashAttribute("userData",userData);
        return "redirect:/user/singleLogin";
    }
    public static String doGet(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    public static String doPost(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
