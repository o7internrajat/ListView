package com.o7solutions.listview

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
   lateinit var myadapter: MyAdapter
   var mobilePattern = "[0-9]{10}"
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
        binding= FragmentStudentListBinding.inflate(layoutInflater)
        myadapter= MyAdapter(mainActivity,userList,this)
        binding.listView.adapter=myadapter
        binding.btnDialogOpen.setOnClickListener {
            val binding=FragmentDialogBinding.inflate(layoutInflater)
            val addDialog=Dialog(mainActivity)
            addDialog.setContentView(binding.root)
            addDialog.create()
            addDialog.show()
            binding.btnAdd.setOnClickListener {
                if(binding.etName.text.isEmpty()){
                    binding.etName.setError("Enter Name")
                }else if(binding.etRollNo.text.isEmpty()){
                    binding.etRollNo.setError("Enter RollNo.")
                }else if(binding.etNumber.text.isEmpty()){
                    binding.etNumber.setError("Enter Number")
                }else if(binding.etNumber.length()< 10) {
                    binding.etNumber.setError("Type 10 Digit Number")
                }else{
                    userList.add(StudentModel("${binding.etName.text.toString().trim()}",
                        "${binding.etRollNo.text.toString().trim()}", "${binding.etNumber.text.toString().trim()}"))
                    println("Array" + userList)
                    myadapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "Student Added", Toast.LENGTH_SHORT).show()
                    addDialog.dismiss()
                }
            }
                binding.btnCancel.setOnClickListener {
                    addDialog.dismiss()
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
        val binding=FragmentDialogBinding.inflate(layoutInflater)
        val editDialog=Dialog(mainActivity)
        editDialog.setContentView(binding.root)
        editDialog.create()
        editDialog.show()
        binding.btnAdd.text = "Update"
        binding.tvStudent.text="Update Student"
        binding.etName.setText(studentModel.name)
        binding.etRollNo.setText(studentModel.rollNo)
        binding.etNumber.setText(studentModel.phoneNumber)
        binding.btnAdd.setOnClickListener {
            if(binding.etName.text.isEmpty()){
                binding.etName.setError("Enter Name")
            }else if(binding.etRollNo.text.isEmpty()){
                binding.etRollNo.setError("Enter RollNo.")
            }else if(binding.etNumber.text.isEmpty()){
                binding.etNumber.setError("Enter Number")
            }else {
                userList.set(position,StudentModel(name = binding.etName.text.toString(),
                    rollNo=binding.etRollNo.text.toString(), phoneNumber = binding.etNumber.text.toString()))
                println("Array" + userList)
                myadapter.notifyDataSetChanged()
                editDialog.dismiss()
            }
        }
        binding.btnCancel.setOnClickListener {
            editDialog.dismiss()
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
        }
    }
    override fun deleteClick(studentModel: StudentModel, position: Int) {
        userList.removeAt(position)
        myadapter.notifyDataSetChanged()
    }
}