package br.com.vitorsalgado.example.analytics

data class TraceScreenArgs(@Screen val screen: String, val params: Map<String, Any>)

data class TraceActionArgs(@Action val action: String, val params: Map<String, Any>)
