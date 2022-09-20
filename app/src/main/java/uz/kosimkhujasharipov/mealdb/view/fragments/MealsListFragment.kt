package uz.kosimkhujasharipov.mealdb.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import uz.kosimkhujasharipov.mealdb.R
import uz.kosimkhujasharipov.mealdb.core.adapters.meals.MealsAdapter
import uz.kosimkhujasharipov.mealdb.core.models.meal.Category
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.Meal
import uz.kosimkhujasharipov.mealdb.core.presenter.Presenter
import uz.kosimkhujasharipov.mealdb.core.presenter.PresenterImp
import uz.kosimkhujasharipov.mealdb.databinding.MealsListFragmentLayoutBinding
import uz.kosimkhujasharipov.mealdb.view.MealsActivity

class MealsListFragment : Fragment(), Presenter.View {

    private lateinit var binding: MealsListFragmentLayoutBinding
    private val adapter = MealsAdapter()
    private var presenter: Presenter.Presenter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // setting view
        binding = MealsListFragmentLayoutBinding.inflate(layoutInflater)
        // preparing list
        binding.list.adapter = adapter
        binding.list.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        // getting intent value
        if (activity?.intent?.getStringExtra(MealsCategoryFragment.INTENT_KEY) != null) {
            val intent = activity?.intent?.getStringExtra(MealsCategoryFragment.INTENT_KEY)
            presenter = PresenterImp(
                view = this,
                key = MealsActivity.DATA_KEY,
                value = intent,
                fromWhere = 1,
                context = context
            )
            binding.title.text = intent
        } else if (activity?.intent?.getStringExtra(AreaFragment.INTENT_KEY) != null) {
            val intent = activity?.intent?.getStringExtra(AreaFragment.INTENT_KEY)
            presenter = PresenterImp(
                view = this,
                key = MealsActivity.DATA_KEY,
                value = intent,
                fromWhere = 2,
                context = context
            )
            binding.title.text = intent
        } else if (activity?.intent?.getStringExtra(IngredientFragment.INTENT_KEY) != null) {
            val intent = activity?.intent?.getStringExtra(IngredientFragment.INTENT_KEY)
            presenter = PresenterImp(
                view = this,
                key = MealsActivity.DATA_KEY,
                value = intent,
                fromWhere = 3,
                context = context
            )
        }
        // loading data
        presenter?.loadData()
        // loading actions
        binding.refreshLayout.setOnRefreshListener {
            presenter?.refreshData()
        }
        binding.back.setOnClickListener {
            activity?.finish()
        }
        binding.search.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }
        adapter.onItemClick = {
            val action = MealsListFragmentDirections.actionMealsListFragmentToMealsInfoFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
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
        if (mealData != null) {
            adapter.data = mealData
        } else {
            activity?.onBackPressed()
            Toast.makeText(context, "Data does not exists", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}