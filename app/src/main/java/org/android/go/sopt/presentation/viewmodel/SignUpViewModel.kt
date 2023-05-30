package org.android.go.sopt.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.ServicePool.retrofitService
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    val signUpResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()

    fun signUp(id: String, password: String, name: String, skill: String) {
        Log.e("함수들어옴: " , "true")
        retrofitService.signUp(RequestSignUpDto(id, password, name, skill))
            .enqueue(object : Callback<ResponseSignUpDto> {
                override fun onResponse(
                    call: Call<ResponseSignUpDto>,
                    response: Response<ResponseSignUpDto>
                ) {
                  if (response.isSuccessful){
                      response.body()?.message?.let{
                          signUpResult.value = response.body()
                          Log.e("회원가입 성공: " , it)
                      }
                  }
                }

                override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                 t.message?.let{
                     Log.e("회원가입 에러: " , it )
                 }
                }
            })
    }
}