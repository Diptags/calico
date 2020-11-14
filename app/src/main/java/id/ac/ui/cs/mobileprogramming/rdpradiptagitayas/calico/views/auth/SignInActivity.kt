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

class SignInActivity : AppCompatActivity() {

    // Variables
    private var formUsername: TextInputLayout? = null
    private var formPassword: TextInputLayout? = null
    private var sharedPreferences: SharedPreferences? = null

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

        context = this@SignInActivity
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(R.layout.signin_activity)

        // Check if user already logged in before
        sharedPreferences = this.getSharedPreferences("calico", MODE_PRIVATE)
        if (Preferences.checkLoggedIn(sharedPreferences)) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    // Action for Opening Sign Up Activity
    @Suppress("UNUSED_PARAMETER")
    fun openSignUpForm(view: View) {
        startActivity(Intent(this, SignUpActivity::class.java))
        finish()
    }

    // Form Validation (Username)
    private fun validateUsername(): Boolean {
        val value: String = formUsername?.editText?.text.toString()

        return if (value.isEmpty()) {
            formUsername?.error = applicationContext.resources.getString(R.string.form_empty_error)
            false
        } else {
            formUsername?.error = null
            formUsername?.isErrorEnabled = false
            true
        }
    }

    // Form Validation (Password)
    private fun validatePassword(): Boolean {
        val value: String = formPassword?.editText?.text.toString()

        return if (value.isEmpty()) {
            formPassword?.error = applicationContext.resources.getString(R.string.form_empty_error)
            false
        } else {
            formPassword?.error = null
            formPassword?.isErrorEnabled = false
            true
        }
    }

    // Action for User Login
    @Suppress("UNUSED_PARAMETER")
    fun signInUser(view: View) {

        formUsername = findViewById(R.id.signin_form_username)
        formPassword = findViewById(R.id.signin_form_password)

        val username = formUsername?.editText!!.text.toString()
        val password = formPassword?.editText!!.text.toString()

        if (isSignInFormValid()) {

            if (userViewModel.insertSignInDetails(context, username, password)) {
                Preferences.saveCredentials(sharedPreferences, username, password)

                Toast.makeText(
                    context,
                    applicationContext.resources.getString(R.string.auth_success),
                    Toast.LENGTH_LONG
                )
                    .show()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    applicationContext.resources.getString(R.string.wrong_credentials_error),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        } else return
    }

    // Form Validation
    private fun isSignInFormValid(): Boolean {
        return !(!validateUsername() or !validatePassword())
    }
}
