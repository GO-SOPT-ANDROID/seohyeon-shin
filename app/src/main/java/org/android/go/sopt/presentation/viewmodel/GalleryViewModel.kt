package org.android.go.sopt.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.model.ServicePool
import org.android.go.sopt.util.ContentUriRequestBody
import retrofit2.Call
import retrofit2.Response

class GalleryViewModel : ViewModel() {
    private val service = ServicePool.imageService
    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        uploadProfileImage(requestBody)
    }

    fun uploadProfileImage(requestBody: ContentUriRequestBody) {
        Log.e("함수진입", "true")

        service.uploadImage(requestBody.toFormData())
            .enqueue(object : retrofit2.Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        Log.e("image", "upload 성공")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("error", t.message.toString())
                }
            })
    }
}