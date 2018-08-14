package com.betaromar.omar1betar.apptest.Api;

import com.betaromar.omar1betar.apptest.models.DefaultResponse;
import com.betaromar.omar1betar.apptest.models.LoginResponse;
import com.betaromar.omar1betar.apptest.models.UsersResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createuser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school

    );
    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );
    @GET("allusers")
    Call<UsersResponse> getUsers();


    @FormUrlEncoded
    @PUT("updateuser{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("email") String email,
            @Field("name") String name,
            @Field("school") String school

    );
}
