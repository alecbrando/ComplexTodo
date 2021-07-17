package com.codinginflow.mvvmtodo.data

import androidx.room.Entity
import android.os.Parcelable
import androidx.room.PrimaryKey
import java.text.DateFormat
import kotlinx.parcelize.Parcelize

@Entity(tableName="task_table")
@Parcelize
data class Task (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val important: Boolean = false,
    val completed: Boolean = false,
    val created: Long = System.currentTimeMillis()
) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}