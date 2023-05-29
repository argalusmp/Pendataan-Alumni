package pnj.uts.ti.vidiseptriargalusmp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.id_btnLogin)
        val edEmail = findViewById<EditText>(R.id.ed_email)
        val edPassword = findViewById<EditText>(R.id.ed_password)
        val togglePassword = findViewById<ToggleButton>(R.id.passwordToggle)

        val sharedPreferences = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)

        //untuk toggle password hide/see
        togglePassword.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Tampilkan karakter password
                edPassword.transformationMethod = null
            } else {
                // Sembunyikan karakter password
                edPassword.transformationMethod = PasswordTransformationMethod()
            }
        }

        btnLogin.setOnClickListener {
            val email = edEmail.text.toString()
            val password = edPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,"Email atau Password belum diisi!", Toast.LENGTH_SHORT).show()
            }else {
                val editor = sharedPreferences.edit()
                editor.putString("Email", "argalus03@gmail.com")
                editor.putString("Password", "argalus03")
                editor.putString("Nim", "2107411032")
                editor.putString("Kelas", "TI 4B")
                editor.putString("Nama", "Vidi Septri Argalus Mp")
                editor.apply()

                val i = Intent(this,BottomMenu::class.java)

                if (email == "argalus03@gmail.com" && password == "argalus03"){
                    startActivity(i)
                }
                else{
                    Toast.makeText(this,"Login Gagal!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    //Untuk Mengenerate Option Menu yang sudah dibuat di res/menu
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.optionmenu, menu)
//        return true;
//    }

    //Aksi ketika Mencet OptionMenu
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val sharedPreferences = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
//
//        return when (item.itemId){
//            R.id.tambahData -> {
//                startActivity(Intent(this,TambahData::class.java))
//                true
//            }
//            R.id.dataAlumni -> {
//                startActivity(Intent(this,DataAlumni::class.java))
//                true
//            }
//            R.id.Logout -> {
//                val editor = sharedPreferences.edit()
//                editor.clear()
//                editor.apply()
//
//                val intent = Intent(this, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//                finish()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
