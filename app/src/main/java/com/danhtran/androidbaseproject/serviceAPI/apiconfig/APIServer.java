package com.danhtran.androidbaseproject.serviceAPI.apiconfig;

import com.danhtran.androidbaseproject.appmodel.Movie;
import com.google.gson.JsonElement;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by danhtran on 11/04/2017.
 */

public interface APIServer {
    String header = "Content-Type: application/json";
    String header1 = "Accept-Encoding: identity";

    //-----------------Call<>
    @Headers(header)
    @POST("{api}")
    Call<JsonElement> postRequest(@Path("api") String api, @Body JsonElement jsonData);

    @Headers(header)
    @POST("{api}/{sub}")
    Call<JsonElement> postRequestSub(@Path("api") String api, @Path("sub") String sub, @Body JsonElement jsonData);

    @Headers(header)
    @GET("{api}/{sub}")
    Call<JsonElement> getRequest(@Path("api") String api, @Path("sub") String sub, @Body JsonElement jsonData);

    @Headers(header)
    @DELETE("{api}")
    Call<JsonElement> deleteRequest(@Path("api") String api, @Body JsonElement jsonData);

    @Headers(header)
    @PUT("{api}")
    Call<JsonElement> putRequest(@Path("api") String api, @Body JsonElement jsonData);


    //-----------------Observable<>
   /* @Headers(header)
    @POST("{api}/{sub}")
    Observable<LoginModel> post(@Path("api") String api, @Path("sub") String sub, @Body JsonElement jsonData);

    @Headers(header)
    @GET("{api}")
    Observable<LoginModel> get(@Path("api") String api, @Path("sub") String sub);

    @Headers(header)
    @DELETE("{api}")
    Observable<LoginModel> delete(@Path("api") String api, @Path("sub") String sub);

    @Headers(header)
    @PUT("{api}")
    Observable<LoginModel> put(@Path("api") String api, @Path("sub") String sub, @Body JsonElement jsonData);*/


    @Headers(header)
    @POST("{api}/{sub}")
    Observable<List<Movie>> getMovies();
}
