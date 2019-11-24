package ian.meda.ligaitesosoccer.ui.plantillaspendientes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterEquiposPendientes
import ian.meda.ligaitesosoccer.adapters.AdapterPlantillasPendientes
import org.jetbrains.anko.doAsync

class PlantillasPendientesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_plantillas_pendientes, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.plantillas_pendientes_recycler)

        doAsync {
            val query = ParseQuery.getQuery<ParseObject>("Equipo")
            query.whereEqualTo("esEquipoValido", true)
            query.whereEqualTo("plantillaValida", false)


            query.findInBackground ( object: FindCallback<ParseObject> {
                var plantillaspendientes: List<ParseObject> = arrayListOf()
                override fun done(plantillaspendientesList: List<ParseObject>, e : ParseException?) {
                    if (e==null) {
                        plantillaspendientes = plantillaspendientesList
                        recyclerView.adapter = AdapterPlantillasPendientes(plantillaspendientes)
                        recyclerView.adapter?.notifyDataSetChanged()
                        recyclerView.layoutManager = LinearLayoutManager(context)
                    }
                }
            })
        }

        return root
    }


}
