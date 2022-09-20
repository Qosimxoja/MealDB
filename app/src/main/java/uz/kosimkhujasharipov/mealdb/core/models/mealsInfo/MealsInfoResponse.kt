package uz.kosimkhujasharipov.mealdb.core.models.mealsInfo


import com.google.gson.annotations.SerializedName

data class MealsInfoResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)