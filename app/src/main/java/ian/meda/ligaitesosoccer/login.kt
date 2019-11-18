package ian.meda.ligaitesosoccer


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser
import org.jetbrains.anko.startActivity

class Login () :  AppCompatActivity(), View.OnClickListener {

    lateinit var login: Button
    lateinit var crear_equipo: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_fut)


        login = findViewById(R.id.login_iniciar)
        crear_equipo = findViewById(R.id.login_darDeAlta)

        login.setOnClickListener(this)
        crear_equipo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.login_iniciar -> {
                startActivity<MainActivity>()
            }

            R.id.login_darDeAlta -> {
                startActivity<CrearEquipo>()
            }
        }
    }


}