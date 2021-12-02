package com.tgiraldo.retociclo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class OpcionesActivity : AppCompatActivity() {
    private lateinit var txvUsername : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)
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
            R.id.action_logout -> {
                Toast.makeText(this,R.string.text_action_logout, Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_compras -> {
                Toast.makeText(this,R.string.text_action_compras,Toast.LENGTH_LONG).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    fun onBuy(view: android.view.View) {
            val intento = Intent(this,CompraActivity::class.java)
            intento.putExtra("username",intent.getStringExtra("username"))
            startActivity(intento)
    }
    fun onPartialPay(view: android.view.View) {
        val intento = Intent(this,AbonoActivity::class.java)
        intento.putExtra("username",intent.getStringExtra("username"))
        startActivity(intento)
    }
}