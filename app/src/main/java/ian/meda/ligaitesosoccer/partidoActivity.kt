package ian.meda.ligaitesosoccer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import ian.meda.ligaitesosoccer.adapters.AdapterPartido
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

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

        var idLocal: String? = ""
        var idVisita: String? = ""

        doAsync {
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
                    }
                }
            })
        }
    }
}
