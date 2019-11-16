package ian.meda.ligaitesosoccer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.beans.Jugador

class AdapterIngresarJugadores (private val jugadores: ArrayList<Jugador>): RecyclerView.Adapter<NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingresa_jugador, parent, false)
        return NameViewHolder(view)
    }

    override fun getItemCount(): Int = jugadores.size

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(jugadores[position])
    }
}

class NameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nombreTitle: EditText = view.findViewById(R.id.ingresa_jugador_nombre)
    private val expedienteTitle: EditText = view.findViewById(R.id.ingresa_jugador_expediente)
    private val correoTitle: EditText = view.findViewById(R.id.ingresa_jugador_correo)
    private val carreraTitle: EditText = view.findViewById(R.id.ingresa_jugador_carrera)


    fun bind(jugador: Jugador) {
        nombreTitle.setText(jugador.nombre)
        expedienteTitle.setText(jugador.expediente)
        correoTitle.setText(jugador.email)
    }
}