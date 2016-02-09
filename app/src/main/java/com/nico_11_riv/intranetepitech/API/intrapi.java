package com.nico_11_riv.intranetepitech.API;

import com.nico_11_riv.intranetepitech.API.Requests.LoginRequest;

import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresCookie;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

/**
 * Created by Jimmy on 09/02/2016.
 */

@Rest(rootUrl = "https://intra.epitech.eu", converters = {StringHttpMessageConverter.class, FormHttpMessageConverter.class})
public interface intrapi {
    @Post("/")
    @RequiresCookie("PHPSESSID")
    String sendToken(LoginRequest lr);

    @Post("/user")
    @RequiresCookie("PHPSESSID")
    String getuserinfo();

    void setCookie(String name, String value);
    String getCookie(String name);
}
