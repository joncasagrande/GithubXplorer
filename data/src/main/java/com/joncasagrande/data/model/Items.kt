package com.joncasagrande.data.model

data class Items(
    val title: String? = null,
    val description: String? = null,
    val type: String? = null,
    val properties: Properties? = null,
    val required: List<String>? = null
)