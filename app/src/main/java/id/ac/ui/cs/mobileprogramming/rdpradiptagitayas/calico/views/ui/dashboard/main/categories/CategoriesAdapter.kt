package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.ui.dashboard.main.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R


class CategoriesAdapter(categoryNames: ArrayList<CategoriesHelperClass>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    var categoryNames: ArrayList<CategoriesHelperClass>? = categoryNames

    class CategoriesViewHolder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView

        init {
            //Hooks
            image = itemView.findViewById(R.id.categories_img)
            title = itemView.findViewById(R.id.categories_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_categories_design, parent, false)
        return CategoriesViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return categoryNames!!.size;
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val featuredHelperClass = categoryNames!![position]
        holder.image.setImageResource(featuredHelperClass.image)
        holder.title.text = featuredHelperClass.title
    }
}