package com.example.arsitekturmvvm_050.viewmodel

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.arsitekturmvvm_050.model.Siswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SiswaViewMode : ViewModel() {
    private val _statusUI = MutableStateFlow(value = Siswa())
    val statusUI: StateFlow<Siswa> = _statusUI.asStateFlow()

    fun setSiswa(ls: MutableList<String>){
        _statusUI.update { statusSaatIni ->
            statusSaatIni.copy(nama = ls[0], gender = ls[1], alamat = ls[2])
        }
    }
}