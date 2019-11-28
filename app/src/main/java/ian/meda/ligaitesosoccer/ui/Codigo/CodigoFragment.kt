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
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.google.android.material.button.MaterialButton
import com.parse.*
import ian.meda.ligaitesosoccer.IngresarJugadores
import ian.meda.ligaitesosoccer.MainActivity
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.SolicitudDeEspera
import ian.meda.ligaitesosoccer.beans.Equipo
import ian.meda.ligaitesosoccer.utils.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class CodigoFragment : Fragment() , View.OnClickListener{

    private lateinit var boton: Button
    private lateinit var tf_hash : EditText
    private lateinit var mCerrarSesionBtn : Button
    private lateinit var mText : TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_codigo, container, false)
        boton = root.findViewById(R.id.btn_enviar_hash)
        boton.setOnClickListener(this)
        tf_hash = root.findViewById(R.id.tf_clavehash)

        mCerrarSesionBtn = root.findViewById(R.id.fragment_codigo_logout)
        mCerrarSesionBtn.setOnClickListener(this)

        mText = root.findViewById(R.id.text_send)

        val sharedPreferences = context!!.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val sessionId = sharedPreferences.getString(SESSION_USERNAME, "")

        mText.text = "User: ${sessionId}"
        return root
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_enviar_hash -> {
                Log.v("CodigoFragment", "clicked on ingresar button")
                val codigo = tf_hash.text.toString();
                if(codigo.equals("")){
                    Toast.makeText(context, "Ingresa un codigo para continuar", Toast.LENGTH_SHORT).show();
                } else {
                    doAsync {
                        val query = ParseQuery.getQuery<ParseObject>("_User");
                        query.include("idEquipo")
                        query.whereEqualTo("code", codigo)
                        query.getFirstInBackground(object: GetCallback<ParseObject> {
                            override fun done(usuario: ParseObject, e: ParseException?) {
                                if(e == null){
                                            val sharedPreferences = context!!.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
                                            val editor = sharedPreferences.edit()
                                            Toast.makeText(context, "User: ${usuario.objectId}, EquipoID: ${usuario.getParseObject("idEquipo")?.objectId}", Toast.LENGTH_SHORT ).show()
                                            editor.putString(SESSION_USER_ID, usuario.objectId)
                                            editor.putString(SESSION_USERNAME, usuario.getString("username"))
                                            if(usuario.getBoolean("esAdmin")) {
                                                editor.putString(SESSION_USERTYPE, "ADMIN")
                                                startActivity<MainActivity>()
                                            } else if(usuario.getBoolean("esCapitan")) {
                                                editor.putString(SESSION_USERTYPE, "CAPITAN")
                                                editor.putString(SESSION_TEAM, usuario.getParseObject("idEquipo")?.objectId)
                                            } else {
                                                editor.putString(SESSION_USERTYPE, "MIEMBRO")
                                                editor.putString(SESSION_TEAM, usuario.getParseObject("idEquipo")?.objectId)
                                            }

                                            editor.apply()

                                            if (usuario.getParseObject("idEquipo")?.getBoolean("esEquipoValido")==false ||
                                                usuario.getParseObject("idEquipo")?.getBoolean("plantillaValida")==false ){
                                                if (usuario.getParseObject("idEquipo")?.getBoolean("accionPendiente")==true &&
                                                    usuario.getBoolean("esCapitan")==true ){
                                                    var nuevoEquipito = Equipo()
                                                    nuevoEquipito.objectId = usuario.getParseObject("idEquipo")?.objectId.toString()
                                                    currEquipo = nuevoEquipito
                                                    startActivity<IngresarJugadores>()
                                                }else{
                                                    startActivity<SolicitudDeEspera>()
                                                }
                                            }else{
                                                startActivity<MainActivity>()
                                            }
                                        }
                                    }

                        })
                    }
                }
            }
            R.id.fragment_codigo_logout -> {

                val sharedPreferences = context!!.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(SESSION_USER_ID, "")
                editor.putString(SESSION_TEAM, "")
                editor.putString(SESSION_USERNAME, "")
                editor.putString(SESSION_USERTYPE, "")
                Toast.makeText(context, "Cerrar sesion", Toast.LENGTH_SHORT).show()
                editor.apply()
                startActivity<MainActivity>()


            }
        }

    }

}