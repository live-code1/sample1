package com.sample.basicapp.data.repository

import com.sample.basicapp.data.model.FruitsResponse
import com.sample.basicapp.data.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val backEndApi: ApiService) {

    suspend fun getFruits(): Response<FruitsResponse> {
        return backEndApi.getFruits()
    }
}