package uz.kosimkhujasharipov.mealdb.view.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import uz.kosimkhujasharipov.mealdb.core.adapters.aboutUs.AboutUsNetworkAdapter
import uz.kosimkhujasharipov.mealdb.core.adapters.aboutUs.AboutUsSourceAdapter
import uz.kosimkhujasharipov.mealdb.core.models.aboutUs.SocialNetworkData
import uz.kosimkhujasharipov.mealdb.databinding.AboutUsFragmentBinding

class AboutUsFragment : Fragment() {

    private lateinit var binding: AboutUsFragmentBinding
    private val adapter = AboutUsNetworkAdapter()
    private val sourceAdapter = AboutUsSourceAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // loading view
        binding = AboutUsFragmentBinding.inflate(layoutInflater)
        // preparing list
        binding.networkList.adapter = adapter
        binding.networkList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.sourceList.adapter = sourceAdapter
        binding.sourceList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        // loading data
        adapter.data = SocialNetworkData.socialNetworkData()
        sourceAdapter.data = SocialNetworkData.sourceData()
        // loading actions
        adapter.onItemClicked = {
            if (it == SocialNetworkData.socialNetworkData()[0].name) {
                val uri = Uri.parse("https://www.instagram.com/kosimkhuja_5574/")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            } else if (it == SocialNetworkData.socialNetworkData()[1].name) {
                val uri = Uri.parse("https://www.facebook.com/sharipov.qosim")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            } else if (it == SocialNetworkData.socialNetworkData()[2].name) {
                val uri = Uri.parse("https://www.linkedin.com/in/sharipov-qosim-8b7038226")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            } else if (it == SocialNetworkData.socialNetworkData()[3].name) {
                val email = Intent(Intent.ACTION_SENDTO)
                email.data = Uri.parse("mailto:sharipovqosim007@gmail.com")
                email.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                email.putExtra(Intent.EXTRA_TEXT, "My Email message")
                startActivity(email)
            }
        }
        sourceAdapter.onItemClicked = {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}