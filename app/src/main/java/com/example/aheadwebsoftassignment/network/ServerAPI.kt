package com.example.aheadwebsoftassignment.network

import com.example.aheadwebsoftassignment.data.ItemModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerAPI {

    @GET("navigation")
    suspend fun getItems(
        @Query("restApi") restApi: String = "Sesapi",
        @Query("sesapi_platform") platform: Int = 1,
        @Query("auth_token") authToken: String = "B179086bb56c32731633335762"
    ) : Response<ItemModel>
}