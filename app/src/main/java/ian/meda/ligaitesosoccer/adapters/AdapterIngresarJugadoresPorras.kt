package ian.meda.ligaitesosoccer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseObject
import ian.meda.ligaitesosoccer.R

class AdapterIngresarJugadoresPorras (private val jugadores: List<ParseObject>): RecyclerView.Adapter<NameViewHolders>() {

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
    private val nombreTitle: TextView = view.findViewById(R.id.ingresa_jugador_porras_nombre)
    private val expedienteTitle: TextView = view.findViewById(R.id.ingresa_jugador_porras_expediente)
    private val correoTitle: TextView = view.findViewById(R.id.ingresa_jugador_porras_correo)
    private val imagePhoto: ImageView = view.findViewById(R.id.ingresa_jugador_porras_foto)
    private var rm = Glide.with(view)


    fun bind(jugador: ParseObject) {
        val imagenParse = jugador.getParseFile("Foto")

        nombreTitle.text = jugador.getString("Nombre")
        expedienteTitle.text = jugador.getString("Expediente")
        correoTitle.text = jugador.getString("Email")

        rm.load(imagenParse!!.url).into(imagePhoto)

    }
}