package ian.meda.ligaitesosoccer.ui.calendario

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import ian.meda.ligaitesosoccer.ActivityCreateJornada
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterCalendario
import ian.meda.ligaitesosoccer.beans.Enfrentamiento
import ian.meda.ligaitesosoccer.utils.SESSION_USERTYPE
import ian.meda.ligaitesosoccer.utils.SHARED_PREFERENCES
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast

class CalendarioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calendario, container, false)

        doAsync{

            val jornadasEnfrentamiento = ParseQuery.getQuery<ParseObject>("JornadaEnfrentamiento")

            jornadasEnfrentamiento.findInBackground(object: FindCallback<ParseObject>{
                val jornadaArray : ArrayList<MutableList<Enfrentamiento>> = arrayListOf(
                    arrayListOf()
                )
                val recyclerView = root.findViewById<RecyclerView>(R.id.fragment_calendario_recyclerview)

                override fun done(enfrentamientos: List<ParseObject>?, e3: ParseException?) {
                    if(e3 == null){
                        Log.v("Enfrentamientos: ", enfrentamientos!!.size.toString())

                        for(enfrentamiento: ParseObject in enfrentamientos!!){
                            val currJornada = enfrentamiento.getNumber("jornada")!!.toInt() - 1

                            val currEnfrentamiento = Enfrentamiento(
                                currJornada + 1,
                                enfrentamiento.getString("equipo1")!!,
                                enfrentamiento.getString("equipo2")!!,
                                enfrentamiento.getNumber("golesEquipo1")!!.toInt(),
                                enfrentamiento.getNumber("golesEquipo2")!!.toInt(),
                                enfrentamiento.objectId,
                                enfrentamiento.getDate("fechaHora")!!
                                )
                            Log.v("CalendarioFragmentTag", "CurrJornada: ${currJornada}")
                            while(currJornada >= jornadaArray.size) {
                                Log.v("CalendarioFragmentTag", "Current size: ${jornadaArray.size}")
                                jornadaArray.add(arrayListOf())
                                Log.v("CalendarioFragmentTag", "After size: ${jornadaArray.size}")
                            }
                            jornadaArray[currJornada].add(currEnfrentamiento)

                        }
                        Log.v("Enfrentamientos map", jornadaArray.toString() + " -\n" + jornadaArray.size)
                        activity?.runOnUiThread {
                            val sharedPreferences = context!!.getSharedPreferences(
                                SHARED_PREFERENCES, Context.MODE_PRIVATE)
                            val sessionId = sharedPreferences.getString(SESSION_USERTYPE, "")
                            if(sessionId.equals("ADMIN")) {
                                jornadaArray.add(arrayListOf())
                            }
                            recyclerView.adapter = AdapterCalendario(jornadaArray,
                                {position: Int -> addButtonClicked(position)})
                            recyclerView.adapter?.notifyDataSetChanged()
                            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            recyclerView.addItemDecoration(LinePagerIndicatorDecoration())
                            PagerSnapHelper().attachToRecyclerView(recyclerView)
                        }
                    }
                }

                fun addButtonClicked(position: Int) {
                    //Dar de alta la Jornada y la Jornada Enfrentamiento
                    //De mientras solo la Jornada-Enfrentamiento
                    Toast.makeText(context, "Position: $position", Toast.LENGTH_LONG).show()
                    val intent: Intent =  Intent(context, ActivityCreateJornada::class.java)
                    intent.putExtra("jornadaNumber", position)
                    startActivity(intent)
                }
            })
        }

        return root
    }

}