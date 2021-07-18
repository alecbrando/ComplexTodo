package com.codinginflow.mvvmtodo.data.database

import androidx.room.*
import com.codinginflow.mvvmtodo.data.model.Task
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table WHERE name LIKE '%' || :searchQuery || '%' ORDER BY important DESC")
    fun getTasks(searchQuery: String): Flow<List<Task>>
    //insert
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)
    //update
    @Update
    suspend fun update(task: Task)
    //delete
    @Delete
    suspend fun delete(task: Task)
}