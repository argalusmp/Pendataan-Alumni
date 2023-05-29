package pnj.uts.ti.vidiseptriargalusmp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pnj.uts.ti.vidiseptriargalusmp.adapter.AlumniAdapter
import pnj.uts.ti.vidiseptriargalusmp.databasehelper.Helper
import pnj.uts.ti.vidiseptriargalusmp.model.AlumniModel

class DataAlumni : AppCompatActivity() {

    private lateinit var alumniAdapter: AlumniAdapter
    var alumniList: List<AlumniModel> = ArrayList<AlumniModel>()
    private lateinit var databaseHelper: Helper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_alumni)

        databaseHelper = Helper(this)
        alumniList = databaseHelper.getAllAlumni()
        alumniAdapter = AlumniAdapter(alumniList)
        val recyclerView: RecyclerView = findViewById(R.id.listAlumni)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = alumniAdapter
        alumniAdapter.notifyDataSetChanged()

        val addButton: Button = findViewById(R.id.btnToAddAlumni)
        addButton.setOnClickListener{
            val intent = Intent(this,TambahData::class.java)
            startActivity(intent)
        }

        alumniAdapter.setOnItemClickListener { alumni ->
            val intent = Intent(this, DetailDataAlumni::class.java)
            intent.putExtra("ALUMNI_ID", alumni.id)
            startActivity(intent)
        }

    }


    //Untuk Mengenerate Option Menu yang sudah dibuat di res/menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu, menu)
        return true;
    }

    //Aksi ketika Mencet OptionMenu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sharedPreferences = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)

        return when (item.itemId){
            R.id.tambahData -> {
                startActivity(Intent(this,TambahData::class.java))
                true
            }
            R.id.dataAlumni -> {
                startActivity(Intent(this,DataAlumni::class.java))
                true
            }
            R.id.Logout -> {
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}