package pnj.uts.ti.vidiseptriargalusmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import pnj.uts.ti.vidiseptriargalusmp.databinding.ActivityDetailBeritaBinding
import pnj.uts.ti.vidiseptriargalusmp.model.ListData

class DetailBeritaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBeritaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)

        val berita = intent.getParcelableExtra<ListData>("berita")
        if (berita != null){
            val judul : TextView = findViewById(R.id.judulBerita)
            val gambar : ImageView = findViewById(R.id.detailImage)
            val detail : TextView = findViewById(R.id.detailBerita)

            judul.setText(berita.judul)
            gambar.setImageResource(berita.image)
            detail.setText(berita.deskripsi)

        }

    }
}