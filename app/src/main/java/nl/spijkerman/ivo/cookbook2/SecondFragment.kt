package nl.spijkerman.ivo.cookbook2

import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import nl.spijkerman.ivo.cookbook2.databinding.FragmentSecondBinding
import nl.spijkerman.ivo.cookbook2.model.RecipeDao
import nl.spijkerman.ivo.cookbook2.model.RecipeDaoImpl

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: SecondFragmentArgs by navArgs()
    private val recipeDao: RecipeDao = RecipeDaoImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val recipeTitle = args.recipeTitle
        binding.textviewSecond.text = recipeDao.get(recipeTitle)!!.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(
            menu,
            inflater
        )
        inflater.inflate(R.menu.menu_recipe_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSelectedRecipe()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareSelectedRecipe() {
        val recipe = "Lorem ipsum"
        val shareByMailIntent = ShareCompat.IntentBuilder(requireContext()).setType("text/plain")
            .setSubject("My menu choice for today")
            .setText("Hi, today I'm having this delicious meal for dinner: $recipe").intent
        startActivity(shareByMailIntent)
    }


}