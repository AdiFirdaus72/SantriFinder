package com.example.santrifinder

import BarangAdapter
import DatabaseHelper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DataBarang : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var barangAdapter: BarangAdapter
    private lateinit var recyclerView: RecyclerView
    private val barangList = ArrayList<Barang>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_barang)

        databaseHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerViewBarang)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadData()

        barangAdapter = BarangAdapter(this, barangList, onEdit = { barang ->
            // Logika edit data
        }, onDelete = { barang ->
            // Logika hapus data
//            databaseHelper.deleteBarang(barang.id)
            loadData()
        })

        recyclerView.adapter = barangAdapter
    }

    private fun loadData() {
        barangList.clear()
//        barangList.addAll(databaseHelper.getAllBarang())
        barangAdapter.notifyDataSetChanged()
    }
}
