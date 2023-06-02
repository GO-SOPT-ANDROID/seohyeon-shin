package org.android.go.sopt.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val headerRequest = originalRequest.newBuilder() // 헤더를 추가한 req
            .header("Authorization","토큰 실제값")
            .build()

        return chain.proceed(headerRequest)
    }
}