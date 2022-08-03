package com.sample.basicapp.data.model

import com.google.gson.annotations.SerializedName

data class FruitsResponse(
    val code: String? = null,
    val status: String?=null,
    @SerializedName("data")
    val fruits: MutableList<Fruit>


)