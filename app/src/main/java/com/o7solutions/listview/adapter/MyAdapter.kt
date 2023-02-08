package com.o7solutions.listview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.o7solutions.ClickInterface
import com.o7solutions.listview.R
import com.o7solutions.model.StudentModel

class MyAdapter(val c: Context, var studentList:ArrayList<StudentModel>,val clickInterface: ClickInterface):BaseAdapter(){
    lateinit var tvStuName:TextView
    lateinit var tvStuRollNo:TextView
    lateinit var tvStuNumber:TextView
    lateinit var ivDelete:ImageView
    lateinit var ivEdit:ImageView


    override fun getCount(): Int {
        return studentList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, v: View?, parent: ViewGroup?): View {
      val v = LayoutInflater.from(c).inflate(R.layout.stu_list,parent,false)
        tvStuName=v.findViewById(R.id.tvStuName)
        tvStuRollNo=v.findViewById(R.id.tvStuRollNo)
        tvStuNumber=v.findViewById(R.id.tvStuNumber)
        ivDelete=v.findViewById(R.id.ivDelete)
        ivEdit=v.findViewById(R.id.ivEdit)

        val newList = studentList[position]
        tvStuName.text=newList.name
        tvStuRollNo.text=newList.rollNo
        tvStuNumber.text=newList.phoneNumber

        ivDelete.setOnClickListener {
            clickInterface.deleteClick(newList,position)
        }
        ivEdit.setOnClickListener {
            clickInterface.editClick(newList,position)
        }


        return v
    }
}

