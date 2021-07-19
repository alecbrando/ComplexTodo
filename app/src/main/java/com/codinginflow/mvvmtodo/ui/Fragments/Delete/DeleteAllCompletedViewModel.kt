package com.codinginflow.mvvmtodo.ui.Fragments.Delete

import androidx.lifecycle.ViewModel
import com.codinginflow.mvvmtodo.data.database.TaskDao
import com.codinginflow.mvvmtodo.data.di.ApplicationScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAllCompletedViewModel @Inject constructor(
    private val taskDao: TaskDao,
    @ApplicationScope private val applicationScope: CoroutineScope
) : ViewModel() {

    fun onConfirmClick() = applicationScope.launch {
        taskDao.deleteCompletetedTasks()
    }
}