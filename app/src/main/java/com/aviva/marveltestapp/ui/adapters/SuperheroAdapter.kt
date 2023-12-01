package com.aviva.marveltestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aviva.marveltestapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.aviva.marveltestapp.data.model.Character

class SuperheroAdapter(
    private var characterPairs: List<Pair<Character, Character>> = emptyList(),
    private val onClick: (Character) -> Unit
) : RecyclerView.Adapter<SuperheroAdapter.ViewHolder>() {

    class ViewHolder(view: View, val onClick: (Character) -> Unit) : RecyclerView.ViewHolder(view) {
        val imageView1: ImageView = view.findViewById(R.id.imageViewSuperhero1)
        val imageView2: ImageView = view.findViewById(R.id.imageViewSuperhero2)
        val textViewName1: TextView = view.findViewById(R.id.textViewSuperheroName1)
        val textViewName2: TextView = view.findViewById(R.id.textViewSuperheroName2)
        private var currentCharacter1: Character? = null
        private var currentCharacter2: Character? = null

        init {
            imageView1.setOnClickListener {
                currentCharacter1?.let(onClick)
            }
            imageView2.setOnClickListener {
                currentCharacter2?.let(onClick)
            }
        }

        fun bind(characterPair: Pair<Character, Character>) {
            currentCharacter1 = characterPair.first
            currentCharacter2 = characterPair.second
            textViewName1.text = currentCharacter1?.name
            textViewName2.text = currentCharacter2?.name

            // Carga la imagen del primer personaje
            Picasso.get()
                .load(currentCharacter1?.thumbnail?.getUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(imageView1, object : Callback {
                    override fun onSuccess() {
                        // La imagen se cargó correctamente
                    }

                    override fun onError(e: Exception?) {
                        // Hubo un error al cargar la imagen
                        e?.printStackTrace()
                        if (e is java.net.SocketTimeoutException) {
                            Toast.makeText(itemView.context, "Error 504: Timeout", Toast.LENGTH_SHORT).show()
                        }
                        imageView1.setImageResource(R.drawable.placeholder_image)
                    }
                })

            // Carga la imagen del segundo personaje
            Picasso.get()
                .load(currentCharacter2?.thumbnail?.getUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(imageView2, object : Callback {
                    override fun onSuccess() {
                        // La imagen se cargó correctamente
                    }

                    override fun onError(e: Exception?) {
                        // Hubo un error al cargar la imagen
                        e?.printStackTrace()
                        if (e is java.net.SocketTimeoutException) {
                            Toast.makeText(itemView.context, "Error 504: Timeout", Toast.LENGTH_SHORT).show()
                        }
                        imageView2.setImageResource(R.drawable.placeholder_image)
                    }
                })
        }
    }

    // Método para actualizar la lista de pares de personajes
    fun submitList(characters: List<Character>) {
        characterPairs = characters.chunked(2).map { Pair(it[0], it.getOrNull(1) ?: it[0]) }
        notifyDataSetChanged() // Notifica al adaptador que los datos han cambiado
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterPair = characterPairs[position]
        holder.bind(characterPair)
    }

    override fun getItemCount() = characterPairs.size
}
