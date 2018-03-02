package com.movilepdibank.movilepdibank.service

import com.movilepdibank.movilepdibank.entity.Extract

interface ExtractService {

    fun getExtractList(id_user: Int, id_account: Int): List<Extract>

    fun insertExtractOperation(id_user: Int, id_account: Int, id_operation: Int, value: Double): String

//    fun getExtractValue(): List<Double>
//
//    fun insertExtract(): List<Extract>
//
//    fun withdrawExtract(): List<Extract>

}