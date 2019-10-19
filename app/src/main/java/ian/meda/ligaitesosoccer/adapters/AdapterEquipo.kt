package ian.meda.ligaitesosoccer.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.beans.Jugador

class AdapterEquipo(var users : List<Jugador>): RecyclerView.Adapter<jugadorViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): jugadorViewHolder {
        Log.d("AdapterEquipo", users.size.toString())
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_jugador, parent, false)
        return jugadorViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: jugadorViewHolder, position: Int) {
        holder.bind(users[position])
    }
}

class jugadorViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val nombreJugador = view.findViewById<TextView>(R.id.item_jugador_nombreJugador)
    private val expedienteCarrera = view.findViewById<TextView>(R.id.item_jugador_expediente_y_carrera)
    private val goles = view.findViewById<TextView>(R.id.item_jugador_goles)
    private val amarillas = view.findViewById<TextView>(R.id.item_jugador_amarillas)
    private val rojas = view.findViewById<TextView>(R.id.item_jugador_rojas)
    fun bind(jugador : Jugador){
        nombreJugador.setText(jugador.nombre)
        expedienteCarrera.setText(jugador.expediente + " " + jugador.carrera)
        goles.setText("Goles: " + jugador.goles)
        amarillas.setText("Amarillas: " + jugador.tarjetasAmarillas)
        rojas.setText("Rojas: " + jugador.tarjetasRojas)
    }
}