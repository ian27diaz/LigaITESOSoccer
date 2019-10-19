package ian.meda.ligaitesosoccer.ui.goleadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterGoleadores
import ian.meda.ligaitesosoccer.adapters.AdapterTablaGeneral

class GoleadoresFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_goleadores, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_goleadores)
        val goleadores = arrayListOf<HashMap<String, String>>()

        goleadores.add(HashMap())
        goleadores[0].put("Nombre", "Hector Chavez")
        goleadores[0].put("Equipo", "Pallacracks")
        goleadores[0].put("Goles","10")

        goleadores.add(HashMap())
        goleadores[1].put("Nombre", "Ian Diaz")
        goleadores[1].put("Equipo", "Pallacracks")
        goleadores[1].put("Goles","7")

        goleadores.add(HashMap())
        goleadores[2].put("Nombre", "Ian Diaz")
        goleadores[2].put("Equipo", "Pallacracks")
        goleadores[2].put("Goles","7")

        goleadores.add(HashMap())
        goleadores[3].put("Nombre", "Ian Diaz")
        goleadores[3].put("Equipo", "Pallacracks")
        goleadores[3].put("Goles","7")

        goleadores.add(HashMap())
        goleadores[4].put("Nombre", "Ian Diaz")
        goleadores[4].put("Equipo", "Pallacracks")
        goleadores[4].put("Goles","7")

        goleadores.add(HashMap())
        goleadores[5].put("Nombre", "Ian Diaz")
        goleadores[5].put("Equipo", "Pallacracks")
        goleadores[5].put("Goles","7")

        goleadores.add(HashMap())
        goleadores[6].put("Nombre", "Ian Diaz")
        goleadores[6].put("Equipo", "Pallacracks")
        goleadores[6].put("Goles","7")

        goleadores.add(HashMap())
        goleadores[7].put("Nombre", "Ian Diaz")
        goleadores[7].put("Equipo", "Pallacracks")
        goleadores[7].put("Goles","7")

        goleadores.add(HashMap())
        goleadores[8].put("Nombre", "Ian Diaz")
        goleadores[8].put("Equipo", "Pallacracks")
        goleadores[8].put("Goles","7")

        goleadores.add(HashMap())
        goleadores[9].put("Nombre", "Ian Diaz")
        goleadores[9].put("Equipo", "Pallacracks")
        goleadores[9].put("Goles","7")


        recyclerView.adapter = AdapterGoleadores(goleadores)
        recyclerView.layoutManager = LinearLayoutManager(context)


        return root
    }
}