package com.volkodav4ik.api;

import com.google.gson.Gson;
import com.volkodav4ik.model.Result;
import com.volkodav4ik.model.User;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ApiService {

    private static ApiService instance;

    public static synchronized ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    private final Gson gson = new Gson();

    private String userToJson(User user) {
        return gson.toJson(user);
    }

    private User userFromJson(String jsonStr) {
        return gson.fromJson(jsonStr, User.class);
    }

    private List<User> userListFromJson(String jsonStr) {
        return gson.fromJson(jsonStr, Const.LIST_OF_USER);
    }

    private Result resultFromJson(String jsonStr) {
        return gson.fromJson(jsonStr, Result.class);
    }

    public String addUser(User user) throws IOException {
        String jsonUser = userToJson(user);
        Result result = CUDRequests(Const.URL_ADD_REQUEST, jsonUser);
        return result.toString();
    }

    public String deleteName(String name) throws IOException {
        User tmpUser = new User(name);
        String jsonUser = userToJson(tmpUser);
        Result result = CUDRequests(Const.URL_DELETE_NAME_REQUEST, jsonUser);
        return result.toString();
    }

    public String deleteId(int id) throws IOException {
        User tmpUser = new User(id);
        String jsonUser = userToJson(tmpUser);
        Result result = CUDRequests(Const.URL_DELETE_ID_REQUEST, jsonUser);
        return result.toString();
    }

    public String updateUser(User user) throws IOException {
        String jsonUser = userToJson(user);
        Result result = CUDRequests(Const.URL_UPDATE_REQUEST, jsonUser);
        return result.toString();
    }

    public User getUserId(int id) throws IOException {
        User tmpUser = new User(id);
        String jsonUser = userToJson(tmpUser);
        Response response = getUserRequest(Const.URL_GET_ID_REQUEST, jsonUser);
        if (response.code() == Const.OK) {
            return userFromJson(Objects.requireNonNull(response.body()).string());
        } else {
            if (response.code() == Const.BAD_REQUEST) {
                Result result = resultFromJson(Objects.requireNonNull(response.body()).string());
                System.out.println(result.toString());
                return null;
            } else {
                System.out.println(Const.FAIL_RESULT.toString());
                return null;
            }
        }
    }

    public User getUserName(String name) throws IOException {
        User tmpUser = new User(name);
        String jsonUser = userToJson(tmpUser);
        Response response = getUserRequest(Const.URL_GET_NAME_REQUEST, jsonUser);
        if (response.code() == Const.OK) {
            return userFromJson(Objects.requireNonNull(response.body()).string());
        } else {
            if (response.code() == Const.BAD_REQUEST) {
                Result result = resultFromJson(Objects.requireNonNull(response.body()).string());
                System.out.println(result.toString());
                return null;
            } else {
                System.out.println(Const.FAIL_RESULT.toString());
                return null;
            }
        }
    }

    public List<User> getAllUser() throws IOException {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        Request request = new Request.Builder()
                .url(Const.URL_GET_ALL_REQUEST)
                .method(Const.GET, null)
                .build();
        Response response = client.newCall(request).execute();
        if (response.code() == Const.OK) {
            return userListFromJson(Objects.requireNonNull(response.body()).string());
        } else {
            if (response.code() == Const.BAD_REQUEST) {
                Result result = resultFromJson(Objects.requireNonNull(response.body()).string());
                System.out.println(result.toString());
            } else {
                System.out.println(Const.FAIL_RESULT.toString());
            }
            return null;
        }
    }

    private Response getUserRequest(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        MediaType mediaType = MediaType.parse(Const.BODY_VALUE);
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .method(Const.POST, body)
                .addHeader(Const.HEADER_NAME, Const.BODY_VALUE)
                .build();
        return client.newCall(request).execute();
    }

    private Result CUDRequests(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        MediaType mediaType = MediaType.parse(Const.BODY_VALUE);
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .method(Const.POST, body)
                .addHeader(Const.HEADER_NAME, Const.BODY_VALUE)
                .build();
        Response response = client.newCall(request).execute();
        if (response.code() == Const.OK || response.code() == Const.BAD_REQUEST) {
            String recievedJson = Objects.requireNonNull(response.body()).string();
            return resultFromJson(recievedJson);
        } else {
            return Const.FAIL_RESULT;
        }
    }
}
