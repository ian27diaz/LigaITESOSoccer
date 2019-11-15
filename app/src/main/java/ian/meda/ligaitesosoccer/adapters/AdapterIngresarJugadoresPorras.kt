package ian.meda.ligaitesosoccer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.R

class AdapterIngresarJugadoresPorras (private val jugadores: ArrayList<HashMap<String, String>>): RecyclerView.Adapter<NameViewHolders>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolders {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingresa_jugador_porras, parent, false)
        return NameViewHolders(view)
    }

    override fun getItemCount(): Int = jugadores.size

    override fun onBindViewHolder(holder: NameViewHolders, position: Int) {
        holder.bind(jugadores[position])
    }
}

class NameViewHolders(view: View) : RecyclerView.ViewHolder(view) {
    private val nombreTitle: EditText = view.findViewById(R.id.ingresa_jugador_nombre)
    private val expedienteTitle: EditText = view.findViewById(R.id.ingresa_jugador_expediente)
    private val correoTitle: EditText = view.findViewById(R.id.ingresa_jugador_correo)
    private val carreraTitle: EditText = view.findViewById(R.id.ingresa_jugador_carrera)


    fun bind(jugador: HashMap<String, String>) {
        nombreTitle.setText(jugador.get("nombre"))
        expedienteTitle.setText(jugador.get("expediente"))
        correoTitle.setText(jugador.get("correo"))
        carreraTitle.setText(jugador.get("carrera"))


    }
}