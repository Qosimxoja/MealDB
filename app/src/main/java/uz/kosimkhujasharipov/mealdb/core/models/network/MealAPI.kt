package uz.kosimkhujasharipov.mealdb.core.models.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.kosimkhujasharipov.mealdb.core.models.area.AreaCategoryResponse
import uz.kosimkhujasharipov.mealdb.core.models.ingredient.IngredientCategoryResponse
import uz.kosimkhujasharipov.mealdb.core.models.meal.MealCategoryResponse
import uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.MealsInfoResponse
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.MealsResponse

interface MealAPI {

    @GET("api/json/v1/1/categories.php")
    fun getMealCategories(): Call<MealCategoryResponse>

    @GET("api/json/v1/1/list.php")
    fun getAreaCategories(
        @Query("a") itemName: String
    ): Call<AreaCategoryResponse>

    @GET("api/json/v1/1/list.php")
    fun getIngredientCategories(
        @Query("i") itemName: String
    ): Call<IngredientCategoryResponse>

    @GET("api/json/v1/1/filter.php")
    fun getMealsByName(
        @Query("c") itemName: String
    ): Call<MealsResponse>

    @GET("api/json/v1/1/filter.php")
    fun getMealsByArea(
        @Query("a") itemName: String
    ): Call<MealsResponse>

    @GET("api/json/v1/1/filter.php")
    fun getMealsByIngredient(
        @Query("i") itemName: String
    ): Call<MealsResponse>

    @GET("api/json/v1/1/search.php")
    fun getMealInfo(
        @Query("s") itemName: String
    ): Call<MealsInfoResponse>

}