package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Preferences
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels.UserViewModel
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home.HomeActivity
import kotlinx.android.synthetic.main.auth_signin_fragment.*


class AuthSignInFragment : Fragment() {

    // Variables
    private var formUsername: TextInputLayout? = null
    private var formPassword: TextInputLayout? = null
    private var sharedPreferences: SharedPreferences? = null

    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.auth_signin_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        // Check if user already logged in before
        sharedPreferences =
            requireContext().getSharedPreferences("calico", AppCompatActivity.MODE_PRIVATE)
        if (Preferences.checkLoggedIn(sharedPreferences)) {
            startActivity(Intent(requireContext(), HomeActivity::class.java))
            requireActivity().finish()
        }

        signUpButtonOnsignIn.setOnClickListener {
            openSignUpForm()
        }

        signInButton.setOnClickListener {
            signInUser()
        }
    }

    private fun openSignUpForm() {
        val nextFragment = AuthSignUpFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.auth_container, nextFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun validateUsername(): Boolean {
        val value: String = formUsername?.editText?.text.toString()

        return if (value.isEmpty()) {
            formUsername?.error = requireContext().resources.getString(R.string.form_empty_error)
            false
        } else {
            formUsername?.error = null
            formUsername?.isErrorEnabled = false
            true
        }
    }

    private fun validatePassword(): Boolean {
        val value: String = formPassword?.editText?.text.toString()

        return if (value.isEmpty()) {
            formPassword?.error = requireContext().resources.getString(R.string.form_empty_error)
            false
        } else {
            formPassword?.error = null
            formPassword?.isErrorEnabled = false
            true
        }
    }

    private fun isSignInFormValid(): Boolean {
        return (validateUsername() and validatePassword())
    }

    private fun signInUser() {

        formUsername = requireActivity().findViewById(R.id.signin_form_username)
        formPassword = requireActivity().findViewById(R.id.signin_form_password)

        val username = formUsername?.editText!!.text.toString()
        val password = formPassword?.editText!!.text.toString()

        if (isSignInFormValid()) {

            if (userViewModel.checkIsUserRegistered(requireContext(), username, password)) {
                Preferences.saveCredentials(sharedPreferences, username, password)

                Toast.makeText(
                    requireContext(),
                    requireContext().resources.getString(R.string.auth_success),
                    Toast.LENGTH_LONG
                )
                    .show()

                startActivity(Intent(requireContext(), HomeActivity::class.java))
                requireActivity().finish()
            } else {
                Toast.makeText(
                    requireContext(),
                    requireContext().resources.getString(R.string.wrong_credentials_error),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        } else return
    }
}
