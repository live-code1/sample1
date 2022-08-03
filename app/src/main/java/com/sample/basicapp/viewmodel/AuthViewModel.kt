package com.sample.basicapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sample.basicapp.data.model.FruitsResponse
import com.sample.basicapp.data.repository.AuthRepository
import com.v4csolutions.spaapp.data.network.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private var repository: AuthRepository
) : ViewModel() {

    fun getFruits(
    ): LiveData<ResultResponse<Response<FruitsResponse>>> = liveData {
        var responseGet: Response<FruitsResponse>? = null
        kotlin.runCatching {
            responseGet = repository.getFruits(
            )
        }.onSuccess {
            when (responseGet?.code()) {
                200 -> {
                    emit(ResultResponse.success(responseGet!!))
                }
                502, 522, 523, 500 -> {
                    emit(ResultResponse.error(responseGet!!.message().toString(), responseGet))
                }
                else -> {
                    emit(ResultResponse.error(responseGet!!.message().toString(), responseGet))
                }
            }
        }.onFailure {
            emit(ResultResponse.error(it.message.toString(), responseGet))
        }
    }

}