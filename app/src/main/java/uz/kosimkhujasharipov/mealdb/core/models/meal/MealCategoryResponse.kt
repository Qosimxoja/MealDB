package uz.kosimkhujasharipov.mealdb.core.models.meal


import com.google.gson.annotations.SerializedName

data class MealCategoryResponse(
    @SerializedName("categories")
    val categories: List<Category>
)