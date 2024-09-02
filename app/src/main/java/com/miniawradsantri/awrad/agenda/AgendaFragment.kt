package com.miniawradsantri.awrad.agenda

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.databinding.FragmentAgendaBinding
import java.text.SimpleDateFormat
import java.util.Date


class AgendaFragment : Fragment() {

    private lateinit var adapter: AgendaAdapter
    private val bd = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agenda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAgendaBinding.bind(view)

        bd.collection("agenda")
            .orderBy("tanggal")
            .get()
            .addOnSuccessListener {
                val listData: ArrayList<AgendaModel> = ArrayList()
                listData.clear()
                Log.d("Error", "Gagal mengambil data")

                for (document in it) {

                    val timestamp = document.get("tanggal") as com.google.firebase.Timestamp
                    val milliseconds = timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000
                    val sdf = SimpleDateFormat("MM/dd/yyyy")
                    val netDate = Date(milliseconds)
                    val formattedDate = sdf.format(netDate)

                    listData.add(
                        AgendaModel(
                            document.get("nama").toString(),
                            timestamp,
                            document.get("kategori").toString(),
                            document.get("gambar").toString()
                        )
                    )
                }

                val agendaAdapter = AgendaAdapter(listData)
                binding.rvAgenda.apply {
                    adapter = agendaAdapter
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
            .addOnFailureListener {
                Log.w("Error", "Gagal mengambil data")
            }

    }
}