package com.bat.tomdog.mvvmstudy.ui.area

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.bat.tomdog.mvvmstudy.databinding.SimpleItemBinding

/**
 * Created by Tomdog on 2019/12/31.
 */
class ChooseAreaAdapter(context: Context,private val resId:Int,private val dataList:List<String>):ArrayAdapter<String>(context,resId,dataList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val bind : SimpleItemBinding?
        val view = if(convertView == null){
            val v = LayoutInflater.from(context).inflate(resId,parent,false)
            bind = DataBindingUtil.bind(v)
            v.tag = bind
            v
        }else{
            bind = convertView.tag as SimpleItemBinding
            convertView
        }
        bind?.data = dataList[position]
        return view
    }
}