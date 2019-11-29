package ian.meda.ligaitesosoccer.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.beans.Enfrentamiento
import ian.meda.ligaitesosoccer.PartidoActivity
import ian.meda.ligaitesosoccer.utils.SESSION_USERTYPE
import ian.meda.ligaitesosoccer.utils.SHARED_PREFERENCES
import java.util.*
import kotlin.collections.ArrayList

class AdapterCalendario(var jornadas : ArrayList<MutableList<Enfrentamiento>>,
                        var addButtonListener: (Int) -> Unit) : RecyclerView.Adapter<JornadaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JornadaViewHolder {
        Log.d("AdapterCalendario", jornadas.size.toString())
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_jornada, parent, false)
        return JornadaViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int = jornadas.size

    override fun onBindViewHolder(holder: JornadaViewHolder, position: Int) {
        holder.bind(jornadas[position], position, jornadas.size, addButtonListener)
    }
}

class JornadaViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val vista: View = view

    private val lineaDivisora: View = view.findViewById(R.id.item_jornada_linea_divisora)

    private val addButton: ImageButton = view.findViewById(R.id.item_jornada_addButton_button)

    private val jornadaNumber: TextView = view.findViewById(R.id.fragment_calendario_jornada_number)
    private val match1team1: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento1_equipo1)
    private val match1result: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento1_resultado_fecha)
    private val match1team2: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento1_equipo2)
    private val match1ver: Button = view.findViewById(R.id.fragment_calendario_enfrentamiento1_ver)


    private val match2team1: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento2_equipo1)
    private val match2result: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento2_resultado_fecha)
    private val match2team2: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento2_equipo2)
    private val match2ver: Button = view.findViewById(R.id.fragment_calendario_enfrentamiento2_ver)

    private val match3team1: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento3_equipo1)
    private val match3result: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento3_resultado_fecha)
    private val match3team2: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento3_equipo2)
    private val match3ver: Button = view.findViewById(R.id.fragment_calendario_enfrentamiento3_ver)

    private val match4team1: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento4_equipo1)
    private val match4result: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento4_resultado_fecha)
    private val match4team2: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento4_equipo2)
    private val match4ver: Button = view.findViewById(R.id.fragment_calendario_enfrentamiento4_ver)

    private val match5team1: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento5_equipo1)
    private val match5result: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento5_resultado_fecha)
    private val match5team2: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento5_equipo2)
    private val match5ver: Button = view.findViewById(R.id.fragment_calendario_enfrentamiento5_ver)

    private val match6team1: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento6_equipo1)
    private val match6result: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento6_resultado_fecha)
    private val match6team2: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento6_equipo2)
    private val match6ver: Button = view.findViewById(R.id.fragment_calendario_enfrentamiento6_ver)

    private val match7team1: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento7_equipo1)
    private val match7result: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento7_resultado_fecha)
    private val match7team2: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento7_equipo2)
    private val match7ver: Button = view.findViewById(R.id.fragment_calendario_enfrentamiento7_ver)

    private val match8team1: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento8_equipo1)
    private val match8result: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento8_resultado_fecha)
    private val match8team2: TextView = view.findViewById(R.id.fragment_calendario_enfrentamiento8_equipo2)
    private val match8ver: Button = view.findViewById(R.id.fragment_calendario_enfrentamiento8_ver)
    private val context: Context = view.context
    private val intent = Intent(context, PartidoActivity::class.java)
    val sharedPreferences = context!!.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
    val sessionId = sharedPreferences.getString(SESSION_USERTYPE, "")


    fun bind(jornada : MutableList<Enfrentamiento>,
             position: Int,
             size: Int,
             addButtonListener: (Int) -> Unit){

        if (sessionId == "ADMIN"){
            match1ver.text = "Editar"
            match2ver.text = "Editar"
            match3ver.text = "Editar"
            match4ver.text = "Editar"
            match5ver.text = "Editar"
            match6ver.text = "Editar"
            match7ver.text = "Editar"
            match8ver.text = "Editar"
        }

        if ((position%2)==1){
            vista.setBackgroundResource(R.drawable.calendario_gradient_background1)
        }else{
            vista.setBackgroundResource(R.drawable.calendario_gradient_background2)
        }
        if (sessionId == "ADMIN" && position == size - 1) {
            addButton.setOnClickListener {
                addButtonListener(position)
            }

            lineaDivisora.visibility = View.GONE
            jornadaNumber.visibility = View.GONE
            match1team1.visibility = View.GONE
            match1result.visibility = View.GONE
            match1team2.visibility = View.GONE
            match1ver.visibility = View.GONE

            match2team1.visibility = View.GONE
            match2result.visibility = View.GONE
            match2team2.visibility = View.GONE
            match2ver.visibility = View.GONE

            match3team1.visibility = View.GONE
            match3result.visibility = View.GONE
            match3team2.visibility = View.GONE
            match3ver.visibility = View.GONE

            match4team1.visibility = View.GONE
            match4result.visibility = View.GONE
            match4team2.visibility = View.GONE
            match4ver.visibility = View.GONE

            match5team1.visibility = View.GONE
            match5result.visibility = View.GONE
            match5team2.visibility = View.GONE
            match5ver.visibility = View.GONE

            match6team1.visibility = View.GONE
            match6result.visibility = View.GONE
            match6team2.visibility = View.GONE
            match6ver.visibility = View.GONE

            match7team1.visibility = View.GONE
            match7result.visibility = View.GONE
            match7team2.visibility = View.GONE
            match7ver.visibility = View.GONE

            match8team1.visibility = View.GONE
            match8result.visibility = View.GONE
            match8team2.visibility = View.GONE
            match8ver.visibility = View.GONE
        } else {
            addButton.visibility = View.GONE

            val hoy = Date()
            jornadaNumber.text = "Jornada " + jornada[0].jornadNum
            match1team1.text = jornada[0].equipo1
            var date = jornada[0].fecha
            Log.v("AdaperCalendario", date.toString())
            if (!jornada[0].editado) {
                match1result.text = "${date.date}/${date.month + 1}/${date.year + 1900}"
            } else {
                match1result.setText(jornada.get(0).golesEquipo1.toString() + " - " + jornada.get(0).golesEquipo2.toString())
            }
            match1team2.setText(jornada.get(0).equipo2)

            match1ver.setOnClickListener {
                intent.putExtra("enfrentamiento", jornada.get(0).idEnfrentamiento)
                context.startActivity(intent)
            }

            match2team1.setText(jornada.get(1).equipo1)
            date = jornada.get(1).fecha
            if (!jornada[1].editado) {
                match2result.setText("${date.date}/${(date.month + 1)}/${date.year + 1900}")
            } else {
                match2result.setText(jornada.get(1).golesEquipo1.toString() + " - " + jornada.get(1).golesEquipo2.toString())
            }

            match2team2.setText(jornada.get(1).equipo2)
            match2ver.setOnClickListener {
                intent.putExtra("enfrentamiento", jornada.get(1).idEnfrentamiento)
                context.startActivity(intent)
            }

            match3team1.setText(jornada.get(2).equipo1)
            date = jornada.get(2).fecha;
            if (!jornada[2].editado) {
                match3result.setText("${date.date}/${(date.month + 1)}/${date.year + 1900}")
            } else {
                match3result.setText(jornada.get(2).golesEquipo1.toString() + " - " + jornada.get(2).golesEquipo2.toString())
            }
            match3team2.setText(jornada.get(2).equipo2)
            match3ver.setOnClickListener {
                intent.putExtra("enfrentamiento", jornada.get(2).idEnfrentamiento)
                context.startActivity(intent)
            }

            match4team1.setText(jornada.get(3).equipo1)
            date = jornada.get(3).fecha;
            if (!jornada[3].editado) {
                match4result.setText("${date.date}/${(date.month + 1)}/${date.year + 1900}")
            } else {
                match4result.setText(jornada.get(3).golesEquipo1.toString() + " - " + jornada.get(3).golesEquipo2.toString())
            }
            match4team2.setText(jornada.get(3).equipo2)
            match4ver.setOnClickListener {
                intent.putExtra("enfrentamiento", jornada.get(3).idEnfrentamiento)
                context.startActivity(intent)
            }

            match5team1.setText(jornada.get(4).equipo1)
            date = jornada.get(4).fecha;
            if (!jornada[4].editado) {
                match5result.setText("${date.date}/${(date.month + 1)}/${date.year + 1900}")
            } else {
                match5result.setText(jornada.get(4).golesEquipo1.toString() + " - " + jornada.get(4).golesEquipo2.toString())
            }
            match5team2.setText(jornada.get(4).equipo2)
            match5ver.setOnClickListener {
                intent.putExtra("enfrentamiento", jornada.get(4).idEnfrentamiento)
                context.startActivity(intent)
            }

            match6team1.setText(jornada.get(5).equipo1)
            date = jornada.get(5).fecha;
            if (!jornada[5].editado) {
                match6result.setText("${date.date}/${(date.month + 1)}/${date.year + 1900}")
            } else {
                match6result.setText(jornada.get(5).golesEquipo1.toString() + " - " + jornada.get(5).golesEquipo2.toString())
            }
            match6team2.setText(jornada.get(5).equipo2)
            match6ver.setOnClickListener {
                intent.putExtra("enfrentamiento", jornada.get(5).idEnfrentamiento)
                context.startActivity(intent)
            }

            match7team1.setText(jornada.get(6).equipo1)
            date = jornada.get(6).fecha;
            if (!jornada[6].editado) {
                match7result.setText("${date.date}/${(date.month + 1)}/${date.year + 1900}")
            } else {
                match7result.setText(jornada.get(6).golesEquipo1.toString() + " - " + jornada.get(6).golesEquipo2.toString())
            }
            match7team2.setText(jornada.get(6).equipo2)
            match7ver.setOnClickListener {
                intent.putExtra("enfrentamiento", jornada.get(6).idEnfrentamiento)
                context.startActivity(intent)
            }

            match8team1.setText(jornada.get(7).equipo1)
            date = jornada.get(7).fecha;
            if (!jornada[7].editado) {
                match8result.setText("${date.date}/${(date.month + 1)}/${date.year + 1900}")
            } else {
                match8result.setText(jornada.get(7).golesEquipo1.toString() + " - " + jornada.get(7).golesEquipo2.toString())
            }
            match8team2.setText(jornada.get(7).equipo2)
            match8ver.setOnClickListener {
                intent.putExtra("enfrentamiento", jornada.get(7).idEnfrentamiento)
                context.startActivity(intent)
            }
        }

    }
}