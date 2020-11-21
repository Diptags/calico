package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.dish

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home.HomeActivity


class DishActivity : AppCompatActivity() {

    private var chipNavigationBar: ChipNavigationBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Helpers.enableFullScreen(this)
        setContentView(R.layout.dish_activity)

        chipNavigationBar = findViewById(R.id.bottom_nav_menu)
        chipNavigationBar!!.setItemSelected(R.id.bottom_nav_food, true)

        if (savedInstanceState == null) {
            changeFragmentToDishFoodFragment()
        }

        bottomNavbarMenu()
    }

    private fun changeFragmentToDishFoodFragment() {
        val nextFragment = DishListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.dish_fragment_container, nextFragment)
            .commit()
    }

    private fun bottomNavbarMenu() {
        chipNavigationBar!!.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                var fragment: Fragment? = null
                when (id) {
                    R.id.bottom_nav_food -> fragment =
                        DishListFragment()
                    R.id.bottom_nav_beverage -> fragment =
                        DishOnlineFragment()
                }
                if (fragment != null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.dish_fragment_container, fragment)
                        .commit()
                }
            }
        })
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
