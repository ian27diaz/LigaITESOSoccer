package ian.meda.ligaitesosoccer.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseObject
import ian.meda.ligaitesosoccer.AprobarEquipo
import ian.meda.ligaitesosoccer.R

class AdapterEquiposPendientes(private val equipospendientes: List<ParseObject>): RecyclerView.Adapter<EquiposPendientesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquiposPendientesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_equipo_pendiente, parent, false)
        return EquiposPendientesViewHolder(view)
    }

    override fun getItemCount(): Int = equipospendientes.size

    override fun onBindViewHolder(holder: EquiposPendientesViewHolder, position: Int) {
        holder.bind(equipospendientes[position])
    }
}

class EquiposPendientesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val equipoButton: Button = view.findViewById(R.id.equipo_pendiente_button)
    val context = view.getContext()
    val intent = Intent(context, AprobarEquipo::class.java)

    fun bind(equipo: ParseObject) {
        equipoButton.text = equipo.getString("nombre")

        equipoButton.setOnClickListener {

            intent.putExtra("equipo", equipo.getString("nombre"))
                context.startActivity(intent)
        }
    }



}