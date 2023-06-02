package org.android.go.sopt.data.repository

import androidx.lifecycle.LiveData
import org.android.go.sopt.data.model.response.ResponseUserListDto

interface HomeUserListRepository {

    fun getUserList(callback: GetDataCallback<List<ResponseUserListDto.Data>>)

    interface GetDataCallback<T>{
        fun onSuccess(data: T?)
        fun onFailure(throwable: Throwable)
    }
}