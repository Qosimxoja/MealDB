package uz.kosimkhujasharipov.mealdb.core.presenter

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.kosimkhujasharipov.mealdb.core.models.area.AreaCategoryResponse
import uz.kosimkhujasharipov.mealdb.core.models.ingredient.IngredientCategoryResponse
import uz.kosimkhujasharipov.mealdb.core.models.meal.Category
import uz.kosimkhujasharipov.mealdb.core.models.meal.MealCategoryResponse
import uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.MealsInfoResponse
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.Meal
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.MealsResponse
import uz.kosimkhujasharipov.mealdb.core.models.network.MealAPIClient
import uz.kosimkhujasharipov.mealdb.view.MealsActivity
import uz.kosimkhujasharipov.mealdb.view.fragments.AreaFragment
import uz.kosimkhujasharipov.mealdb.view.fragments.IngredientFragment
import uz.kosimkhujasharipov.mealdb.view.fragments.MealsCategoryFragment
import uz.kosimkhujasharipov.mealdb.view.fragments.MealsInfoFragment

class PresenterImp(
    val view: Presenter.View,
    private val key: String,
    private val value: String?,
    private val fromWhere: Int?,
    private val context: Context
) :
    Presenter.Presenter {
    override fun loadData() {
        view.dataState(isLoading = true)
        val call = MealAPIClient.mealAPI(context)
        when (key) {
            MealsCategoryFragment.IMP_KEY -> {
                call.getMealCategories().enqueue(object : Callback<MealCategoryResponse> {
                    override fun onResponse(
                        call: Call<MealCategoryResponse>,
                        response: Response<MealCategoryResponse>
                    ) {
                        view.dataState(isLoading = false)
                        if (response.isSuccessful) {
                            response.body()?.let {
                                view.showData(
                                    it.categories as ArrayList<Category>,
                                    null,
                                    null,
                                    null,
                                    null
                                )
                            }
                        } else {
                            view.showError(response.message())
                        }
                    }

                    override fun onFailure(call: Call<MealCategoryResponse>, t: Throwable) {
                        view.dataState(isLoading = false)
                        view.showError(t.toString())
                    }
                })
            }
            MealsActivity.DATA_KEY -> {
                when (fromWhere) {
                    1 -> {
                        call.getMealsByName(value!!).enqueue(object : Callback<MealsResponse> {
                            override fun onResponse(
                                call: Call<MealsResponse>,
                                response: Response<MealsResponse>
                            ) {
                                view.dataState(isLoading = false)
                                if (response.isSuccessful) {
                                    response.body()?.let {
                                        view.showData(
                                            null, it.meals as ArrayList<Meal>, null, null,
                                            null
                                        )
                                    }
                                } else {
                                    view.showError(response.message())
                                }
                            }

                            override fun onFailure(call: Call<MealsResponse>, t: Throwable) {
                                view.dataState(isLoading = false)
                                view.showError(t.toString())
                            }
                        })
                    }
                    2 -> {
                        call.getMealsByArea(value!!).enqueue(object : Callback<MealsResponse> {
                            override fun onResponse(
                                call: Call<MealsResponse>,
                                response: Response<MealsResponse>
                            ) {
                                view.dataState(isLoading = false)
                                if (response.isSuccessful) {
                                    response.body()?.let {
                                        view.showData(
                                            null,
                                            it.meals as ArrayList<Meal>,
                                            null,
                                            null,
                                            null
                                        )
                                    }
                                } else {
                                    view.showError(response.message())
                                }
                            }

                            override fun onFailure(call: Call<MealsResponse>, t: Throwable) {
                                view.dataState(isLoading = false)
                                view.showError(t.toString())
                            }
                        })
                    }
                    3 -> {
                        call.getMealsByIngredient(value!!)
                            .enqueue(object : Callback<MealsResponse> {
                                override fun onResponse(
                                    call: Call<MealsResponse>,
                                    response: Response<MealsResponse>
                                ) {
                                    view.dataState(isLoading = false)
                                    if (response.isSuccessful) {
                                        response.body()?.let {
                                            view.showData(
                                                null,
                                                it.meals as ArrayList<Meal>?,
                                                null,
                                                null,
                                                null
                                            )
                                        }
                                    } else {
                                        view.showError(response.message())
                                    }
                                }

                                override fun onFailure(call: Call<MealsResponse>, t: Throwable) {
                                    view.dataState(isLoading = false)
                                    view.showError(t.toString())
                                }
                            })
                    }
                }
            }
            AreaFragment.DATA_KEY -> {
                call.getAreaCategories("list").enqueue(object : Callback<AreaCategoryResponse> {
                    override fun onResponse(
                        call: Call<AreaCategoryResponse>,
                        response: Response<AreaCategoryResponse>
                    ) {
                        view.dataState(isLoading = false)
                        if (response.isSuccessful) {
                            response.body()?.let {
                                view.showData(
                                    null,
                                    null,
                                    it.meals as ArrayList<uz.kosimkhujasharipov.mealdb.core.models.area.Meal>,
                                    null,
                                    null
                                )
                            }
                        } else {
                            view.showError(response.message())
                        }
                    }

                    override fun onFailure(call: Call<AreaCategoryResponse>, t: Throwable) {
                        view.dataState(isLoading = false)
                        view.showError(t.toString())
                    }
                })
            }
            IngredientFragment.DATA_KEY -> {
                call.getIngredientCategories("list")
                    .enqueue(object : Callback<IngredientCategoryResponse> {
                        override fun onResponse(
                            call: Call<IngredientCategoryResponse>,
                            response: Response<IngredientCategoryResponse>
                        ) {
                            view.dataState(isLoading = false)
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    view.showData(
                                        null,
                                        null,
                                        null,
                                        it.meals as ArrayList<uz.kosimkhujasharipov.mealdb.core.models.ingredient.Meal>,
                                        null
                                    )
                                }
                            } else {
                                view.showError(response.message())
                            }
                        }

                        override fun onFailure(
                            call: Call<IngredientCategoryResponse>,
                            t: Throwable
                        ) {
                            view.dataState(isLoading = false)
                            view.showError(t.toString())
                        }
                    })
            }
            MealsInfoFragment.DATA_KEY -> {
                call.getMealInfo(value!!).enqueue(object : Callback<MealsInfoResponse> {
                    override fun onResponse(
                        call: Call<MealsInfoResponse>,
                        response: Response<MealsInfoResponse>
                    ) {
                        view.dataState(isLoading = false)
                        if (response.isSuccessful) {
                            response.body()?.let {
                                view.showData(
                                    null,
                                    null,
                                    null,
                                    null,
                                    mealsInfoData = it.meals as ArrayList<uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.Meal>
                                )
                            }
                        } else {
                            view.showError(response.message())
                        }
                    }

                    override fun onFailure(call: Call<MealsInfoResponse>, t: Throwable) {
                        view.dataState(isLoading = false)
                        view.showError(t.toString())
                    }
                })
            }
        }
    }

    override fun refreshData() {
        loadData()
    }
}