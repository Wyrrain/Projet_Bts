package com.example.application30;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private Map<String, String> params;

    public LoginRequest(String username, String password, String ip, Response.Listener<String> listener){
        super(Request.Method.POST, "http://10.16.37.120/login.php", listener, null);
        params = new HashMap<>();
        params.put("login", username);
        params.put("motdepasse", password);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}
