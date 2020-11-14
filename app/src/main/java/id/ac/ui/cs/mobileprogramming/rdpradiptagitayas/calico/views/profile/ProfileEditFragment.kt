package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.User
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.*
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.profile_edit_fragment.*
import java.io.File
import java.io.IOException


class ProfileEditFragment : Fragment() {

    lateinit var userViewModel: UserViewModel

    // Editing Form
    private var formFullName: TextInputEditText? = null
    private var formUsername: TextInputEditText? = null
    private var formEmail: TextInputEditText? = null
    private var formPhoneNo: TextInputEditText? = null

    private var signedInUser: User? = null

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
            getProfileImageFile()
        }

        profileUpdateButton.setOnClickListener {
            renameTemporaryProfileImage()
            sendProfileInformation()

            Toast.makeText(
                requireContext(),
                requireContext().resources.getString(R.string.profile_update_success),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun showProfileImage() {
        val profileImageFile: File = Helpers.createImageFile(requireContext(), PROFILE_IMAGE_NAME)
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
            userViewModel.getSignedInUser(requireContext(), username)
                ?.observe(requireActivity(), Observer {
                    if (it != null) {
                        signedInUser = it
                        formFullName?.setText(it.name)
                        formUsername?.setText(it.username)
                        formEmail?.setText(it.email)
                        formPhoneNo?.setText(it.phoneNo)
                    }
                })
        }
    }

    private fun prepareFormData() {
        formFullName = requireActivity().findViewById(R.id.profileFullNameForm)
        formUsername = requireActivity().findViewById(R.id.profileUsernameForm)
        formEmail = requireActivity().findViewById(R.id.profileEmailForm)
        formPhoneNo = requireActivity().findViewById(R.id.profilePhoneNumberForm)
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun getProfileImageFile() {

        val photoFile = try {
            Helpers.createImageFile(requireContext(), PROFILE_IMAGE_NAME_TEMP)
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
        Helpers.createImageFile(requireContext(), PROFILE_IMAGE_NAME_TEMP)
            .renameTo(
                Helpers.createImageFile(requireContext(), PROFILE_IMAGE_NAME)
            )
        return true
    }

    private fun sendProfileInformation() {
        val updatedUserProfile = User(
            signedInUser!!.userId,
            formFullName?.text.toString(),
            formUsername?.text.toString(),
            formEmail?.text.toString(),
            formPhoneNo?.text.toString(),
            signedInUser!!.password
        )
        userViewModel.updateUserInformation(requireContext(), updatedUserProfile)

        val sharedPreferences : SharedPreferences =
            requireContext().getSharedPreferences("calico", AppCompatActivity.MODE_PRIVATE)
        Preferences.saveCredentials(
            sharedPreferences,
            formUsername?.text.toString(),
            signedInUser!!.password
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                profileImage.setImageURI(
                    Uri.parse(
                        Helpers.createImageFile(
                            requireContext(),
                            PROFILE_IMAGE_NAME_TEMP
                        ).absolutePath
                    )
                )
            }
        }
    }
}