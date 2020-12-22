package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.misc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R

class PermissionDeniedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.permission_denied_fragment, container, false)
    }

}