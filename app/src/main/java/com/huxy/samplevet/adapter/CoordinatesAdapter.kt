package com.huxy.samplevet.adapter

import android.app.Application
import android.content.Context
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.huxy.samplevet.R
import com.huxy.samplevet.data.offline.Coordinates
import com.huxy.samplevet.repository.CoordinatesRepository

import java.util.ArrayList

 class CoordinatesAdapter(
    private var mCoordinates: List<Coordinates>?,
    private val mContext: Context,
    private val listener: OnItemClickListener,
    private val application: Application
) : RecyclerView.Adapter<CoordinatesAdapter.CoordinatesViewHolder>(), Filterable {
    private var mFilteredList: List<Coordinates>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoordinatesViewHolder {

        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.items_coordinates, parent, false)

        return CoordinatesViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CoordinatesViewHolder, position: Int) {

        val coord = mCoordinates!![position]
        holder.place_name.text = coord.getmUPlaceName()
        holder.bind(mCoordinates!![position], listener)

        holder.delete.setOnClickListener { v ->
            val coordinatesRepository = CoordinatesRepository(application)
            coordinatesRepository.deleteSingle(coord.mId)
            Toast.makeText(v.context, "Bookmarked city, deleted successfully", Toast.LENGTH_LONG).show()
        }


    }


    fun setCoordinates(coordinates: List<Coordinates>) {
        mCoordinates = coordinates
        notifyDataSetChanged()
    }

    fun filterList(filterdNames: List<Coordinates>) {
        this.mCoordinates = filterdNames
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return mCoordinates!!.size
    }

    override fun getFilter(): Filter {


        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {

                val charString = charSequence.toString()

                if (charString.isEmpty()) {

                    mFilteredList = mCoordinates
                } else {

                    val filteredList = ArrayList<Coordinates>()

                    for (coordinates in mCoordinates!!) {

                        if (coordinates.getmUPlaceName().toLowerCase().contains(charString)) {

                            filteredList.add(coordinates)
                        }
                    }

                    mFilteredList = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = mFilteredList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                mFilteredList = filterResults.values as ArrayList<Coordinates>
                notifyDataSetChanged()
            }
        }
    }


    inner class CoordinatesViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        val place_name: TextView = view.findViewById(R.id.place_name)
        val delete: TextView = view.findViewById(R.id.delete)

        fun bind(item: Coordinates, listener: OnItemClickListener) {

            itemView.setOnClickListener { listener.onItemClicked(item) }
        }
    }

}


