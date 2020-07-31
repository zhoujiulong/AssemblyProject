package com.zhoujiulong.assemblyproject

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface MainServices {

    @Streaming
    @GET
    fun downLoadApk(@Url fileUrl: String): Call<ResponseBody>

}