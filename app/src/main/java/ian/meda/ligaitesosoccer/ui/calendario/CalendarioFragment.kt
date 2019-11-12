package ian.meda.ligaitesosoccer.ui.calendario

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.adapters.AdapterCalendario
import ian.meda.ligaitesosoccer.beans.Enfrentamiento
import org.jetbrains.anko.doAsync

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
                 arrayListOf(),
                    arrayListOf()/*,
                    arrayListOf(),
                    arrayListOf(),
                    arrayListOf(),
                    arrayListOf(),
                    arrayListOf() */

                )
                val recyclerView = root.findViewById<RecyclerView>(R.id.fragment_calendario_recyclerview)

                override fun done(enfrentamientos: List<ParseObject>, e3: ParseException?) {
                    if(e3 == null){
                        Log.v("Enfrentamientos: ", enfrentamientos.size.toString())

                        for(enfrentamiento: ParseObject in enfrentamientos){
                            var currJornada = enfrentamiento.getNumber("jornada")!!.toInt() - 1

                            var currEnfrentamiento = Enfrentamiento(
                                currJornada + 1,
                                enfrentamiento.getString("equipo1")!!,
                                enfrentamiento.getString("equipo2")!!,
                                enfrentamiento.getNumber("golesEquipo1")!!.toInt(),
                                enfrentamiento.getNumber("golesEquipo2")!!.toInt(),
                                enfrentamiento.objectId
                                )

                            jornadaArray[currJornada].add(currEnfrentamiento)

                        }
                        Log.v("Enfrentamientos map", jornadaArray.toString() + " -\n" + jornadaArray.size)
                        activity?.runOnUiThread {
                            recyclerView.adapter = AdapterCalendario(jornadaArray)
                            recyclerView.adapter?.notifyDataSetChanged()
                            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            recyclerView.addItemDecoration(LinePagerIndicatorDecoration())
                            PagerSnapHelper().attachToRecyclerView(recyclerView)
                        }
                    }
                }
            })
        }

        return root
    }

}