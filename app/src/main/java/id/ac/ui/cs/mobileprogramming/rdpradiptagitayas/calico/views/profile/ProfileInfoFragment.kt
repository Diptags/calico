package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.profile

import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.PROFILE_IMAGE_NAME
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Preferences
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.profile_info_fragment.*
import java.io.File


class ProfileInfoFragment : Fragment() {

    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        showProfileImage()
        getSignedInUser()

        profileChangeButton.setOnClickListener {
            changeFragmentToProfileEditFragment()
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
            userViewModel.getUserByUsername(requireContext(), username)
                ?.observe(requireActivity(), Observer {
                    try {
                        if (it != null) {
                            profileFullName?.text = it.name
                            profileUsername?.text = it.username
                            profileFullNameData?.text = it.name
                            profileUsernameData?.text = it.username
                            profileEmailData?.text = it.email
                            profilePhoneNumberData?.text = it.phoneNo
                        }
                    } catch (e: IllegalStateException) {
                    }
                })
        }
    }

    private fun changeFragmentToProfileEditFragment() {
        val nextFragment = ProfileEditFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.profile_container, nextFragment)
            .addToBackStack(null)
            .commit()
    }
}
