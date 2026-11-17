package kelas.paba.latihanfragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val viewModel: HistoryViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val op = requireArguments().getString(ARG_OP)!!
        val tvJudul = view.findViewById<TextView>(R.id.tvJudul)
        val tvHistory = view.findViewById<TextView>(R.id.tvHistory)

        tvJudul.text = "History " + when (op) {
            "+" -> "Penjumlahan (+)"
            "-" -> "Pengurangan (-)"
            "*" -> "Perkalian (*)"
            else -> "Pembagian (/)"
        }

        viewModel.history(op).observe(viewLifecycleOwner) { list ->
            tvHistory.text =
                if (list.isEmpty()) "Belum ada riwayat" else list.joinToString("\n")
        }
    }

    companion object {
        private const val ARG_OP = "op"

        fun newInstance(op: String) = HistoryFragment().apply {
            arguments = Bundle().apply { putString(ARG_OP, op) }
        }
    }
}