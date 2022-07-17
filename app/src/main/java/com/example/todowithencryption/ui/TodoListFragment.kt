package com.example.todowithencryption.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todowithencryption.MainActivity
import com.example.todowithencryption.R
import com.example.todowithencryption.data.db.entities.TodoItem
import com.example.todowithencryption.databinding.FragmentTodoListBinding
import com.example.todowithencryption.other.ItemAdapter
import com.example.todowithencryption.viewModel.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : Fragment() {

    private var _binding : FragmentTodoListBinding? = null
    private val binding get() = _binding!!

    private var searchText = ""
    private var flag = 0

    private var todoList : MutableList<TodoItem> = mutableListOf()
    private var searchList : MutableList<TodoItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_todo_list, container, false)
        _binding = FragmentTodoListBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this is used to take the instance of the mainActivity
        // and perform changes on the custom action bar .
        val mainActivityInstance = requireActivity() as MainActivity
        mainActivityInstance.binding.welcomeMessage.text = "The List"
        mainActivityInstance.binding.createNewTodoIV.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_createTodoFragment)
        }
        //Disclaimer :  It may not be a proper way but it worked really well for me .
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                searchText = newText!!
                if(flag == 1){
                    setUpData()
                }else if(searchText.length>=2){
                    flag = 1
                    setUpData()
                }
                return false
            }

        })

        setUpData()

    }

    private fun setUpData() {

        val todoListViewModel : TodoListViewModel by viewModels()

        todoListViewModel.generateTodoList().observe(viewLifecycleOwner , Observer {
            // we'll add all the item in the todoList
            for(item in it){
                todoList.add(item)
            }
            // if the entered text in the searchBox has length greater than 5 and
            // any item in the todoList contain that searchText then we'll add that
            // todoList item in the searchList .
            for (searchItem in todoList){
                if (searchText.length >= 5){
                    if( searchItem.todoItemTitle.contains(searchText)){
                        searchList.add(searchItem)
                    }
                }
            }
            //establishing the recyclerView
            binding.rView.layoutManager = LinearLayoutManager(context)
            // initial state i.e when the search list is empty we'll pass the todoList to the
            // adapter else if the searchList is not empty we'll pass the searchList to the
            // adapter
            if (searchList.size!=0){
                binding.rView.adapter = ItemAdapter(searchList.distinct() , todoListViewModel )
                binding.rView.adapter?.notifyDataSetChanged()
                searchList.clear()
            }else{
                binding.rView.adapter = ItemAdapter(todoList.distinct() , todoListViewModel )
                binding.rView.adapter?.notifyDataSetChanged()
                todoList.clear()
            }
        })

    }
}