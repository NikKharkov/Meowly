package org.catstagram.trackpet.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class Database(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val database = CatDatabase.Companion(databaseDriverFactory.createDriver())
    private val dbQuery = database.catDatabaseQueries

    fun insertNewCat(name: String, gender: String, birthDate: Long) {
        dbQuery.insertNewCat(
            name = name,
            gender = gender,
            birthDate = birthDate,
            avatarImg = null
        )
    }

    fun getAllCats(): Flow<List<UserCat>> {
        return dbQuery.getAllUserCats()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    fun updateCat(id: Long, name: String, gender: String, birthDate: Long) {
        dbQuery.updateCat(
            name = name,
            gender = gender,
            birthDate = birthDate,
            id = id
        )
    }

    fun setCatAvatar(id: Long, avatarImg: ByteArray) {
        dbQuery.setCatAvatar(id = id, avatarImg = avatarImg)
    }
}