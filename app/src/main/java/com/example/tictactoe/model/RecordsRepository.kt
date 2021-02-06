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
            user.winCount = user.winCount + 1
        else
            user.loseCount = user.loseCount + 1
        recordsList.add(user)
    }
}