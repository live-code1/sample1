package com.sample.basicapp.data.network

import com.sample.basicapp.data.model.FruitsResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

//    @FormUrlEncoded
    @POST("restapi.php?token=Y2F0ZWdvcnk=")
    suspend fun getFruits(
    ): Response<FruitsResponse>


}


