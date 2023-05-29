package pnj.uts.ti.vidiseptriargalusmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import android.widget.EditText
import pnj.uts.ti.vidiseptriargalusmp.databasehelper.Helper
import pnj.uts.ti.vidiseptriargalusmp.model.AlumniModel
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class EditAlumniActivity : AppCompatActivity() {

    private lateinit var databaseHelper : Helper
    private lateinit var alumni: AlumniModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_alumni)


        val nikEditText: EditText = findViewById(R.id.inputNikEdit)
        val namaEditText: EditText = findViewById(R.id.inputNameEdit)
        val tanggalLahirEditText: EditText = findViewById(R.id.inputTanggalLahirEdit)
        val alamatEditText: EditText = findViewById(R.id.inputAlamatEdit)
        val tempatLahirEditText: EditText = findViewById(R.id.inputTempatLahirEdit)
        val noHpEditText: EditText = findViewById(R.id.inputNoHpEdit)

        databaseHelper = Helper(this)
        val editBtn : Button = findViewById(R.id.btnEditAlumni)
        val alumniID = intent.getIntExtra("ALUMNI_ID",0)
        this.alumni = databaseHelper.getAlumni(alumniID)

        if (alumniID != 0){

            nikEditText.setText(alumni.nik)
            namaEditText.setText(alumni.nama_alumni)
            tempatLahirEditText.setText(alumni.tempat_lahir)
            alamatEditText.setText(alumni.alamat)
            noHpEditText.setText(alumni.nohp)

            // Mengubah format tanggal_lahir menjadi dd/MM/yyyy
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val tanggalLahirText = dateFormat.format(alumni.tanggal_lahir)
            tanggalLahirEditText.setText(tanggalLahirText)
        }
        editBtn.setOnClickListener {
            val nik = nikEditText.text.toString()
            val nama = namaEditText.text.toString()
            val tempatLahir = tempatLahirEditText.text.toString()
            val tanggalLahir = tanggalLahirEditText.text.toString()
            val alamat = alamatEditText.text.toString()
            val noHp = noHpEditText.text.toString()

            // Update data alumni
            alumni.nik = nik
            alumni.nama_alumni = nama
            alumni.tempat_lahir = tempatLahir
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val date = dateFormat.parse(tanggalLahir)
            val sqldate = Date(date.time)
            alumni.tanggal_lahir = sqldate
            alumni.alamat = alamat
            alumni.nohp = noHp

            // Simpan perubahan pada database
            databaseHelper.updateAlumni(alumni)

            setResult(RESULT_OK)
            finish()

            startActivity(Intent(this,DataAlumni::class.java))

        }

    }
}