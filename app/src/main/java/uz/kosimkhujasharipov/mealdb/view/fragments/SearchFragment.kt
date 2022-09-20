package uz.kosimkhujasharipov.mealdb.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import uz.kosimkhujasharipov.mealdb.core.adapters.search.SearchListAdapter
import uz.kosimkhujasharipov.mealdb.core.models.meal.Category
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.Meal
import uz.kosimkhujasharipov.mealdb.core.presenter.Presenter
import uz.kosimkhujasharipov.mealdb.core.presenter.PresenterImp
import uz.kosimkhujasharipov.mealdb.databinding.FragmentSearchBinding
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : Fragment(), Presenter.View {

    private lateinit var binding: FragmentSearchBinding
    private var presenter: Presenter.Presenter? = null
    private val adapter = SearchListAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // loading view
        binding = FragmentSearchBinding.inflate(layoutInflater)
        // preparing list
        binding.searchedList.adapter = adapter
        binding.searchedList.layoutManager = GridLayoutManager(context, 4)
        // load data
        binding.input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            var timer = Timer()
            val DELAY: Long = 1000

            override fun afterTextChanged(p0: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        presenter = PresenterImp(
                            view = this@SearchFragment,
                            key = MealsInfoFragment.DATA_KEY,
                            value = p0.toString(),
                            fromWhere = null,
                            context = context
                        )
                        presenter?.loadData()
                    }
                }, DELAY)
            }
        })
        // loading actions
        binding.back.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun dataState(isLoading: Boolean) {}

    override fun showData(
        categoryData: ArrayList<Category>?,
        mealData: ArrayList<Meal>?,
        areaCategoryData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.area.Meal>?,
        ingredientCategoryData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.ingredient.Meal>?,
        mealsInfoData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.Meal>?
    ) {
        adapter.data = mealsInfoData!!
    }

    override fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE).show()
    }

}