package com.zhoujiulong.assemblyproject

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming

interface MainServices {

    @Streaming
    @GET("android_delivery_v4.3.3_2020_04_03.apk")
    fun downLoadApk(): Call<ResponseBody>

}