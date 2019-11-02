package ian.meda.ligaitesosoccer.beans

data class Equipo(val nombre : String, var esEquipoValido: Boolean, var puntosTotales: Int, var partidosGanados: Int, var escudo: Int,
                  var golesFavor: Int, var diferenciaGoles: Int, var partidosEmpatados: Int, var partidosJugados: Int, var golesContra: Int,
                  var partidosPerdidos: Int)