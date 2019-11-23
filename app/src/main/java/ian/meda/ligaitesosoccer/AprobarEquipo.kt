package ian.meda.ligaitesosoccer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.parse.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast

class AprobarEquipo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprobar_equipo)

        var botonAceptar: Button = findViewById<Button>(R.id.aprobar_equipo_aceptar)
        var botonRechazar: Button = findViewById<Button>(R.id.aprobar_equipo_rechazar)

        var equipo = intent.getStringExtra("equipo")
        var rm = Glide.with(this)
        var rm2 = Glide.with(this)

        var comprobante: ImageView = findViewById<ImageView>(R.id.aprobar_equipo_comprobante)
        var escudo: ImageView = findViewById<ImageView>(R.id.aprobar_equipo_escudo_equipo)
        var nombre: TextView = findViewById<TextView>(R.id.aprobar_equipo_nombre_equipo)

        doAsync {
            val query = ParseQuery.getQuery<ParseObject>("Equipo")
            query.whereEqualTo("nombre", equipo)

            query.findInBackground ( object: FindCallback<ParseObject> {

                    var datos: List<ParseObject> = arrayListOf()

                override fun done(datosList: List<ParseObject>, e: ParseException?) {
                    if (e == null) {
                        datos = datosList
                        activityUiThread {
                            rm.load(datos[0].getParseFile("comprobantePago")!!.url).into(comprobante)
                            rm2.load(datos[0].getParseFile("escudo")!!.url).into(escudo)
                            nombre.text = datos[0].getString("nombre")
                        }

                        botonAceptar.setOnClickListener {
                            datos[0].put("esEquipoValido",true)
                            datos[0].saveInBackground()
                            startActivity<MainActivity>()
                        }

                        botonRechazar.setOnClickListener {
                            datos[0].delete()
                            datos[0].saveInBackground()
                            startActivity<MainActivity>()
                        }
                    }
                }
            })
        }

    }
}
