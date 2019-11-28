package ian.meda.ligaitesosoccer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import ian.meda.ligaitesosoccer.adapters.AdapterPartido
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.*


class PartidoActivity (): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partido)

        var enfrentamiento = intent.getStringExtra("enfrentamiento")

        var listView1 = findViewById<ListView>(R.id.partido_eventos_local)
        var listView2 = findViewById<ListView>(R.id.partido_eventos_visitas)
        var localTitle: TextView = findViewById<TextView>(R.id.partido_nombre_local)
        var marcadorlocalTitle: TextView = findViewById<TextView>(R.id.partido_marcador_local)
        var marcadorvisitaTitle: TextView = findViewById<TextView>(R.id.partido_marcador_visita)
        var visitaTitle: TextView = findViewById<TextView>(R.id.partido_nombre_visita)
        var localEscudo: ImageView = findViewById<ImageView>(R.id.partido_escudo_local)
        var visitaEscudo: ImageView = findViewById<ImageView>(R.id.partido_escudo_visita)
        var rm = Glide.with(this)
        var spinnerLocalTipo: Spinner = findViewById<Spinner>(R.id.partido_ingresar_evento_tipo_local)
        var spinnerLocalJugadores: Spinner = findViewById<Spinner>(R.id.partido_ingresar_evento_jugador_local)
        var spinnerVisitaTipo: Spinner = findViewById<Spinner>(R.id.partido_ingresar_evento_tipo_visita)
        var spinnerVisitaJugadores: Spinner = findViewById<Spinner>(R.id.partido_ingresar_evento_jugador_visita)
        val tipoEventos = arrayOf("Gol", "Amarilla", "Roja")

        val adapterspinnerLocalTipo = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipoEventos)
        val adapterspinnerVisitaTipo = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipoEventos)

        var botonAceptarLocal: Button = findViewById<Button>(R.id.partido_ingresar_evento_aceptar_local)
        var botonAceptarVisita: Button = findViewById<Button>(R.id.partido_ingresar_evento_aceptar_visita)


        var idLocal: String? = ""
        var idVisita: String? = ""

        var diferenciaMarcador = 0

        doAsync {
            spinnerLocalTipo.adapter = adapterspinnerLocalTipo
            spinnerVisitaTipo.adapter = adapterspinnerVisitaTipo

            val queryjugador = ParseQuery.getQuery<ParseObject>("Jugador")
            queryjugador.include("IDEquipo")


            val query = ParseQuery.getQuery<ParseObject>("JornadaJugadorEvento")
            query.include("jornadaenfrentamiento")
            query.include("jugador").include("IDEquipo")

            val querymarcador = ParseQuery.getQuery<ParseObject>("JornadaEnfrentamiento")
            querymarcador.include("local")
            querymarcador.include("visitante")
            querymarcador.whereEqualTo("objectId", enfrentamiento)

            querymarcador.findInBackground ( object: FindCallback<ParseObject> {
                var marcador: List<ParseObject> = arrayListOf()

                override fun done(marcadorList: List<ParseObject>, e : ParseException?) {
                    if (e==null) {
                        marcador = marcadorList
                        activityUiThread {
                            rm.load(marcador[0].getParseObject("local")?.getParseFile("escudo")!!.url).into(localEscudo)
                            rm.load(marcador[0].getParseObject("visitante")?.getParseFile("escudo")!!.url).into(visitaEscudo)
                            localTitle.text = marcador[0].getParseObject("local")?.getString("nombre")
                            marcadorlocalTitle.text = marcador[0].getInt("golesEquipo1").toString()
                            marcadorvisitaTitle.text = marcador[0].getInt("golesEquipo2").toString()
                            visitaTitle.text = marcador[0].getParseObject("visitante")?.getString("nombre")

                            idLocal = marcador[0].getParseObject("local")?.objectId
                            idVisita = marcador[0].getParseObject("visitante")?.objectId

                        }

                        query.findInBackground ( object: FindCallback<ParseObject> {
                            var eventosLocal: MutableList<ParseObject> = arrayListOf()
                            var eventosVisita: MutableList<ParseObject> = arrayListOf()
                            var eventos: List<ParseObject> = arrayListOf()

                            override fun done(eventosList: List<ParseObject>, e : ParseException?) {
                                if (e==null) {
                                    eventos = eventosList
                                    for (evento in eventosList){
                                        if (enfrentamiento == evento.getParseObject("jornadaenfrentamiento")?.objectId.toString()){
                                            if (evento.getParseObject("jugador")?.getParseObject("IDEquipo")?.objectId.toString() == idLocal ){
                                                eventosLocal.add(evento)
                                            }else if (evento.getParseObject("jugador")?.getParseObject("IDEquipo")?.objectId.toString() == idVisita){
                                                eventosVisita.add(evento)
                                            }
                                        }
                                    }

                                    activityUiThread {
                                        listView1.adapter = AdapterPartido(eventosLocal)
                                        listView2.adapter = AdapterPartido(eventosVisita)
                                    }
                                }
                            }
                        })


                        queryjugador.findInBackground( object: FindCallback<ParseObject> {
                            var jugadoresLocal : Array<String> = arrayOf()
                            var jugadoresVisita : Array<String> = arrayOf()
                            override fun done(jugadoresList: List<ParseObject>, e : ParseException?) {
                                if (e == null) {
                                    for (jugador in jugadoresList) {
                                        if (jugador.getParseObject("IDEquipo")?.objectId.toString().compareTo(idLocal.toString())==0){
                                            jugadoresLocal += jugador.getString("Nombre").toString()
                                        }

                                        if (jugador.getParseObject("IDEquipo")?.objectId.toString().compareTo(idVisita.toString())==0){
                                            jugadoresVisita += jugador.getString("Nombre").toString()
                                        }
                                    }
                                    val adapterspinnerLocalJugador = ArrayAdapter(this@PartidoActivity, android.R.layout.simple_spinner_dropdown_item, jugadoresLocal)
                                    spinnerLocalJugadores.adapter = adapterspinnerLocalJugador
                                    val adapterspinnerVisitaJugador = ArrayAdapter(this@PartidoActivity, android.R.layout.simple_spinner_dropdown_item, jugadoresVisita)
                                    spinnerVisitaJugadores.adapter = adapterspinnerVisitaJugador

                                    botonAceptarLocal.setOnClickListener {
                                        toast(spinnerLocalJugadores.selectedItem.toString()+ " -> " + spinnerLocalTipo.selectedItem.toString()).show()

                                        if (spinnerLocalTipo.selectedItem.toString() == "Amarilla"){

                                        }

                                        if(spinnerLocalTipo.selectedItem.toString() == "Roja"){

                                        }

                                        if(spinnerLocalTipo.selectedItem.toString() == "Gol"){
                                            diferenciaMarcador += 1
                                        }
                                    }

                                    botonAceptarVisita.setOnClickListener {
                                        toast(spinnerVisitaJugadores.selectedItem.toString()+ " -> " + spinnerVisitaTipo.selectedItem.toString()).show()

                                        if (spinnerVisitaTipo.selectedItem.toString() == "Amarilla"){

                                        }

                                        if(spinnerVisitaTipo.selectedItem.toString() == "Roja"){

                                        }

                                        if(spinnerVisitaTipo.selectedItem.toString() == "Gol"){
                                            diferenciaMarcador += -1
                                        }
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
