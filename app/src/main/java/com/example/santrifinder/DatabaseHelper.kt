import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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
                $COLUMN_GAMBAR_DATA BLOB
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
    }
}
