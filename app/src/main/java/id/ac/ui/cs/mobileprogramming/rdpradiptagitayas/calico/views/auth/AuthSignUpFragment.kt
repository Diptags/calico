package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.auth

import android.app.PendingIntent
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_ID
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.NOTIFICATION_CODE
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Preferences
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels.UserViewModel
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home.HomeActivity
import kotlinx.android.synthetic.main.auth_signup_fragment.*


class AuthSignUpFragment : Fragment() {

    lateinit var userViewModel: UserViewModel

    private var formName: TextInputLayout? = null
    private var formUsername: TextInputLayout? = null
    private var formEmail: TextInputLayout? = null
    private var formPhoneNo: TextInputLayout? = null
    private var formPassword: TextInputLayout? = null
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.auth_signup_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        prepareFormData()

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
            sendSignUpInformation()
        }
    }

    private fun prepareFormData() {
        formName = requireActivity().findViewById(R.id.signup_form_fullname)
        formUsername = requireActivity().findViewById(R.id.signup_form_username)
        formEmail = requireActivity().findViewById(R.id.signup_form_email)
        formPhoneNo = requireActivity().findViewById(R.id.signup_form_phone)
        formPassword = requireActivity().findViewById(R.id.signup_form_password)
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
                requireContext().resources.getString(R.string.form_max_length_error)
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
                requireContext().resources.getString(R.string.form_min_length_error)
            false
        } else {
            formPassword?.error = null
            formPassword?.isErrorEnabled = false
            true
        }
    }

    private fun isSignUpFormValid(): Boolean {
        return (validateName() and validateUsername() and validateEmail() and validatePhoneNo() and validatePassword())
    }

    private fun compileUserData(): HashMap<String, String> {
        val userData = HashMap<String, String>()
        userData["name"] = formName?.editText!!.text.toString()
        userData["username"] = formUsername?.editText!!.text.toString()
        userData["email"] = formEmail?.editText!!.text.toString()
        userData["phoneNo"] = formPhoneNo?.editText!!.text.toString()
        userData["password"] = formPassword?.editText!!.text.toString()
        return userData
    }

    private fun sendSignUpInformation() {
        if (isSignUpFormValid()) {
            val userData = compileUserData()
            userViewModel.addUser(requireContext(), userData)

            updateSharedPreferences(userData)
            showWelcomeNotification()

            Helpers.showToastMessage(requireContext(), R.string.auth_success)
            startActivity(Intent(requireContext(), HomeActivity::class.java))
            requireActivity().finish()

        } else return
    }

    private fun updateSharedPreferences(userData: HashMap<String, String>) {
        Preferences.saveCredentials(
            sharedPreferences,
            userData["username"].toString(),
            userData["password"].toString()
        )
    }

    private fun showWelcomeNotification() {
        val context = requireContext()
        val pendingIntent: PendingIntent = Helpers.prepareNotificationIntent(context)
        val notificationLargeIcon =
            BitmapFactory.decodeResource(context.resources, R.drawable.logo_color)

        val notificationBuilder = NotificationCompat.Builder(
            context,
            GENERAL_NOTIFICATION_CHANNEL_ID
        ).apply {
            setSmallIcon(R.drawable.logo_color)
            setLargeIcon(notificationLargeIcon)
            setContentIntent(pendingIntent)
            setContentTitle(context.resources.getString(R.string.welcome_notification_title))
            setContentText(context.resources.getString(R.string.welcome_notification_content))
            priority = NotificationCompat.PRIORITY_DEFAULT
            setAutoCancel(false)
        }
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_CODE, notificationBuilder.build())
    }
}
