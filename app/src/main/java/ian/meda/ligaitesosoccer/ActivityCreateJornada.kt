package ian.meda.ligaitesosoccer

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import org.jetbrains.anko.doAsync
import android.widget.ArrayAdapter
import com.parse.*
import java.util.*


class ActivityCreateJornada : AppCompatActivity(), View.OnClickListener {



    private lateinit var jornadaNumber: TextView
    private lateinit var match1team1: Spinner
    private lateinit var match1result: Button
    private lateinit var match1team2: Spinner

    private lateinit var match2team1: Spinner
    private lateinit var match2result: Button
    private lateinit var match2team2: Spinner

    private lateinit var match3team1: Spinner
    private lateinit var match3result: Button
    private lateinit var match3team2: Spinner

    private lateinit var match4team1: Spinner
    private lateinit var match4result: Button
    private lateinit var match4team2: Spinner

    private lateinit var match5team1: Spinner
    private lateinit var match5result: Button
    private lateinit var match5team2: Spinner

    private lateinit var match6team1: Spinner
    private lateinit var match6result: Button
    private lateinit var match6team2: Spinner

    private lateinit var match7team1: Spinner
    private lateinit var match7result: Button
    private lateinit var match7team2: Spinner

    private lateinit var match8team1: Spinner
    private lateinit var match8result: Button
    private lateinit var match8team2: Spinner
    private lateinit var dateSelected: Date
    private lateinit var aceptarBtn: Button
    private lateinit var cancelarBtn: Button
    private lateinit var equiposLista: MutableList<ParseObject>
    var newJornadaNumber : Int = 0

    private val newJornadaEnfrentamientos : MutableList<ParseObject> =
        arrayListOf(
            ParseObject("JornadaEnfrentamiento"),
            ParseObject("JornadaEnfrentamiento"),
            ParseObject("JornadaEnfrentamiento"),
            ParseObject("JornadaEnfrentamiento"),
            ParseObject("JornadaEnfrentamiento"),
            ParseObject("JornadaEnfrentamiento"),
            ParseObject("JornadaEnfrentamiento"),
            ParseObject("JornadaEnfrentamiento"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_jornada)
        newJornadaNumber = intent.getIntExtra("jornadaNumber", 0)
        jornadaNumber = findViewById(R.id.activity_create_jornada_jornada_number)
        jornadaNumber.text = "Jornada ${newJornadaNumber + 1}"

        aceptarBtn = findViewById(R.id.activity_create_jornada_aceptar)
        cancelarBtn = findViewById(R.id.activity_create_jornada_cancelar)

        aceptarBtn.setOnClickListener(this)
        cancelarBtn.setOnClickListener(this)

        match1team1 = findViewById(R.id.activity_create_jornada_enfrentamiento1_equipo1)
        match1result = findViewById(R.id.activity_create_jornada_enfrentamiento1_resultado_fecha)
        match1result.setOnClickListener(this)
        match1team2 = findViewById(R.id.activity_create_jornada_enfrentamiento1_equipo2)


        match2team1 = findViewById(R.id.activity_create_jornada_enfrentamiento2_equipo1)
        match2result = findViewById(R.id.activity_create_jornada_enfrentamiento2_resultado_fecha)
        match2result.setOnClickListener(this)
        match2team2 = findViewById(R.id.activity_create_jornada_enfrentamiento2_equipo2)

        match3team1 = findViewById(R.id.activity_create_jornada_enfrentamiento3_equipo1)
        match3result = findViewById(R.id.activity_create_jornada_enfrentamiento3_resultado_fecha)
        match3result.setOnClickListener(this)
        match3team2 = findViewById(R.id.activity_create_jornada_enfrentamiento3_equipo2)

        match4team1 = findViewById(R.id.activity_create_jornada_enfrentamiento4_equipo1)
        match4result = findViewById(R.id.activity_create_jornada_enfrentamiento4_resultado_fecha)
        match4result.setOnClickListener(this)
        match4team2 = findViewById(R.id.activity_create_jornada_enfrentamiento4_equipo2)

        match5team1 = findViewById(R.id.activity_create_jornada_enfrentamiento5_equipo1)
        match5result = findViewById(R.id.activity_create_jornada_enfrentamiento5_resultado_fecha)
        match5result.setOnClickListener(this)
        match5team2 = findViewById(R.id.activity_create_jornada_enfrentamiento5_equipo2)

        match6team1 = findViewById(R.id.activity_create_jornada_enfrentamiento6_equipo1)
        match6result = findViewById(R.id.activity_create_jornada_enfrentamiento6_resultado_fecha)
        match6result.setOnClickListener(this)
        match6team2 = findViewById(R.id.activity_create_jornada_enfrentamiento6_equipo2)

        match7team1 = findViewById(R.id.activity_create_jornada_enfrentamiento7_equipo1)
        match7result = findViewById(R.id.activity_create_jornada_enfrentamiento7_resultado_fecha)
        match7result.setOnClickListener(this)
        match7team2 = findViewById(R.id.activity_create_jornada_enfrentamiento7_equipo2)

        match8team1 = findViewById(R.id.activity_create_jornada_enfrentamiento8_equipo1)
        match8result = findViewById(R.id.activity_create_jornada_enfrentamiento8_resultado_fecha)
        match8result.setOnClickListener(this)
        match8team2 = findViewById(R.id.activity_create_jornada_enfrentamiento8_equipo2)

        doAsync {
            val equiposQuery = ParseQuery.getQuery<ParseObject>("Equipo")
            val spinnerItems = arrayListOf<String>()
            equiposQuery.findInBackground(object: FindCallback<ParseObject> {
                override fun done(equipos: MutableList<ParseObject>?, e: ParseException?) {
                    equiposLista = equipos!!
                    for(equipo:ParseObject in equipos!!) {
                        spinnerItems.add(equipo.getString("nombre")!!)
                    }
                    val array = arrayOfNulls<String>(spinnerItems.size)
                    spinnerItems.toArray(array)
                    val adapter =
                        ArrayAdapter(this@ActivityCreateJornada , android.R.layout.simple_spinner_dropdown_item, array)

                    match1team1.adapter = adapter
                    match1team2.adapter = adapter

                    match2team1.adapter = adapter
                    match2team2.adapter = adapter

                    match3team1.adapter = adapter
                    match3team2.adapter = adapter

                    match4team1.adapter = adapter
                    match4team2.adapter = adapter

                    match5team1.adapter = adapter
                    match5team2.adapter = adapter

                    match6team1.adapter = adapter
                    match6team2.adapter = adapter

                    match7team1.adapter = adapter
                    match7team2.adapter = adapter

                    match8team1.adapter = adapter
                    match8team2.adapter = adapter

                }

            })
        }
    }

