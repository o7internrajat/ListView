package com.o7solutions.listview

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.o7solutions.ClickInterface
import com.o7solutions.listview.adapter.MyAdapter
import com.o7solutions.listview.databinding.FragmentDialogBinding
import com.o7solutions.listview.databinding.FragmentStudentListBinding
import com.o7solutions.model.StudentModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentListFragment : Fragment(), ClickInterface {
    lateinit var binding: FragmentStudentListBinding
    lateinit var etName:EditText
    lateinit var etRollNo:EditText
    lateinit var etNumber:EditText
    lateinit var btnCancel:Button
    lateinit var btnAdd:Button
   lateinit var myadapter: MyAdapter
     var userList=ArrayList<StudentModel>()


    lateinit var mainActivity: MainActivity
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val v= inflater.inflate(R.layout.fragment_dialog, container, false)
        binding= FragmentStudentListBinding.inflate(layoutInflater)
        myadapter= MyAdapter(mainActivity,userList,this)
        binding.listView.adapter=myadapter
        binding.btnDialogOpen.setOnClickListener {
            val v=LayoutInflater.from(mainActivity).inflate(R.layout.fragment_dialog,null)
            val dialog=Dialog(mainActivity)
            dialog.setContentView(v)
            dialog.create()
            dialog.show()
            etName=v.findViewById(R.id.etName)
            etRollNo=v.findViewById(R.id.etRollNo)
            etNumber=v.findViewById(R.id.etNumber)
            btnAdd=v.findViewById(R.id.btnAdd)
            btnCancel=v.findViewById(R.id.btnCancel)
            btnAdd.setOnClickListener {
                val name = etName.text.toString()
                val rollNo = etRollNo.text.toString()
                val number = etNumber.text.toString()
                if(etName.text.isEmpty()){
                    etName.setError("Enter Name")
                }else if(etRollNo.text.isEmpty()){
                    etRollNo.setError("Enter RollNo.")
                }else if(etNumber.text.isEmpty()){
                    etNumber.setError("Enter Number")
                }else {

                    userList.add(StudentModel("$name", "$rollNo", "$number"))
                    println("Array" + userList)
                    myadapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "Student Added", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

            }
                btnCancel.setOnClickListener {
                    dialog.dismiss()
                    Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
                }
        }
        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun editClick(studentModel: StudentModel, position: Int) {
        val v=LayoutInflater.from(mainActivity).inflate(R.layout.fragment_dialog,null)
        val dialog=Dialog(mainActivity)
        dialog.setContentView(v)
        dialog.create()
        dialog.show()
        etName=v.findViewById(R.id.etName)
        etRollNo=v.findViewById(R.id.etRollNo)
        etNumber=v.findViewById(R.id.etNumber)
        btnAdd=v.findViewById(R.id.btnAdd)
        btnCancel=v.findViewById(R.id.btnCancel)
        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val rollNo = etRollNo.text.toString()
            val number = etNumber.text.toString()
            if(etName.text.isEmpty()){
                etName.setError("Enter Name")
            }else if(etRollNo.text.isEmpty()){
                etRollNo.setError("Enter RollNo.")
            }else if(etNumber.text.isEmpty()){
                etNumber.setError("Enter Number")
            }else {

                userList.add(StudentModel("$name", "$rollNo", "$number"))
                println("Array" + userList)
                myadapter.notifyDataSetChanged()
            }

        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
        }
        }

    override fun deleteClick(studentModel: StudentModel, position: Int) {
        userList.removeAt(position)
        userList.clear()
    }


}