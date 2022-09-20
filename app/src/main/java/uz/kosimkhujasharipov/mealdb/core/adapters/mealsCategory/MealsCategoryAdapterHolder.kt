package uz.kosimkhujasharipov.mealdb.core.adapters.mealsCategory

import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.kosimkhujasharipov.mealdb.core.models.meal.Category
import uz.kosimkhujasharipov.mealdb.databinding.CategoriesListItemBinding

class MealsCategoryAdapterHolder(val binding: CategoriesListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: Category) {
        binding.itemImage.load(data.strCategoryThumb)
        binding.itemName.text = data.strCategory
    }

}