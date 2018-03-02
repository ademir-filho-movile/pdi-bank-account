package com.movilepdibank.movilepdibank.resource

import com.movilepdibank.movilepdibank.dao.AccountDAO
import com.movilepdibank.movilepdibank.dao.ClientDAO
import com.movilepdibank.movilepdibank.entity.Account
import com.movilepdibank.movilepdibank.entity.Client
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class AccountResource {

    @RestController
    @RequestMapping("/account")
    class ClientResource(val accountDAO: AccountDAO){


        @GetMapping("/{clientId}")
        fun getClients(@PathVariable clientId:String): List<Account>{
            //return clientRepository.findAll();
            return accountDAO.selectAccounts(clientId.toInt())
        }


    }

}