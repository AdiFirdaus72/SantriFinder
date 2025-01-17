import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santrifinder.Barang
import com.example.santrifinder.EditDataBarang
import com.example.santrifinder.konfirmasiPengambilan
import com.example.santrifinder.R

class BarangAdapter(
    private val context: Context,
    private val dataList: ArrayList<Barang>
) : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    class BarangViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaBarang: TextView = view.findViewById(R.id.tvNamaBarang)
        val tvNamaPenemu: TextView = view.findViewById(R.id.tvNamaPenemu)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
        val tvJam: TextView = view.findViewById(R.id.tvJam)
        val tvTempat: TextView = view.findViewById(R.id.tvTempat)
        val tvCiri: TextView = view.findViewById(R.id.tvCiri)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val ivGambarBarang: ImageView = view.findViewById(R.id.ivGambarBarang)

        val tombolKonfirmasi: ImageView = view.findViewById(R.id.tombolKonfirmasi)

        // Tambahkan ID tombolEdit
        val tombolEdit: ImageView = view.findViewById(R.id.tombolEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false)
        return BarangViewHolder(view)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val barang = dataList[position]

        // Menampilkan data teks ke dalam TextView
        holder.tvNamaBarang.text = "Nama Barang: " + barang.namaBarang
        holder.tvNamaPenemu.text = "Nama Penemu: " + barang.namaPenemu
        holder.tvTanggal.text = "Tanggal Ditemukan: " + barang.tanggalDitemukan
        holder.tvJam.text = "Jam Ditemukan: " + barang.jamDitemukan
        holder.tvTempat.text = "Tempat Ditemukan: " + barang.tempatDitemukan
        holder.tvCiri.text = "Ciri-Ciri: " + barang.ciriCiri
        holder.tvStatus.text = "Status Barang: " + barang.statusBarang

        // Menampilkan gambar jika ada
        if (barang.gambarData != null) {
            val bitmap: Bitmap = BitmapFactory.decodeByteArray(barang.gambarData, 0, barang.gambarData.size)
            holder.ivGambarBarang.setImageBitmap(bitmap)
        }

        // Aksi klik tombolKonfirmasi
        holder.tombolKonfirmasi.setOnClickListener {
            val intent = Intent(context, konfirmasiPengambilan::class.java)
            intent.putExtra("NAMA_BARANG", barang.namaBarang)
            intent.putExtra("NAMA_PEMILIK", barang.namaPemilik)
            context.startActivity(intent)
        }

        // Aksi klik tombolEdit
        holder.tombolEdit.setOnClickListener {
            val editIntent = Intent(context, EditDataBarang::class.java)
            editIntent.putExtra("BARANG_ID", barang.id)
            context.startActivity(editIntent)

        }

    }

    override fun getItemCount(): Int = dataList.size
}
