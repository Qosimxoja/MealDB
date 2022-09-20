package uz.kosimkhujasharipov.mealdb.core.models.mealsItem


import com.google.gson.annotations.SerializedName

data class MealsResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)