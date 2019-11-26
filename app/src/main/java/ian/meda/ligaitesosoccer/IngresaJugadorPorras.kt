package ian.meda.ligaitesosoccer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.*
import ian.meda.ligaitesosoccer.adapters.AdapterIngresarJugadoresPorras
import ian.meda.ligaitesosoccer.adapters.AdapterPartido
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class IngresaJugadorPorras : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresa_jugador_porras)

        var botonAceptar: Button = findViewById<Button>(R.id.ingresar_jugadores_porras_btn_aceptar)
        var botonRechazar: Button = findViewById<Button>(R.id.ingresar_jugadores_porras_btn_rechazar)
        var RecyclerJugadores = findViewById<RecyclerView>(R.id.recycler_view_ingresar_jugadores_porras)
        var equipo = intent.getStringExtra("equipo")

        doAsync {
            val query = ParseQuery.getQuery<ParseObject>("Jugador")
            query.include("IDEquipo")
            query.findInBackground ( object: FindCallback<ParseObject> {

                var jugadores: List<ParseObject> = arrayListOf()

                override fun done(jugadoresList: List<ParseObject>, e: ParseException?) {
                    if (e == null) {
                        jugadores = jugadoresList.filter {e -> e.getParseObject("IDEquipo")?.objectId == equipo}
                        activityUiThread {
                            RecyclerJugadores.adapter = AdapterIngresarJugadoresPorras(jugadores)
                            RecyclerJugadores.adapter?.notifyDataSetChanged()
                            RecyclerJugadores.layoutManager = LinearLayoutManager(this@IngresaJugadorPorras)
                        }

                        val queryequipo = ParseQuery.getQuery<ParseObject>("Equipo")



                            queryequipo.whereEqualTo("objectId",equipo)
                            queryequipo.getFirstInBackground ( object: GetCallback<ParseObject> {
                                override fun done(equipoParse: ParseObject, e: ParseException?) {
                                    if (e == null) {
                                        botonAceptar.setOnClickListener {

                                            toast(equipoParse.objectId).show()
                                            equipoParse.put("plantillaValida", true)
                                            equipoParse.put("accionPendiente", false)
                                            equipoParse.saveInBackground()
                                            startActivity<MainActivity>()
                                        }

                                        botonRechazar.setOnClickListener {
                                            for (i in jugadores.indices){
                                                jugadores[i].delete()
                                            }
                                            for (i in jugadores.indices){
                                                jugadores[i].saveInBackground()
                                            }
                                            equipoParse.put("accionPendiente", true)
                                            equipoParse.saveInBackground()
                                            startActivity<MainActivity>()
                                        }

                                    }
                                }
                            })
                        }
                    }
            })
        }
    }
}
