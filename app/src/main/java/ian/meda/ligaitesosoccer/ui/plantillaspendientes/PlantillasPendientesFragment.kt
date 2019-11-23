package ian.meda.ligaitesosoccer.ui.plantillaspendientes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ian.meda.ligaitesosoccer.R

class PlantillasPendientesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_plantillas_pendientes, container, false)
    }


}
