package ian.meda.ligaitesosoccer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import ian.meda.ligaitesosoccer.adapters.AdapterPartido
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class partidoActivity (): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partido)

        var recyclerView1 = findViewById<RecyclerView>(R.id.partido_eventos_local)
        var recyclerView2 = findViewById<RecyclerView>(R.id.partido_eventos_visitas)


        doAsync {
            val query = ParseQuery.getQuery<ParseObject>("JornadaJugadorEvento")
            query.include("jornadaenfrentamiento").include("local")
            query.include("jornadaenfrentamiento").include("visita")
            query.include("jugador").include("IDEquipo")
            //query.whereEqualTo("objectId", enfrentamiento)


            query.findInBackground ( object: FindCallback<ParseObject> {
                var eventos: List<ParseObject> = arrayListOf()

                override fun done(eventosList: List<ParseObject>, e : ParseException?) {
                    if (e==null) {
                        eventos = eventosList
                        activityUiThread {
                            recyclerView1.adapter = AdapterPartido(eventos, true)
                            recyclerView2.adapter = AdapterPartido(eventos, false)

                            recyclerView1.adapter?.notifyDataSetChanged()
                            recyclerView2.adapter?.notifyDataSetChanged()

                            recyclerView1.layoutManager = LinearLayoutManager(parent)
                            recyclerView2.layoutManager = LinearLayoutManager(parent)

                        }
                    }
                }


            })
        }

    }
}
