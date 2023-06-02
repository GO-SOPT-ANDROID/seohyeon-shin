package org.android.go.sopt.domain.repository

import androidx.lifecycle.LiveData
import org.android.go.sopt.data.datasource.remote.HomeUserListRemoteDataSource
import org.android.go.sopt.data.model.response.ResponseUserListDto
import org.android.go.sopt.data.repository.HomeUserListRepository

class HomeUserListRepositoryImpl : HomeUserListRepository {

    private val remoteDataSource = HomeUserListRemoteDataSource
    override fun getUserList(callback: HomeUserListRepository.GetDataCallback<List<ResponseUserListDto.Data>>) {
        remoteDataSource.getUserList(callback)
    }
}