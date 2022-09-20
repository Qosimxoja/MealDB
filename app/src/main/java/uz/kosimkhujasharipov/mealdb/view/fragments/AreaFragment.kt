package uz.kosimkhujasharipov.mealdb.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import uz.kosimkhujasharipov.mealdb.R
import uz.kosimkhujasharipov.mealdb.core.adapters.areaCategory.AreaCategoryAdapter
import uz.kosimkhujasharipov.mealdb.core.models.meal.Category
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.Meal
import uz.kosimkhujasharipov.mealdb.core.presenter.Presenter
import uz.kosimkhujasharipov.mealdb.core.presenter.PresenterImp
import uz.kosimkhujasharipov.mealdb.databinding.CategoryFragmentBinding
import uz.kosimkhujasharipov.mealdb.view.MealsActivity

class AreaFragment : Fragment(), Presenter.View {

    private lateinit var binding: CategoryFragmentBinding
    private val adapter = AreaCategoryAdapter()
    private var presenter: Presenter.Presenter? = null
    private val bundle = Bundle()

    companion object {
        var DATA_KEY = "AreaFragment"
        const val INTENT_KEY = "area_intent_key"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = CategoryFragmentBinding.inflate(layoutInflater)
        // preparing list
        binding.list.adapter = adapter
        binding.list.layoutManager = GridLayoutManager(context, 2)
        // changing details of action bar
        binding.actionBar.setBackgroundResource(R.drawable.category_action_bar_2)
        binding.actionBar.text = "Areas"
        // loading data
        presenter = PresenterImp(this, DATA_KEY, null, null, context)
        presenter?.loadData()
        // loading actions
        binding.refreshLayout.setOnRefreshListener {
            presenter?.refreshData()
        }
        adapter.onItemClick = {
            val intent = Intent(context, MealsActivity::class.java)
            intent.putExtra(INTENT_KEY, it)
            startActivity(intent)
        }
    }

    override fun dataState(isLoading: Boolean) {
        binding.refreshLayout.isRefreshing = isLoading
    }

    override fun showData(
        categoryData: ArrayList<Category>?,
        mealData: ArrayList<Meal>?,
        areaCategoryData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.area.Meal>?,
        ingredientCategoryData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.ingredient.Meal>?,
        mealsInfoData: ArrayList<uz.kosimkhujasharipov.mealdb.core.models.mealsInfo.Meal>?
    ) {
        adapter.data = areaCategoryData!!
    }

    override fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}