package com.codinginflow.mvvmtodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codinginflow.mvvmtodo.data.di.ApplicationScope
import com.codinginflow.mvvmtodo.data.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase()  {
    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            //db operations
            val dao = database.get().taskDao()
            applicationScope.launch {
                dao.insert(Task(name="Wash the dishes"))
                dao.insert(Task(name="Do the laundry", important = true))
                dao.insert(Task(name="Run into the wall", completed = true))
                dao.insert(Task(name="Smack the homeless guy"))
                dao.insert(Task(name="Rob the bank"))
                dao.insert(Task(name="Do a boat load of acid"))
            }
        }
    }
}