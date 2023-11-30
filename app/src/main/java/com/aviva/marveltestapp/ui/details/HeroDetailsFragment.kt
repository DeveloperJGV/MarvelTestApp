package com.aviva.marveltestapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aviva.marveltestapp.R
import com.aviva.marveltestapp.data.api.RetrofitInstance
import com.aviva.marveltestapp.data.repository.SuperheroRepository
import com.aviva.marveltestapp.databinding.FragmentHeroDetailsBinding
import com.aviva.marveltestapp.ui.adapters.ComicAdapter
import com.squareup.picasso.Picasso
import com.aviva.marveltestapp.util.Result
import com.aviva.marveltestapp.data.model.Character



class HeroDetailsFragment : Fragment() {
    private lateinit var viewModel: HeroDetailViewModel
    private lateinit var viewModelFactory: HeroDetailViewModelFactory
    private lateinit var comicAdapter: ComicAdapter
    private var _binding: FragmentHeroDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val superheroRepository = SuperheroRepository(RetrofitInstance.api)
        val heroId = arguments?.getInt("characterId") ?: 0
        println("Received character ID in HeroDetailsFragment: $heroId")
        viewModelFactory = HeroDetailViewModelFactory(superheroRepository, heroId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HeroDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comicAdapter = ComicAdapter()
        binding.recyclerViewComics.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewComics.adapter = comicAdapter

        viewModel.characterDetails.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    // Muestra un indicador de carga
                }
                is Result.Success -> {
                    // Actualiza la UI con los detalles del personaje
                    if (result.data is Character) {
                        val character = result.data
                        binding.textViewHeroName.text = character.name
                        // Verifica si la descripción está vacía
                        if (character.description.isNullOrEmpty()) {
                            binding.textViewHeroDescription.text = getString(R.string.classified_description)
                        } else {
                            binding.textViewHeroDescription.text = character.description
                        }
                        comicAdapter.updateComics(character.comics.items)
                        // Carga la imagen del personaje con Picasso
                        Picasso.get()
                            .load(arguments?.getString("characterImage"))
                            .into(binding.imageViewHero)
                    }
                }
                is Result.Error -> {
                    // Muestra un mensaje de error
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
