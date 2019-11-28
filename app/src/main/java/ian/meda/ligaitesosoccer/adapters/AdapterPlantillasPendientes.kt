package ian.meda.ligaitesosoccer.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseObject
import ian.meda.ligaitesosoccer.IngresaJugadorPorras
import ian.meda.ligaitesosoccer.R

class AdapterPlantillasPendientes (private val plantillaspendientes: List<ParseObject>): RecyclerView.Adapter<PlantillasPendientesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantillasPendientesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_equipo_pendiente, parent, false)
        return PlantillasPendientesViewHolder(view)
    }

    override fun getItemCount(): Int = plantillaspendientes.size

    override fun onBindViewHolder(holder: PlantillasPendientesViewHolder, position: Int) {
        holder.bind(plantillaspendientes[position])
    }
}

class PlantillasPendientesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val equipoButton: Button = view.findViewById(R.id.equipo_pendiente_button)
    val context = view.getContext()
    val intent = Intent(context, IngresaJugadorPorras::class.java)

    fun bind(equipo: ParseObject) {
        equipoButton.text = equipo.getString("nombre")
        if (equipo.getBoolean("accionPendiente")== true){
            equipoButton.setBackgroundResource(R.drawable.rojo)
        }else{
            equipoButton.setBackgroundResource(R.drawable.verde)
        }

        equipoButton.setOnClickListener {

            intent.putExtra("equipo", equipo.objectId)
            context.startActivity(intent)
        }
    }

}