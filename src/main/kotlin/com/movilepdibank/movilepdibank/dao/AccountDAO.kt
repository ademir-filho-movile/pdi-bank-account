package com.movilepdibank.movilepdibank.dao

import com.movilepdibank.movilepdibank.entity.Account
import com.movilepdibank.movilepdibank.entity.Client
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

//private val SELECT_ACCOUNT = """﻿SELECT id, id_user FROM public._client_account"""
private val SELECT_ACCOUNTS = """SELECT * FROM _client_account WHERE id_user = ?"""

@Repository
open class AccountDAO(
        //@Qualifier("systemJdbcTemplate")
        private val jdbcTemplate: JdbcTemplate) {


    private val accountRowMapper = { resultSet: ResultSet, _: Int ->
        Account(
                id = resultSet.getInt("id"),
                id_client = resultSet.getInt("id_user")
        )
    }


    fun selectAccounts(clientId: Int): List<Account> {
        //val startTime = System.currentTimeMillis()
        try {
            val list = jdbcTemplate.query(SELECT_ACCOUNTS,
                    arrayOf<Any>(clientId), accountRowMapper)

            //val list = jdbcTemplate.query(SELECT_CLIENT, clientRowMapper)
            return list
        } catch (e: Exception) {
            throw Exception("Error while selecting account from database with externalId: " + clientId, e)
        } finally {
            //showSelectStats("Account by external id", startTime)
        }
    }



}