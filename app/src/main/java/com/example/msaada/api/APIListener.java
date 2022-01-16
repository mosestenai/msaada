package com.example.msaada.api;

import com.example.msaada.helpers.User;

public interface APIListener {
    void onLogin(User user);
    void onRegister(User user);
    void onAddcontribution(User user);
    void onContribute(User user);
    void onVerify(User user);
    void onDelete(User user);
    void onPostfeedback(User user);

}
