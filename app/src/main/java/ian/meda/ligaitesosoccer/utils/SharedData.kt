package ian.meda.ligaitesosoccer.utils

import ian.meda.ligaitesosoccer.beans.Equipo
import ian.meda.ligaitesosoccer.beans.Jugador

var currEquipo: Equipo = Equipo()
var currCapitan: Jugador = Jugador()
var capitanCode: String = ""
var jugadorCode: String = ""

const val SHARED_PREFERENCES = "ian.meda.ligaitesosoccer.SHARED_PREFERENCES"
const val SESSION_USER_ID = "SESSION_USER_ID"
const val SESSION_TEAM = "SESSION_TEAM"
const val SESSION_USERNAME = "SESSION_USERNAME"
const val SESSION_USERTYPE = "SESSION_USERTYPE"
const val SESSION_IS_IN_PLAYER_REGISTRATION = "SESSION_IS_IN_PLAYER_REGISTRATION"
const val SESSION_CREATE_TEAM_ID = "SESSION_CREATE_TEAM_ID"



