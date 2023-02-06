package com.o7solutions.listview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.o7solutions.ClickInterface
import com.o7solutions.listview.R
import com.o7solutions.model.StudentModel

class MyAdapter(var c: Context,  var  arrayList:ArrayList<StudentModel>,var clickInterface: ClickInterface) :
    ArrayAdapter<StudentModel>(c,R.layout.stu_list) {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
//        val listData = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.stu_list, parent, false)

        }
        val stuName = view!!.findViewById<TextView>(R.id.tvStuName)
        val stuRoll = view.findViewById<TextView>(R.id.tvStuRollNo)
        val stuPhone = view.findViewById<TextView>(R.id.tvStuNumber)
        val stuDelete = view.findViewById<TextView>(R.id.ivDelete)
        val stuEdit = view.findViewById<TextView>(R.id.ivEdit)
        val newList = arrayList[position]
        stuName.text = newList.name
        stuRoll.text = newList.rollNo
        stuPhone.text = newList.phoneNumber
        stuEdit.setOnClickListener {
            clickInterface.editClick(newList,position)
        }
        stuDelete.setOnClickListener {
            clickInterface.deleteClick(newList,position)
        }

        return view
    }
}

