package com.movilepdibank.movilepdibank.resource

import com.movilepdibank.movilepdibank.dao.ExtractDAO
import com.movilepdibank.movilepdibank.entity.Extract
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/extract")
class ExtractResource(val extractDAO: ExtractDAO){

    @GetMapping("/{clientId}/{accountId}")
    fun getClients(@PathVariable clientId:String, @PathVariable accountId:String): List<Extract>{
        //return clientRepository.findAll();
        return extractDAO.selectExtractList(clientId.toInt(), accountId.toInt())
    }

    @GetMapping("/deposit/{clientId}/{accountId}/{value}")
    fun insertDepositClient(@PathVariable clientId:String, @PathVariable accountId:String, @PathVariable value:String): String{
        //return clientRepository.findAll();
        var valueReplace = value.replace("," , ".")

        val extractOperation: Extract = Extract(
                id_user = clientId.toInt(), // auto_increment
                id_account = accountId.toInt(),
                id_operation = 1,
                value = valueReplace.toDouble()
        )

        return extractDAO.insertExtractOperation(extractOperation)
    }

    @GetMapping("/withdraw/{clientId}/{accountId}/{value}")
    fun insertWithdrawClient(@PathVariable clientId:String, @PathVariable accountId:String, @PathVariable value:String): String{
        //return clientRepository.findAll();
        var valueReplace = value.replace("," , ".")

        val extractOperation: Extract = Extract(
                id_user = clientId.toInt(), // auto_increment
                id_account = accountId.toInt(),
                id_operation = 2,
                value = valueReplace.toDouble()
        )

        return extractDAO.insertExtractOperation(extractOperation)
    }

}