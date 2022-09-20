package uz.kosimkhujasharipov.mealdb.core.adapters.search

import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.Meal
import uz.kosimkhujasharipov.mealdb.databinding.MealsListItemBinding

class SearchListAdapterHolder(val binding: MealsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: Meal) {
        binding.name.text = data.strMeal
        binding.image.load(data.strMealThumb)
        binding.image.elevation = 0f
    }

}