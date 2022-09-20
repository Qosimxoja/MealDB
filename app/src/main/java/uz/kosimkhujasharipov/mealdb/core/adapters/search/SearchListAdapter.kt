package uz.kosimkhujasharipov.mealdb.core.adapters.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.Meal
import uz.kosimkhujasharipov.mealdb.databinding.MealsListItemBinding

class SearchListAdapter : RecyclerView.Adapter<SearchListAdapterHolder>() {

    var data = ArrayList<Meal>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListAdapterHolder =
        SearchListAdapterHolder(
            MealsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SearchListAdapterHolder, position: Int) {
        holder.bindData(data = data[position])
    }

    override fun getItemCount(): Int = data.size

}