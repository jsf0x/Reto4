package com.tgiraldo.retociclo4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VentaAdapter(private val datos: ArrayList<Venta>, private val clickListener: (Venta) -> Unit):
    RecyclerView.Adapter<VentaAdapter.TaskViewHolder>(){

    class TaskViewHolder(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.ventas_list_items, parent, false)
        return TaskViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val venta = datos[position]
        val textViewTask: TextView = holder.layout.findViewById(R.id.textViewTask)
        val textViewTime: TextView = holder.layout.findViewById(R.id.textViewTime)
        textViewTask.text = venta.venta
        textViewTime.text = venta.precio
        holder.layout.setOnClickListener { clickListener(venta) }
    }

    override fun getItemCount(): Int {
        return datos.size
    }


}