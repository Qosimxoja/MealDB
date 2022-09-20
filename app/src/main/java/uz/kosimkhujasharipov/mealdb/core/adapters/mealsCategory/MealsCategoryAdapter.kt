package uz.kosimkhujasharipov.mealdb.core.adapters.mealsCategory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kosimkhujasharipov.mealdb.core.models.meal.Category
import uz.kosimkhujasharipov.mealdb.databinding.CategoriesListItemBinding

class MealsCategoryAdapter : RecyclerView.Adapter<MealsCategoryAdapterHolder>() {

    var data = ArrayList<Category>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }
    var onItemClick: ((name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsCategoryAdapterHolder =
        MealsCategoryAdapterHolder(
            CategoriesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MealsCategoryAdapterHolder, position: Int) {
        holder.bindData(data = data[position])
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(data[position].strCategory)
        }
    }

    override fun getItemCount(): Int = data.size
}