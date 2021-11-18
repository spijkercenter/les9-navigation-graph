package nl.spijkerman.ivo.cookbook2.model

data class Recipe(
    val title: String,
    val description: String,
    val recipeType: RecipeType,
    val nutritionalValue: NutritionalValue,
)
