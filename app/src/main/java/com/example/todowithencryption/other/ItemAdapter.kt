package com.example.todowithencryption.other



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todowithencryption.R
import com.example.todowithencryption.data.db.entities.TodoItem
import com.example.todowithencryption.databinding.ItemNoteBinding
import com.example.todowithencryption.viewModel.TodoListViewModel


class ItemAdapter(
    private val listNotes: List<TodoItem> ,
    private val todoListViewModel : TodoListViewModel ,
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(var binding : ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =  ItemNoteBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder){
            with(listNotes[position]){
                binding.notesListTitleTv.text = this.todoItemTitle
                binding.notesListItemDescriptionTv.text = this.todoItemDescription
                binding.notesDeleteIv.setOnClickListener {
                    todoListViewModel.todoItemDelete(listNotes[position])
                }
            }
        }
        val rainbow = holder.itemView.resources.getIntArray(R.array.rainbow)

        if(listNotes[position].todoItemPriority =="High"){
            holder.binding.cardView.setCardBackgroundColor(rainbow[0])
        }
        if(listNotes[position].todoItemPriority =="Moderate"){
            holder.binding.cardView.setCardBackgroundColor(rainbow[1])
        }
        if(listNotes[position].todoItemPriority =="Low"){
            holder.binding.cardView.setCardBackgroundColor(rainbow[2])
        }

//        val randomColor  = (rainbow.indices).random()
//        holder.binding.cardView.setCardBackgroundColor(rainbow[randomColor])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }


}