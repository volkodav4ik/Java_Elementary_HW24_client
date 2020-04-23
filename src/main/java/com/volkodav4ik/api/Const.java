package com.volkodav4ik.api;

import com.google.gson.reflect.TypeToken;
import com.volkodav4ik.model.Result;
import com.volkodav4ik.model.User;

import java.lang.reflect.Type;
import java.util.List;

public class Const {
    public static final Result FAIL_RESULT = new Result("Some unchecked fail.");
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String HEADER_NAME = "Content-Type";
    public static final String BODY_VALUE = "application/json";
    public static final String MAIN_URL = "http://127.0.0.1:2705/vovk_server_part/user/";
    public static final String URL_ADD_REQUEST = MAIN_URL + "add";
    public static final String URL_DELETE_NAME_REQUEST = MAIN_URL + "delete/name";
    public static final String URL_DELETE_ID_REQUEST = MAIN_URL + "delete/id";
    public static final String URL_UPDATE_REQUEST = MAIN_URL + "update";
    public static final String URL_GET_NAME_REQUEST = MAIN_URL + "get/name";
    public static final String URL_GET_ID_REQUEST = MAIN_URL + "get/id";
    public static final String URL_GET_ALL_REQUEST = MAIN_URL + "get/all";
    public static final Type LIST_OF_USER = new TypeToken<List<User>>() {
    }.getType();

}
