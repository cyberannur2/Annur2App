package com.miniawradsantri.awrad.bacaan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.databinding.FragmentHomeBinding
import com.miniawradsantri.awrad.databinding.FragmentMenuBacaanBinding

class MenuBacaan : Fragment() {

    private var _binding: FragmentMenuBacaanBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMenuBacaanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager = parentFragmentManager
        // Tawassul
        binding.icTawassul.setOnClickListener {
            Log.d("MenuBacaan", "Tawassul icon clicked")
            Toast.makeText(requireContext(), "Tawassul clicked", Toast.LENGTH_SHORT).show()
            fragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_menu_bacaan,
                    BacaanSinglePage.newInstance(
                        getString(R.string.tawassul),
                        getString(R.string.b_tawassul)
                    )
                )
                .addToBackStack(null)
                .commit()
        }

    }

}