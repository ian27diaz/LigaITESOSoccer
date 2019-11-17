package ian.meda.ligaitesosoccer.ui.goleadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.*
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterGoleadores
import ian.meda.ligaitesosoccer.adapters.AdapterTablaGeneral
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import java.io.File

class GoleadoresFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_goleadores, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_goleadores)

        doAsync {
            val queryGoleadores = ParseQuery.getQuery<ParseObject>("Jugador")
            queryGoleadores.include("IDEquipo")

            queryGoleadores.orderByDescending("GolesTotales")
            queryGoleadores.setLimit(10)
            queryGoleadores.findInBackground ( object: FindCallback<ParseObject> {
            var goleadores: List<ParseObject> = arrayListOf()
                override fun done(goleadoresList: List<ParseObject>, e : ParseException?) {
                    if (e==null) {
                        goleadores = goleadoresList
                            recyclerView.adapter = AdapterGoleadores(goleadores)
                            recyclerView.adapter?.notifyDataSetChanged()
                            recyclerView.layoutManager = LinearLayoutManager(context)
                    }
                }
            })
        }
        return root
    }
}