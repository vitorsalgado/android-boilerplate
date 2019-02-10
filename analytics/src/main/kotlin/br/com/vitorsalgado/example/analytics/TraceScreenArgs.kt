package br.com.vitorsalgado.example.analytics

data class TraceScreenArgs @JvmOverloads constructor(
    val screen: String,
    val params: Map<String, Any> = emptyMap()
)
