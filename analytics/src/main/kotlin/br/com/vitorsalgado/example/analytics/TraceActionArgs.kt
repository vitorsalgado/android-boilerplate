package br.com.vitorsalgado.example.analytics

data class TraceActionArgs @JvmOverloads constructor(
    val action: String,
    val params: Map<String, Any> = emptyMap()
)
