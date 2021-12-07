package com.example.kodeheroesapp.data.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kodeheroesapp.data.contracts.HeroContract

@Entity(tableName = HeroContract.TABLE_NAME)
data class HeroEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = HeroContract.Columns.ID)
    val id: Int,

    @ColumnInfo(name = HeroContract.Columns.NICK_NAME)
    val nickName: String,

    @ColumnInfo(name = HeroContract.Columns.REAL_NAME)
    val realName: String,

    @ColumnInfo(name = HeroContract.Columns.PUBLISHER)
    val publisher: String,

    @ColumnInfo(name = HeroContract.Columns.IMAGE_URL)
    val imageUrl: String
)
