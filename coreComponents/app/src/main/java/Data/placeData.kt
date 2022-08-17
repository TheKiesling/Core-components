package com.example.basicfrontend.Data

import java.io.Serializable

data class placeData(
        val name: String,
        val address: String,
        val schedule: String
) : Serializable