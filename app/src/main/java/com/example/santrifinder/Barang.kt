package com.example.santrifinder

data class Barang(
    val id: Int,
    val namaBarang: String,
    val namaPenemu: String,
    val jamDitemukan: String,
    val tanggalDitemukan: String,
    val tempatDitemukan: String,
    val ciriCiri: String,
    val statusBarang: String,
    val gambarData: ByteArray?,

    val namaPemilik: String,
    val tanggalPengambilan: String,
    val jamPengambilan: String,
    val gambarPengambilan: ByteArray?
)
