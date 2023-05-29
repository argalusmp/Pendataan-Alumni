package pnj.uts.ti.vidiseptriargalusmp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pnj.uts.ti.vidiseptriargalusmp.adapter.ListAdapter
import pnj.uts.ti.vidiseptriargalusmp.databinding.FragmentBeritaBinding
import pnj.uts.ti.vidiseptriargalusmp.model.ListData

class BeritaFragment : Fragment() {

    private lateinit var binding: FragmentBeritaBinding
    private lateinit var listAdapter: ListAdapter
    private lateinit var listData: ListData
    var dataArrayList = ArrayList<ListData?>()

    private lateinit var recyclerView:RecyclerView
    private lateinit var listBerita: ArrayList<ListData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding = FragmentBeritaBinding.inflate(inflater, container, false)
        val view = binding.root
        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        listBerita = ArrayList()

        listBerita.add(
            ListData(
            R.string.judul_berita_1,
            R.string.deskripsi_berita_1,
            R.string.ringkasan_berita_1,
            R.drawable.berita_1)
        )
        listBerita.add(ListData(
            R.string.judul_berita_2,
            R.string.deskripsi_berita_2,
            R.string.ringkasan_berita_2,
            R.drawable.berita_2))
        listBerita.add(ListData(
            R.string.judul_berita_3,
            R.string.deskripsi_berita_3,
            R.string.ringkasan_berita_3,
            R.drawable.berita_3))
        listBerita.add(ListData(
            R.string.judul_berita_4,
            R.string.deskripsi_berita_4,
            R.string.ringkasan_berita_4,
            R.drawable.berita_4))
        listBerita.add(ListData(
            R.string.judul_berita_5,
            R.string.deskripsi_berita_5,
            R.string.ringkasan_berita_5,
            R.drawable.berita_5))
        listBerita.add(ListData(
            R.string.judul_berita_6,
            R.string.deskripsi_berita_6,
            R.string.ringkasan_berita_6,
            R.drawable.berita_6))
        listBerita.add(ListData(
            R.string.judul_berita_7,
            R.string.deskripsi_berita_7,
            R.string.ringkasan_berita_7,
            R.drawable.berita_7))
        listBerita.add(ListData(
            R.string.judul_berita_8,
            R.string.deskripsi_berita_8,
            R.string.ringkasan_berita_8,
            R.drawable.berita_2))
        listBerita.add(ListData(
            R.string.judul_berita_9,
            R.string.deskripsi_berita_9,
            R.string.ringkasan_berita_9,
            R.drawable.berita_3))
        listBerita.add(ListData(
            R.string.judul_berita_10,
            R.string.deskripsi_berita_10,
            R.string.ringkasan_berita_10,
            R.drawable.berita_1))

        listAdapter = ListAdapter(listBerita)
        recyclerView.adapter = listAdapter

        listAdapter.onItemClick = {
            val intent = Intent(requireActivity(),DetailBeritaActivity::class.java)
            intent.putExtra("berita",it)
            startActivity(intent)
        }

        return view



    }



    //Untuk Mengenerate Option Menu yang sudah dibuat di res/menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.optionmenu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    //Untuk selected Option
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sharedPreferences = activity?.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)

        return when (item.itemId){
            R.id.tambahData -> {
                startActivity(Intent(context,TambahData::class.java))
                true
            }
            R.id.dataAlumni -> {
                startActivity(Intent(context,DataAlumni::class.java))
                true
            }
            R.id.Logout -> {
                val editor = sharedPreferences?.edit()
                editor?.clear()
                editor?.apply()

                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}