package ian.meda.ligaitesosoccer

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseFile
import com.parse.ParseObject
import ian.meda.ligaitesosoccer.R
import ian.meda.ligaitesosoccer.beans.Equipo
import ian.meda.ligaitesosoccer.utils.currEquipo
import kotlinx.android.synthetic.main.crear_equipo.*
import org.jetbrains.anko.startActivity
import java.io.File
import java.io.IOException

class crearEquipo() :  AppCompatActivity(), View.OnClickListener {

    companion object{
        const val GALLERY_COMPROBANTE_REQUEST_ACEPTED = 1000
        const val GALLERY_ESCUDO_REQUEST_ACEPTED = 2000
    }

    private var id = 0
    private var escudoFile : ParseFile = ParseFile(File("barcelona.png"), "barcelona.png")

    private lateinit var escudo: ImageView
    private lateinit var comprobante: ImageView
    private lateinit var continuar : Button
    private lateinit var nombreEquipo: EditText
    private lateinit var nombreCapitan: EditText
    private lateinit var Telefono: EditText
    private lateinit var CorreoElectronico: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_equipo)

        nombreEquipo = findViewById(R.id.et_nombre_equipo)

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
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, GALLERY_ESCUDO_REQUEST_ACEPTED)

            }
            R.id.image_upload_comprobante -> {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, GALLERY_COMPROBANTE_REQUEST_ACEPTED)

            }
            R.id.btn_continuar_SE -> {
                //Es la variable compartida currEquipo del utils del SharedData
                currEquipo = Equipo(nombreEquipo.text.toString(), false, 0, 0, 5,
                    0, 0, 0, 0, 0, 0)
                var newEquipo = ParseObject("Equipo")
                /*newEquipo.put("nombre", currEquipo.nombre)
                newEquipo.put("esEquipoValido", currEquipo.nombre)
                newEquipo.put("puntosTotales", currEquipo.nombre)

                newEquipo.put("partidosGanados", currEquipo.nombre)
                //newEquipo.put("escudo", escudoFile)
                newEquipo.put("golesFavor", currEquipo.nombre)
                newEquipo.put("diferenciaGoles", currEquipo.nombre)
                newEquipo.put("partidosEmpatados", currEquipo.nombre)
                newEquipo.put("partidosJugados", currEquipo.nombre)
                newEquipo.put("golesContra", currEquipo.nombre)
                newEquipo.put("partidosPerdidos", currEquipo.nombre)


                newEquipo.saveInBackground()
                */

                startActivity<IngresarJugadores>()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            GALLERY_COMPROBANTE_REQUEST_ACEPTED -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    comprobante.setImageURI(data?.data)
                }
            }

            GALLERY_ESCUDO_REQUEST_ACEPTED -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    escudo.setImageURI(data?.data)
                }
            }
        }
    }
}