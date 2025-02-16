package com.example.stores

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.stores.commonModule.database.StoreDatabase

//DOC: Se declara en el Manifest
class StoreApplication: Application() {
    //object _> singleton, companion -> static
    companion object {
        lateinit var database: StoreDatabase
    }

    override fun onCreate() {
        super.onCreate()
        val MIGRATION_1_2 = object: Migration(1, 2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE STORE_ENTITY ADD COLUMN photoUrl TEXT NOT NULL DEFAULT ''")
            }

        }
        database = Room.databaseBuilder(
            this,
            StoreDatabase::class.java,
            "StoreDatabase")
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}