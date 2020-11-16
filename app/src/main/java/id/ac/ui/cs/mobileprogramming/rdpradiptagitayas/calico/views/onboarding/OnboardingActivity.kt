package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.onboarding

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelper
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.PERMISSION_ALL_CODE
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.auth.AuthActivity


@Suppress("DEPRECATION", "UNUSED_PARAMETER")
class OnboardingActivity : AppCompatActivity() {

    private var currentPosition = 0
    private var viewPager: ViewPager? = null
    private var dots: Array<TextView?>? = null
    private var dotsLayout: LinearLayout? = null

    private var prevButton: Button? = null
    private var nextButton: Button? = null
    private var getStartedButton: Button? = null
    private var onboardingAdapter: OnboardingAdapter? = null

    // Permissions
    private var PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GeneralHelper.enableFullScreen(this)

        askPermissions()
        setContentView(R.layout.onboarding_activity)

        // Hooks
        viewPager = findViewById(R.id.slider)
        dotsLayout = findViewById(R.id.dots)
        getStartedButton = findViewById(R.id.get_started_btn)
        nextButton = findViewById(R.id.next_btn)
        prevButton = findViewById(R.id.prev_button)

        // Memanggil Adapter
        onboardingAdapter =
            OnboardingAdapter(
                this
            )
        viewPager!!.adapter = onboardingAdapter

        // Memanggil listener untuk komponen ViewPager
        addDots(0)
        viewPager?.addOnPageChangeListener(changeListener)
    }

    private fun askPermissions() {
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_ALL_CODE && grantResults.isNotEmpty()) {
            val messageToUser =
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    this.getString(R.string.permission_granted)
                } else {
                    this.getString(R.string.permission_denied)
                }
            Toast.makeText(this, messageToUser, Toast.LENGTH_SHORT).show()
        }
    }

    fun nextSlide(view: View) {
        if (currentPosition < 4) {
            viewPager?.currentItem = currentPosition + 1
        }
    }

    fun prevSlide(view: View) {
        if (currentPosition > 0) {
            viewPager?.currentItem = currentPosition - 1
        }
    }

    fun start(view: View) {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    private fun addDots(position: Int) {
        dots = arrayOfNulls(4)
        dotsLayout?.removeAllViews()

        for (i in dots!!.indices) {
            dots!![i] = TextView(this)
            dots!![i]?.text = Html.fromHtml("&#8226;")
            dots!![i]?.textSize = 35F
            dotsLayout?.addView(dots!![i])
        }
        if (dots!!.isNotEmpty()) {
            dots!![position]!!.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        }
    }

    private var changeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            addDots(position)
            currentPosition = position
            if (position == 0) {
                prevButton?.visibility = View.INVISIBLE
            } else if (position == 3) {
                getStartedButton?.visibility = View.VISIBLE
                nextButton?.visibility = View.INVISIBLE
            } else {
                getStartedButton?.visibility = View.INVISIBLE
                nextButton?.visibility = View.VISIBLE
                prevButton?.visibility = View.VISIBLE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}
