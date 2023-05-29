package pnj.uts.ti.vidiseptriargalusmp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import pnj.uts.ti.vidiseptriargalusmp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
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