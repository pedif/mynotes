package com.techspark.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.techspark.mynotes.model.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat

class NoteAdapter (val listener: OnclickListener) :
ListAdapter<Note, NoteAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)

        holder.itemView.setOnClickListener {

            listener.onclick(note)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) {
            if(adapterPosition%2 == 0)
                itemView.img.setImageResource(R.mipmap.ic_img_1)
            else
                itemView.img.setImageResource(R.mipmap.ic_img_2)

            itemView.title.text = note.text
            itemView.date.text  = SimpleDateFormat("hh:mm:ss").format(note.time)
        }
    }

}

class OnclickListener(val clickListener: (item: Note) -> Unit) {
    fun onclick(item: Note) = clickListener(item)
}