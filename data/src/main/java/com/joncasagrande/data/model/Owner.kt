package com.joncasagrande.data.model

data class Owner(
    val title: String,
    val description: String,
    val type: String,
    val properties: Properties,
    val required: List<String>
)