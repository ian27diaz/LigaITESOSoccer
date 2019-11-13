package ian.meda.ligaitesosoccer.utils

import ian.meda.ligaitesosoccer.beans.Equipo

var currEquipo: Equipo = Equipo("", true, 0, 0, 0,
                                0, 0, 0, 0, 0,
                                0 )

const val SHARED_PREFERENCES = "ian.meda.ligaitesosoccer.SHARED_PREFERENCES"
const val SESSION_USER_ID = "SESSION_USER_ID"
const val SESSION_TEAM = "SESSION_TEAM"
const val SESSION_USERNAME = "SESSION_USERNAME"
const val SESSION_USERTYPE = "SESSION_USERTYPE"