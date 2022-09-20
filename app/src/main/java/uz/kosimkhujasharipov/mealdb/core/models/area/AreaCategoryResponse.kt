package uz.kosimkhujasharipov.mealdb.core.models.area


import com.google.gson.annotations.SerializedName

data class AreaCategoryResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)