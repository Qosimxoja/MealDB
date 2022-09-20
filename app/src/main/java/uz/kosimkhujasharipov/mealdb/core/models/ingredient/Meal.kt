package uz.kosimkhujasharipov.mealdb.core.models.ingredient


import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idIngredient")
    val idIngredient: String,
    @SerializedName("strIngredient")
    val strIngredient: String,
    @SerializedName("strDescription")
    val strDescription: String,
    @SerializedName("strType")
    val strType: String
)