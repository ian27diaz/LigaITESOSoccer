package ian.meda.ligaitesosoccer.ui.tablageneral

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterIngresarJugadores
import ian.meda.ligaitesosoccer.adapters.AdapterTablaGeneral

class TablaGeneralFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val root = inflater.inflate(R.layout.fragment_tablageneral, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_tabla_equipos)
        val equipos = arrayListOf<HashMap<String, String>>()

        equipos.add(HashMap())
        equipos[0].put("nombre", "Club Deportivo 1")
        equipos[0].put("PJ", "4")
        equipos[0].put("PG", "4")
        equipos[0].put("PE", "0")
        equipos[0].put("PP", "0")
        equipos[0].put("GF", "15")
        equipos[0].put("GC", "2")
        equipos[0].put("DG", "13")
        equipos[0].put("PTS", "12")

        equipos.add(HashMap())
        equipos[1].put("nombre", "Club Deportivo 2")
        equipos[1].put("PJ", "4")
        equipos[1].put("PG", "4")
        equipos[1].put("PE", "0")
        equipos[1].put("PP", "0")
        equipos[1].put("GF", "15")
        equipos[1].put("GC", "2")
        equipos[1].put("DG", "13")
        equipos[1].put("PTS", "12")

        equipos.add(HashMap())
        equipos[2].put("nombre", "Club Deportivo 3")
        equipos[2].put("PJ", "4")
        equipos[2].put("PG", "4")
        equipos[2].put("PE", "0")
        equipos[2].put("PP", "0")
        equipos[2].put("GF", "15")
        equipos[2].put("GC", "2")
        equipos[2].put("DG", "13")
        equipos[2].put("PTS", "12")

        equipos.add(HashMap())
        equipos[3].put("nombre", "Club Deportivo 4")
        equipos[3].put("PJ", "4")
        equipos[3].put("PG", "4")
        equipos[3].put("PE", "0")
        equipos[3].put("PP", "0")
        equipos[3].put("GF", "15")
        equipos[3].put("GC", "2")
        equipos[3].put("DG", "13")
        equipos[3].put("PTS", "12")

        equipos.add(HashMap())
        equipos[4].put("nombre", "Club Deportivo 5")
        equipos[4].put("PJ", "4")
        equipos[4].put("PG", "4")
        equipos[4].put("PE", "0")
        equipos[4].put("PP", "0")
        equipos[4].put("GF", "15")
        equipos[4].put("GC", "2")
        equipos[4].put("DG", "13")
        equipos[4].put("PTS", "12")

        equipos.add(HashMap())
        equipos[5].put("nombre", "Club Deportivo 6")
        equipos[5].put("PJ", "4")
        equipos[5].put("PG", "4")
        equipos[5].put("PE", "0")
        equipos[5].put("PP", "0")
        equipos[5].put("GF", "15")
        equipos[5].put("GC", "2")
        equipos[5].put("DG", "13")
        equipos[5].put("PTS", "12")

        equipos.add(HashMap())
        equipos[6].put("nombre", "Club Deportivo 7")
        equipos[6].put("PJ", "4")
        equipos[6].put("PG", "4")
        equipos[6].put("PE", "0")
        equipos[6].put("PP", "0")
        equipos[6].put("GF", "15")
        equipos[6].put("GC", "2")
        equipos[6].put("DG", "13")
        equipos[6].put("PTS", "12")

        equipos.add(HashMap())
        equipos[7].put("nombre", "Club Deportivo 8")
        equipos[7].put("PJ", "4")
        equipos[7].put("PG", "4")
        equipos[7].put("PE", "0")
        equipos[7].put("PP", "0")
        equipos[7].put("GF", "15")
        equipos[7].put("GC", "2")
        equipos[7].put("DG", "13")
        equipos[7].put("PTS", "12")

        equipos.add(HashMap())
        equipos[8].put("nombre", "Club Deportivo 9")
        equipos[8].put("PJ", "4")
        equipos[8].put("PG", "4")
        equipos[8].put("PE", "0")
        equipos[8].put("PP", "0")
        equipos[8].put("GF", "15")
        equipos[8].put("GC", "2")
        equipos[8].put("DG", "13")
        equipos[8].put("PTS", "12")

        equipos.add(HashMap())
        equipos[9].put("nombre", "Club Deportivo 10")
        equipos[9].put("PJ", "4")
        equipos[9].put("PG", "4")
        equipos[9].put("PE", "0")
        equipos[9].put("PP", "0")
        equipos[9].put("GF", "15")
        equipos[9].put("GC", "2")
        equipos[9].put("DG", "13")
        equipos[9].put("PTS", "12")

        equipos.add(HashMap())
        equipos[10].put("nombre", "Club Deportivo 11")
        equipos[10].put("PJ", "4")
        equipos[10].put("PG", "4")
        equipos[10].put("PE", "0")
        equipos[10].put("PP", "0")
        equipos[10].put("GF", "15")
        equipos[10].put("GC", "2")
        equipos[10].put("DG", "13")
        equipos[10].put("PTS", "12")

        equipos.add(HashMap())
        equipos[11].put("nombre", "Club Deportivo 12")
        equipos[11].put("PJ", "4")
        equipos[11].put("PG", "4")
        equipos[11].put("PE", "0")
        equipos[11].put("PP", "0")
        equipos[11].put("GF", "15")
        equipos[11].put("GC", "2")
        equipos[11].put("DG", "13")
        equipos[11].put("PTS", "12")

        equipos.add(HashMap())
        equipos[12].put("nombre", "Club Deportivo 13")
        equipos[12].put("PJ", "4")
        equipos[12].put("PG", "4")
        equipos[12].put("PE", "0")
        equipos[12].put("PP", "0")
        equipos[12].put("GF", "15")
        equipos[12].put("GC", "2")
        equipos[12].put("DG", "13")
        equipos[12].put("PTS", "12")

        equipos.add(HashMap())
        equipos[13].put("nombre", "Club Deportivo 14")
        equipos[13].put("PJ", "4")
        equipos[13].put("PG", "4")
        equipos[13].put("PE", "0")
        equipos[13].put("PP", "0")
        equipos[13].put("GF", "15")
        equipos[13].put("GC", "2")
        equipos[13].put("DG", "13")
        equipos[13].put("PTS", "12")

        equipos.add(HashMap())
        equipos[14].put("nombre", "Club Deportivo 15")
        equipos[14].put("PJ", "4")
        equipos[14].put("PG", "4")
        equipos[14].put("PE", "0")
        equipos[14].put("PP", "0")
        equipos[14].put("GF", "15")
        equipos[14].put("GC", "2")
        equipos[14].put("DG", "13")
        equipos[14].put("PTS", "12")

        equipos.add(HashMap())
        equipos[15].put("nombre", "Club Deportivo 16")
        equipos[15].put("PJ", "4")
        equipos[15].put("PG", "4")
        equipos[15].put("PE", "0")
        equipos[15].put("PP", "0")
        equipos[15].put("GF", "15")
        equipos[15].put("GC", "2")
        equipos[15].put("DG", "13")
        equipos[15].put("PTS", "12")
        recyclerView.adapter = AdapterTablaGeneral(equipos)
        recyclerView.layoutManager = LinearLayoutManager(context)

        return root
    }



}