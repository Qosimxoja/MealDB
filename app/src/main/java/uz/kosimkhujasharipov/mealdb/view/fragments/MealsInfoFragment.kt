package uz.kosimkhujasharipov.mealdb.view.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.material.snackbar.Snackbar
import uz.kosimkhujasharipov.mealdb.R
import uz.kosimkhujasharipov.mealdb.core.adapters.mealsInfo.MealsInfoAdapter
import uz.kosimkhujasharipov.mealdb.core.models.meal.Category
import uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.MealsInfoCategoryData
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.Meal
import uz.kosimkhujasharipov.mealdb.core.presenter.Presenter
import uz.kosimkhujasharipov.mealdb.core.presenter.PresenterImp
import uz.kosimkhujasharipov.mealdb.databinding.FragmentMealsInfoBinding

class MealsInfoFragment : Fragment(), Presenter.View {

    private lateinit var binding: FragmentMealsInfoBinding
    private val args: MealsInfoFragmentArgs by navArgs()
    private var presenter: Presenter.Presenter? = null
    private val adapter = MealsInfoAdapter()

    companion object {
        var DATA_KEY = "MealsInfoFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // loading view
        binding = FragmentMealsInfoBinding.inflate(layoutInflater)
        // preparing list
        binding.ingredientsList.adapter = adapter
        binding.ingredientsList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        // loading data
        presenter = PresenterImp(
            view = this,
            key = DATA_KEY,
            value = args.mealName,
            fromWhere = null,
            context = context
        )
        presenter?.loadData()
        // loading actions
        binding.refreshLayout.setOnRefreshListener {
            presenter?.refreshData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun dataState(isLoading: Boolean) {
        binding.refreshLayout.isRefreshing = isLoading
    }

    @SuppressLint("SetTextI18n")
    override fun showData(
        categoryData: ArrayList<Category>?,
        mealData: ArrayList<Meal>?,
        areaCategoryData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.area.Meal>?,
        ingredientCategoryData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.ingredient.Meal>?,
        mealsInfoData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.Meal>?
    ) {
        // image
        binding.image.load(mealsInfoData!![0].strMealThumb)
        // name, type
        binding.name.text = mealsInfoData[0].strMeal
        binding.type.text = "Type: ${mealsInfoData[0].strCategory}"
        // location
        val text = "Area: ${mealsInfoData[0].strArea}"
        val spannable: Spannable = SpannableString(text)
        spannable.setSpan(
            ForegroundColorSpan(Color.BLUE),
            "Area: ".length,
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.location.setText(spannable, TextView.BufferType.SPANNABLE)
        // videoContainer
        if (!mealsInfoData[0].strYoutube.isNullOrBlank()) {
            val youtubeId = buildString {
                for (i in mealsInfoData[0].strYoutube.length - 11 until mealsInfoData[0].strYoutube.length) {
                    append(mealsInfoData[0].strYoutube[i])
                }
            }
            val videoImages: ArrayList<String> = arrayListOf(
                "https://img.youtube.com/vi/$youtubeId/1.jpg",
                "https://img.youtube.com/vi/$youtubeId/2.jpg",
                "https://img.youtube.com/vi/$youtubeId/3.jpg",
            )
            binding.mainImage.load("https://img.youtube.com/vi/$youtubeId/hqdefault.jpg")
            binding.image1.load(videoImages[0])
            binding.image2.load(videoImages[1])
            binding.image3.load(videoImages[2])
            binding.videoContainer.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(mealsInfoData[0].strYoutube)
                    )
                )
            }
        } else {
            binding.youtubeText.visibility = View.GONE
            binding.videoContainer.visibility = View.GONE
            binding.videoImagesList.visibility = View.GONE
            binding.line2.visibility = View.GONE
        }
        // ingredients
        val data = ArrayList<MealsInfoCategoryData>()
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient1,
                mealsInfoData[0].strMeasure1
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient2,
                mealsInfoData[0].strMeasure2
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient3,
                mealsInfoData[0].strMeasure3
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient4,
                mealsInfoData[0].strMeasure4
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient5,
                mealsInfoData[0].strMeasure5
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient6,
                mealsInfoData[0].strMeasure6
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient7,
                mealsInfoData[0].strMeasure7
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient8,
                mealsInfoData[0].strMeasure8
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient9,
                mealsInfoData[0].strMeasure9
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient10,
                mealsInfoData[0].strMeasure10
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient11,
                mealsInfoData[0].strMeasure11
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient12,
                mealsInfoData[0].strMeasure12
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient13,
                mealsInfoData[0].strMeasure13
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient14,
                mealsInfoData[0].strMeasure14
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient15,
                mealsInfoData[0].strMeasure15
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient16,
                mealsInfoData[0].strMeasure16
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient17,
                mealsInfoData[0].strMeasure17
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient18,
                mealsInfoData[0].strMeasure18
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient19,
                mealsInfoData[0].strMeasure19
            ),
        )
        data.add(
            MealsInfoCategoryData(
                mealsInfoData[0].strIngredient20,
                mealsInfoData[0].strMeasure20
            ),
        )
        val sortedData = ArrayList<MealsInfoCategoryData>()
        for (position in 0 until data.size) {
            if (data[position].ingredient.toString().isNotBlank() &&
                data[position].ingredient != null
            ) {
                sortedData.add(data[position])
            }
        }
        adapter.data = sortedData

        // instruction
        binding.instructionText.text = mealsInfoData[0].strInstructions
    }

    override fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}