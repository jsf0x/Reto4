package com.tgiraldo.retociclo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class AbonoActivity : AppCompatActivity() {
    private lateinit var txvUsername : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abono)
        txvUsername = findViewById(R.id.txvUsername)
        txvUsername.setText(intent.getStringExtra("username"))

        setSupportActionBar(findViewById(R.id.my_toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search -> {
                Toast.makeText(this,"HOLA", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_settings -> {
                Toast.makeText(this,R.string.text_action_settings, Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_nav -> {
                Toast.makeText(this,R.string.text_action_nav, Toast.LENGTH_LONG).show()
                val intento = Intent(this,DrawerActivity::class.java)
                startActivity(intento)
                return true
            }
            R.id.action_logout -> {
                Toast.makeText(this,R.string.text_action_logout, Toast.LENGTH_LONG).show()
                val intento = Intent(this,MainActivity::class.java)
                startActivity(intento)
                return true
            }
            R.id.action_compras -> {
                //Toast.makeText(this,R.string.text_action_compras,Toast.LENGTH_LONG).show()
                val intento = Intent(this,ToDoActivity::class.java)
                startActivity(intento)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }
}