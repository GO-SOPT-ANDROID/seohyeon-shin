package org.android.go.sopt.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.android.go.sopt.data.model.ServicePool
import org.android.go.sopt.data.model.User
import org.android.go.sopt.data.model.response.ResponseMusicDto
import org.android.go.sopt.util.ContentUriRequestBody
import retrofit2.Call
import retrofit2.Response

class GalleryViewModel : ViewModel() {
    private val service = ServicePool.imageService
    private val _image = MutableLiveData<ContentUriRequestBody>()
    private val _btnClearStatus = MutableLiveData<Boolean>()
    val btnClearStatus : MutableLiveData<Boolean>
            get() = _btnClearStatus
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    fun setRequestBody(requestBody: ContentUriRequestBody,title:String,singer:String) {
        uploadMusic(requestBody,title,singer)
    }

    fun uploadMusic(requestBody: ContentUriRequestBody,title:String, singer:String) {
        Log.e("함수진입", "true")
        service.uploadImage("hyeon123",requestBody.toFormData(),title.toRequestBody("text/plain".toMediaType()),singer.toRequestBody("text/plain".toMediaType()))
            .enqueue(object : retrofit2.Callback<ResponseMusicDto> {
                override fun onResponse(call: Call<ResponseMusicDto>, response: Response<ResponseMusicDto>) {
                    if (response.isSuccessful) {
                        Log.e("image", "upload 성공")
                        _btnClearStatus.value = true
                    }
                }

                override fun onFailure(call: Call<ResponseMusicDto>, t: Throwable) {
                    Log.e("이미지 에러요", t.message.toString())
                }
            })
    }
}