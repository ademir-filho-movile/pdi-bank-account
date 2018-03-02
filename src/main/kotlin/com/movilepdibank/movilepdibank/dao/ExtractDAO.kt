package com.movilepdibank.movilepdibank.dao

import com.movilepdibank.movilepdibank.entity.Extract
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.HashMap




private val SELECT_EXTRACT = """SELECT id, id_user, id_account, id_operation, value FROM _account_extract WHERE id_account= ?"""
private val INSERT_EXTRACT_OPERATION = """INSERT INTO _account_extract(id_user, id_account, id_operation, value) VALUES (?, ?, ?, ?)"""
private val SELECT_BALANCE = """SELECT (SUM(value) - (SELECT SUM(value) FROM _account_extract WHERE id_account = ? AND id_operation = 2)) As value  FROM _account_extract WHERE id_account = 1 AND id_operation = ?;"""


@Repository
open class ExtractDAO(
        //@Qualifier("systemJdbcTemplate")
        private val jdbcTemplate: JdbcTemplate) {


    private val extractRowMapper = { resultSet: ResultSet, _: Int ->
        Extract(
                id = resultSet.getInt("id"),
                id_user = resultSet.getInt("id_user"),
                id_account = resultSet.getInt("Id_account"),
                id_operation = resultSet.getInt("id_operation"),
                value = resultSet.getDouble("value")
        )
    }

    private val balanceRowMapper = { resultSet: ResultSet, _: Int ->
         resultSet.getDouble("value")
    }


    fun selectExtractList(clientId: Int, accountId: Int): List<Extract> {
        try {
            val list = jdbcTemplate.query(SELECT_EXTRACT,
                    arrayOf<Any>(accountId), extractRowMapper)
            return list

        } catch (e: Exception) {
            throw Exception("Error while selecting account from database with externalId", e)
        } finally {
            //showSelectStats("Account by external id", startTime)"
        }
    }

    fun getAccountBalance(clientId: Int, accountId: Int): Double? {
        try {

            //val parameters = HashMap<String, Any>()
            //parameters.put("accountId", accountId)

            val list = jdbcTemplate.query(
                    SELECT_BALANCE, arrayOf(accountId, accountId), balanceRowMapper)

            return list?.first()

        } catch (e: Exception) {
            throw Exception("Error while selecting account from database with externalId", e)
        } finally {
            //showSelectStats("Account by external id", startTime)"
        }
    }

    fun insertExtractOperation(extractOperation: Extract): String {


        //val startTime = System.currentTimeMillis()
        try {

            synchronized(this){
                var balance = getAccountBalance(extractOperation.id_user, extractOperation.id_account)

                if(extractOperation.id_operation == 2){
                    if(balance != null){
                        balance -= extractOperation.value
                        if(balance <= 0){
                            return "Saldo insuficiente:" + balance
                        }
                    }
                } else {
                    if(balance != null){
                        balance += extractOperation.value
                    }
                }

                jdbcTemplate.update(INSERT_EXTRACT_OPERATION) { preparedStatement ->
                    preparedStatement.setInt(1, extractOperation.id_user)
                    preparedStatement.setInt(2, extractOperation.id_account)
                    preparedStatement.setInt(3, extractOperation.id_operation)
                    preparedStatement.setDouble(4, extractOperation.value)

                }
                return "Saldo: ${balance}"
            }


        } catch (e: java.lang.Exception) {
            throw java.lang.Exception("Error while inserting ExternalPlatformReference in database: " + extractOperation, e)
        }
        finally {
            //showInsertStats("ExternalPlatformReference", startTime)
        }
    }

//    fun selectClient(clientId: Int): List<Client> {
//        //val startTime = System.currentTimeMillis()
//        try {
//            val list = jdbcTemplate.query(SELECT_CLIENT,
//                    arrayOf<Any>(clientId), clientRowMapper)
//
//            //val list = jdbcTemplate.query(SELECT_CLIENT, clientRowMapper)
//            return list
//        } catch (e: Exception) {
//            throw Exception("Error while selecting account from database with externalId: " + clientId, e)
//        } finally {
//            //showSelectStats("Account by external id", startTime)
//        }
//    }


}