package com.movilepdibank.movilepdibank.service

import com.movilepdibank.movilepdibank.entity.Account

interface AccountService {

    fun getAccounts(clientId: Int): List<Account>

}