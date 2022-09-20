package uz.kosimkhujasharipov.mealdb.core.adapters.aboutUs

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kosimkhujasharipov.mealdb.core.models.aboutUs.SocialNetworkData
import uz.kosimkhujasharipov.mealdb.databinding.AboutUsListItemBinding

class AboutUsNetworkAdapter : RecyclerView.Adapter<AboutUsAdapterHolder>() {

    var data = ArrayList<SocialNetworkData>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }
    var onItemClicked: ((name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutUsAdapterHolder =
        AboutUsAdapterHolder(
            AboutUsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AboutUsAdapterHolder, position: Int) {
        holder.bindData(data = data[position])
        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(data[position].name)
        }
    }

    override fun getItemCount(): Int = data.size

}