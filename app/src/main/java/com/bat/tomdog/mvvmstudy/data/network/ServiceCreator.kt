package com.bat.tomdog.mvvmstudy.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by Tomdog on 2019/12/30.
 * 对象声明，专门用于实现单例模式，对象声明所定义的对象也就是该类的唯一实例，程序可以通过对象声明的名称直接访问该类的唯一实例
 */
object ServiceCreator {

    private const val BASE_URL = "http://guolin.tech/"

    private val httpCliet = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpCliet.build())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = builder.build()

    fun <T> create(serviceClass: Class<T>) :T = retrofit.create(serviceClass)
}