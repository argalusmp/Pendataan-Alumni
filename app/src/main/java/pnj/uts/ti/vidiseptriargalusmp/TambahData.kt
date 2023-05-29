package pnj.uts.ti.vidiseptriargalusmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import pnj.uts.ti.vidiseptriargalusmp.databasehelper.Helper
import pnj.uts.ti.vidiseptriargalusmp.model.AlumniModel
import java.sql.Date
import java.text.SimpleDateFormat

class TambahData : AppCompatActivity() {

    private lateinit var databaseHelper: Helper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data)


        databaseHelper = Helper(this)

        val saveButton: Button = findViewById(R.id.btnSaveAlumni)
        saveButton.setOnClickListener {
            val nikEditText: EditText = findViewById(R.id.inputNik)
            val namaEditText: EditText = findViewById(R.id.inputName)
            val tempatLahirEditText: EditText = findViewById(R.id.inputTempatLahir)
            val tanggalLahirEditText: EditText = findViewById(R.id.inputTanggalLahir)
            val alamatEditText: EditText = findViewById(R.id.inputAlamat)
            val noHpEditText: EditText = findViewById(R.id.inputNoHp)

            val nik = nikEditText.text.toString()
            val nama = namaEditText.text.toString()
            val tempatLahir = tempatLahirEditText.text.toString()
            val tanggalLahir = tanggalLahirEditText.text.toString()
            val alamat = alamatEditText.text.toString()
            val noHp = noHpEditText.text.toString()

            val alumni = AlumniModel()
            alumni.nik = nik
            alumni.nama_alumni = nama
            alumni.tempat_lahir = tempatLahir
            alumni.alamat = alamat
            alumni.nohp = noHp
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val date = dateFormat.parse(tanggalLahir)
            val sqldate = Date(date.time)
            alumni.tanggal_lahir = sqldate

            databaseHelper.addAlumni(alumni)

            setResult(RESULT_OK)
            finish()

            startActivity(Intent(this,DataAlumni::class.java))
        }
    }
}