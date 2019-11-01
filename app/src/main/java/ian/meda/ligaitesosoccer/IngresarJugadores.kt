package ian.meda.ligaitesosoccer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.adapters.AdapterIngresarJugadores
import org.jetbrains.anko.startActivity

class IngresarJugadores : AppCompatActivity() {
    private lateinit var addButton: ImageButton
    private lateinit var nextButton: Button
    private lateinit var deleteButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_jugadores)


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_ingresar_jugadores)
        val jugadores = arrayListOf<HashMap<String, String>>()

        addButton = findViewById<ImageButton>(R.id.ingresar_jugadores_btn_nuevo_jugador)
        nextButton = findViewById<Button>(R.id.ingresar_jugadores_btn_siguiente)

        addButton.setOnClickListener {
            jugadores.add(HashMap())
            jugadores[jugadores.size-1].put("nombre", "")
            jugadores[jugadores.size-1].put("expediente", "")
            jugadores[jugadores.size-1].put("correo", "")
            jugadores[jugadores.size-1].put("carrera", "")


            recyclerView.adapter = AdapterIngresarJugadores(jugadores)
            recyclerView.layoutManager = LinearLayoutManager(this)

        }

        nextButton.setOnClickListener {
            startActivity<solicitudespera>()
        }

        jugadores.add(HashMap())
        jugadores[0].put("nombre", "Erick De Santiago")
        jugadores[0].put("expediente", "699887")
        jugadores[0].put("correo", "is699887@iteso.mx")
        jugadores[0].put("carrera", "Ingenieria en Sistemas Computacionales")

        jugadores.add(HashMap())
        jugadores[1].put("nombre", "Ian Díaz Meda")
        jugadores[1].put("expediente", "710007")
        jugadores[1].put("correo", "is710007@iteso.mx")
        jugadores[1].put("carrera", "Ingenieria en Sistemas Computacionales")

        jugadores.add(HashMap())
        jugadores[2].put("nombre", "Alessandro Pallaro Gómez")
        jugadores[2].put("expediente", "709389")
        jugadores[2].put("correo", "is709389@iteso.mx")
        jugadores[2].put("carrera", "Ingenieria en Sistemas Computacionales")

        jugadores.add(HashMap())
        jugadores[3].put("nombre", "Pedro Gutierrez")
        jugadores[3].put("expediente", "706183")
        jugadores[3].put("correo", "is706183@iteso.mx")
        jugadores[3].put("carrera", "Ingenieria en Sistemas Computacionales")

        recyclerView.adapter = AdapterIngresarJugadores(jugadores)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
