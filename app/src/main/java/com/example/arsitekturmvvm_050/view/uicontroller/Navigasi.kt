package com.example.arsitekturmvvm_050.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arsitekturmvvm_050.model.DataJK.JenisK
import com.example.arsitekturmvvm_050.view.FormIsian
import com.example.arsitekturmvvm_050.view.TampilData
import com.example.arsitekturmvvm_050.viewmodel.SiswaViewMode

enum class Navigasi{
    Formulirku,

    Detail
}


@Composable
fun DataApp(
    modifier: Modifier,
    viewModel: SiswaViewMode = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold { isiRuang->
        val uiState = viewModel.statusUI
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,

            modifier = Modifier.padding(paddingValues = isiRuang)){
            composable(route = Navigasi.Formulirku.name){
                val konteks = LocalContext.current
                FormIsian(

                    jenisK = JenisK.map { id -> konteks.resources.getString(id) },
                    OnSubmitBtnClicked = {
                        viewModel.setSiswa(it)
                        navController.navigate(route = Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name){
                TampilData(
                    statusUiSiswa = uiState.value,
                    onBackBtnClicked = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
        }
    }
}

private fun cancelAndBackToFormulir(
    navController: NavController
){
    navController.popBackStack(route = Navigasi.Formulirku.name, inclusive = false)
}