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
import uz.kosimkhujasharipov.mealdb.core.adapters.ingredientCategory.IngredientCategoryAdapter
import uz.kosimkhujasharipov.mealdb.core.models.meal.Category
import uz.kosimkhujasharipov.mealdb.core.models.mealsItem.Meal
import uz.kosimkhujasharipov.mealdb.core.presenter.Presenter
import uz.kosimkhujasharipov.mealdb.core.presenter.PresenterImp
import uz.kosimkhujasharipov.mealdb.databinding.CategoryFragmentBinding
import uz.kosimkhujasharipov.mealdb.view.MealsActivity

class IngredientFragment : Fragment(), Presenter.View {

    private lateinit var binding: CategoryFragmentBinding
    private val adapter = IngredientCategoryAdapter()
    private var presenter: Presenter.Presenter? = null

    companion object {
        const val DATA_KEY = "IngredientFragment"
        const val INTENT_KEY = "ingredient_intent_key"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // loading view
        binding = CategoryFragmentBinding.inflate(layoutInflater)
        // preparing action bar
        binding.actionBar.setBackgroundResource(R.drawable.category_action_bar_3)
        binding.actionBar.text = "Ingredients"
        //preparing list
        binding.list.adapter = adapter
        binding.list.layoutManager = GridLayoutManager(context, 2)
        // loading data
        presenter = PresenterImp(this, DATA_KEY, null, null, context)
        presenter?.loadData()
        // loading actions
        adapter.onItemClick = {
            val intent = Intent(context, MealsActivity::class.java)
            intent.putExtra(INTENT_KEY, it)
            startActivity(intent)
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
        adapter.data = ingredientCategoryData!!
    }

    override fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}