package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.material.textfield.TextInputLayout
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.User
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.*
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels.UserViewModel
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.misc.PermissionDeniedFragment
import kotlinx.android.synthetic.main.profile_edit_fragment.*
import java.io.File
import java.io.IOException


class ProfileEditFragment : Fragment() {

    lateinit var userViewModel: UserViewModel

    private var formName: TextInputLayout? = null
    private var formUsername: TextInputLayout? = null
    private var formEmail: TextInputLayout? = null
    private var formPhoneNo: TextInputLayout? = null

    private var signedInUser: User? = null

    // Permissions
    private var PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        showProfileImage()
        getSignedInUser()
        prepareFormData()

        profileImage.setOnClickListener {

            // Bagian ini akan menanyakan permission terlebih dahulu, dan mengambil foto jika diizinkan.
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) +
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                getProfileImageFile()
            } else {
                // Apabila tidak diizinkan, maka akan menanyakan permission baru
                askPermissions()
            }
        }

        profileUpdateButton.setOnClickListener {
            renameTemporaryProfileImage()
            sendProfileInformation()
        }
    }

    private fun prepareFormData() {
        formName = requireActivity().findViewById(R.id.profileFullNameForm)
        formUsername = requireActivity().findViewById(R.id.profileUsernameForm)
        formEmail = requireActivity().findViewById(R.id.profileEmailForm)
        formPhoneNo = requireActivity().findViewById(R.id.profilePhoneNumberForm)
    }

    private fun showProfileImage() {
        val profileImageFile: File =
            GeneralHelpers.createImageFile(requireContext(), PROFILE_IMAGE_NAME)
        if (profileImageFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(profileImageFile.absolutePath)
            profileImage.setImageBitmap(myBitmap)
        } else {
            profileImage.setImageResource(R.drawable.profile_dummy)
        }
    }

    private fun getSignedInUser() {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("calico", AppCompatActivity.MODE_PRIVATE)
        val username: String? = Preferences.getSignedInUser(sharedPreferences)

        if (username != null) {
            userViewModel.getUserByUsername(requireContext(), username)
                ?.observe(requireActivity()) {
                    signedInUser = it
                    formName?.editText!!.setText(it.name)
                    formUsername?.editText!!.setText(it.username)
                    formEmail?.editText!!.setText(it.email)
                    formPhoneNo?.editText!!.setText(it.phoneNo)
                }
        }
    }

    private fun askPermissions() {
        // Request permission di fragment tidak menggunakan ActivityCompat.requestPermissions
        requestPermissions(PERMISSIONS, PERMISSION_ALL_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_ALL_CODE && grantResults.isNotEmpty()) {
            val messageToUser =
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    getProfileImageFile()
                    this.getString(R.string.permission_granted)
                } else {
                    changeFragmentToPermissionDeniedFragment()
                    this.getString(R.string.permission_denied)
                }
            GeneralHelpers.showToastMessage(requireContext(), messageToUser)
        }
    }

    private fun changeFragmentToPermissionDeniedFragment() {
        val nextFragment = PermissionDeniedFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.profile_container, nextFragment)
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun getProfileImageFile() {

        val photoFile = try {
            GeneralHelpers.createImageFile(requireContext(), PROFILE_IMAGE_NAME_TEMP)
        } catch (e: IOException) {
            e.printStackTrace()
            return
        }

        val photoUri: Uri = FileProvider.getUriForFile(
            requireContext(),
            requireActivity().packageName.toString() + ".provider",
            photoFile
        )

        val chooserIntent: Intent = prepareIntents(photoUri)
        startActivityForResult(chooserIntent, REQUEST_IMAGE_CODE)
    }

    private fun prepareIntents(photoUri: Uri): Intent {

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)

        val galleryIntent = Intent()
        galleryIntent.type = "image/*"
        galleryIntent.action = Intent.ACTION_GET_CONTENT

        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        chooserIntent.putExtra(Intent.EXTRA_INTENT, galleryIntent)
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Select Image Source")

        val intentArray = arrayOf(cameraIntent)
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
        return chooserIntent
    }

    private fun renameTemporaryProfileImage(): Boolean {
        GeneralHelpers.createImageFile(requireContext(), PROFILE_IMAGE_NAME_TEMP)
            .renameTo(
                GeneralHelpers.createImageFile(requireContext(), PROFILE_IMAGE_NAME)
            )
        return true
    }

    private fun isProfileFormValid(): Boolean {
        return (validateName() and validateUsername() and validateEmail() and validatePhoneNo())
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

    private fun compileUserData(): User {
        return User(
            signedInUser!!.userId,
            formName?.editText!!.text.toString(),
            formUsername?.editText!!.text.toString(),
            formEmail?.editText!!.text.toString(),
            formPhoneNo?.editText!!.text.toString(),
            signedInUser!!.password
        )
    }

    private fun sendProfileInformation() {
        if (isProfileFormValid()) {
            val user = compileUserData()
            userViewModel.updateUser(requireContext(), user)
            updateSharedPreferences()
            GeneralHelpers.showToastMessage(requireContext(), R.string.profile_update_success)
        } else return
    }

    private fun updateSharedPreferences() {
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("calico", AppCompatActivity.MODE_PRIVATE)
        Preferences.saveCredentials(
            sharedPreferences,
            formUsername?.editText!!.text.toString(),
            signedInUser!!.password
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                profileImage.setImageURI(
                    Uri.parse(
                        GeneralHelpers.createImageFile(
                            requireContext(),
                            PROFILE_IMAGE_NAME_TEMP
                        ).absolutePath
                    )
                )
            }
        }
    }
}
