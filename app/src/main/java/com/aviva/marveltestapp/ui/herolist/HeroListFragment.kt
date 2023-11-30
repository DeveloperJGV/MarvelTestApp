package com.aviva.marveltestapp.ui.herolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aviva.marveltestapp.R
import com.aviva.marveltestapp.data.api.RetrofitInstance
import com.aviva.marveltestapp.data.repository.SuperheroRepository
import com.aviva.marveltestapp.ui.adapters.SuperheroAdapter
import com.aviva.marveltestapp.util.Result
import android.widget.ProgressBar
import android.widget.Toast
import com.aviva.marveltestapp.data.model.Character

class HeroListFragment : Fragment() {

    private lateinit var viewModel: HeroListViewModel
    private lateinit var viewModelFactory: HeroListModelFactory
    private lateinit var superheroAdapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicialización del repositorio y la fábrica de ViewModel
        val superheroRepository = SuperheroRepository(RetrofitInstance.api)
        viewModelFactory = HeroListModelFactory(superheroRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HeroListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el RecyclerView y el adaptador
        val navController = findNavController()
        superheroAdapter = SuperheroAdapter { character: Character ->
            // Manejo del clic en cada personaje
            val action = HeroListFragmentDirections.actionMainFragmentToHeroDetailsFragment(character.id, character.thumbnail.getUrl())
            navController.navigate(action)

        }

        val recyclerView: RecyclerView = view.findViewById(R.id.rvHeroList)
        recyclerView.layoutManager = LinearLayoutManager(context) // Asignación del LayoutManager
        recyclerView.adapter = superheroAdapter

        // Observar los cambios del ViewModel
        viewModel.superheroes.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    view.findViewById<ProgressBar>(R.id.pbHeroList).visibility = View.VISIBLE
                }
                is Result.Success -> {
                    view.findViewById<ProgressBar>(R.id.pbHeroList).visibility = View.GONE
                    superheroAdapter.submitList(result.data)
                }
                is Result.Error -> {
                    view.findViewById<ProgressBar>(R.id.pbHeroList).visibility = View.GONE
                    Toast.makeText(context, "Error: ${result.exception.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}