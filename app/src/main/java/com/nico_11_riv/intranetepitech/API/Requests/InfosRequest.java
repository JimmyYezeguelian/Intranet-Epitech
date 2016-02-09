package com.nico_11_riv.intranetepitech.API.Requests;

import org.springframework.util.LinkedMultiValueMap;

public class InfosRequest extends LinkedMultiValueMap<String, String> {
    public InfosRequest(String token) {
        add("token", token);
    }
}
