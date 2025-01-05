import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.santrifinder.Barang

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME_GAMBAR (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAMA_BARANG TEXT,
                $COLUMN_NAMA_PENEMU TEXT,
                $COLUMN_JAM_DITEMUKAN TEXT,
                $COLUMN_TANGGAL_DITEMUKAN TEXT,
                $COLUMN_TEMPAT_DITEMUKAN TEXT,
                $COLUMN_CIRI_CIRI TEXT,
                $COLUMN_STATUS_BARANG TEXT,
                $COLUMN_GAMBAR_DATA BLOB, 
                $COLUMN_NAMA_PEMILIK TEXT,
                $COLUMN_JAM_PENGAMBILAN TEXT,
                $COLUMN_TANGGAL_PENGAMBILAN TEXT,
                $COLUMN_GAMBAR_PENGAMBILAN BLOB
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_GAMBAR")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "santrifinder.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME_GAMBAR = "barang_hilang"
        const val COLUMN_ID = "id"
        const val COLUMN_NAMA_BARANG = "nama_barang"
        const val COLUMN_NAMA_PENEMU = "nama_penemu"
        const val COLUMN_JAM_DITEMUKAN = "jam_ditemukan"
        const val COLUMN_TANGGAL_DITEMUKAN = "tanggal_ditemukan"
        const val COLUMN_TEMPAT_DITEMUKAN = "tempat_ditemukan"
        const val COLUMN_CIRI_CIRI = "ciri_ciri"
        const val COLUMN_STATUS_BARANG = "status_barang"
        const val COLUMN_GAMBAR_DATA = "gambar_data"
        const val COLUMN_NAMA_PEMILIK = "nama_pemilik"
        const val COLUMN_JAM_PENGAMBILAN = "jam_pengambilan"
        const val COLUMN_TANGGAL_PENGAMBILAN = "tanggal_pengambilan"
        const val COLUMN_GAMBAR_PENGAMBILAN = "gambar_pengambilan"
    }

    // Mengambil semua barang
    fun getAllBarang(): ArrayList<Barang> {
        val barangList = ArrayList<Barang>()
        val db = readableDatabase

        val cursor = db.query(TABLE_NAME_GAMBAR, null, null, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                barangList.add(createBarangFromCursor(cursor))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return barangList
    }

    // Mengambil barang berdasarkan ID
    fun getBarangById(id: Int): Barang? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME_GAMBAR,
            null,
            "$COLUMN_ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        var barang: Barang? = null
        if (cursor != null && cursor.moveToFirst()) {
            barang = createBarangFromCursor(cursor)
        }

        cursor.close()
        db.close()
        return barang
    }

    // Fungsi untuk memperbarui data barang
    fun updateBarang(barang: Barang): Int {
        val db = writableDatabase

        val contentValues = ContentValues().apply {
            put(COLUMN_NAMA_BARANG, barang.namaBarang)
            put(COLUMN_NAMA_PENEMU, barang.namaPenemu)
            put(COLUMN_JAM_DITEMUKAN, barang.jamDitemukan)
            put(COLUMN_TANGGAL_DITEMUKAN, barang.tanggalDitemukan)
            put(COLUMN_TEMPAT_DITEMUKAN, barang.tempatDitemukan)
            put(COLUMN_CIRI_CIRI, barang.ciriCiri)
            put(COLUMN_GAMBAR_DATA, barang.gambarData)
        }

        val result = db.update(
            TABLE_NAME_GAMBAR,
            contentValues,
            "$COLUMN_ID = ?",
            arrayOf(barang.id.toString())
        )

        db.close()
        return result
    }

    // Membuat objek Barang dari Cursor
    private fun createBarangFromCursor(cursor: Cursor): Barang {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val namaBarang = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_BARANG))
        val namaPenemu = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_PENEMU))
        val jamDitemukan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JAM_DITEMUKAN))
        val tanggalDitemukan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL_DITEMUKAN))
        val tempatDitemukan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEMPAT_DITEMUKAN))
        val ciriCiri = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CIRI_CIRI))
        val statusBarang = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS_BARANG))
        val gambarData = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_GAMBAR_DATA))

        val namaPemilik = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_PEMILIK))
        val jamPengambilan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JAM_PENGAMBILAN))
        val tanggalPengambilan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL_PENGAMBILAN))
        val gambarPengambilan = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_GAMBAR_PENGAMBILAN))

        return Barang(
            id, namaBarang, namaPenemu, jamDitemukan, tanggalDitemukan, tempatDitemukan,
            ciriCiri, statusBarang, gambarData, namaPemilik, tanggalPengambilan, jamPengambilan, gambarPengambilan
        )
    }
}
