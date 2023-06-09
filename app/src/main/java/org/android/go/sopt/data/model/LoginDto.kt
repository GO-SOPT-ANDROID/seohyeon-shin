package org.android.go.sopt.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String,
)

@Serializable
data class ResponseLoginDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: LoginData,
) {
    @Serializable
    data class LoginData(
        @SerialName("id")
        val id : String,
        @SerialName("name")
        val name: String,
        @SerialName("skill")
        val skill: String
    )
}