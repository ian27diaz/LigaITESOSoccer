package ian.meda.ligaitesosoccer.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseObject
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.beans.Jugador

class AdapterEquipo(var users : List<ParseObject>): RecyclerView.Adapter<jugadorViewHolder>(){
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
    val nombreJugador = view.findViewById<TextView>(R.id.item_jugador_nombreJugador)
    val expedienteCarrera = view.findViewById<TextView>(R.id.item_jugador_expediente_y_carrera)
    val goles = view.findViewById<TextView>(R.id.item_jugador_goles)
    val amarillas = view.findViewById<TextView>(R.id.item_jugador_amarillas)
    val rojas = view.findViewById<TextView>(R.id.item_jugador_rojas)
    val imagen = view.findViewById<ImageView>(R.id.item_jugador_imagen)
    val glide = Glide.with(view)
    fun bind(jugador : ParseObject){
        nombreJugador.setText(jugador.getString("Nombre"))
        expedienteCarrera.setText(jugador.getString("Expediente"))
        goles.setText("Goles: ${jugador.getNumber("GolesTotales")}")
        amarillas.setText("Amarillas: 0")
        rojas.setText("Rojas: 0")
        glide.load(jugador.getParseFile("Foto")!!.url).into(imagen)
    }
}