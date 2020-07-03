package com.rashmiappd.texta.Fragments;

import com.rashmiappd.texta.Notifications.MyResponse;
import com.rashmiappd.texta.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAylrwg00:APA91bG8kjZdLW-ODn8AMWrLE2XoBIBVvm_ExS6le4TEgiyt-RIwgtmA-8vKaN7R1M2tE0qANY1iOZTd58RvD0M4aD4r_GIvz1sjRAfFSEhwTfoF9mYLOMlfI4y7-3CuzCkKn4SmdiQh"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);

}
