package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Helpers.enableFullScreen(this)
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
