package uz.kosimkhujasharipov.mealdb.core.adapters.aboutUs

import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.kosimkhujasharipov.mealdb.core.models.aboutUs.SocialNetworkData
import uz.kosimkhujasharipov.mealdb.databinding.AboutUsListItemBinding

class AboutUsAdapterHolder(val binding: AboutUsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: SocialNetworkData) {
        binding.image.load(data.image)
    }

}