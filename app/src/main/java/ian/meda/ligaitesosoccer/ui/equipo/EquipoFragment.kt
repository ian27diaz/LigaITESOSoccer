package ian.meda.ligaitesosoccer.ui.equipo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterEquipo
import ian.meda.ligaitesosoccer.beans.Jugador

class EquipoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_equipo, container, false)
        val recyclerView : RecyclerView = root.findViewById<RecyclerView>(R.id.fragment_equipo_recyclerview)
        var jugadores : List<Jugador> = arrayListOf(
            Jugador("Ian Diaz Meda", "710007", "ISC", 19, 0, 0),
            Jugador("Alberto Ortiz", "710007", "IM", 10, 0, 1),
            Jugador("Alessandro Pallaro Gomez", "710207", "ISC", 19, 0, 0),
            Jugador("Hector Chavez Morales", "710067", "ISC", 0, 0, 0),
            Jugador("Alexis Salgado Ramirez", "711007", "ISC", 0, 0, 0),
            Jugador("Joaquin Avalos Guzman", "710697", "ISC", 0, 3, 0),
            Jugador("Erick de Santiago", "690020", "ISC", 20, 0, 0),
            Jugador("Ivan Piza", "159124", "ISC", 5, 0, 4),
            Jugador("Luthe", "159123", "ISC", 4, 0, 8)
        )


        recyclerView.adapter = AdapterEquipo(jugadores)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        PagerSnapHelper().attachToRecyclerView(recyclerView)
        return root
    }
}