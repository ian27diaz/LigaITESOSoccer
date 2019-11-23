package ian.meda.ligaitesosoccer.ui.equipospendientes

import android.content.Context
import android.net.Uri
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
import ian.meda.ligaitesosoccer.adapters.AdapterGoleadores
import org.jetbrains.anko.doAsync

class EquipoPendienteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_equipo_pendiente, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.equipos_pendientes_recycler)

        doAsync {
           val query = ParseQuery.getQuery<ParseObject>("Equipo")
            query.whereEqualTo("esEquipoValido", false)


            query.findInBackground ( object: FindCallback<ParseObject> {
                var equipospendientes: List<ParseObject> = arrayListOf()
                override fun done(equipospendientesList: List<ParseObject>, e : ParseException?) {
                    if (e==null) {
                        equipospendientes = equipospendientesList
                        recyclerView.adapter = AdapterEquiposPendientes(equipospendientes)
                        recyclerView.adapter?.notifyDataSetChanged()
                        recyclerView.layoutManager = LinearLayoutManager(context)
                    }
                }
            })
        }

        return root
    }

}
