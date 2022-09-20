package uz.kosimkhujasharipov.mealdb.core.adapters.mealsInfo

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.MealsInfoCategoryData
import uz.kosimkhujasharipov.mealdb.databinding.MealInfoListItemBinding

class MealsInfoAdapterHolder(val binding: MealInfoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindData(data: MealsInfoCategoryData) {
        binding.ingredient.text = "\u25CF ${data.measure} ${data.ingredient}"
    }

}