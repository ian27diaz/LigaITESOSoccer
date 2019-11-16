package ian.meda.ligaitesosoccer.beans

import java.util.Date

data class Enfrentamiento(var jornadNum: Int, var equipo1: String, var equipo2: String,
                          var golesEquipo1: Int, var golesEquipo2: Int, var idEnfrentamiento: String,
                            var fecha: Date)