package ian.meda.ligaitesosoccer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.parse.*
import ian.meda.ligaitesosoccer.beans.Equipo
import ian.meda.ligaitesosoccer.utils.*
import org.jetbrains.anko.singleLine
import org.jetbrains.anko.startActivity
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

class CrearEquipo() :  AppCompatActivity(), View.OnClickListener {

    companion object {
        const val GALLERY_COMPROBANTE_REQUEST_ACCEPTED = 1000
        const val GALLERY_ESCUDO_REQUEST_ACCEPTED = 2000
        const val WRITE_EXTERNAL_STORE = 3000
        const val WRITE_EXTERNAL_STORE_COMPROBANTE = 4000
    }

    private lateinit var escudo: ImageView
    private lateinit var comprobante: ImageView
    private lateinit var continuar: Button
    private lateinit var nombreEquipo: EditText
    private lateinit var nombreCapitan: EditText
    private lateinit var expediente: EditText
    private lateinit var correoElectronico: EditText

    private lateinit var escudoImage: ParseFile
    private lateinit var comprobanteImage: ParseFile

    private var escudoSelected = false
    private var comprobanteSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_equipo)

        nombreEquipo = findViewById(R.id.crear_equipo_et_nombre_equipo)
        continuar = findViewById(R.id.btn_continuar_SE)
        escudo = findViewById(R.id.crear_equipo_image_upload_escudo)
        comprobante = findViewById(R.id.image_upload_comprobante)
        nombreCapitan = findViewById(R.id.crear_equipo_et_nombre_capitan)
        expediente = findViewById(R.id.crear_equipo_et_expediente_crearequipo)
        correoElectronico = findViewById(R.id.crear_equipo_et_correo)

        
        nombreEquipo.imeOptions = EditorInfo.IME_ACTION_DONE
        nombreEquipo.singleLine = true
        nombreCapitan.imeOptions = EditorInfo.IME_ACTION_DONE
        nombreCapitan.singleLine = true
        expediente.imeOptions = EditorInfo.IME_ACTION_DONE
        expediente.singleLine = true
        correoElectronico.imeOptions = EditorInfo.IME_ACTION_DONE
        correoElectronico.singleLine = true

        continuar.setOnClickListener(this)
        escudo.setOnClickListener(this)
        comprobante.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.crear_equipo_image_upload_escudo -> {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ){
                        val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        requestPermissions(permission, WRITE_EXTERNAL_STORE)
                    }
                    else {
                        escudoSelected = true
                        openGallery(GALLERY_ESCUDO_REQUEST_ACCEPTED)
                    }
                }
            }

            R.id.image_upload_comprobante -> {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ){
                        val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        requestPermissions(permission, WRITE_EXTERNAL_STORE_COMPROBANTE)
                    }
                    else {
                        comprobanteSelected = true
                        openGallery(GALLERY_COMPROBANTE_REQUEST_ACCEPTED)
                    }
                }
            }

            R.id.btn_continuar_SE -> {

                //Es la variable compartida currEquipo del utils del SharedData
                val teamName = nombreEquipo.text.toString()
                if(teamName == "" || nombreCapitan.text.toString() == ""
                    || expediente.text.toString() == "" || correoElectronico.text.toString() == ""
                    || !escudoSelected || !comprobanteSelected) {
                    Toast.makeText(this, "Llena todos los datos antes de continuar!", Toast.LENGTH_LONG).show()
                } else {
                    currCapitan.nombre = nombreCapitan.text.toString()
                    currCapitan.expediente = expediente.text.toString()
                    currCapitan.email = correoElectronico.text.toString()

                    val newEquipo = ParseObject("Equipo")
                    newEquipo.put("nombre", nombreEquipo.text.toString())
                    newEquipo.put("esEquipoValido", false)
                    newEquipo.put("puntosTotales", 0)

                    newEquipo.put("partidosGanados", 0)
                    newEquipo.put("escudo", escudoImage)
                    newEquipo.put("comprobantePago", comprobanteImage)
                    newEquipo.put("golesFavor", 0   )
                    newEquipo.put("diferenciaGoles", 0)
                    newEquipo.put("partidosEmpatados", 0)
                    newEquipo.put("partidosJugados", 0)
                    newEquipo.put("golesContra", 0)
                    newEquipo.put("partidosPerdidos", 0)
                    newEquipo.put("plantillaValida", false)
                    newEquipo.put("accionPendiente", false)


                    val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()

                    newEquipo.saveInBackground (SaveCallback { e ->
                        if (e == null) {
                            editor.putString(SESSION_CREATE_TEAM_ID, newEquipo.objectId)
                            editor.putBoolean(SESSION_IS_IN_PLAYER_REGISTRATION, true)
                            editor.apply()
                            currEquipo = Equipo(
                                teamName, false, 0, 0, escudoImage,
                                comprobanteImage, 0, 0, 0, 0,
                                0, 0, newEquipo.objectId, false, false
                            )

                            val capitanUser = ParseUser()
                            val jugadorUser = ParseUser()
                            capitanUser.username = "capitan${currEquipo.nombre}"
                            jugadorUser.username = "jugador${currEquipo.nombre}"
                            capitanUser.setPassword("Pr1v4d0&j3j3")
                            jugadorUser.setPassword("Pr1v4d0&j3j3")
                            capitanUser.email = currCapitan.email
                            jugadorUser.email = "jug_${currCapitan.email}"
                            capitanUser.put("idEquipo", ParseObject.createWithoutData("Equipo", currEquipo.objectId))
                            jugadorUser.put("idEquipo", ParseObject.createWithoutData("Equipo", currEquipo.objectId))
                            capitanUser.put("esAdmin", false)
                            jugadorUser.put("esAdmin", false)
                            capitanCode = currEquipo.nombre.replace(" ", "") + Random.nextInt(100) + Random.nextInt(100) + Random.nextInt(100)
                            + Random.nextInt(100) + Random.nextInt(100) + Random.nextInt(100)
                            jugadorCode = currEquipo.nombre.replace(" ", "") + Random.nextInt(100) + Random.nextInt(100) + Random.nextInt(100)
                            + Random.nextInt(100) + Random.nextInt(100) + Random.nextInt(100)
                            capitanUser.put("code", capitanCode)
                            jugadorUser.put("code", jugadorCode)
                            capitanUser.put("esCapitan", true)
                            jugadorUser.put("esCapitan", false)
                            capitanUser.signUp()
                            capitanUser.saveInBackground(SaveCallback { e ->
                                if (e == null) {
                                    Log.v("IngresarJugadoresGG", "Capitan guardado")
                                    ParseUser.logOut()
                                } else {
                                    Log.v("IngresarJugadoresGG", "Capi Error -> $e")
                                }
                            })

                            jugadorUser.signUp()
                            jugadorUser.saveInBackground(SaveCallback { e ->
                                if (e == null) {
                                    Log.v("IngresarJugadoresGG", "jugador guardado")
                                    ParseUser.logOut()
                                } else {
                                    Log.v("IngresarJugadoresGG", "jugador Error -> $e")
                                }
                            })

                        } else {
                            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                            Log.v("CrearEquipo", "Error -> $e")
                        }
                    })



                    startActivity<IngresarJugadores>()
                }

            }
        }
    }

    private fun openGallery(code: Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, code)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            WRITE_EXTERNAL_STORE -> {
                if(grantResults.size >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    openGallery(GALLERY_ESCUDO_REQUEST_ACCEPTED)
                else Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show()
            }
            WRITE_EXTERNAL_STORE_COMPROBANTE -> {
                if(grantResults.size >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    openGallery(GALLERY_COMPROBANTE_REQUEST_ACCEPTED)
                else Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val image: Bitmap
        when(requestCode){
            GALLERY_COMPROBANTE_REQUEST_ACCEPTED -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    Glide.with(this).load(data.data).into(comprobante)
                    image = if(Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(this.contentResolver, data.data)
                    } else {
                        ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, data.data!!))
                    }

                    val file = File(this.cacheDir, image.toString())
                    file.createNewFile()

                    val bos = ByteArrayOutputStream()
                    image.compress(Bitmap.CompressFormat.PNG, 0, bos)
                    val bitmapdata = bos.toByteArray()

                    val fos = FileOutputStream(file)
                    fos.write(bitmapdata)
                    fos.flush()
                    fos.close()
                    comprobanteImage = ParseFile(file)
                    Log.v("CrearEquipoComprobante", comprobanteImage.toString())
                }
            }

            GALLERY_ESCUDO_REQUEST_ACCEPTED -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    Glide.with(this).load(data.data).into(escudo)
                    image = if(Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(this.contentResolver, data.data)
                    } else{
                        val imageDecoder = ImageDecoder.createSource(this.contentResolver, data.data!!)
                        ImageDecoder.decodeBitmap(imageDecoder)
                    }

                    val file = File(this.cacheDir, image.toString())
                    file.createNewFile()

                    val bos = ByteArrayOutputStream()
                    image.compress(Bitmap.CompressFormat.PNG, 0, bos)
                    val bitmapdata = bos.toByteArray()

                    val fos = FileOutputStream(file)
                    fos.write(bitmapdata)
                    fos.flush()
                    fos.close()
                    escudoImage = ParseFile(file)
                    Log.v("CrearEquipoEscudo", escudoImage.toString())
                }
            }
        }
    }
}
