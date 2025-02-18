package com.tlc.chatapp.data.api.models

import com.google.gson.annotations.SerializedName

data class PhoneRequest(
    @SerializedName("phone")
    val phone: String? = null
)