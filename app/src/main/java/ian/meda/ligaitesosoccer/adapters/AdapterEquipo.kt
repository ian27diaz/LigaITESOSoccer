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


class AdapterEquipo(private var users : List<ParseObject>): RecyclerView.Adapter<JugadorViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JugadorViewHolder {
        Log.d("AdapterEquipo", users.size.toString())
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_jugador, parent, false)
        return JugadorViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: JugadorViewHolder, position: Int) {
        holder.bind(users[position])
    }
}

class JugadorViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val nombreJugador = view.findViewById<TextView>(R.id.item_jugador_nombreJugador)
    private val expedienteCarrera = view.findViewById<TextView>(R.id.item_jugador_expediente_y_carrera)
    private val goles = view.findViewById<TextView>(R.id.item_jugador_goles)
    private val amarillas = view.findViewById<TextView>(R.id.item_jugador_amarillas)
    private val rojas = view.findViewById<TextView>(R.id.item_jugador_rojas)
    private val imagen = view.findViewById<ImageView>(R.id.item_jugador_imagen)
    private val glide = Glide.with(view)

    fun bind(jugador : ParseObject){
        val golesNumber = jugador.getNumber("GolesTotales")
        nombreJugador.text = jugador.getString("Nombre")
        expedienteCarrera.text = jugador.getString("Expediente")
        goles.text = "Goles: $golesNumber"
        amarillas.text = "Amarillas: ${jugador.getNumber("Amarillas")}"
        rojas.text = "Rojas: ${jugador.getNumber("Rojas")}"
        glide.load(jugador.getParseFile("Foto")!!.url).into(imagen)
    }
}