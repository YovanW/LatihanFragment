package kelas.paba.latihanfragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: HistoryViewModel by viewModels()

    private lateinit var root: View
    private lateinit var etAngka1: EditText
    private lateinit var etAngka2: EditText
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        root = findViewById(R.id.root)
        etAngka1 = findViewById(R.id.etAngka1)
        etAngka2 = findViewById(R.id.etAngka2)
        tvHasil = findViewById(R.id.tvHasil)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerTambah, HistoryFragment.newInstance("+"))
                .replace(R.id.containerKurang, HistoryFragment.newInstance("-"))
                .replace(R.id.containerKali, HistoryFragment.newInstance("*"))
                .replace(R.id.containerBagi, HistoryFragment.newInstance("/"))
                .commit()
        }

        findViewById<Button>(R.id.btnTambah).setOnClickListener { hitung("+") }
        findViewById<Button>(R.id.btnKurang).setOnClickListener { hitung("-") }
        findViewById<Button>(R.id.btnKali).setOnClickListener { hitung("*") }
        findViewById<Button>(R.id.btnBagi).setOnClickListener { hitung("/") }
    }

    private fun hitung(op: String) {
        val a = etAngka1.text.toString().toDoubleOrNull()
        val b = etAngka2.text.toString().toDoubleOrNull()

        if (a == null || b == null) {
            Toast.makeText(this, "Isi kedua angka dengan benar", Toast.LENGTH_SHORT).show()
            return
        }

        val hasil = when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            else -> {
                if (b == 0.0) {
                    Toast.makeText(this, "Tidak bisa dibagi 0", Toast.LENGTH_SHORT).show()
                    return
                }
                a / b
            }
        }

        tvHasil.text = "Hasil: ${format(hasil)}"
        viewModel.add(op, "${format(a)} $op ${format(b)} = ${format(hasil)}")

        root.setBackgroundColor(if (hasil < 0) Color.RED else Color.WHITE)
    }

    private fun format(d: Double): String =
        if (d % 1.0 == 0.0 && Math.abs(d) < 1e15) d.toLong().toString() else d.toString()
}
