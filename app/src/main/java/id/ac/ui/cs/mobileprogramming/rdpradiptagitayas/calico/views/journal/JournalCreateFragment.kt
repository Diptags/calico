package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Journal
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels.JournalViewModel
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.journal.detail.JournalDetailFragment
import kotlinx.android.synthetic.main.journal_create_fragment.*


class JournalCreateFragment : Fragment() {

    lateinit var journalViewModel: JournalViewModel

    private var formTitle: TextInputLayout? = null
    private var formDishType: RadioGroup? = null
    private var formSummary: TextInputLayout? = null
    private var formDescription: TextInputLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.journal_create_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        journalViewModel = ViewModelProvider(requireActivity()).get(JournalViewModel::class.java)

        journalSaveButton.setOnClickListener {
            renameTemporaryJournalImage()
            sendJournalInformation()

            Toast.makeText(
                requireContext(),
                requireContext().resources.getString(R.string.journal_create_success),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun renameTemporaryJournalImage() {
        //
    }

    private fun isJournalFormValid(): Boolean {
        return (validateTitle() or validateSummary() or validateDescription())
    }

    private fun validateTitle(): Boolean {
        val value: String = formTitle?.editText?.text.toString()

        return if (value.isEmpty()) {
            formTitle?.error = requireContext().resources.getString(R.string.form_empty_error)
            false
        } else if (value.length > 20) {
            formTitle?.error =
                requireContext().resources.getString(R.string.form_max_length_error)
            false
        } else {
            formTitle?.error = null
            formTitle?.isErrorEnabled = false
            true
        }
    }

    private fun validateSummary(): Boolean {
        val value: String = formSummary?.editText?.text.toString()

        return if (value.isEmpty()) {
            formSummary?.error = requireContext().resources.getString(R.string.form_empty_error)
            false
        } else if (value.length > 30) {
            formSummary?.error =
                requireContext().resources.getString(R.string.form_max_length_error)
            false
        } else {
            formSummary?.error = null
            formSummary?.isErrorEnabled = false
            true
        }
    }

    private fun validateDescription(): Boolean {
        val value: String = formDescription?.editText?.text.toString()

        return if (value.isEmpty()) {
            formDescription?.error = requireContext().resources.getString(R.string.form_empty_error)
            false
        } else if (value.length > 300) {
            formDescription?.error =
                requireContext().resources.getString(R.string.form_max_length_error)
            false
        } else {
            formDescription?.error = null
            formDescription?.isErrorEnabled = false
            true
        }
    }

    private fun compileJournalData(): Journal {
        val journalData = HashMap<String, String>()

        journalData["title"] = formTitle?.editText!!.text.toString()
        journalData["summary"] = formSummary?.editText!!.text.toString()
        journalData["description"] = formDescription?.editText!!.text.toString()

        val radioId = formDishType?.checkedRadioButtonId!!
        val radioView: View = requireActivity().findViewById(radioId)

        val radioType: String = if (formDishType!!.indexOfChild(radioView) == 0) "food"
        else "beverage"

        val journal = Journal(
            0,
            formTitle?.editText!!.text.toString(),
            radioType,
            formSummary?.editText!!.text.toString(),
            formDescription?.editText!!.text.toString()
        )
        return journal
    }

    private fun sendJournalInformation() {
        formTitle = requireActivity().findViewById(R.id.journalTitleForm)
        formDishType = requireActivity().findViewById(R.id.journalDishForm)
        formSummary = requireActivity().findViewById(R.id.journalSummaryForm)
        formDescription = requireActivity().findViewById(R.id.journalDescriptionForm)

        if (isJournalFormValid()) {
            val journal = compileJournalData()
            journalViewModel.journalMutableLiveData.value = null
            journalViewModel.addJournal(requireContext(), journal)
        }

        Toast.makeText(
            requireContext(),
            requireContext().resources.getString(R.string.journal_create_success),
            Toast.LENGTH_LONG
        ).show()

        changeFragmentToJournalDetailFragment()
    }

    private fun changeFragmentToJournalDetailFragment() {
        val nextFragment = JournalListFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.journal_container, nextFragment)
            .commit()
    }
}
