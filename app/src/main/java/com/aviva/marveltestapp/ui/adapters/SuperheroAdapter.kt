package com.aviva.marveltestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aviva.marveltestapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.aviva.marveltestapp.data.model.Character


class SuperheroAdapter(
    private var characterList: List<Character> = emptyList(),
    private val onClick: (Character) -> Unit
) : RecyclerView.Adapter<SuperheroAdapter.ViewHolder>() {

    class ViewHolder(view: View, val onClick: (Character) -> Unit) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewSuperhero)
        val textViewName: TextView = view.findViewById(R.id.textViewSuperheroName)
        private var currentCharacter: Character? = null

        init {
            view.setOnClickListener {
                currentCharacter?.let(onClick)
            }
        }

        fun bind(character: Character) {
            currentCharacter = character
            textViewName.text = character.name
            // Utiliza Picasso para cargar la imagen del personaje
            Picasso.get()
                .load(character.thumbnail.getUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        // La imagen se cargó correctamente
                    }

                    override fun onError(e: Exception?) {
                        // Hubo un error al cargar la imagen
                        e?.printStackTrace()
                        if (e is java.net.SocketTimeoutException) {
                            Toast.makeText(itemView.context, "Error 504: Timeout", Toast.LENGTH_SHORT).show()
                        }
                        imageView.setImageResource(R.drawable.placeholder_image)
                    }
                })
        }
    }

    // Método para actualizar la lista de personajes
    fun submitList(characters: List<Character>) {
        characterList = characters
        notifyDataSetChanged() // Notifica al adaptador que los datos han cambiado
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount() = characterList.size
}
