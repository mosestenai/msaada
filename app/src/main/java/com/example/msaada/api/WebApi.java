package com.example.msaada.api;

import  android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.util.ArrayMap;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.IntentCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.msaada.helpers.User;
import com.example.msaada.forms.loginActivity;


import org.json.JSONException;
import org.json.JSONObject;

import android.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class WebApi implements API {

   public static final String BASE_URL = "https://msaadaproject.herokuapp.com/";

   private final Application mApplication;
   private final RequestQueue mRequestQueue;


   public WebApi(Application application){
       mApplication = application;
       mRequestQueue = Volley.newRequestQueue(application);
    }


   //login any user
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void login(String username, String password, final APIListener listener){
       String url = BASE_URL + "api/login";
       JSONObject jsonObject = new JSONObject();


       try {
          jsonObject.put("Context-Type","application/json");
          jsonObject.put("email",username);
          jsonObject.put("password",password);

          Response.Listener<JSONObject> successListener = response -> {


             try {
                User user = User.getUser(response);
                listener.onLogin(user);
                }
             catch (JSONException e) {
                try {
                   User error =User.getErr(response);
                   listener.onLogin(error);
                  // Toast.makeText(mApplication, "ERROR: "+ error.getError(), Toast.LENGTH_LONG).show();

                } catch (JSONException jsonException) {
                   Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();
                }
             }
          };


          Response.ErrorListener errorListener = error ->{
             if (error instanceof com.android.volley.NoConnectionError) {
                Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
             }else{
                Toast.makeText(mApplication, "Wrong credentials", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mApplication, loginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mApplication.startActivity(intent);
             }
          };



          JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener){

             @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
               final Map<String, String> headers = new HashMap<>();
               String creds = String.format("%s:%s","USERNAME","PASSWORD");
               String auth = "Basic" + Base64.encodeToString(creds.getBytes(),Base64.DEFAULT);
               headers.put("Authorization",auth);
               return headers;
            }
          };
          mRequestQueue.add(request);
       } catch (JSONException e) {
          Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
       }

    }
    //Register user

    public void register(String username, String email, String phone, String password, final APIListener listener){
       String url = BASE_URL + "api/register";
       JSONObject jsonObject = new JSONObject();



       try {

          jsonObject.put("name",username);
          jsonObject.put("password",password);
          jsonObject.put("email",email);
          jsonObject.put("phone",phone);

          Response.Listener<JSONObject> successListener = response -> {
             try {
                User user = User.getsuccess(response);
                listener.onRegister(user);
             }
             catch (JSONException e) {
                try {
                   User error =User.getErr(response);
                   listener.onRegister(error);
                   // Toast.makeText(mApplication, "ERROR: "+ error.getError(), Toast.LENGTH_LONG).show();

                } catch (JSONException jsonException) {
                   Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();
                }
             }

          };


          Response.ErrorListener errorListener = error -> {
             //Log.v("Response",error.toString());
             if (error instanceof com.android.volley.NoConnectionError) {
                Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
             }

          };

          JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
          mRequestQueue.add(request);
       } catch (JSONException e) {
          Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
       }

    }



   //add contribution
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void addcontribution(String heading, String description, String username1, String username2, String phone1, String phone2,String amount,String name, final APIListener listener){
      String url = BASE_URL + "api/create/contribution";
      JSONObject jsonObject = new JSONObject();
      String option = "mpesa";



      try {

         jsonObject.put("title",heading);
         jsonObject.put("description",description);
         jsonObject.put("referee1",username1);
         jsonObject.put("paymentoption",option);
         jsonObject.put("referee2",username2);
         jsonObject.put("targetAmount",amount);
         jsonObject.put("referee1Phone",phone1);
         jsonObject.put("referee2Phone",phone2);
         jsonObject.put("createdBy",name);

         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getsuccess(response);
               listener.onAddcontribution(user);
            }
            catch (JSONException e) {
               try {
                  User error =User.getErr(response);
                  listener.onAddcontribution(error);
                 //  Toast.makeText(mApplication, "ERROR: "+ error.getError(), Toast.LENGTH_LONG).show();

               } catch (JSONException jsonException) {
                  Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();
               }
            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }


   //Contribute
   public void contribute(String phone,String amount,String id, final APIListener listener){
      String url = BASE_URL + "api/pay";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("phone",phone);
         jsonObject.put("amount",amount);
         jsonObject.put("id",id);



         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getResponseCode(response);
               listener.onContribute(user);
            }
            catch (JSONException e) {
               try {
                  User error = User.getErr(response);
                  Toast.makeText(mApplication,error.getError(),Toast.LENGTH_LONG).show();

               } catch (JSONException jsonException) {
                  Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

               }

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }


   //Verify Contribution
   public void verify(String id,String title,String description,String targetAmount,String ref1,String ref2,String phone1,String phone2,String name,String amounnt,final APIListener listener){
      String url = BASE_URL + "api/update/contribution";
      JSONObject jsonObject = new JSONObject();
      String paymentoption = "Mpesa";
      Integer verified = 1;



      try {

         jsonObject.put("id",id);
         jsonObject.put("title",title);
         jsonObject.put("description",description);
         jsonObject.put("targetAmount",targetAmount);
         jsonObject.put("verified",verified);
         jsonObject.put("paymentoption",paymentoption);
         jsonObject.put("referee1",ref1);
         jsonObject.put("referee2",ref2);
         jsonObject.put("referee1Phone",phone1);
         jsonObject.put("referee2Phone",phone2);
         jsonObject.put("createdBy",name);
         jsonObject.put("amount",amounnt);




         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getsuccess(response);
               listener.onVerify(user);
            }
            catch (JSONException e) {
               try {
                  User error =User.getErr(response);
                  listener.onVerify(error);
                  //  Toast.makeText(mApplication, "ERROR: "+ error.getError(), Toast.LENGTH_LONG).show();

               } catch (JSONException jsonException) {
                  Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();
               }
            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }


   //post feedback
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void postfeedback(String feedback, final APIListener listener){
      String url = BASE_URL + "api/feedback";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("feedback",feedback);



         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getErr(response);
               listener.onPostfeedback(user);
            }
            catch (JSONException e) {
               Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }



   //Delete Contribution
   public void delete(String id, final APIListener listener){
      String url = BASE_URL + "api/delete/contribution";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("id",id);


         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getresponse(response);
               listener.onDelete(user);
            }
            catch (JSONException e) {
               try {
                  User error =User.getErr(response);
                  listener.onDelete(error);
                 // Toast.makeText(mApplication, "ERROR: "+ error.getError(), Toast.LENGTH_LONG).show();

               } catch (JSONException jsonException) {
                  Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();
               }
            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }






}
