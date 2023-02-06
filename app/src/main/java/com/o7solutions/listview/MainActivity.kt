package com.o7solutions.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.o7solutions.model.StudentModel

class MainActivity : AppCompatActivity() {
    lateinit var arrayList:List<StudentModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}