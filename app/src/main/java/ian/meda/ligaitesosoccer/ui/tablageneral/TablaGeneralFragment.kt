package ian.meda.ligaitesosoccer.ui.tablageneral

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterGoleadores
import ian.meda.ligaitesosoccer.adapters.AdapterIngresarJugadores
import ian.meda.ligaitesosoccer.adapters.AdapterTablaGeneral
import org.jetbrains.anko.doAsync

class TablaGeneralFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val root = inflater.inflate(R.layout.fragment_tablageneral, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_tabla_equipos)

        doAsync {
            val queryEquipos = ParseQuery.getQuery<ParseObject>("Equipo")
            queryEquipos.orderByDescending("puntosTotales")
            queryEquipos.findInBackground ( object: FindCallback<ParseObject> {
                var equipos: List<ParseObject> = arrayListOf()
                override fun done(equiposList: List<ParseObject>, e : ParseException?) {
                    if (e==null) {
                        equipos = equiposList
                        recyclerView.adapter = AdapterTablaGeneral(equipos)
                        recyclerView.adapter?.notifyDataSetChanged()
                        recyclerView.layoutManager = LinearLayoutManager(context)
                    }
                }
            })
        }
        return root
    }



}