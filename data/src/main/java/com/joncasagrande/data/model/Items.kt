package com.joncasagrande.data.model

data class Items(
    val title: String,
    val description: String,
    val type: String,
    val properties: Properties,
    val required: List<String>
)