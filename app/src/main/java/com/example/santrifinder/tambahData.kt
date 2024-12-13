package com.example.santrifinder

import DatabaseHelper
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.ByteArrayOutputStream
import java.util.*

class tambahData : AppCompatActivity() {

    private lateinit var namaBarang: EditText
    private lateinit var namaPenemu: EditText
    private lateinit var tempatDitemukan: EditText
    private lateinit var ciriCiri: EditText
    private lateinit var statusBelumDiambil: RadioButton
    private lateinit var statusSudahDiambil: RadioButton
    private lateinit var jamDitemukan: Button
    private lateinit var tanggalDitemukan: Button
    private lateinit var uploadFoto: Button
    private lateinit var gambarPreview: ImageView
    private var selectedImage: Bitmap? = null
    private var selectedJam: String = ""
    private var selectedTanggal: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_data)

        // Layout adjustments
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi View
        namaBarang = findViewById(R.id.namaBarang)
        namaPenemu = findViewById(R.id.namaPenemu)
        tempatDitemukan = findViewById(R.id.tempatDitemukan)
        ciriCiri = findViewById(R.id.ciriCiri)
        statusBelumDiambil = findViewById(R.id.statusBelumDiambil)
        statusSudahDiambil = findViewById(R.id.statusSudahDiambil)
        jamDitemukan = findViewById(R.id.jamDitemukan)
        tanggalDitemukan = findViewById(R.id.tanggalDitemukan)
        uploadFoto = findViewById(R.id.uploadFoto)
        gambarPreview = findViewById(R.id.gambarPreview)

        val tombolKembali: ImageButton = findViewById(R.id.tombolKembali)
        tombolKembali.setOnClickListener { onBackPressed() }

        val tombolSimpan: Button = findViewById(R.id.tombolSimpan)
        tombolSimpan.setOnClickListener { simpanData() }

        // Fungsi Date Picker
        tanggalDitemukan.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                selectedTanggal = "$dayOfMonth-${month + 1}-$year"
                tanggalDitemukan.text = selectedTanggal
            }, year, month, day)

            datePicker.show()
        }

        // Fungsi Time Picker
        jamDitemukan.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(this, { _, hourOfDay, minute ->
                selectedJam = "$hourOfDay:$minute"
                jamDitemukan.text = selectedJam
            }, hour, minute, true)

            timePicker.show()
        }

        // Fungsi Upload Foto
        uploadFoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 101)
        }
    }

    // Fungsi Menangani Hasil Upload Foto
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            val inputStream = contentResolver.openInputStream(uri!!)
            selectedImage = android.graphics.BitmapFactory.decodeStream(inputStream)
            gambarPreview.setImageBitmap(selectedImage)
        }
    }

    // Fungsi Simpan Data
    private fun simpanData() {
        val nama = namaBarang.text.toString()
        val penemu = namaPenemu.text.toString()
        val tempat = tempatDitemukan.text.toString()
        val ciri = ciriCiri.text.toString()
        val status = if (statusBelumDiambil.isChecked) "Belum Diambil" else "Sudah Diambil"

        if (nama.isNotBlank() && penemu.isNotBlank() && tempat.isNotBlank() && selectedImage != null) {
            val dbHelper = DatabaseHelper(this)
            val db = dbHelper.writableDatabase

            val byteArrayOutputStream = ByteArrayOutputStream()
            selectedImage?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val imageBytes = byteArrayOutputStream.toByteArray()

            val sql = """
                INSERT INTO ${DatabaseHelper.TABLE_NAME_GAMBAR} (
                    ${DatabaseHelper.COLUMN_NAMA_BARANG},
                    ${DatabaseHelper.COLUMN_NAMA_PENEMU},
                    ${DatabaseHelper.COLUMN_JAM_DITEMUKAN},
                    ${DatabaseHelper.COLUMN_TANGGAL_DITEMUKAN},
                    ${DatabaseHelper.COLUMN_TEMPAT_DITEMUKAN},
                    ${DatabaseHelper.COLUMN_CIRI_CIRI},
                    ${DatabaseHelper.COLUMN_STATUS_BARANG},
                    ${DatabaseHelper.COLUMN_GAMBAR_DATA})
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """

            val stmt = db.compileStatement(sql)
            stmt.bindString(1, nama)
            stmt.bindString(2, penemu)
            stmt.bindString(3, selectedJam)
            stmt.bindString(4, selectedTanggal)
            stmt.bindString(5, tempat)
            stmt.bindString(6, ciri)
            stmt.bindString(7, status)
            stmt.bindBlob(8, imageBytes)
            stmt.executeInsert()

            Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Harap isi semua data dengan lengkap!", Toast.LENGTH_SHORT).show()
        }
    }
}
