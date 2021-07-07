package com.santis.bcard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val empresa: String,
    val tel: String,
    val email: String,
    val cor: String
)
