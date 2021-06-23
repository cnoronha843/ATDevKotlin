package br.edu.infnet.gymcardtraning.Onclick

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition


interface OnItemClickListener{
    fun onItemCliecked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onCliclListener: OnItemClickListener){
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener{
        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view?.setOnClickListener ({
                val holder = getChildViewHolder(view)
                onCliclListener.onItemCliecked(holder.adapterPosition,view)
            })
        }
    })
}