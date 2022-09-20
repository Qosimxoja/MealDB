package uz.kosimkhujasharipov.mealdb.core.models.ingredient


import com.google.gson.annotations.SerializedName

data class IngredientCategoryResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)