package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard.main.featured

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R


class FeaturedAdapter(featuredLocations: ArrayList<FeaturedHelperClass>) :
    RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder>() {
    var featuredLocations: ArrayList<FeaturedHelperClass>? = featuredLocations

    class FeaturedViewHolder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var desc: TextView

        init {
            //Hooks
            image = itemView.findViewById(R.id.featured_image)
            title = itemView.findViewById(R.id.featured_title)
            desc = itemView.findViewById(R.id.featured_desc)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_featured_design, parent, false)
        return FeaturedViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return featuredLocations!!.size;
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        val featuredHelperClass = featuredLocations!![position]
        holder.image.setImageResource(featuredHelperClass.image)
        holder.title.text = featuredHelperClass.title
        holder.desc.text = featuredHelperClass.description
    }
}