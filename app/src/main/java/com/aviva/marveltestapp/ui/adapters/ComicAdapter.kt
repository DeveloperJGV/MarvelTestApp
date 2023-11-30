package com.aviva.marveltestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aviva.marveltestapp.R
import com.aviva.marveltestapp.data.model.ComicSummary

class ComicAdapter : RecyclerView.Adapter<ComicAdapter.ViewHolder>() {

    private var comicList: List<ComicSummary> = emptyList()

    fun updateComics(newComics: List<ComicSummary>) {
        comicList = newComics
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewComicName)

        fun bind(comic: ComicSummary) {
            textViewName.text = comic.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comicList[position])
    }

    override fun getItemCount() = comicList.size
}
