package com.example.msaada.api;

public interface API {
    void login(String username, String password,APIListener listener);
    void register(String username,String email,String phone,String password,APIListener listener);
    void addcontribution(String heading, String description, String username1, String username2, String phone1, String phone2,String amount,String name,APIListener listener);
    void contribute(String phone,String amount,String id,APIListener listener);
    void verify (String id,String title,String description,String targetAmount,String ref1,String ref2,String phone1,String phone2,String name,String amounnt,APIListener listener);
    void delete(String id,APIListener listener);
    void postfeedback(String feedback,APIListener listener);
}

