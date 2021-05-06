package com.bakiyem.surucu.project.activity.galeri

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bakiyem.surucu.project.R
import com.squareup.picasso.Picasso

class GalleryAdapter(ctx: Context, listener: (DataModel) -> Unit) : RecyclerView.Adapter<ImageViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(ctx)
    private val picasso: Picasso = Picasso.get()
    private var items: MutableList<DataModel> = mutableListOf()
    private var galeriItemClickListener: (DataModel) -> Unit = listener

    val spanSizeLookup: GridLayoutManager.SpanSizeLookup by lazy {
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return items[position].columns
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].columns
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(inflater.inflate(R.layout.holder_galeri_item, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = items[position]
        picasso.load(item.resim)
            .fit()
            .centerCrop()
            .into(holder.image)

        holder.clGaleriItemRoot.setOnClickListener {
            galeriItemClickListener.invoke(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: MutableList<DataModel>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.iv_galeriImage)
    val clGaleriItemRoot: ConstraintLayout = itemView.findViewById(R.id.cl_galeriItemRoot)


}
