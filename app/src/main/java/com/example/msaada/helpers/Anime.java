package com.example.msaada.helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Anime {

    private String title;
    private String description;
    private String id;
    private String amount;
    private String ref1;
    private String ref2;
    private String phone1;
    private String phone2;
    private String status;
    private String raised;
    private String name;
    private String amountt;
    private String phone;
    private String date;

    public Anime(){

    }




//model to get json response of all contributions
    public static Anime contributions(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.getString("title");
        String description = jsonObject.getString("description");
        String amount = jsonObject.getString("targetAmount");
        String id = jsonObject.getString("id");
        String raised = jsonObject.getString("amount");

           return new Anime(title, description, amount, id,raised);

    }
    public static Anime gettransactions(JSONObject jsonObject) throws  JSONException{
        String phone = jsonObject.getString("phone");
        String amount = jsonObject.getString("amount");
        String id = jsonObject.getString("contributionId");
        String date = jsonObject.getString("created_at");


        return new Anime(phone,amount,id,date);
    }
    //model to get json response of all contribution to be verified
    public static Anime verifycontributions(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.getString("title");
        String description = jsonObject.getString("description");
        String status = jsonObject.getString("verified");
        String ref1 = jsonObject.getString("referee1");
        String ref2 = jsonObject.getString("referee2");
        String phone1 = jsonObject.getString("referee1Phone");
        String phone2 = jsonObject.getString("referee2Phone");
        String name = jsonObject.getString("createdBy");
        String amountt = jsonObject.getString("amount");


        String amount = jsonObject.getString("targetAmount");
        String id = jsonObject.getString("id");

        return new Anime(title, description, amount, id,ref1,ref2,phone1,phone2,status,name,amountt);

    }
    public Anime(String phone, String amount, String id, String date) {
        this.phone = phone;
        this.amount = amount;
        this.id = id;
        this.date = date;
    }
    public Anime(String title, String description, String amount, String id,String raised) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.raised = raised;
        this.id = id;
    }
    public Anime(String title, String description, String amount, String id,String ref1,String ref2,String phone1,String phone2,String status,String name,String amountt) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.amountt = amountt;
        this.id = id;
        this.ref1 = ref1;
        this.status = status;
        this.ref2 = ref2;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmountt() {
        return amountt;
    }

    public void setAmountt(String amountt) {
        this.amountt = amountt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRaised() {
        return raised;
    }

    public void setRaised(String raised) {
        this.raised = raised;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
