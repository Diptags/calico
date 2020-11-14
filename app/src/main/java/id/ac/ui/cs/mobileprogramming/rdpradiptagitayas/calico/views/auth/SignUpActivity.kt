package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Preferences
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels.UserViewModel
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home.HomeActivity


class SignUpActivity : AppCompatActivity() {

    // Variables
    private var formName: TextInputLayout? = null
    private var formUsername: TextInputLayout? = null
    private var formEmail: TextInputLayout? = null
    private var formPhoneNo: TextInputLayout? = null
    private var formPassword: TextInputLayout? = null
    private var sharedPreferences: SharedPreferences? = null

    // ViewModel
    lateinit var userViewModel: UserViewModel
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Remove Status Bar (Full Screen App)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        context = this@SignUpActivity
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(R.layout.signup_activity)

        // Check if user already logged in before
        sharedPreferences = this.getSharedPreferences("calico", MODE_PRIVATE)
        if (Preferences.checkLoggedIn(sharedPreferences)) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    // Action for Opening Sign In Activity
    @Suppress("UNUSED_PARAMETER")
    fun openSignInForm(view: View) {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    // Form Validation (Full Name)
    private fun validateName(): Boolean {
        val value: String = formName?.editText?.text.toString()

        return if (value.isEmpty()) {
            formName?.error = applicationContext.resources.getString(R.string.form_empty_error)
            false
        } else {
            formName?.error = null
            formName?.isErrorEnabled = false
            true
        }
    }

    // Form Validation (Username)
    private fun validateUsername(): Boolean {
        val value: String = formUsername?.editText?.text.toString()

        return if (value.isEmpty()) {
            formUsername?.error = applicationContext.resources.getString(R.string.form_empty_error)
            false
        } else if (value.length >= 10) {
            formUsername?.error =
                applicationContext.resources.getString(R.string.username_length_error)
            false
        } else {
            formUsername?.error = null
            formUsername?.isErrorEnabled = false
            true
        }
    }

    // Form Validation (E-mail)
    private fun validateEmail(): Boolean {
        val value: String = formEmail?.editText?.text.toString()
        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        return if (value.isEmpty()) {
            formEmail?.error = applicationContext.resources.getString(R.string.form_empty_error)
            false
        } else if (!value.matches(emailPattern)) {
            formEmail?.error = applicationContext.resources.getString(R.string.email_format_error)
            false
        } else {
            formEmail?.error = null
            formEmail?.isErrorEnabled = false
            true
        }
    }

    // Form Validation (Phone Number)
    private fun validatePhoneNo(): Boolean {
        val value: String = formPhoneNo?.editText?.text.toString()

        return if (value.isEmpty()) {
            formPhoneNo?.error = applicationContext.resources.getString(R.string.form_empty_error)
            false
        } else {
            formPhoneNo?.error = null
            formPhoneNo?.isErrorEnabled = false
            true
        }
    }

    // Form Validation (Password)
    private fun validatePassword(): Boolean {
        val value: String = formPassword?.editText?.text.toString()

        return if (value.isEmpty()) {
            formPassword?.error = applicationContext.resources.getString(R.string.form_empty_error)
            false
        } else if (value.length < 4) {
            formPassword?.error =
                applicationContext.resources.getString(R.string.password_length_error)
            false
        } else {
            formPassword?.error = null
            formPassword?.isErrorEnabled = false
            true
        }
    }

    // Action for User Registration
    @Suppress("UNUSED_PARAMETER")
    fun signUpUser(view: View) {

        formName = findViewById(R.id.signup_form_fullname)
        formUsername = findViewById(R.id.signup_form_username)
        formEmail = findViewById(R.id.signup_form_email)
        formPhoneNo = findViewById(R.id.signup_form_phone)
        formPassword = findViewById(R.id.signup_form_password)

        if (isSignUpFormValid()) {

            val userData = prepareUserData()
            userViewModel.insertSignUpDetails(context, userData)

            Toast.makeText(
                context,
                context.resources.getString(R.string.auth_success),
                Toast.LENGTH_LONG
            ).show()

            Preferences.saveCredentials(
                sharedPreferences,
                userData["username"].toString(),
                userData["password"].toString()
            )

            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        } else return
    }

    // Form Validation
    private fun isSignUpFormValid(): Boolean {
        return (validateName() or validateUsername() or validateEmail() or validatePhoneNo() or validatePassword())
    }

    // Prepare data to be sent
    private fun prepareUserData(): HashMap<String, String> {
        val userData = HashMap<String, String>()
        userData["name"] = formName?.editText!!.text.toString()
        userData["username"] = formUsername?.editText!!.text.toString()
        userData["email"] = formEmail?.editText!!.text.toString()
        userData["phoneNo"] = formPhoneNo?.editText!!.text.toString()
        userData["password"] = formPassword?.editText!!.text.toString()
        return userData
    }
}
