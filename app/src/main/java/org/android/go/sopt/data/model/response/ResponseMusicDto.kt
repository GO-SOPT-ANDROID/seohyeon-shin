package org.android.go.sopt.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMusicDto(
    @SerialName("data")
    val data: Music,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Int
){
    @Serializable
    data class Music(
        @SerialName("singer")
        val singer: String,
        @SerialName("title")
        val title: String,
        @SerialName("url")
        val url: String
    )
}
