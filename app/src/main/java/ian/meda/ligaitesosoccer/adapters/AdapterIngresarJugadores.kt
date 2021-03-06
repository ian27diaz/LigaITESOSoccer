package ian.meda.ligaitesosoccer.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.beans.Jugador
import org.jetbrains.anko.singleLine
import org.jetbrains.anko.wrapContent

class AdapterIngresarJugadores (private val jugadores: ArrayList<Jugador>,
                                private val clickListener: (Jugador, ImageView) -> Unit,
                                private val deleteListener: (Jugador) -> Unit): RecyclerView.Adapter<NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingresa_jugador, parent, false)
        return NameViewHolder(view)
    }

    override fun getItemCount(): Int = jugadores.size

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(jugadores[position], clickListener, deleteListener)
    }
}

class NameViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val nombreTitle: EditText = view.findViewById(R.id.ingresa_jugador_nombre)
    private val expedienteTitle: EditText = view.findViewById(R.id.ingresa_jugador_expediente)
    private val correoTitle: EditText = view.findViewById(R.id.ingresa_jugador_correo)
    private val carreraTitle: EditText = view.findViewById(R.id.ingresa_jugador_carrera)
    private val fotoJugador: ImageView = view.findViewById(R.id.ingresa_jugador_foto)
    private val deleteButton: ImageButton = view.findViewById(R.id.ingresa_jugador_delete)
    private val showMoreButton: ImageButton = view.findViewById(R.id.ingresa_jugador_mas_info)
    private val showLessButton: ImageButton = view.findViewById(R.id.ingresa_jugador_menos_info)
    private val expedienteLabel: TextView = view.findViewById(R.id.ingresa_jugador_etiqueta_expediente)
    private val correoLabel: TextView = view.findViewById(R.id.ingresa_jugador_etiqueta_correo)
    private val carreraLabel: TextView = view.findViewById(R.id.ingresa_jugador_etiqueta_carrera)
    private val glide = Glide.with(view)


    fun bind(jugador: Jugador, clickListener: (Jugador, ImageView) -> Unit, deleteListener: (Jugador) -> Unit) {
        nombreTitle.imeOptions = EditorInfo.IME_ACTION_DONE
        nombreTitle.singleLine = true
        expedienteTitle.imeOptions = EditorInfo.IME_ACTION_DONE
        expedienteTitle.singleLine = true
        correoTitle.imeOptions = EditorInfo.IME_ACTION_DONE
        correoTitle.singleLine = true
        carreraTitle.imeOptions = EditorInfo.IME_ACTION_DONE
        carreraTitle.singleLine = true
        

        nombreTitle.setText(jugador.nombre)
        expedienteTitle.setText(jugador.expediente)
        correoTitle.setText(jugador.email)

        if(!jugador.fotoCargada) {
            glide.load(R.drawable.account).into(fotoJugador)
        } else {
            glide.load(jugador.Foto).into(fotoJugador)
        }


        Log.v("AdapterIngJugadores", "Adapter-> Name: ${jugador.nombre}, Parse: ${jugador.Foto}")
        nombreTitle.setOnFocusChangeListener { _ : View, hasFocus ->
            if(!hasFocus){
                jugador.nombre = nombreTitle.text.toString()
                Log.v("AdapterIngJugadores", "Le diste click a ${jugador.nombre}")
            }
        }
        expedienteTitle.setOnFocusChangeListener { _ : View, hasFocus ->
            if(!hasFocus) jugador.expediente = expedienteTitle.text.toString()
        }
        correoTitle.setOnFocusChangeListener { _ : View, hasFocus ->
            if(!hasFocus) jugador.email = correoTitle.text.toString()
        }
        fotoJugador.setOnClickListener {clickListener(jugador, fotoJugador)}

        deleteButton.setOnClickListener{
            deleteListener(jugador)
        }

        showMoreButton.setOnClickListener{
            showMoreButton.visibility = View.INVISIBLE
            showLessButton.visibility = View.VISIBLE
            expedienteLabel.visibility = View.VISIBLE
            expedienteTitle.visibility = View.VISIBLE
            correoLabel.visibility = View.VISIBLE
            correoTitle.visibility = View.VISIBLE
            carreraLabel.visibility = View.VISIBLE
            carreraTitle.visibility = View.VISIBLE
            deleteButton.visibility = View.VISIBLE
        }

        showLessButton.setOnClickListener{
            showLessButton.visibility = View.INVISIBLE
            showMoreButton.visibility = View.VISIBLE
            expedienteLabel.visibility = View.GONE
            expedienteTitle.visibility = View.GONE
            correoLabel.visibility = View.GONE
            correoTitle.visibility = View.GONE
            carreraLabel.visibility = View.GONE
            carreraTitle.visibility = View.GONE
            deleteButton.visibility = View.GONE
        }
    }


}