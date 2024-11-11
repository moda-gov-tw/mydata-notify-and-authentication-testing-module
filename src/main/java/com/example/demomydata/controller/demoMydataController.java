package com.example.demomydata.controller;


import com.example.demomydata.domain.IntrospectEntity;
import com.example.demomydata.domain.UserInfoEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

@Slf4j
@RestController
@Api(tags = "Mydata測試API")
@RequestMapping("/connect")
public class demoMydataController {

    //提供DP-API被呼叫後驗證
    @RequestMapping(value = "/introspect/{resource}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<IntrospectEntity> getResourceDataHandleProcessor(
            @PathVariable("resource") String resource,
            @RequestHeader Map<String, String> headers,
            @RequestParam(value = "heartbeat", required = false, defaultValue = "false") Boolean heartbeat,
            HttpServletRequest request,
            HttpServletResponse response) {

        return handleResourceIntrospect(resource, headers, heartbeat, request);
    }

    //提供DP-API被呼叫後驗證
    @RequestMapping(value = "/introspect/{resource}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<IntrospectEntity> postResourceDataHandleProcessor(
            @PathVariable("resource") String resource,
            @RequestHeader Map<String, String> headers,
            @RequestParam(value = "heartbeat", required = false, defaultValue = "false") Boolean heartbeat,
            HttpServletRequest request,
            HttpServletResponse response) {

        return handleResourceIntrospect(resource, headers, heartbeat, request);
    }

    //提供DP-API驗證後GET資料
    @RequestMapping(value = "/userinfo/{resource}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserInfoEntity> getUserDataHandleProcessor(
            @PathVariable("resource") String resource,
            @RequestHeader Map<String, String> headers,
            @RequestParam(value = "heartbeat", required = false, defaultValue = "false") Boolean heartbeat,
            HttpServletRequest request,
            HttpServletResponse response) {

        return handleResourceUserInfo(resource, headers, heartbeat, request);
    }

    //提供DP-API驗證後POST資料
    @RequestMapping(value = "/userinfo/{resource}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserInfoEntity> postUserDataHandleProcessor(
            @PathVariable("resource") String resource,
            @RequestHeader Map<String, String> headers,
            @RequestParam(value = "heartbeat", required = false, defaultValue = "false") Boolean heartbeat,
            HttpServletRequest request,
            HttpServletResponse response) {

        return handleResourceUserInfo(resource, headers, heartbeat, request);
    }

    private ResponseEntity<IntrospectEntity> handleResourceIntrospect(@PathVariable("resource") String resource, @RequestHeader Map<String, String> headers, @RequestParam(value = "heartbeat", required = false, defaultValue = "false") Boolean heartbeat, HttpServletRequest request) {

        String accessToken = ResourceBundle.getBundle("mydataTest").getString("token");
        Set<String> introspectData = ResourceBundle.getBundle("mydataIntrospect").keySet();
        //TODO:驗證資訊
        String[] resourceT=resource.split("::");
        String token= resourceT[1];
        if (token ==null){
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<IntrospectEntity>(new IntrospectEntity(), responseHeaders, HttpStatus.UNAUTHORIZED);
        }else if (token != accessToken){
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<IntrospectEntity>(new IntrospectEntity(), responseHeaders, HttpStatus.UNAUTHORIZED);
        }
        //TODO:回傳驗證成功
        HttpHeaders responseHeaders = new HttpHeaders();
        //TODO:填入驗證成功資料
        IntrospectEntity returnIEntity= new IntrospectEntity();
        returnIEntity.setActive(true);
        return new ResponseEntity<IntrospectEntity>(returnIEntity, responseHeaders, HttpStatus.OK);

    }

    private ResponseEntity<UserInfoEntity> handleResourceUserInfo(@PathVariable("resource") String resource, @RequestHeader Map<String, String> headers, @RequestParam(value = "heartbeat", required = false, defaultValue = "false") Boolean heartbeat, HttpServletRequest request) {

        String accessToken = ResourceBundle.getBundle("mydataTest").getString("token");
        Set<String> UserInfoData = ResourceBundle.getBundle("mydataUserInfo").keySet();
        //TODO:驗證資訊
        String[] resourceT=resource.split("::");
        String token= resourceT[1];
        if (token ==null){
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<UserInfoEntity>(new UserInfoEntity(), responseHeaders, HttpStatus.UNAUTHORIZED);
        }else if (token != accessToken){
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<UserInfoEntity>(new UserInfoEntity(), responseHeaders, HttpStatus.UNAUTHORIZED);
        }
        //TODO:回傳使用者資料
        HttpHeaders responseHeaders = new HttpHeaders();
        //TODO:填入使用者資料
        UserInfoEntity userInfo =new UserInfoEntity();
        userInfo.setSub(ResourceBundle.getBundle("mydataUserInfo").getString("sub"));
        userInfo.setName(ResourceBundle.getBundle("mydataUserInfo").getString("name"));
        userInfo.setPreferredUsername(ResourceBundle.getBundle("mydataUserInfo").getString("preferredUsername"));
        userInfo.setAccountName(ResourceBundle.getBundle("mydataUserInfo").getString("accountName"));
        userInfo.setUid(ResourceBundle.getBundle("mydataUserInfo").getString("uid"));
        userInfo.setIsValidUid(ResourceBundle.getBundle("mydataUserInfo").getString("isValidUid"));
        userInfo.setBirthdate(ResourceBundle.getBundle("mydataUserInfo").getString("birthdate"));
        userInfo.setGender(ResourceBundle.getBundle("mydataUserInfo").getString("gender"));
        userInfo.setEmail(ResourceBundle.getBundle("mydataUserInfo").getString("email"));
        userInfo.setEmailVerified(Boolean.parseBoolean(ResourceBundle.getBundle("mydataUserInfo").getString("emailVerified")));
        userInfo.setAccount(ResourceBundle.getBundle("mydataUserInfo").getString("account"));
        userInfo.setProfile(ResourceBundle.getBundle("mydataUserInfo").getString("profile"));
        userInfo.setIdp(ResourceBundle.getBundle("mydataUserInfo").getString("idp"));
        userInfo.setX509type(ResourceBundle.getBundle("mydataUserInfo").getString("x509type"));
        userInfo.setAmr(ResourceBundle.getBundle("mydataUserInfo").getString("amr"));
        userInfo.setPhoneNumber(ResourceBundle.getBundle("mydataUserInfo").getString("phoneNumber"));
        userInfo.setBoxcheck(Boolean.parseBoolean(ResourceBundle.getBundle("mydataUserInfo").getString("boxcheck")));
        return new ResponseEntity<UserInfoEntity>(userInfo, responseHeaders, HttpStatus.OK);

    }
}
