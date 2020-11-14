package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R

// Kelas OnboardingAdapter yang extends ke adapternya si ViewPager
class OnboardingAdapter(var context: Context) : PagerAdapter() {

    private var layoutInflater: LayoutInflater? = null

    private val images: Array<Int> = arrayOf(
        R.drawable.logo_long_color,
        R.drawable.onboarding_slide_2,
        R.drawable.onboarding_slide_3,
        R.drawable.onboarding_slide_4
    )

    private val headings: Array<Int> = arrayOf(
        R.string.first_slide_title,
        R.string.second_slide_title,
        R.string.third_slide_title,
        R.string.fourth_slide_title
    )

    private val descriptions: Array<Int> = arrayOf(
        R.string.first_slide_desc,
        R.string.second_slide_desc,
        R.string.third_slide_desc,
        R.string.fourth_slide_desc
    )

    override fun getCount(): Int {
        return headings.size
    }

    override fun isViewFromObject(@NonNull view: View, @NonNull obj: Any): Boolean {
        return view == obj as ConstraintLayout
    }

    // Fungsi ini untuk mengambil desain XML dan memodifikasi resource didalamnya
    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
        layoutInflater =
            this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View = layoutInflater!!.inflate(R.layout.onboarding_slides, container, false)

        val imageView: ImageView = view.findViewById(R.id.slider_image)
        val heading: TextView = view.findViewById(R.id.slider_heading)
        val desc: TextView = view.findViewById(R.id.slider_desc)

        imageView.setImageResource(images[position])
        heading.setText(headings[position])
        desc.setText(descriptions[position])
        container.addView(view)
        return view
    }

    // Menghapus view
    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as ConstraintLayout)
    }
}