package com.example.tictactoe

object RecordsData {

    private var recordsList: MutableSet<Record> = mutableSetOf()

    init {
        for (i in 0..5) recordsList.add(Record("User$i", 0, 0))
    }

    fun getRecordsList(): List<Record> {
        return recordsList.toList()
    }

    private fun getUserRecord(username: String): Record {
        return recordsList.find { record -> record.name === username } ?: Record(username, 0, 0)
    }

    fun updateUserRecord(username: String, isWinner: Boolean) {
        val user = getUserRecord(username)
        recordsList.remove(user)
        if (isWinner)
            user.winCount++
        else
            user.loseCount++
        recordsList.add(user)
    }

    fun getRecordsListSize(): Int {
        return recordsList.size
    }
}