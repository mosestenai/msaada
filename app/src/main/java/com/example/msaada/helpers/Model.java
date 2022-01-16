package com.example.msaada.helpers;
import  android.app.Application;

import com.example.msaada.helpers.User;
import com.example.msaada.api.API;
import com.example.msaada.api.APIListener;
import com.example.msaada.api.AbstractAPIListener;
import com.example.msaada.api.WebApi;
import com.example.msaada.api.AbstractAPIListener;

public class Model {
    //to make sure there is only one instance of a model class
    private static  Model sInstance = null;

    private final API mApi;
    private User mUser;

    public static Model getInstance(Application application) {

        if (sInstance == null) {
            sInstance = new Model(application);
        }return  sInstance;
    }

    private final Application mApplication;
    private Model(Application application) {
        mApplication = application;
        mApi = new WebApi(mApplication);
    }
    public Application getApplication() { return mApplication;}


    public void login(String username, String password, AbstractAPIListener listener){
        mApi.login(username, password, listener);
    }
    public  void register(String username,String email, String phone,String password,APIListener listener){
        mApi.register(username,email,phone,password,listener);
    }
    public void addcontribution(String heading, String description, String username1, String username2, String phone1, String phone2, String amount,String name,AbstractAPIListener listener){
        mApi.addcontribution(heading,description,username1,username2,phone1,phone2,amount,name,listener);
    }
    public void contribute(String phone,String amount,String id,APIListener listener){
        mApi.contribute(phone,amount,id,listener);
    }
    public void verify(String id,String title,String description,String targetAmount,String ref1,
                       String ref2,String phone1,String phone2,String name,String amounnt,APIListener listener){
        mApi.verify(id,title,description,targetAmount,ref1,ref2,phone1,phone2,name,amounnt,listener);
    }
    public void postfeedback(String feedback,AbstractAPIListener listener){
        mApi.postfeedback(feedback,listener);
    }
    public void delete(String id,APIListener listener){
        mApi.delete(id,listener);
    }


    public User getUser() {
        return mUser;
    }
   public void seterror(User error){this.mUser= error ;}
    public void setUser(User user) {
        this.mUser = user;
    }
}

