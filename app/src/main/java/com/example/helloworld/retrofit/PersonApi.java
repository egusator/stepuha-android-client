package com.example.helloworld.retrofit;

import com.example.helloworld.dto.PersonDTO;
import com.example.helloworld.model.PersonEntity;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PersonApi {
    @GET("person/get-by-login")
    public Call<JsonObject> getPersonByLogin(@Query("login") String login);
}
