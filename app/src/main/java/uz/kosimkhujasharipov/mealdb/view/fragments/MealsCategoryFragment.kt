package uz.kosimkhujasharipov.mealdb.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import uz.kosimkhujasharipov.mealdb.core.adapters.mealsCategory.MealsCategoryAdapter
import uz.kosimkhujasharipov.mealdb.core.models.meal.Category
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.Meal
import uz.kosimkhujasharipov.mealdb.core.presenter.Presenter
import uz.kosimkhujasharipov.mealdb.core.presenter.PresenterImp
import uz.kosimkhujasharipov.mealdb.databinding.CategoryFragmentBinding
import uz.kosimkhujasharipov.mealdb.view.MealsActivity

class MealsCategoryFragment : Fragment(), Presenter.View {

    private lateinit var binding: CategoryFragmentBinding
    private var presenter: Presenter.Presenter? = null
    private val adapter = MealsCategoryAdapter()

    companion object {
        var IMP_KEY = "MealsFragment"
        const val INTENT_KEY = "meals_intent_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = CategoryFragmentBinding.inflate(layoutInflater)
        binding.list.adapter = adapter
        binding.list.layoutManager = GridLayoutManager(context, 2)
        presenter = PresenterImp(this, IMP_KEY, null, null, context)
        presenter?.loadData()
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
        adapter.data = categoryData!!
    }

    override fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

}