    fun getEquipoId(nombreEquipo : String) : String {
        Log.v("ActivityCreateJornadaGG", "Nombre received: $nombreEquipo")
        for(equipo: ParseObject in equiposLista) {
            var currName = equipo.getString("nombre")
            Log.v("ActivityCreateJornadaGG", "Id: ${equipo.objectId}, nombre: $currName")
            if(currName == nombreEquipo) {
                Log.v("ActivityCreateJornadaGG", "es correcta.")
                return equipo.objectId
            }
        }
        return ""
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.activity_create_jornada_aceptar -> {
                newJornadaEnfrentamientos[0].put("local",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match1team1.selectedItem.toString())))
                newJornadaEnfrentamientos[0].put("visitante",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match1team2.selectedItem.toString())))
                newJornadaEnfrentamientos[0].put("jornada", newJornadaNumber + 1)
                newJornadaEnfrentamientos[0].put("equipo1", match1team1.selectedItem.toString())
                newJornadaEnfrentamientos[0].put("equipo2", match1team2.selectedItem.toString())
                newJornadaEnfrentamientos[0].put("golesEquipo1", 0)
                newJornadaEnfrentamientos[0].put("golesEquipo2", 0)
                newJornadaEnfrentamientos[0].put("editado", false)

                newJornadaEnfrentamientos[1].put("local",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match2team1.selectedItem.toString())))
                newJornadaEnfrentamientos[1].put("visitante",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match2team2.selectedItem.toString())))
                newJornadaEnfrentamientos[1].put("jornada", newJornadaNumber + 1)
                newJornadaEnfrentamientos[1].put("equipo1", match2team1.selectedItem.toString())
                newJornadaEnfrentamientos[1].put("equipo2", match2team2.selectedItem.toString())
                newJornadaEnfrentamientos[1].put("golesEquipo1", 0)
                newJornadaEnfrentamientos[1].put("golesEquipo2", 0)
                newJornadaEnfrentamientos[1].put("editado", false)

                newJornadaEnfrentamientos[2].put("local",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match3team1.selectedItem.toString())))
                newJornadaEnfrentamientos[2].put("visitante",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match3team2.selectedItem.toString())))
                newJornadaEnfrentamientos[2].put("jornada", newJornadaNumber + 1)
                newJornadaEnfrentamientos[2].put("equipo1", match3team1.selectedItem.toString())
                newJornadaEnfrentamientos[2].put("equipo2", match3team2.selectedItem.toString())
                newJornadaEnfrentamientos[2].put("golesEquipo1", 0)
                newJornadaEnfrentamientos[2].put("golesEquipo2", 0)
                newJornadaEnfrentamientos[2].put("editado", false)

                newJornadaEnfrentamientos[3].put("local",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match4team1.selectedItem.toString())))
                newJornadaEnfrentamientos[3].put("visitante",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match4team2.selectedItem.toString())))
                newJornadaEnfrentamientos[3].put("jornada", newJornadaNumber + 1)
                newJornadaEnfrentamientos[3].put("equipo1", match4team1.selectedItem.toString())
                newJornadaEnfrentamientos[3].put("equipo2", match4team2.selectedItem.toString())
                newJornadaEnfrentamientos[3].put("golesEquipo1", 0)
                newJornadaEnfrentamientos[3].put("golesEquipo2", 0)
                newJornadaEnfrentamientos[3].put("editado", false)

                newJornadaEnfrentamientos[4].put("local",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match5team1.selectedItem.toString())))
                newJornadaEnfrentamientos[4].put("visitante",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match5team2.selectedItem.toString())))
                newJornadaEnfrentamientos[4].put("jornada", newJornadaNumber + 1)
                newJornadaEnfrentamientos[4].put("equipo1", match5team1.selectedItem.toString())
                newJornadaEnfrentamientos[4].put("equipo2", match5team2.selectedItem.toString())
                newJornadaEnfrentamientos[4].put("golesEquipo1", 0)
                newJornadaEnfrentamientos[4].put("golesEquipo2", 0)
                newJornadaEnfrentamientos[4].put("editado", false)

                newJornadaEnfrentamientos[5].put("local",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match6team1.selectedItem.toString())))
                newJornadaEnfrentamientos[5].put("visitante",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match6team2.selectedItem.toString())))
                newJornadaEnfrentamientos[5].put("jornada", newJornadaNumber + 1)
                newJornadaEnfrentamientos[5].put("equipo1", match6team1.selectedItem.toString())
                newJornadaEnfrentamientos[5].put("equipo2", match6team2.selectedItem.toString())
                newJornadaEnfrentamientos[5].put("golesEquipo1", 0)
                newJornadaEnfrentamientos[5].put("golesEquipo2", 0)
                newJornadaEnfrentamientos[5].put("editado", false)

                newJornadaEnfrentamientos[6].put("local",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match7team1.selectedItem.toString())))
                newJornadaEnfrentamientos[6].put("visitante",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match7team2.selectedItem.toString())))
                newJornadaEnfrentamientos[6].put("jornada", newJornadaNumber + 1)
                newJornadaEnfrentamientos[6].put("equipo1", match7team1.selectedItem.toString())
                newJornadaEnfrentamientos[6].put("equipo2", match7team2.selectedItem.toString())
                newJornadaEnfrentamientos[6].put("golesEquipo1", 0)
                newJornadaEnfrentamientos[6].put("golesEquipo2", 0)
                newJornadaEnfrentamientos[6].put("editado", false)

                newJornadaEnfrentamientos[7].put("local",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match8team1.selectedItem.toString())))
                newJornadaEnfrentamientos[7].put("visitante",
                    ParseObject.createWithoutData("Equipo", getEquipoId(match8team2.selectedItem.toString())))
                newJornadaEnfrentamientos[7].put("jornada", newJornadaNumber + 1)
                newJornadaEnfrentamientos[7].put("equipo1", match8team1.selectedItem.toString())
                newJornadaEnfrentamientos[7].put("equipo2", match8team2.selectedItem.toString())
                newJornadaEnfrentamientos[7].put("golesEquipo1", 0)
                newJornadaEnfrentamientos[7].put("golesEquipo2", 0)
                newJornadaEnfrentamientos[7].put("editado", false)
                var contador = 0
                for(enfrentamiento: ParseObject in newJornadaEnfrentamientos) {
                    enfrentamiento.saveInBackground(SaveCallback { e ->
                        if (e == null) {
                            contador++
                            if(contador == 8) finish()
                            Log.v("ActivityCreateJornada", "Enfrentamiento guardado")
                        } else {
                            Log.v("ActivityCreateJornada", "Enfrentamiento Error -> $e")
                        }
                    })
                }
            }
            R.id.activity_create_jornada_cancelar -> {
                finish()
            }
            R.id.activity_create_jornada_enfrentamiento1_resultado_fecha -> {
                openDateDialog(0)
            }
            R.id.activity_create_jornada_enfrentamiento2_resultado_fecha -> {
                openDateDialog(1)
            }
            R.id.activity_create_jornada_enfrentamiento3_resultado_fecha -> {
                openDateDialog(2)
            }
            R.id.activity_create_jornada_enfrentamiento4_resultado_fecha -> {
                openDateDialog(3)
            }
            R.id.activity_create_jornada_enfrentamiento5_resultado_fecha -> {
                openDateDialog(4)
            }
            R.id.activity_create_jornada_enfrentamiento6_resultado_fecha -> {
                openDateDialog(5)
            }
            R.id.activity_create_jornada_enfrentamiento7_resultado_fecha -> {
                openDateDialog(6)
            }
            R.id.activity_create_jornada_enfrentamiento8_resultado_fecha -> {
                openDateDialog(7)
            }
        }
    }

    private fun openDateDialog(index: Int) {
        val c = Calendar.getInstance()
        val day    = c.get(Calendar.DAY_OF_MONTH)
        val year   = c.get(Calendar.YEAR)
        val month  = c.get(Calendar.MONTH)

        val datePick = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->
                val c = Calendar.getInstance()
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, month)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                c.set(Calendar.HOUR_OF_DAY, 0)
                c.set(Calendar.MINUTE, 0)
                c.set(Calendar.SECOND, 0)
                c.set(Calendar.MILLISECOND, 0)

                dateSelected = c.time
                newJornadaEnfrentamientos[index].put("fechaHora", dateSelected)
            }, year, month, day)

        datePick.show()
    }
}
