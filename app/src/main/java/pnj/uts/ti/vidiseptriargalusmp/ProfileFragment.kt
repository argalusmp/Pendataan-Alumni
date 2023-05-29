package pnj.uts.ti.vidiseptriargalusmp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val emailDisplay = view.findViewById<TextView>(R.id.emailTextView)
        val namaDisplay = view.findViewById<TextView>(R.id.namaTextView)
        val kelasDisplay = view.findViewById<TextView>(R.id.kelasTextView)
        val nimDisplay = view.findViewById<TextView>(R.id.nimTextView)
        val btnLogout = view.findViewById<Button>(R.id.btnLogoutProfil)

        val sharedPreferences = requireActivity().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)


        val email = sharedPreferences.getString("Email","").toString()
        val nim  = sharedPreferences.getString("Nim","").toString()
        val password  = sharedPreferences.getString("Password","").toString()
        val kelas  = sharedPreferences.getString("Kelas","").toString()
        val nama  = sharedPreferences.getString("Nama","").toString()

        emailDisplay.text = email
        namaDisplay.text = nama
        kelasDisplay.text = kelas
        nimDisplay.text = nim

        btnLogout.setOnClickListener{
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(view.context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.finish()


        }

        // Inflate the layout for this fragment
        return view
    }

    //Untuk Mengenerate Option Menu yang sudah dibuat di res/menu
    override fun onCreateOptionsMenu(menu: Menu,inflater: MenuInflater ) {
        inflater.inflate(R.menu.optionmenu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    //Untuk selected Option
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sharedPreferences = activity?.getSharedPreferences("MY_PRE",Context.MODE_PRIVATE)

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