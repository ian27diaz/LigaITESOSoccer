package ian.meda.ligaitesosoccer.beans

import com.parse.ParseFile
import java.io.File

data class Jugador(var nombre: String, var expediente: String, var goles: Int, var Foto: ParseFile,
                   var esEgresado: Boolean, var esSeleccionado: Boolean, var email: String, var pointer: String) {
    constructor(): this("", "", 0, ParseFile(File("")), false, false, "", "")
}