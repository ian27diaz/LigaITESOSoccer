package ian.meda.ligaitesosoccer.ui.equipo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.*
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterEquipo
import ian.meda.ligaitesosoccer.beans.Jugador
import ian.meda.ligaitesosoccer.ui.calendario.LinePagerIndicatorDecoration
import ian.meda.ligaitesosoccer.utils.SESSION_TEAM
import ian.meda.ligaitesosoccer.utils.SHARED_PREFERENCES
import org.jetbrains.anko.doAsync

class EquipoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_equipo, container, false)
        val recyclerView : RecyclerView = root.findViewById<RecyclerView>(R.id.fragment_equipo_recyclerview)

        doAsync {
            val sharedPreferences = context!!.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
            val currentTeam = sharedPreferences.getString(SESSION_TEAM, "")
            val escudoEquipo = root.findViewById<ImageView>(R.id.fragment_equipo_logoEquipo)
            val nombreEquipo = root.findViewById<TextView>(R.id.fragment_equipo_nombreEquipo)
            val glide = Glide.with(root)

            val teamQuery = ParseQuery.getQuery<ParseObject>("Equipo")
            teamQuery.whereEqualTo("objectId", currentTeam)



            val query = ParseQuery.getQuery<ParseObject>("Jugador")
            query.whereEqualTo("IDEquipo", ParseObject.createWithoutData("Equipo", currentTeam))
            if(!currentTeam.equals("")){

                teamQuery.findInBackground(object: FindCallback<ParseObject> {
                    override fun done(equipos: List<ParseObject>, e: ParseException?) {
                        val equipo = equipos.get(0)
                        activity?.runOnUiThread{
                            nombreEquipo.text = equipo.getString("nombre")
                            glide.load(equipo.getParseFile("escudo")!!.url).into(escudoEquipo)
                        }
                    }
                })

                query.findInBackground(object: FindCallback<ParseObject> {
                    override fun done(jugadores: List<ParseObject>, e: ParseException?) {
                        for(jugador: ParseObject in jugadores){
                            Log.v("EquipoFragment", jugador.getString("Nombre"))
                        }

                        activity?.runOnUiThread{
                            recyclerView.adapter = AdapterEquipo(jugadores)
                            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            recyclerView.addItemDecoration(LinePagerIndicatorDecoration())
                            PagerSnapHelper().attachToRecyclerView(recyclerView)
                        }
                    }
                })
            }

        }

        return root
    }
}