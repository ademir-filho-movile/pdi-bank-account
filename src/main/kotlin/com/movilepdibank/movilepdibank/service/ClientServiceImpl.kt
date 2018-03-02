package com.movilepdibank.movilepdibank.service

import com.movilepdibank.movilepdibank.dao.ClientDAO
import com.movilepdibank.movilepdibank.entity.Client
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl (val clientDAO: ClientDAO):ClientService
{
        override fun getClients(): List<Client> {
            //val externalIds: MutableList<String> = mutableListOf()
            val externalPlatformReferences = clientDAO.selectClients()
            return externalPlatformReferences
        }

        override fun getClient(clientId: Int): List<Client> {
            //val externalIds: MutableList<String> = mutableListOf()
            val externalPlatformReferences = clientDAO.selectClient(clientId)
            return externalPlatformReferences
        }


}