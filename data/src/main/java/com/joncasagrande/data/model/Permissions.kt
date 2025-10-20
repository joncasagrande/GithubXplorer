package com.joncasagrande.data.model

data class Permissions(
	val pull: Boolean? = null,
	val maintain: Boolean? = null,
	val admin: Boolean? = null,
	val triage: Boolean? = null,
	val push: Boolean? = null
)