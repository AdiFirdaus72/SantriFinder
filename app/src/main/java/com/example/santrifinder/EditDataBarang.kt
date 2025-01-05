package com.example.santrifinder

import DatabaseHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class EditDataBarang : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private var barangId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data_barang)

        // Inisialisasi DatabaseHelper
        dbHelper = DatabaseHelper(this)

        // Ambil ID barang dari Intent
        barangId = intent.getIntExtra("BARANG_ID", -1)
        if (barangId == -1) {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Ambil data barang berdasarkan ID
        val barang = dbHelper.getBarangById(barangId)
        if (barang != null) {
            // Isi field dengan data barang
            populateFields(barang)
        } else {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Tombol Simpan
        val tombolSimpan: Button = findViewById(R.id.tombolSimpan)
        tombolSimpan.setOnClickListener {
            updateData()
        }
    }

    private fun populateFields(barang: Barang) {
        findViewById<EditText>(R.id.editNamaBarang).setText(barang.namaBarang)
        findViewById<EditText>(R.id.editNamaPenemu).setText(barang.namaPenemu)
        findViewById<EditText>(R.id.editJamDitemukan).setText(barang.jamDitemukan)
        findViewById<EditText>(R.id.editTanggalDitemukan).setText(barang.tanggalDitemukan)
        findViewById<EditText>(R.id.editTempatDitemukan).setText(barang.tempatDitemukan)
        findViewById<EditText>(R.id.editCiriCiri).setText(barang.ciriCiri)
        // Foto tidak diisi ulang pada UI (bisa ditambahkan sesuai kebutuhan)
    }

    private fun updateData() {
        val namaBarang = findViewById<EditText>(R.id.editNamaBarang).text.toString()
        val namaPenemu = findViewById<EditText>(R.id.editNamaPenemu).text.toString()
        val jamDitemukan = findViewById<EditText>(R.id.editJamDitemukan).text.toString()
        val tanggalDitemukan = findViewById<EditText>(R.id.editTanggalDitemukan).text.toString()
        val tempatDitemukan = findViewById<EditText>(R.id.editTempatDitemukan).text.toString()
        val ciriCiri = findViewById<EditText>(R.id.editCiriCiri).text.toString()

        val success = true


//        val success = dbHelper.updateBarang(
//            barangId,
//            namaBarang,
//            namaPenemu,
//            jamDitemukan,
//            tanggalDitemukan,
//            tempatDitemukan,
//            ciriCiri
//        )

        if (success) {
            Toast.makeText(this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show()
        }
    }
}
