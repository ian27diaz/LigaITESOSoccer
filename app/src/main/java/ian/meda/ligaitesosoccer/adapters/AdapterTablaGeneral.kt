package ian.meda.ligaitesosoccer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.R
import org.jetbrains.anko.backgroundColor

class AdapterTablaGeneral (private val equipos: ArrayList<HashMap<String, String>>): RecyclerView.Adapter<EquipoTablaViewHolder>() {

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


    fun bind(equipo: HashMap<String, String>, position: Int) {
        positionTitle.text = (position+1).toString()
        nombreTitle.text = equipo.get("nombre")
        PJTitle.text = equipo.get("PJ")
        PGTitle.text = equipo.get("PG")
        PETitle.text = equipo.get("PE")
        PPTitle.text = equipo.get("PP")
        GFTitle.text = equipo.get("GF")
        GCTitle.text = equipo.get("GC")
        DGTitle.text = equipo.get("DG")
        PTSTitle.text = equipo.get("PTS")

        if ((position%2)==1){
            renglon.setBackgroundResource(R.drawable.gris1)
        }else{
            renglon.setBackgroundResource(R.drawable.gris2)
        }


    }
}