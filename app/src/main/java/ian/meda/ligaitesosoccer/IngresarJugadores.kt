package ian.meda.ligaitesosoccer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ian.meda.ligaitesosoccer.adapters.AdapterIngresarJugadores
import ian.meda.ligaitesosoccer.beans.Jugador
import ian.meda.ligaitesosoccer.utils.currCapitan
import org.jetbrains.anko.startActivity

class IngresarJugadores : AppCompatActivity() {
    private lateinit var addButton: ImageButton
    private lateinit var nextButton: Button
    private lateinit var deleteButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_jugadores)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_ingresar_jugadores)
        val jugadores = arrayListOf<Jugador>()

        addButton = findViewById<ImageButton>(R.id.ingresar_jugadores_btn_nuevo_jugador)
        nextButton = findViewById<Button>(R.id.ingresar_jugadores_btn_siguiente)
        jugadores.add(currCapitan)
        addButton.setOnClickListener {
            jugadores.add(currCapitan)
            recyclerView.adapter = AdapterIngresarJugadores(jugadores)
            recyclerView.layoutManager = LinearLayoutManager(this)

        }

        nextButton.setOnClickListener {
            startActivity<solicitudespera>()
        }


        recyclerView.adapter = AdapterIngresarJugadores(jugadores)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
