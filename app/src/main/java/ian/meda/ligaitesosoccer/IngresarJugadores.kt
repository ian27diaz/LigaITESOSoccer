package ian.meda.ligaitesosoccer

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import com.parse.SaveCallback
import ian.meda.ligaitesosoccer.adapters.AdapterIngresarJugadores
import ian.meda.ligaitesosoccer.beans.Jugador
import ian.meda.ligaitesosoccer.utils.capitanCode
import ian.meda.ligaitesosoccer.utils.currCapitan
import ian.meda.ligaitesosoccer.utils.currEquipo
import ian.meda.ligaitesosoccer.utils.jugadorCode
import org.jetbrains.anko.startActivity
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

class IngresarJugadores : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val JUGADOR_FOTO_REQUEST_ACCEPTED = 5000
    }

    private lateinit var addButton: ImageButton
    private lateinit var nextButton: Button
    /*private lateinit var deleteButton: ImageButton //Este va en el adapter */
    private lateinit var currentImageView: ImageView
    private lateinit var recyclerView: RecyclerView
    private var currentJugador = Jugador()
    private var jugadoresContador : Int = 1
    private var jugadores = arrayListOf<Jugador>()

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, JUGADOR_FOTO_REQUEST_ACCEPTED)
    }

    override fun onRequestPermissionsResult( requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            JUGADOR_FOTO_REQUEST_ACCEPTED -> {
                if(grantResults.size >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    openGallery()
                else Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun jugadorFotoItemClicked(jugador: Jugador, imageView: ImageView) {
        currentImageView = imageView
        currentJugador = jugador

        Toast.makeText(this, "Clicked on ${jugador.nombre}", Toast.LENGTH_LONG).show()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ){
                val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permission, JUGADOR_FOTO_REQUEST_ACCEPTED)
            }
            else {
                openGallery()
            }
        }
    }

    private fun jugadorDeleteItemClicked(jugadorToDelete: Jugador){
        jugadores.remove(jugadorToDelete)
        jugadoresContador--
        recyclerView.adapter = AdapterIngresarJugadores(jugadores,
            {jugadorItem : Jugador, imageView: ImageView -> jugadorFotoItemClicked(jugadorItem, imageView)},
            {jugadorItem : Jugador -> jugadorDeleteItemClicked(jugadorItem)}
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_jugadores)

        recyclerView = findViewById(R.id.recycler_view_ingresar_jugadores)

        addButton = findViewById(R.id.ingresar_jugadores_btn_nuevo_jugador)
        nextButton = findViewById(R.id.ingresar_jugadores_btn_siguiente)

        addButton.setOnClickListener(this)
        nextButton.setOnClickListener(this)
        jugadores.add(currCapitan)
        recyclerView.adapter = AdapterIngresarJugadores(jugadores,
            {jugadorItem : Jugador, imageView: ImageView -> jugadorFotoItemClicked(jugadorItem, imageView)},
            {jugadorItem : Jugador -> jugadorDeleteItemClicked(jugadorItem)}
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.ingresar_jugadores_btn_nuevo_jugador -> {
                currCapitan.pointer = currEquipo.objectId
                val newJugador = Jugador()
                jugadores.add(newJugador)
                recyclerView.adapter = AdapterIngresarJugadores(jugadores,
                    {jugadorItem : Jugador, imageView: ImageView -> jugadorFotoItemClicked(jugadorItem, imageView)},
                    {jugadorItem : Jugador -> jugadorDeleteItemClicked(jugadorItem)}
                )

                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.scrollToPosition(jugadores.size - 1)
                jugadoresContador++
            }
            R.id.ingresar_jugadores_btn_siguiente -> {
                currCapitan.pointer = currEquipo.objectId
                jugadores[0].pointer = currEquipo.objectId
                if(jugadoresContador < 7) {
                    Toast.makeText(this, "Necesitas al menos siete jugadores!", Toast.LENGTH_SHORT)
                        .show()
                }
                else {
                    for(jugador : Jugador in jugadores) {
                        val jugadorParse = ParseObject("Jugador")
                        val image: Bitmap = if(Build.VERSION.SDK_INT < 28) {
                            MediaStore.Images.Media.getBitmap(this.contentResolver, jugador.Foto)
                        } else{
                            ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, jugador.Foto))
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
                        jugadorParse.put("Foto", ParseFile(file))
                        jugadorParse.put("Nombre", jugador.nombre)
                        jugadorParse.put("IDEquipo", ParseObject.createWithoutData("Equipo", jugador.pointer))
                        jugadorParse.put("Expediente", jugador.expediente)
                        jugadorParse.put("GolesTotales", jugador.goles)
                        jugadorParse.put("esEgresado", jugador.esEgresado)
                        jugadorParse.put("Email", jugador.email)
                        jugadorParse.put("esSeleccionado", jugador.esSeleccionado)

                        jugadorParse.saveInBackground()

                    }

                    val capitanUser = ParseUser()
                    val jugadorUser = ParseUser()
                    capitanUser.username = "capitan${currEquipo.nombre}"
                    jugadorUser.username = "jugador${currEquipo.nombre}"
                    capitanUser.setPassword("Pr1v4d0&j3j3")
                    jugadorUser.setPassword("Pr1v4d0&j3j3")
                    capitanUser.email = currCapitan.email
                    jugadorUser.email = "example@hotmail.com"
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
                        } else {
                            Log.v("IngresarJugadoresGG", "Capi Error -> $e")
                        }
                    })
                    jugadorUser.signUp()
                    jugadorUser.saveInBackground(SaveCallback { e ->
                        if (e == null) {
                            Log.v("IngresarJugadoresGG", "jugador guardado")
                        } else {
                            Log.v("IngresarJugadoresGG", "jugador Error -> $e")
                        }
                    })
                    startActivity<SolicitudDeEspera>()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            JUGADOR_FOTO_REQUEST_ACCEPTED -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    Glide.with(this).load(data.data).into(currentImageView)
                    currentJugador.Foto = data.data!!
                    for(jugador : Jugador in jugadores) {
                        if(jugador.nombre == currentJugador.nombre && jugador.expediente == currentJugador.expediente
                            && jugador.email == currentJugador.email) {
                            Log.v("IngresarJugadoresGG", "Activity -> Name: ${jugador.nombre}, Parse: ${jugador.Foto}, fotoSeleccionada: ${jugador.fotoCargada}")
                            jugador.fotoCargada = true
                            jugador.Foto = data.data!!
                            Log.v("IngresarJugadoresGG", "Activity -> Name: ${jugador.nombre}, Parse: ${jugador.Foto}, fotoSeleccionada: ${jugador.fotoCargada}")
                            break
                        }
                    }
                }
            }
        }
    }
}
