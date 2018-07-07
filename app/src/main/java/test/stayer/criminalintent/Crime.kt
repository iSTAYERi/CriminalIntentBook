package test.stayer.criminalintent

import java.util.*

class Crime {

    val id = UUID.randomUUID()!!
    var date = Date()
    var title = ""
    var solved = false
    var requiresPolice = false
}