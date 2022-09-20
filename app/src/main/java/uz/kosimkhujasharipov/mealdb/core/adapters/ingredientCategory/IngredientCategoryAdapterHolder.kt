package uz.kosimkhujasharipov.mealdb.core.adapters.ingredientCategory

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.kosimkhujasharipov.mealdb.core.models.ingredient.Meal
import uz.kosimkhujasharipov.mealdb.databinding.CategoriesListItemBinding

class IngredientCategoryAdapterHolder(val binding: CategoriesListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: Meal) {
        binding.itemName.text = data.strIngredient
        binding.itemImage.load("https://www.themealdb.com/images/ingredients/${data.strIngredient}.png")
        binding.itemImage.scaleType = ImageView.ScaleType.CENTER_CROP
    }

}