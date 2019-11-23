package ian.meda.ligaitesosoccer


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser
import ian.meda.ligaitesosoccer.utils.SESSION_IS_IN_PLAYER_REGISTRATION
import ian.meda.ligaitesosoccer.utils.SHARED_PREFERENCES
import org.jetbrains.anko.startActivity

class Login () :  AppCompatActivity(), View.OnClickListener {

    lateinit var login: Button
    lateinit var crear_equipo: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_fut)


        login = findViewById(R.id.login_iniciar)
        crear_equipo = findViewById(R.id.login_darDeAlta)
        Toast.makeText(this, ParseUser.getCurrentSessionToken(), Toast.LENGTH_LONG).show()

        ParseUser.logOut()

        login.setOnClickListener(this)
        crear_equipo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.login_iniciar -> {
                startActivity<MainActivity>()
            }

            R.id.login_darDeAlta -> {
                val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
                val isInPlayerRegistration = sharedPreferences.getBoolean(SESSION_IS_IN_PLAYER_REGISTRATION, false)
                /* if(!isInPlayerRegistration)
                    startActivity<CrearEquipo>()
                else
                    startActivity<IngresarJugadores>()
                */
                startActivity<CrearEquipo>()

            }
        }
    }


}