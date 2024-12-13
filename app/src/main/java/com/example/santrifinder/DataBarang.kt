package com.example.santrifinder

import BarangAdapter
import DatabaseHelper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private lateinit var dbHelper: DatabaseHelper

class DataBarang : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var barangAdapter: BarangAdapter
    private val barangList = ArrayList<Barang>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_barang)

        // Database Helper
        dbHelper = DatabaseHelper(this)

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerViewBarang)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi Adapter hanya untuk menampilkan data
        barangAdapter = BarangAdapter(this, barangList)

        recyclerView.adapter = barangAdapter

        // Tampilkan data
        tampilkanDataBarang()
    }

    override fun onResume() {
        super.onResume()
        tampilkanDataBarang()
    }

    private fun tampilkanDataBarang() {
        barangList.clear()
        barangList.addAll(dbHelper.getAllBarang()) // Mengambil data dari database
        barangAdapter.notifyDataSetChanged()
    }
}
