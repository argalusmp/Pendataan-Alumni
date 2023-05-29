package pnj.uts.ti.vidiseptriargalusmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import pnj.uts.ti.vidiseptriargalusmp.databasehelper.Helper
import pnj.uts.ti.vidiseptriargalusmp.model.AlumniModel

class DetailDataAlumni : AppCompatActivity() {

    private lateinit var databaseHelper: Helper
    private lateinit var alumni: AlumniModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_data_alumni)

        databaseHelper = Helper(this)


        val alumniId = intent.getIntExtra("ALUMNI_ID", 0)
        if (alumniId == 0) {
            finish()
        } else {
            alumni = databaseHelper.getAlumni(alumniId)
            displayAlumniDetail()

            val editButton: Button = findViewById(R.id.editButton)
            editButton.setOnClickListener {
                val intent = Intent(this, EditAlumniActivity::class.java)
                intent.putExtra("ALUMNI_ID", alumni.id)
                startActivity(intent)
            }

            val deleteButton: Button = findViewById(R.id.deleteButton)
            deleteButton.setOnClickListener {
                databaseHelper.deleteAlumni(alumni)
                setResult(RESULT_OK)
                finish()
                startActivity(Intent(this,DataAlumni::class.java))
            }
        }
    }


    private fun displayAlumniDetail() {
        val nikTextView: TextView = findViewById(R.id.nikTextViewDetail)
        val namaTextView: TextView = findViewById(R.id.namaTextViewDetail)
        val tempatLahirTextView: TextView = findViewById(R.id.tempatlahirTextViewDetail)
        val tanggalLahirTextView: TextView = findViewById(R.id.tanggallahirTextViewDetail)
        val alamatTextView: TextView = findViewById(R.id.alamatTextViewDetail)
        val noHpTextView: TextView = findViewById(R.id.nohpTextViewDetail)

        nikTextView.text = alumni.nik
        namaTextView.text = alumni.nama_alumni
        tempatLahirTextView.text = alumni.tempat_lahir
        tanggalLahirTextView.text = alumni.tanggal_lahir.toString()
        alamatTextView.text = alumni.alamat
        noHpTextView.text = alumni.nohp
    }

    // Tambahkan kode untuk menghandle hasil dari activity edit alumni
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                EDIT_ALUMNI_REQUEST -> {
                    alumni = databaseHelper.getAlumni(alumni.id)
                    displayAlumniDetail()
                }
            }
        }
    }

    companion object {
        private const val EDIT_ALUMNI_REQUEST = 3
    }

}