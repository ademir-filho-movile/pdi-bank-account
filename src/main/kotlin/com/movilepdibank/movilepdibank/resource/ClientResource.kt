package com.movilepdibank.movilepdibank.resource

import com.movilepdibank.movilepdibank.entity.Client
import com.movilepdibank.movilepdibank.dao.ClientDAO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/client")
class ClientResource(val clientDAO: ClientDAO){

    @GetMapping("/")
    fun getClients(): List<Client>{
        //return clientRepository.findAll();
        return clientDAO.selectClients()
    }

    @GetMapping("/{clientId}")
    fun getClients(@PathVariable clientId:String): List<Client>{
        //return clientRepository.findAll();
        return clientDAO.selectClient(clientId.toInt())
    }


}
