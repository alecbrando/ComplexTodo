package com.codinginflow.mvvmtodo.data.database

import androidx.room.*
import com.codinginflow.mvvmtodo.data.model.Task
import com.codinginflow.mvvmtodo.ui.viewmodel.SortOrder
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    fun getTasks(query: String, sortOrder: SortOrder, hideCompleted: Boolean) : Flow<List<Task>> =
        when(sortOrder){
            SortOrder.BY_DATE -> getTasksSortedByDateCreated(query, hideCompleted)
            SortOrder.BY_NAME -> getTasksSortedByName(query, hideCompleted)
        }

    @Query("SELECT * FROM task_table WHERE (completed != :hideCompleted OR completed = 0) AND name LIKE '%' || :searchQuery || '%' ORDER BY important DESC, name")
    fun getTasksSortedByName(searchQuery: String, hideCompleted: Boolean): Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE (completed != :hideCompleted OR completed = 0) AND name LIKE '%' || :searchQuery || '%' ORDER BY important DESC, created")
    fun getTasksSortedByDateCreated(searchQuery: String, hideCompleted: Boolean): Flow<List<Task>>
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