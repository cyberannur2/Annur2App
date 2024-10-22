package com.miniawradsantri.awrad.bacaan

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.setFragmentResult
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.databinding.FragmentEditTextSizeBinding


class EditTextSize : Fragment() {
    private lateinit var binding: FragmentEditTextSizeBinding
    private val MIN_TEXT_SIZE = 16
    private val DEFAULT_TEXT_SIZE = 16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditTextSizeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedTextSize = loadTextSize()
        Log.d("EditTextSize", "Saved text size: $savedTextSize")
        binding.editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, savedTextSize.toFloat())
        binding.seekBar.progress = (savedTextSize - MIN_TEXT_SIZE) / 6

        binding.seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    val textSize = p1 * 6 + MIN_TEXT_SIZE
                    binding.editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())

                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    val textSize = p0?.progress?.let { it * 6 + MIN_TEXT_SIZE } ?: MIN_TEXT_SIZE
                    saveTextSize(textSize)

                    val resultBundle = Bundle()
                    resultBundle.putInt("TEXT_SIZE", textSize)
                    Log.d("EditTextSize", "Setting text size result: $textSize")
                    setFragmentResult("requestKey", resultBundle)


                }

            }
        )
    }

    private fun saveTextSize(textSize: Int) {
        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs_TextSize", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("TEXT_SIZE", textSize)
        editor.apply()

    }

    private fun loadTextSize(): Int {
        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs_TextSize", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("TEXT_SIZE", DEFAULT_TEXT_SIZE)
    }


    


}