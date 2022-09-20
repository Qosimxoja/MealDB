package uz.kosimkhujasharipov.mealdb.core.adapters.mealsInfo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.MealsInfoCategoryData
import uz.kosimkhujasharipov.mealdb.databinding.MealInfoListItemBinding

class MealsInfoAdapter : RecyclerView.Adapter<MealsInfoAdapterHolder>() {

    var data = ArrayList<MealsInfoCategoryData>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }
    val onItemClicked: ((ingredient: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsInfoAdapterHolder =
        MealsInfoAdapterHolder(
            MealInfoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MealsInfoAdapterHolder, position: Int) {
        holder.bindData(data = data[position])
        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(data[position].ingredient.toString())
        }
    }

    override fun getItemCount(): Int = data.size

}