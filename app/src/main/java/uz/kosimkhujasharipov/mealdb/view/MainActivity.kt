package uz.kosimkhujasharipov.mealdb.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import uz.kosimkhujasharipov.mealdb.R
import uz.kosimkhujasharipov.mealdb.databinding.ActivityMainBinding
import uz.kosimkhujasharipov.mealdb.view.fragments.AboutUsFragment
import uz.kosimkhujasharipov.mealdb.view.fragments.AreaFragment
import uz.kosimkhujasharipov.mealdb.view.fragments.MealsCategoryFragment
import uz.kosimkhujasharipov.mealdb.view.fragments.IngredientFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // making status bar transparent
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = Color.TRANSPARENT
        // loading fragments
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MealsCategoryFragment()).commit()
        binding.bottomTab.setOnNavigationItemSelectedListener {
            val selectedFragment: Fragment = when (it.itemId) {
                R.id.category -> {
                    binding.bottomTab.setBackgroundResource(R.drawable.category_action_bar_1)
                    MealsCategoryFragment()
                }
                R.id.area -> {
                    binding.bottomTab.setBackgroundResource(R.drawable.category_action_bar_2)
                    AreaFragment()
                }
                R.id.ingredient -> {
                    binding.bottomTab.setBackgroundResource(R.drawable.category_action_bar_3)
                    IngredientFragment()
                }
                else -> {
                    binding.bottomTab.setBackgroundResource(R.drawable.category_action_bar_4)
                    AboutUsFragment()
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment).commit()
            true
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

}