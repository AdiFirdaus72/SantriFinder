import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santrifinder.Barang
import com.example.santrifinder.R

class BarangAdapter(
    private val context: Context,
    private val dataList: ArrayList<Barang>,
    private val onEdit: (Barang) -> Unit,
    private val onDelete: (Barang) -> Unit
) : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    class BarangViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaBarang: TextView = view.findViewById(R.id.tvNamaBarang)
        val tvNamaPenemu: TextView = view.findViewById(R.id.tvNamaPenemu)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
        val btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false)
        return BarangViewHolder(view)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val barang = dataList[position]
        holder.tvNamaBarang.text = barang.namaBarang
        holder.tvNamaPenemu.text = barang.namaPenemu
        holder.tvTanggal.text = barang.tanggalDitemukan

        holder.btnEdit.setOnClickListener { onEdit(barang) }
        holder.btnDelete.setOnClickListener { onDelete(barang) }
    }

    override fun getItemCount(): Int = dataList.size
}
