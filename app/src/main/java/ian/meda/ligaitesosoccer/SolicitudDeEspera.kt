package ian.meda.ligaitesosoccer
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ian.meda.ligaitesosoccer.utils.*
import org.jetbrains.anko.startActivity

class SolicitudDeEspera :  AppCompatActivity(), View.OnClickListener{

    private lateinit var back: Button
    private lateinit var capitanCodigo: TextView
    private lateinit var jugadorCodigo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wait_screen)

        back = findViewById(R.id.btn_espera_atras)
        capitanCodigo = findViewById(R.id.solicitud_espera_capitancodigo_tv)
        jugadorCodigo = findViewById(R.id.solicitud_espera_jugadorcodigo_tv)

        capitanCodigo.text = "Codigo de capitan: $capitanCode"
        jugadorCodigo.text = "Codigo de jugador: $jugadorCode"
        back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val sharedPreferences = this!!.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SESSION_USER_ID, "")
        editor.putString(SESSION_TEAM, "")
        editor.putString(SESSION_USERNAME, "")
        editor.putString(SESSION_USERTYPE, "")
        Toast.makeText(this, "Cerrar sesion", Toast.LENGTH_SHORT).show()
        editor.apply()
        startActivity<MainActivity>()    }


}