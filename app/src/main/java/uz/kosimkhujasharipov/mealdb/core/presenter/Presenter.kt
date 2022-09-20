package uz.kosimkhujasharipov.mealdb.core.presenter

import uz.kosimkhujasharipov.mealdb.core.models.meal.Category
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.Meal

interface Presenter {
    interface View {
        fun dataState(isLoading: Boolean)
        fun showData(
            categoryData: ArrayList<Category>?,
            mealData: ArrayList<Meal>?,
            areaCategoryData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.area.Meal>?,
            ingredientCategoryData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.ingredient.Meal>?,
            mealsInfoData:ArrayList<uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.Meal>?
        )

        fun showError(message: String)
    }

    interface Presenter {
        fun loadData()
        fun refreshData()
    }
}