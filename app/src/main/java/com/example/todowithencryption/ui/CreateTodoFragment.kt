package com.example.todowithencryption.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todowithencryption.MainActivity
import com.example.todowithencryption.R
import com.example.todowithencryption.data.db.entities.TodoItem
import com.example.todowithencryption.databinding.FragmentCreateTodoBinding
import com.example.todowithencryption.viewModel.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class CreateTodoFragment : Fragment() {

    private var _binding : FragmentCreateTodoBinding? = null
    private val binding get() = _binding!!
    private var dateText : String = ""
    var priorityStateVariable : String = ""
    private lateinit var item : TodoItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTodoBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this is used to take the instance of the mainActivity
        // and perform changes on the custom action bar .
        val mainActivityInstance = requireActivity() as MainActivity
        mainActivityInstance.binding.createNewTodoIV.visibility = View.INVISIBLE
        mainActivityInstance.binding.welcomeMessage.text = "What's on the list ."

        //Line 50-63 is used to setUp data picker .
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR  , year)
            myCalendar.set(Calendar.MONTH  , month)
            myCalendar.set(Calendar.DAY_OF_MONTH  , dayOfMonth)

            upDateLabel(myCalendar)
        }
        binding.datePickerIv.setOnClickListener {
            DatePickerDialog(context!! ,
                datePicker ,
                myCalendar.get(Calendar.YEAR) ,
                myCalendar.get(Calendar.MONTH) ,
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        // spinnerSetup is used to set a spinner with priority i.e High , Moderate , Low
        // and will change the priorityStateVariable depending on which item is clicked .
        spinnerSetup()

        binding.notesAdd.setOnClickListener {
            //here on the press of add button we'll add the form data in the database .
            // Note : there is no check happening here i.e if the data is empty the application will
            // not perform correctly .
            setUpData()
            findNavController().navigate(R.id.action_createTodoFragment_to_todoListFragment)
        }
    }

    private fun spinnerSetup() {
        val priorityState = resources.getStringArray(R.array.PriorityState)
        val prioritySpinner = binding.spinner
        val adapter = ArrayAdapter(context!! , android.R.layout.simple_spinner_item,priorityState)
        prioritySpinner.adapter = adapter
        prioritySpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (priorityState[p2] == "High"){
                    Toast.makeText(context , "Item Priority is ${priorityState[p2]}" , Toast.LENGTH_SHORT).show()
                    priorityStateVariable = "High"
                }
                if(priorityState[p2] == "Moderate"){
                    Toast.makeText(context , "Item Priority is ${priorityState[p2]}" , Toast.LENGTH_SHORT).show()
                    priorityStateVariable = "Moderate"
                }
                if(priorityState[p2] == "Low"){
                    Toast.makeText(context , "Item Priority is ${priorityState[p2]}" , Toast.LENGTH_SHORT).show()
                    priorityStateVariable = "Low"
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }

    private fun setUpData() {
        val title = binding.notesTitleTv.text.toString()
        val description = binding.notesDescriptionTv.text.toString()

        val viewModel:TodoListViewModel by viewModels()

        item = TodoItem(
            todoItemTitle = title ,
            todoItemDescription =  description ,
            todoItemCreation = dateText ,
            todoItemPriority = priorityStateVariable
        )

        Log.d("CreateTodoFragment" , "Item is $item")
        viewModel.todoItemUpsert(item)

    }

    private fun upDateLabel(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat , Locale.UK)
        dateText = sdf.format(myCalendar.time)
        binding.dateTV.text = sdf.format(myCalendar.time)
    }

}