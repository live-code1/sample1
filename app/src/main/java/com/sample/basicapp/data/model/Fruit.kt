package com.sample.basicapp.data.model

import com.google.gson.annotations.SerializedName

data class Fruit(
    @SerializedName("CAT_Id")
    val catId: String? = null,
    @SerializedName("CAT_Name")
    val catName: String? = null,
    @SerializedName("CAT_Image")
    val catImage: String? = null,
    @SerializedName("CAT_Status")
    val catStatus: String? = null,
    @SerializedName("CAT_Offer_Id")
    val catOfferId: String? = null
)
