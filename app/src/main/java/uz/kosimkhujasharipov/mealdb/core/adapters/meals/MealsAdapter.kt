package uz.kosimkhujasharipov.mealdb.core.adapters.meals

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.Meal
import uz.kosimkhujasharipov.mealdb.databinding.MealsListItemBinding

class MealsAdapter : RecyclerView.Adapter<MealsAdapterHolder>() {

    var data = ArrayList<Meal>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }
    var onItemClick: ((name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsAdapterHolder =
        MealsAdapterHolder(
            MealsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MealsAdapterHolder, position: Int) {
        holder.bindData(data = data[position])
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(data[position].strMeal)
        }
    }

    override fun getItemCount(): Int = data.size
}