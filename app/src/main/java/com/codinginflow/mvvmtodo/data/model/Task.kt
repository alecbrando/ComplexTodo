package com.codinginflow.mvvmtodo.data.model

import androidx.room.Entity
import android.os.Parcelable
import androidx.room.PrimaryKey
import java.text.DateFormat
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="task_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val important: Boolean = false,
    val completed: Boolean = false,
    val created: Long = System.currentTimeMillis()
) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}