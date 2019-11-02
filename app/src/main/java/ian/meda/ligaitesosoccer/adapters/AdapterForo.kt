package ian.meda.ligaitesosoccer.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseObject
import ian.meda.ligaitesosoccer.R

class AdapterForo(var mensajes : List<ParseObject>): RecyclerView.Adapter<MensajeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajeViewHolder {
        Log.d("AdapterForo", mensajes.size.toString())
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_mensaje, parent, false)
        return MensajeViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int = mensajes.size

    override fun onBindViewHolder(holder: MensajeViewHolder, position: Int) {
        holder.bind(mensajes[position])
    }
}

class MensajeViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val autorMensaje : TextView = view.findViewById(R.id.item_mensaje_usuario)
    val fechaMensaje : TextView = view.findViewById(R.id.item_mensaje_fecha)
    val contenidoMensaje : TextView = view.findViewById(R.id.item_mensaje_mensaje)
    fun bind(mensaje : ParseObject){
        autorMensaje.text = mensaje.getParseObject("IDUser")?.getString("username") + " dijo:"
        fechaMensaje.text = mensaje.getDate("Fecha").toString().replace("CDT ", "")
        contenidoMensaje.text = mensaje.getString("Mensaje")
    }
}