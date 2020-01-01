package com.bat.tomdog.mvvmstudy

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.litepal.LitePal

/**
 * Created by Tomdog on 2019/12/30.
 */
class MyApplication : Application() {

    companion object{
        //lateinit的含义：
        //kotlin要求所有属性必须显示初始化，要么在定义该属性时赋值；要么在构造器中对该属性赋值.
        //但在某些时候不是必需的，lateinit 修饰符来解决属性的延迟初始化，该修饰符的属性可以在定义该属性时和构造器中都不指定初始值
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context
    }

    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
        context = this
    }
}