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
            when (it) {
                SocialNetworkData.socialNetworkData()[0].name -> {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/kosimkhuja_5574/")
                        )
                    )
                }
                SocialNetworkData.socialNetworkData()[1].name -> {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/sharipov.qosim")
                        )
                    )
                }
                SocialNetworkData.socialNetworkData()[2].name -> {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.linkedin.com/in/sharipov-qosim-8b7038226")
                        )
                    )
                }
                SocialNetworkData.socialNetworkData()[3].name -> {
                    val email = Intent(Intent.ACTION_SENDTO)
                    email.data = Uri.parse("mailto:sharipovqosim007@gmail.com")
                    email.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                    email.putExtra(Intent.EXTRA_TEXT, "My Email message")
                    startActivity(email)
                }
            }
        }
        sourceAdapter.onItemClicked = {
            when (it) {
                SocialNetworkData.sourceData()[0].name -> {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/Qosimxoja/MealDb")
                        )
                    )
                }
                SocialNetworkData.sourceData()[1].name -> {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://gitlab.com/Qosim2004/MealDb")
                        )
                    )
                }
            }
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