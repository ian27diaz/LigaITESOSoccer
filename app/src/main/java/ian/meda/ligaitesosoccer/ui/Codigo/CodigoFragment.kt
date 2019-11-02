package ian.meda.ligaitesosoccer.ui.Codigo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.google.android.material.button.MaterialButton
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import ian.meda.ligaitesosoccer.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast

class CodigoFragment : Fragment() , View.OnClickListener{

    lateinit var boton: Button
    lateinit var tf_hash : EditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_codigo, container, false)
        boton = root.findViewById(R.id.btn_enviar_hash)
        tf_hash = root.findViewById(R.id.tf_clavehash)

        boton.setOnClickListener(this)
        return root
    }


    override fun onClick(p0: View?) {

        boton.setOnClickListener() {

            doAsync {
                val query = ParseQuery.getQuery<ParseObject>("User")
                query.include("idEquipo")
                query.findInBackground(object : FindCallback<ParseObject> {
                    var hashes: MutableList<String> = arrayListOf<String>()
                    var auxlist: List<ParseObject>? = arrayListOf<ParseObject>()
                    override fun done(objects: List<ParseObject>?, e: ParseException?) {

                        if (e == null) {

                            auxlist=objects
                            Log.d("RARO",auxlist?.size.toString())
                            var cont = 0
                            /*    while(cont  < objects!!.size){

                                hashes[cont] = objects?.get(cont)?.getString("objectId")
                                cont++
                            }*/
                            //Log.d(auxlist.size.toString())
                           // toast(auxlist?.size.toString())
                            for (hash: ParseObject in objects!!) {

                                hashes[cont] = hash.getString("objectId")!!
                                cont++

                            }
//HARDCODEADO
           // var tf_hash= "QQf5JROv0T"
                            //buscas si esta el hash
                            var conti = 0
                            toast(hashes.size.toString())
                            //ESTO FOR SE DESCOMENTA PARA SU USO NORMAL
                            //for (h: String in hashes) {
                                //toast(h)
                                if (tf_hash.text.toString() == "QQf5JROv0T") {
                                    toast("entro")
                                    // SE HACE LO DE GUARDAR EN SHARED PREFECENCES

                                    val sharedPreferences =
                                        activity?.getSharedPreferences(
                                            "myPref",
                                            Context.MODE_PRIVATE
                                        )
                                    val editor = sharedPreferences?.edit()

                                    val sharedPreferencesCapi =
                                        activity?.getSharedPreferences(
                                            "myPref",
                                            Context.MODE_PRIVATE
                                        )
                                    val editorCapi = sharedPreferencesCapi?.edit()

                                    val sharedPreferencesAdmin =
                                        activity?.getSharedPreferences(
                                            "myPref",
                                            Context.MODE_PRIVATE
                                        )
                                    val editorAdmin = sharedPreferencesAdmin?.edit()



                                    if (objects[cont].getBoolean("esAdmin")!! == true) {
                                        toast("admim")
                                        editorAdmin?.putString("admin", "yes")
                                        editorAdmin?.apply()
                                    } else {
                                        //si es capitan agregas el shared prefences de que si es
                                        if (objects[cont].getBoolean("esCapitan")!! == true) {

                                            editorCapi?.putString("capi", "yes")

                                        } else {

                                            editorCapi?.putString("capi", "no")

                                        }
                                        //pones el valor del equipo!!! FATA... h -> valor actual del hash del equipo()

                                        val nombreEquipodelHash =
                                            objects[cont].getParseObject("idEquipo")!!.getString("nombre")
                                        editor?.putString("equipo", nombreEquipodelHash)
                                        editor?.apply()
                                        editorCapi?.apply()
                                        toast("Funciona?")
                                        //toast(nombreEquipodelHash.toString())
                                        //break
                                    }
                                }
                                conti++


                            if (conti >= hashes.size - 1) {
                                //NO SE GUARDO EL SHARED PREFECNCES
                                //HACES UN TOAST DICEINDO QUE NO ES CORRECTO

                                //toast("Ncodigo")


                                /*var t = Toast.makeText(this@CodigoFragment,)
                               maketext(this@CodigoFragment,  “Hello”, Toast.LENGTH_LONG)
                            t. show()*/
                            }


                        } else {
                            toast("fatal")
                        }

                    }

                })


            }
        }
    }

}