package ian.meda.ligaitesosoccer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseObject
import ian.meda.ligaitesosoccer.R
import org.jetbrains.anko.image

class AdapterPartido (private val eventos: MutableList<ParseObject>): BaseAdapter() {

    override fun getCount(): Int {
        return eventos.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return eventos[position]
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val rowView = LayoutInflater.from(parent?.context).inflate(R.layout.item_evento, parent, false)

        val titleTextView = rowView.findViewById(R.id.item_evento_jugador) as TextView
        val tipoEvento = rowView.findViewById(R.id.item_evento_icono) as ImageView

        val evento = getItem(position) as ParseObject

        titleTextView.text = evento.getParseObject("jugador")?.getString("Nombre")


        when(evento.getString("evento")){
            "Amarilla" -> {tipoEvento.setImageResource(R.drawable.amarilla)}
            "Roja" -> {tipoEvento.setImageResource(R.drawable.roja)}
            "Gol" -> {tipoEvento.setImageResource(R.drawable.gol)}
        }

        return rowView
    }

}