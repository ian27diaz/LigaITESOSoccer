package ian.meda.ligaitesosoccer.beans

import com.parse.ParseFile
import java.io.File

data class Equipo(var nombre : String, var esEquipoValido: Boolean, var puntosTotales: Int, var partidosGanados: Int, var escudo: ParseFile,
                  var comprobantePago: ParseFile, var golesFavor: Int, var golesContra: Int, var diferenciaGoles: Int, var partidosEmpatados: Int,
                  var partidosJugados: Int, var partidosPerdidos: Int, var objectId: String, var plantillaValida: Boolean) {

        constructor() : this("", false, 0,0, ParseFile(File("")),
                                ParseFile(File("")), 0,0,0,0,
                    0,0, "", false)
}