package com.movilepdibank.movilepdibank.service

import com.movilepdibank.movilepdibank.entity.Client

interface ClientService {

    fun getClients(): List<Client>

    fun getClient(clientId: Int): List<Client>

}