package test.stayer.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class CrimeListFragment: Fragment() {

    lateinit var crimeRecyclerView: RecyclerView
    var adapter: CrimeAdapter? = null
    var positionOfChangedItem = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view)
        crimeRecyclerView.layoutManager = LinearLayoutManager(activity)

        updateUI()
        return view
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI(){
        val crimeLab = CrimeLab
        val crimes = crimeLab.crimesArrayList
        if (adapter == null) {
            adapter = CrimeAdapter(crimes)
            crimeRecyclerView.adapter = adapter
        } else {
            adapter!!.notifyItemChanged(positionOfChangedItem)
        }
    }

    inner class CrimeHolder(itemView: View):
            RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        private var tvTitle: TextView = itemView.findViewById(R.id.crime_title)
        private var tvDate: TextView = itemView.findViewById(R.id.crime_date)
        private var solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)
        private lateinit var crime: Crime

        fun bind(crime: Crime, position: Int) {
            this.crime = crime
            tvTitle.text = crime.title
            tvDate.text = DateFormat.format("HH:mm, dd.MM.yyyy", crime.date)
            solvedImageView.visibility = if (crime.solved) View.VISIBLE else View.GONE
        }

        override fun onClick(v: View?) {
            val intent = CrimeActivity.newIntent(activity!!, crime.id)
            positionOfChangedItem = adapterPosition
            startActivity(intent)
        }
    }

    inner class CrimeAdapter(var crimes: ArrayList<Crime>): RecyclerView.Adapter<CrimeHolder>() {

        override fun getItemViewType(position: Int): Int {
            return if (crimes[position].requiresPolice) {
                1
            } else {
                0
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val layoutInflater = LayoutInflater.from(activity)
            return if (viewType == 0) {
                CrimeHolder(layoutInflater.inflate(R.layout.list_item_crime, parent, false))
            } else {
                CrimeHolder(layoutInflater.inflate(R.layout.list_item_crime, parent, false))
            }
        }

        override fun getItemCount(): Int {
            return crimes.size
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.bind(crime, position)
        }


    }
}