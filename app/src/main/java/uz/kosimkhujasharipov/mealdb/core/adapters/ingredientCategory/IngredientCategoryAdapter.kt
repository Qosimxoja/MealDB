package uz.kosimkhujasharipov.mealdb.core.adapters.ingredientCategory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kosimkhujasharipov.mealdb.core.models.ingredient.Meal
import uz.kosimkhujasharipov.mealdb.databinding.CategoriesListItemBinding

class IngredientCategoryAdapter : RecyclerView.Adapter<IngredientCategoryAdapterHolder>() {

    var data = ArrayList<Meal>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }
    var onItemClick: ((name: String) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientCategoryAdapterHolder = IngredientCategoryAdapterHolder(
        CategoriesListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: IngredientCategoryAdapterHolder, position: Int) {
        holder.bindData(data = data[position])
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(data[position].strIngredient)
        }
    }

    override fun getItemCount(): Int = data.size

}