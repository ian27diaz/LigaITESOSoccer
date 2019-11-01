package ian.meda.ligaitesosoccer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseObject
import ian.meda.ligaitesosoccer.R

class AdapterGoleadores (private val goleadores: List<ParseObject>): RecyclerView.Adapter<GoleadoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoleadoresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_goleador, parent, false)
        return GoleadoresViewHolder(view)
    }

    override fun getItemCount(): Int = goleadores.size

    override fun onBindViewHolder(holder: GoleadoresViewHolder, position: Int) {
        holder.bind(goleadores[position], position)
    }
}

class GoleadoresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val positionTitle: TextView = view.findViewById(R.id.goleador_POS)
    private val nombreTitle: TextView = view.findViewById(R.id.goleador_nombre)
    private val equipoTitle: TextView = view.findViewById(R.id.goleador_equipo)
    private val golesTitle: TextView = view.findViewById(R.id.goleador_goles)
    private val imagePhoto: ImageView = view.findViewById(R.id.goleador_foto)
    private val escudoTitle: ImageView = view.findViewById(R.id.goleador_equipo_escudo)
    private val renglon: View = view
    private var rm = Glide.with(view)
    private var rm2 = Glide.with(view)



    fun bind(goleador: ParseObject, position: Int) {
        positionTitle.text = (position+1).toString()
        nombreTitle.text = goleador.getString("Nombre")
        equipoTitle.text = goleador.getParseObject("IDEquipo")?.getString("nombre")
        golesTitle.text = goleador.getInt("GolesTotales").toString()

        val imagenParse = goleador.getParseFile("Foto")
        val imagenParse2 = goleador.getParseObject("IDEquipo")?.getParseFile("escudo")

        rm.load(imagenParse!!.url).into(imagePhoto)
        rm2.load(imagenParse2!!.url).into(escudoTitle)



        if ((position%2)==1){
            renglon.setBackgroundResource(R.drawable.gris1)
        }else{
            renglon.setBackgroundResource(R.drawable.gris2)
        }
    }
}