package uz.kosimkhujasharipov.mealdb.core.adapters.meals

import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.Meal
import uz.kosimkhujasharipov.mealdb.databinding.MealsListItemBinding

class MealsAdapterHolder(val binding: MealsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: Meal) {
        binding.image.load(data.strMealThumb)
        binding.name.text = data.strMeal
    }

}