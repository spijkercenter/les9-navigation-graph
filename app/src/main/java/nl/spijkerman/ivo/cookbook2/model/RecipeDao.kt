package nl.spijkerman.ivo.cookbook2.model

interface RecipeDao {
    fun getAll(): List<Recipe>
    fun get(title: String): Recipe?
}