package com.bakiyem.surucu.project.activity.randevuEkle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.model.randevuSaat.Response4Saat
import com.bakiyem.surucu.project.utils.ext.regular
import com.bakiyem.surucu.project.utils.ext.semibold

typealias OnSelectedSaat =  (Response4Saat) -> Unit

class DataAdapter2(private val context: Context, private var saatList: MutableList<Response4Saat>) :
    RecyclerView.Adapter<DataAdapter2.ViewHolder>() {

    var onShopSelected: OnSelectedSaat? = null

    var selectedInnerPosition: Int
        get() {
            return saatList.indexOfFirst { it.isSelect!! }
        }
        set(newIndex) {
            saatList.forEachIndexed { index, category ->
                category.isSelect = index == newIndex
            }
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.row_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        viewHolder.tvAndroid.text = saatList[pos].saat

        if(saatList[pos].isSelect!!){
            viewHolder.tvAndroid.alpha = 1f
            viewHolder.tvAndroid.setTextColor(ContextCompat.getColor(context, R.color.green))
            viewHolder.tvAndroid.semibold()
        } else{
            viewHolder.tvAndroid.alpha = 0.5f
            viewHolder.tvAndroid.setTextColor(ContextCompat.getColor(context, R.color.textColor))
            viewHolder.tvAndroid.regular()
        }

        viewHolder.itemView.setOnClickListener {
            selectedInnerPosition = pos
            onShopSelected?.invoke(saatList[pos])
        }
    }

    override fun getItemCount(): Int {
        return saatList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAndroid: TextView = view.findViewById<View>(R.id.tv_android) as TextView



    }
}
