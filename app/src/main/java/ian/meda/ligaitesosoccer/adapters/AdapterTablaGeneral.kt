package ian.meda.ligaitesosoccer.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseObject
import ian.meda.ligaitesosoccer.R
import org.jetbrains.anko.backgroundColor

class AdapterTablaGeneral (private val equipos: List<ParseObject>): RecyclerView.Adapter<EquipoTablaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoTablaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_equipo_tabla, parent, false)
        return EquipoTablaViewHolder(view)
    }

    override fun getItemCount(): Int = equipos.size

    override fun onBindViewHolder(holder: EquipoTablaViewHolder, position: Int) {
        holder.bind(equipos[position], position)
    }
}

class EquipoTablaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val positionTitle: TextView = view.findViewById(R.id.equipo_tabla_POS)
    private val nombreTitle: TextView = view.findViewById(R.id.equipo_tabla_nombre)
    private val PJTitle: TextView = view.findViewById(R.id.equipo_tabla_PJ)
    private val PGTitle: TextView = view.findViewById(R.id.equipo_tabla_PG)
    private val PETitle: TextView = view.findViewById(R.id.equipo_tabla_PE)
    private val PPTitle: TextView = view.findViewById(R.id.equipo_tabla_PP)
    private val GFTitle: TextView = view.findViewById(R.id.equipo_tabla_GF)
    private val GCTitle: TextView = view.findViewById(R.id.equipo_tabla_GC)
    private val DGTitle: TextView = view.findViewById(R.id.equipo_tabla_DG)
    private val PTSTitle: TextView = view.findViewById(R.id.equipo_tabla_PTS)
    private val renglon: View = view
    private var rm = Glide.with(view)
    private val imagePhoto: ImageView = view.findViewById(R.id.equipo_tabla_escudo)


    fun bind(equipo: ParseObject, position: Int) {
        positionTitle.text = (position+1).toString()
        nombreTitle.text = equipo.getString("nombre")
        PJTitle.text = equipo.getInt("partidosJugados").toString()
        PGTitle.text = equipo.getInt("partidosGanados").toString()
        PETitle.text = equipo.getInt("partidosEmpatados").toString()
        PPTitle.text = equipo.getInt("partidosPerdidos").toString()
        GFTitle.text = equipo.getInt("golesFavor").toString()
        GCTitle.text = equipo.getInt("golesContra").toString()
        DGTitle.text = equipo.getInt("diferenciaGoles").toString()
        PTSTitle.text = equipo.getInt("puntosTotales").toString()

        val imagenParse = equipo.getParseFile("escudo")
        Log.v("AdapterTablaGeneral", imagenParse!!.url)
        rm.load(imagenParse!!.url).into(imagePhoto)

        if ((position%2)==1){
            renglon.setBackgroundResource(R.drawable.gris1)
        }else{
            renglon.setBackgroundResource(R.drawable.gris2)
        }


    }
}