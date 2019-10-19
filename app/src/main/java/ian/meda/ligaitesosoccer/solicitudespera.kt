package ian.meda.ligaitesosoccer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.startActivity

class solicitudespera :  AppCompatActivity(), View.OnClickListener{

    lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wait_screen)


        back = findViewById(R.id.btn_espera_atras)

        back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        startActivity<login>()
    }


}