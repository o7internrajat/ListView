package com.o7solutions

import com.o7solutions.model.StudentModel

interface ClickInterface {
    fun editClick(studentModel: StudentModel,position:Int)
    fun deleteClick(studentModel: StudentModel,position:Int)

}