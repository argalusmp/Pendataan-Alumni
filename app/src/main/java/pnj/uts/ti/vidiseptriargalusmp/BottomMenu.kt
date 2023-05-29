package pnj.uts.ti.vidiseptriargalusmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomMenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_menu)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        loadFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.nav_bottom_home -> {
                    loadFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.nav_bottom_news->{
                    loadFragment(BeritaFragment())
//                    startActivity(Intent(this,BeritaActivity::class.java))
                    true
                }
                R.id.nav_bottom_user->{
                    loadFragment(ProfileFragment())
                    true
                }
                else->return@setOnItemSelectedListener false

            }


        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}