package com.movilepdibank.movilepdibank.service

import com.movilepdibank.movilepdibank.dao.AccountDAO
import com.movilepdibank.movilepdibank.entity.Account
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl (val accountDAO: AccountDAO):AccountService{

    override fun getAccounts(clientId: Int): List<Account> {
        //val externalIds: MutableList<String> = mutableListOf()
        val externalPlatformReferences = accountDAO.selectAccounts(clientId)
        return externalPlatformReferences
    }

}