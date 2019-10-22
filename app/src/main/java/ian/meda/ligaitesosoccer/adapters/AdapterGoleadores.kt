package ian.meda.ligaitesosoccer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.R

class AdapterGoleadores (private val goleadores: ArrayList<HashMap<String, String>>): RecyclerView.Adapter<GoleadoresViewHolder>() {

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
    private val renglon: View = view


    fun bind(goleador: HashMap<String, String>, position: Int) {
        positionTitle.text = (position+1).toString()
        nombreTitle.text = goleador.get("Nombre")
        equipoTitle.text = goleador.get("Equipo")
        golesTitle.text = goleador.get("Goles")

        if ((position%2)==1){
            renglon.setBackgroundResource(R.drawable.gris1)
        }else{
            renglon.setBackgroundResource(R.drawable.gris2)
        }
    }
}