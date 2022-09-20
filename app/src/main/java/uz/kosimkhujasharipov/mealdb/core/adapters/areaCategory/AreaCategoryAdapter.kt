package uz.kosimkhujasharipov.mealdb.core.adapters.areaCategory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kosimkhujasharipov.mealdb.core.models.FlagsData
import uz.kosimkhujasharipov.mealdb.core.models.area.Meal
import uz.kosimkhujasharipov.mealdb.databinding.OtherCategoryListItemBinding

class AreaCategoryAdapter : RecyclerView.Adapter<AreaCategoryAdapterHolder>() {

    var data = ArrayList<Meal>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }
    var onItemClick: ((name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaCategoryAdapterHolder =
        AreaCategoryAdapterHolder(
            OtherCategoryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AreaCategoryAdapterHolder, position: Int) {
        holder.bindData(data = data[position])
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(data[position].strArea)
        }
    }

    override fun getItemCount(): Int = data.size

}