package pnj.uts.ti.vidiseptriargalusmp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pnj.uts.ti.vidiseptriargalusmp.R
import pnj.uts.ti.vidiseptriargalusmp.model.ListData

class ListAdapter(private val ListBerita:ArrayList<ListData>)
    :RecyclerView.Adapter<ListAdapter.BeritaViewHolder>()
{
    lateinit var onItemClick : (ListData)->Unit

    class BeritaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val listImage: ImageView = itemView.findViewById(R.id.listImage)
        val listJudul: TextView = itemView.findViewById(R.id.listJudul)
        val listRingkasan: TextView = itemView.findViewById(R.id.listRingkasan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return BeritaViewHolder(view)
    }


    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = ListBerita[position]
        holder.listImage.setImageResource(berita.image)
        holder.listJudul.setText(berita.judul)
        holder.listRingkasan.setText(berita.ringkasan)

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(berita)
        }
    }

    override fun getItemCount(): Int {
        return ListBerita.size
    }
}