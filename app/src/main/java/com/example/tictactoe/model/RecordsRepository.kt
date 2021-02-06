package com.example.tictactoe.model

object RecordsRepository {

    private var recordsList = mutableListOf<Record>()

    fun getRecordsList() = recordsList

    private fun getUserRecord(username: String): Record {
        return recordsList.find { record -> record.name == username } ?: Record(username, 0, 0)
    }

    fun updateUserRecord(username: String, isWinner: Boolean) {
        val user = getUserRecord(username)
        recordsList.remove(getUserRecord(username))
        if (isWinner)
            user.winCount++
        else
            user.loseCount++
        recordsList.add(user)
    }
}