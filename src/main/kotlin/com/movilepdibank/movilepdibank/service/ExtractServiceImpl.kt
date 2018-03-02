package com.movilepdibank.movilepdibank.service

import com.movilepdibank.movilepdibank.dao.ClientDAO
import com.movilepdibank.movilepdibank.dao.ExtractDAO
import com.movilepdibank.movilepdibank.entity.Extract
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class ExtractServiceImpl (val extractDAO: ExtractDAO):ExtractService
{

    override fun getExtractList(id_user: Int, id_account: Int): List<Extract> {
        //val externalIds: MutableList<String> = mutableListOf()
        val externalPlatformReferences = extractDAO.selectExtractList(id_user, id_account)
        return externalPlatformReferences
    }

    override fun insertExtractOperation(id_user: Int, id_account: Int, id_operation: Int, value: Double): String {
        //val externalIds: MutableList<String> = mutableListOf()

        val extractOperation: Extract = Extract(
                id_user = id_user, // auto_increment
                id_account = id_account,
                id_operation = id_operation,
                value = value
               )

        val externalPlatformReferencesFim = extractDAO.insertExtractOperation(extractOperation)
        return externalPlatformReferencesFim
    }

}