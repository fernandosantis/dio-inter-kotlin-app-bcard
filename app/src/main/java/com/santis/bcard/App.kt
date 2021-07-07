package com.santis.bcard

import android.app.Application
import com.santis.bcard.data.AppDatabase
import com.santis.bcard.data.BusinessCardRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }

    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}
