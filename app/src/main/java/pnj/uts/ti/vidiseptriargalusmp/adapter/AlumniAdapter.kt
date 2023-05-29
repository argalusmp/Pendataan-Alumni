package pnj.uts.ti.vidiseptriargalusmp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pnj.uts.ti.vidiseptriargalusmp.R
import pnj.uts.ti.vidiseptriargalusmp.model.AlumniModel

class AlumniAdapter(listAlumni: List<AlumniModel>)
    : RecyclerView.Adapter<AlumniAdapter.AlumniViewHolder>()
{
    private var onItemClickListener: ((AlumniModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (AlumniModel) -> Unit) {
        onItemClickListener = listener
    }

    internal var listAlumni : List<AlumniModel> = ArrayList()
    init {
        this.listAlumni = listAlumni
    }

    inner class AlumniViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val namaTV: TextView = view.findViewById(R.id.namaAlumniTV)
        val nikTV : TextView = view.findViewById(R.id.nikTV)

        init {
            view.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val alumni = listAlumni[position]
                    onItemClickListener?.invoke(alumni)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumniViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_alumni,parent,false)
        return AlumniViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlumniViewHolder, position: Int) {
        val alumni = listAlumni[position]
        holder.namaTV.text = alumni.nama_alumni
        holder.nikTV.text = alumni.nik

    }

    override fun getItemCount(): Int {
       return listAlumni.size
    }



}