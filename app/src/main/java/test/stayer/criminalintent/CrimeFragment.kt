package test.stayer.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import java.util.*

class CrimeFragment : Fragment() {

    lateinit var crime: Crime

    private lateinit var etTitle: EditText
    private lateinit var btnDetails: Button
    private lateinit var cbSolved: CheckBox

    companion object {

        const val ARG_CRIME_ID = "crime_id"

        fun newInstance(crimeID: UUID): CrimeFragment {
            val args = Bundle()
            args.putSerializable(ARG_CRIME_ID, crimeID)

            val fragment = CrimeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val crimeId = arguments!!.getSerializable(ARG_CRIME_ID) as UUID
        crime = CrimeLab.getCrime(crimeId)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_crime, container, false)

        etTitle = v.findViewById(R.id.et_title)
        btnDetails = v.findViewById(R.id.btn_details)
        cbSolved = v.findViewById(R.id.cb_solved)

        etTitle.setText(crime.title)
        cbSolved.isChecked = crime.solved

        etTitle.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }
        })

        btnDetails.text = crime.date.toString()
        btnDetails.isEnabled = false

        cbSolved.setOnCheckedChangeListener { _, isChecked ->
            crime.solved = isChecked
        }

        return v
    }
}