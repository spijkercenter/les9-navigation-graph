package nl.spijkerman.ivo.cookbook2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import nl.spijkerman.ivo.cookbook2.databinding.FragmentFirstBinding
import nl.spijkerman.ivo.cookbook2.model.RecipeDao
import nl.spijkerman.ivo.cookbook2.model.RecipeDaoImpl

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val recipeDao: RecipeDao = RecipeDaoImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeTitle = binding.recipeSelectionSpn.selectedItem.toString()

        binding.showDescriptionBtn.setOnClickListener {
            binding.descriptionTextview.text = getRecipeDescription(recipeTitle)
        }

        binding.recipeDetailsBtn.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(recipeTitle)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRecipeDescription(recipe: String): String =
        recipeDao.get(recipe)?.description ?: "no recipe found"
}