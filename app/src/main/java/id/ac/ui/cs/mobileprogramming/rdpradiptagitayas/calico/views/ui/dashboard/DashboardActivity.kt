package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard.main.categories.CategoriesAdapter
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard.main.categories.CategoriesHelperClass
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard.main.featured.FeaturedAdapter
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard.main.featured.FeaturedHelperClass


class DashboardActivity : AppCompatActivity() {

    private var featuredRecycler: RecyclerView? = null
    private var categoriesRecycler: RecyclerView? = null

    var adapter: RecyclerView.Adapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        setContentView(R.layout.activity_dashboard)

        // Hooks
        featuredRecycler = findViewById(R.id.featured_recycler)
        categoriesRecycler = findViewById(R.id.categories_recycler);

        featuredRecycler()
        categoriesRecycler()
    }

    @Suppress("UNUSED_PARAMETER")
    fun openManageMenu(view: View) {
        startActivity(Intent(this, ManageMenuActivity::class.java))
    }

    // Bagian ini masih hardcoded untuk memasukkan data
    private fun featuredRecycler() {
        featuredRecycler!!.setHasFixedSize(true)
        featuredRecycler!!.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val featuredLocations: ArrayList<FeaturedHelperClass> = ArrayList()
        featuredLocations.add(
            FeaturedHelperClass(
                R.drawable.icon_bottom_nav_food,
                "Mcdonald's",
                "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"
            )
        )
        featuredLocations.add(
            FeaturedHelperClass(
                R.drawable.icon_bottom_nav_beverage,
                "Edenrobe",
                "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"
            )
        )
        adapter =
            FeaturedAdapter(
                featuredLocations
            )
        featuredRecycler!!.adapter = adapter
    }

    private fun categoriesRecycler() {

        categoriesRecycler!!.setHasFixedSize(true)
        categoriesRecycler!!.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val categoriesHelperClasses: ArrayList<CategoriesHelperClass> = ArrayList()
        categoriesHelperClasses.add(
            CategoriesHelperClass(
                R.drawable.icon_bottom_nav_food,
                "Education"
            )
        )
        categoriesHelperClasses.add(
            CategoriesHelperClass(
                R.drawable.icon_bottom_nav_beverage,
                "HOSPITAL"
            )
        )
        adapter = CategoriesAdapter(categoriesHelperClasses)
        categoriesRecycler!!.adapter = adapter
    }

}