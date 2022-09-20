package uz.kosimkhujasharipov.mealdb.core.adapters.areaCategory

import androidx.recyclerview.widget.RecyclerView
import uz.kosimkhujasharipov.mealdb.core.models.area.Meal
import uz.kosimkhujasharipov.mealdb.databinding.OtherCategoryListItemBinding

class AreaCategoryAdapterHolder(val binding: OtherCategoryListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: Meal) {
        binding.name.text = data.strArea
    }

}