package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.intro

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Preferences
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.SPLASH_SCREEN_TIMER
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.auth.AuthActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.onboarding.OnboardingActivity


class IntroFragment : Fragment() {

    // Variables
    private var backgroundImage: ImageView? = null
    private var createdBy: TextView? = null

    // Shared Preferences
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hooks (Designs)
        backgroundImage = requireActivity().findViewById(R.id.background_image)
        createdBy = requireActivity().findViewById(R.id.created_by_line)

        sharedPreferences =
            requireContext().getSharedPreferences("calico", AppCompatActivity.MODE_PRIVATE)

        // Wait for few seconds before redirect to onboarding activity
        Handler(Looper.getMainLooper()).postDelayed({
            if (Preferences.checkFirstTime(sharedPreferences)) {
                Preferences.saveFirstTime(sharedPreferences)
                startActivity(Intent(requireContext(), OnboardingActivity::class.java))
            } else {
                startActivity(Intent(requireContext(), AuthActivity::class.java))
            }
            requireActivity().finish()
        }, SPLASH_SCREEN_TIMER.toLong())
    }
}
