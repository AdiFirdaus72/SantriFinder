package com.example.santrifinder

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


// DEKLARASI UNTUK BERPINDAH KE ACTIVITY TAMBAH DATA
private lateinit var tombolTambahData: LinearLayout

// DEKLARASI UNTUK BERPINDAH KE ACTIVITY DATA BARANG HILANG
private lateinit var tombolLihatData: LinearLayout

// DEKLARASI UNTUK BERPINDAH KE ACTIVITY BANTUAN
private lateinit var tombolBantuan: LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // AKSI UNTUK BERPINDAH KE ACTIVITY TAMBAH DATA
        tombolTambahData = findViewById(R.id.tombolTambahData)
        tombolTambahData.setOnClickListener {
            val intent = Intent(this, tambahData::class.java)
            startActivity(intent)
        }

        // AKSI UNTUK BERPINDAH KE ACTIVITY DATA BARANG HILANG
        tombolLihatData = findViewById(R.id.tombolLihatData)
        tombolLihatData.setOnClickListener {
            val intent = Intent(this, DataBarang::class.java)
            startActivity(intent)
        }
        // AKSI UNTUK BERPINDAH KE ACTIVITY DATA BANTUAN
        tombolBantuan = findViewById(R.id.tombolBantuan)
        tombolBantuan.setOnClickListener {
            val intent = Intent(this, bantuan::class.java)
            startActivity(intent)
        }

    }
}