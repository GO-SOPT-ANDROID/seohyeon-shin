package org.android.go.sopt.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDto(
    @SerialName("data")
    val data: Data,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Int
){
    @Serializable
    data class Data(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String,
        @SerialName("skill")
        val skill: String
    )
}