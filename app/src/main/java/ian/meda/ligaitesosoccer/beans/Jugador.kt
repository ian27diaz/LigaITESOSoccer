package ian.meda.ligaitesosoccer.beans

import android.net.Uri
import ian.meda.ligaitesosoccer.utils.currEquipo

data class Jugador(var nombre: String, var expediente: String, var goles: Int, var Foto: Uri,
                   var esEgresado: Boolean, var esSeleccionado: Boolean, var email: String, var pointer: String,
                   var fotoCargada: Boolean) {
    constructor(): this("", "", 0, Uri.parse(""),
        false, false, "", currEquipo.objectId, false)
}