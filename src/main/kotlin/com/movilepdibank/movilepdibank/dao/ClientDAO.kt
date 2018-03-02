package com.movilepdibank.movilepdibank.dao

import com.movilepdibank.movilepdibank.entity.Client
import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.JdbcTemplate
import java.sql.ResultSet

private val SELECT_CLIENT = """SELECT * FROM _client WHERE id = ?"""
private val SELECT_CLIENTS = """SELECT * FROM _client ORDER BY id"""


@Repository
open class ClientDAO(
    //@Qualifier("systemJdbcTemplate")
    private val jdbcTemplate: JdbcTemplate) {

    private val clientRowMapper = { resultSet: ResultSet, _: Int ->
        Client(
                id = resultSet.getInt("id"),
                name = resultSet.getString("name"),
                age = resultSet.getInt("age")

        )
    }


    fun selectClients(): List<Client> {
        try {
            val list = jdbcTemplate.query(SELECT_CLIENTS, clientRowMapper)
            return list
        } catch (e: Exception) {
            throw Exception("Error while selecting account from database with externalId", e)
        } finally {
            //showSelectStats("Account by external id", startTime)"
        }
    }

    fun selectClient(clientId: Int): List<Client> {
        //val startTime = System.currentTimeMillis()
        try {
            val list = jdbcTemplate.query(SELECT_CLIENT,
                    arrayOf<Any>(clientId), clientRowMapper)

            //val list = jdbcTemplate.query(SELECT_CLIENT, clientRowMapper)
            return list
        } catch (e: Exception) {
            throw Exception("Error while selecting account from database with externalId: " + clientId, e)
        } finally {
            //showSelectStats("Account by external id", startTime)
        }
    }


}