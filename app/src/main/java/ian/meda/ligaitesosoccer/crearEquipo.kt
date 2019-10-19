package ian.meda.ligaitesosoccer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ian.meda.ligaitesosoccer.R
import kotlinx.android.synthetic.main.crear_equipo.*
import org.jetbrains.anko.startActivity
import java.io.IOException

class crearEquipo() :  AppCompatActivity(), View.OnClickListener {

    private var id = 0
    lateinit var escudo: ImageView
    lateinit var comprobante: ImageView
    lateinit var continuar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_equipo)

        continuar = findViewById(R.id.btn_continuar_SE)
        escudo = findViewById(R.id.image_upload_escudo)
        comprobante = findViewById(R.id.image_upload_comprobante)

        continuar.setOnClickListener(this)
        escudo.setOnClickListener(this)
        comprobante.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.image_upload_escudo -> {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                id = 1
                startActivityForResult(intent, id)

            }
            R.id.image_upload_comprobante -> {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                id = 2
                startActivityForResult(intent, id)

            }
            R.id.btn_continuar_SE -> {

                startActivity<IngresarJugadores>()
            }
        }
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        var selectedImage: Uri? = null
        selectedImage = intent?.data
        if (requestCode == 1 && selectedImage != null) {
            Log.d("Entro a escudo BIEN", "CORRECTO")
            escudo.setImageURI(selectedImage)

        } else {
            comprobante.setImageURI(selectedImage)
        }


    }
}