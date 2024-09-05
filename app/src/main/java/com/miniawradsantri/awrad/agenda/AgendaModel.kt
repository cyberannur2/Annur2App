package com.miniawradsantri.awrad.agenda

data class AgendaModel(
    val nama: String,
    val tanggal: com.google.firebase.Timestamp,
    val kategori: String,
    val gambar: String
)
