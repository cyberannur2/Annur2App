package com.miniawradsantri.awrad.bacaan

import androidx.lifecycle.ViewModel

class BacaanViewModel: ViewModel() {

    val tabFragmentMap = mapOf(
        "Yasin" to { ItemTabFragment.newInstance("Content for Yasin") },
        "Tahlil" to { ItemTabFragment.newInstance("Content for Tahlil") },
        "Doa Tahlil" to { ItemTabFragment.newInstance("Content for Doa Tahlil") },
        "Doa Yasin" to { ItemTabFragment.newInstance("Content for Doa Yasin") }
        // Tambahkan tab baru di sini sesuai kebutuhan
    )

}