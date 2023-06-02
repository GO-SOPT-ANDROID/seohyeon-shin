package org.android.go.sopt.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.model.response.ResponseUserListDto
import org.android.go.sopt.data.repository.HomeUserListRepository
import org.android.go.sopt.domain.repository.HomeUserListRepositoryImpl

class HomeViewModel : ViewModel(){
    private val _userResult : MutableLiveData<List<ResponseUserListDto.Data>> = MutableLiveData()

    val userResult : LiveData<List<ResponseUserListDto.Data>>
        get() = _userResult

    private val repository by lazy {
        HomeUserListRepositoryImpl()
    }

    fun getUserList() {
        repository.getUserList(object: HomeUserListRepository.GetDataCallback<List<ResponseUserListDto.Data>>{
            override fun onSuccess(data:List<ResponseUserListDto.Data>?) {
                data?.let{
                    _userResult.value = it
                }
            }

            override fun onFailure(throwable: Throwable) {
              throwable.printStackTrace()
            }
        })
    }

}