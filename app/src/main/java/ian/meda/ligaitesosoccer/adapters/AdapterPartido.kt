package ian.meda.ligaitesosoccer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseObject
import ian.meda.ligaitesosoccer.R
import org.jetbrains.anko.image

class AdapterPartido (private val eventos: List<ParseObject>): RecyclerView.Adapter<EventosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_evento, parent, false)
        return EventosViewHolder(view)
    }

    override fun getItemCount(): Int = eventos.size

    override fun onBindViewHolder(holder: EventosViewHolder, position: Int) {
        holder.bind(eventos[position])
    }
}

class EventosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val jugadorTitle: TextView = view.findViewById(R.id.item_evento_jugador)
    private val iconoFoto: ImageView = view.findViewById(R.id.item_evento_icono)

    fun bind(evento: ParseObject) {

        jugadorTitle.text = evento.getParseObject("jugadores")?.getString("nombre")

                //when(evento.getString("tipo")){
                 //   "amarilla" -> {iconoFoto.image = R.drawable.hector_chavez.toDrawable()}
                //    "roja" -> {iconoFoto.image = R.drawable.elsiguientemessi.toDrawable()}
                 //   "gol" -> {iconoFoto.image = R.drawable.barcelonalogo.toDrawable()}
                //}



    }
}