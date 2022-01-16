package com.example.msaada.helpers;


import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {



    public static User getUser(JSONObject jsonObject) throws JSONException{
        JSONObject token = jsonObject.getJSONObject("token");
        JSONObject accessToken = token.getJSONObject("accessToken");
        String abilitiess = accessToken.getString("abilities");
        JSONObject userdetails = jsonObject.getJSONObject("userdetails");
        String username = userdetails.getString("name");
        String phone = userdetails.getString("phone");
        String permission = "none";
        String abilities = jsonObject.getString("userType");
        String id = userdetails.getString("id");

        return new User(abilities,username,permission,id);
    }
    public User (String token,String username,String permission,String id){
    this.token = token;
    this.username = username;
    this.permission = permission;
    this.id = id;
    }
    private String username;
    private String permission;
    private String id;
    private String token;
    private String error;
    private String responceStatusCode;
    private String test;
    private String p;
    private String success;
    private String r;
    private String z;
    private String y;

    public static User getErr(JSONObject jsonObject) throws JSONException{
        String error = jsonObject.getString("error");
        String permission = "test";


        return new User(error,permission);
    }
    public static User getResponseCode(JSONObject jsonObject) throws JSONException{
        String responceStatusCode = jsonObject.getString("responceStatusCode");
        String test="";
        String p = "";


        return new User(responceStatusCode,test,p);
    }

    public static User getsuccess(JSONObject jsonObject) throws JSONException{
        String success = jsonObject.getString("message");
        String test="";
        String p = "";
        String r = "";
        String z = "";


        return new User(success,test,p,r,z);
    }
    public static User getresponse(JSONObject jsonObject) throws JSONException{
        String success = jsonObject.getString("response");
        String test="";
        String p = "";
        String r = "";
        String z = "";
        String y = "";


        return new User(success,test,p,r,z,y);
    }
    public User(String success,String test,String P,String r,String z,String y){
        this.success = success;
        this.test = test;
        this.p = P;
        this.r = r;
        this.z = z;
        this.y = y;
    }
    public User(String success,String test,String P,String r,String z){
        this.success = success;
        this.test = test;
        this.p = P;
        this.r = r;
        this.z = z;
    }

    public User(String responceStatusCode,String test,String P){
        this.responceStatusCode = responceStatusCode;
        this.test = test;
        this.p = P;
    }
    public User(String error, String permission) {
        this.error= error;
        this.permission = permission;

    }

    public User(String error){
    this.error= error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResponceStatusCode() {
        return responceStatusCode;
    }

    public void setResponceStatusCode(String responceStatusCode) {
        this.responceStatusCode = responceStatusCode;
    }

    public String getError() { return error; }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}



