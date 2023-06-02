package org.android.go.sopt.presentation.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.ServicePool.retrofitService
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import org.android.go.sopt.util.verifyId
import org.android.go.sopt.util.verifyPw
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    val signUpResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val id: MutableLiveData<String> = MutableLiveData()
    val pw: MutableLiveData<String> = MutableLiveData()
    val name:MutableLiveData<String> = MutableLiveData()
    val specialty: MutableLiveData<String> = MutableLiveData()

    val enableBtn = MediatorLiveData<Boolean>().apply {
        addSource(id) { value = verifyId(id.value.toString()) }
        addSource(pw) { value = verifyPw(pw.value.toString()) }
        addSource(name) {value = !name.value.isNullOrBlank()}
        addSource(specialty) {value = !specialty.value.isNullOrBlank()}
    }

    fun signUp(id: String, password: String, name: String, skill: String) {
        Log.e("함수들어옴: ", "true")
        retrofitService.signUp(RequestSignUpDto(id, password, name, skill))
            .enqueue(object : Callback<ResponseSignUpDto> {
                override fun onResponse(
                    call: Call<ResponseSignUpDto>,
                    response: Response<ResponseSignUpDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.message?.let {
                            signUpResult.value = response.body()
                            Log.e("회원가입 성공: ", it)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                    t.message?.let {
                        Log.e("회원가입 에러: ", it)
                    }
                }
            })
    }
}