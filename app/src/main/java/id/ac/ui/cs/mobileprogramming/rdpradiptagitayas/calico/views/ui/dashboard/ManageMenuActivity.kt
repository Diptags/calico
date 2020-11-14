package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard.journal.JournalFragment
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard.other.OtherFragment
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard.profile.ProfileFragment


class ManageMenuActivity : AppCompatActivity() {

    private var chipNavigationBar: ChipNavigationBar? = null

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

        setContentView(R.layout.activity_menu)

        // Hooks
        chipNavigationBar = findViewById(R.id.bottom_nav_menu)
//        chipNavigationBar!!.setItemSelected(R.id.bottom_nav_journal, true)

        // Transaction for Fragment
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                JournalFragment()
            ).commit()
        bottomMenu()
    }

    private fun bottomMenu() {
        chipNavigationBar!!.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                var fragment: Fragment? = null
//                when (id) {
//                    R.id.bottom_nav_journal -> fragment =
//                        JournalFragment()
//                    R.id.bottom_nav_profile -> fragment =
//                        ProfileFragment()
//                    R.id.bottom_nav_other -> fragment =
//                        OtherFragment()
//                }
                if (fragment != null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment).commit()
                }
            }
        })
    }
}
