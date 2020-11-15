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
import kotlinx.android.synthetic.main.auth_signup_fragment.*


class AuthSignUpFragment : Fragment() {

    // Variables
    private var formName: TextInputLayout? = null
    private var formUsername: TextInputLayout? = null
    private var formEmail: TextInputLayout? = null
    private var formPhoneNo: TextInputLayout? = null
    private var formPassword: TextInputLayout? = null
    private var sharedPreferences: SharedPreferences? = null

    // ViewModel
    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.auth_signup_fragment, container, false)
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

        signInButtonOnsignUp.setOnClickListener {
            openSignInForm()
        }

        sigupButton.setOnClickListener {
            signUpUser()
        }
    }

    private fun openSignInForm() {
        val nextFragment = AuthSignInFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.auth_container, nextFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun validateName(): Boolean {
        val value: String = formName?.editText?.text.toString()

        return if (value.isEmpty()) {
            formName?.error = requireContext().resources.getString(R.string.form_empty_error)
            false
        } else {
            formName?.error = null
            formName?.isErrorEnabled = false
            true
        }
    }

    private fun validateUsername(): Boolean {
        val value: String = formUsername?.editText?.text.toString()

        return if (value.isEmpty()) {
            formUsername?.error = requireContext().resources.getString(R.string.form_empty_error)
            false
        } else if (value.length >= 20) {
            formUsername?.error =
                requireContext().resources.getString(R.string.username_length_error)
            false
        } else {
            formUsername?.error = null
            formUsername?.isErrorEnabled = false
            true
        }
    }

    private fun validateEmail(): Boolean {
        val value: String = formEmail?.editText?.text.toString()
        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        return if (value.isEmpty()) {
            formEmail?.error = requireContext().resources.getString(R.string.form_empty_error)
            false
        } else if (!value.matches(emailPattern)) {
            formEmail?.error = requireContext().resources.getString(R.string.email_format_error)
            false
        } else {
            formEmail?.error = null
            formEmail?.isErrorEnabled = false
            true
        }
    }

    private fun validatePhoneNo(): Boolean {
        val value: String = formPhoneNo?.editText?.text.toString()

        return if (value.isEmpty()) {
            formPhoneNo?.error = requireContext().resources.getString(R.string.form_empty_error)
            false
        } else {
            formPhoneNo?.error = null
            formPhoneNo?.isErrorEnabled = false
            true
        }
    }

    private fun validatePassword(): Boolean {
        val value: String = formPassword?.editText?.text.toString()

        return if (value.isEmpty()) {
            formPassword?.error = requireContext().resources.getString(R.string.form_empty_error)
            false
        } else if (value.length < 4) {
            formPassword?.error =
                requireContext().resources.getString(R.string.password_length_error)
            false
        } else {
            formPassword?.error = null
            formPassword?.isErrorEnabled = false
            true
        }
    }

    private fun isSignUpFormValid(): Boolean {
        return (validateName() or validateUsername() or validateEmail() or validatePhoneNo() or validatePassword())
    }

    private fun prepareUserData(): HashMap<String, String> {
        val userData = HashMap<String, String>()
        userData["name"] = formName?.editText!!.text.toString()
        userData["username"] = formUsername?.editText!!.text.toString()
        userData["email"] = formEmail?.editText!!.text.toString()
        userData["phoneNo"] = formPhoneNo?.editText!!.text.toString()
        userData["password"] = formPassword?.editText!!.text.toString()
        return userData
    }

    fun signUpUser() {

        formName = requireActivity().findViewById(R.id.signup_form_fullname)
        formUsername = requireActivity().findViewById(R.id.signup_form_username)
        formEmail = requireActivity().findViewById(R.id.signup_form_email)
        formPhoneNo = requireActivity().findViewById(R.id.signup_form_phone)
        formPassword = requireActivity().findViewById(R.id.signup_form_password)

        if (isSignUpFormValid()) {

            val userData = prepareUserData()
            userViewModel.insertSignUpDetails(requireContext(), userData)

            Toast.makeText(
                requireContext(),
                requireContext().resources.getString(R.string.auth_success),
                Toast.LENGTH_LONG
            ).show()

            Preferences.saveCredentials(
                sharedPreferences,
                userData["username"].toString(),
                userData["password"].toString()
            )

            startActivity(Intent(requireContext(), HomeActivity::class.java))
            requireActivity().finish()
        } else return
    }
}