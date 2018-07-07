package test.stayer.criminalintent

import java.util.*

object CrimeLab {

    var crimes = mutableMapOf<UUID, Crime>()
    var crimesArrayList = arrayListOf<Crime>()

    init {
        for (i in 0.until(100)) {
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.solved = i % 2 == 0
            crime.requiresPolice = i % 3 == 0
            crimes[crime.id] = crime
        }
        for (crime in crimes) {
            crimesArrayList.add(crime.value)
        }
    }

    fun getCrime(id: UUID): Crime? {
        return crimes[id]
    }

}