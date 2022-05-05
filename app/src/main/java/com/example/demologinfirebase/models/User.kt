package com.example.demologinfirebase.models

import java.util.Date

data class User(
    val uid: String? = null,
    val nombre: String? = null,
    val apellido: String? = null,
    val email: String? = null,
    val registered_at: Date? = null
)
