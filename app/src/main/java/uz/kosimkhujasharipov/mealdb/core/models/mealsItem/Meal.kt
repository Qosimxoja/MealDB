package uz.kosimkhujasharipov.mealdb.core.models.mealsItem


import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String,
    @SerializedName("idMeal")
    val idMeal: String
)