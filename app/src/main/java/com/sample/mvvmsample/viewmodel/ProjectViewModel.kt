package com.sample.mvvmsample.viewmodel

import android.app.Application
import com.sample.mvvmsample.service.model.Project
import com.sample.mvvmsample.service.repository.ProjectRepository
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProjectViewModel(application: Application, projectID: String): AndroidViewModel(application) {
    private val projectObservable: LiveData<Project> = ProjectRepository.getInstance().getProjectDetails("hiroshi-homma", projectID)
    var project: ObservableField<Project> = ObservableField()
    fun getObservableProject(): LiveData<Project> {
        return projectObservable
    }

    fun setProject(project: Project) {
        this.project.set(project)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val application: Application, private val projectID: String) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ProjectViewModel(application, projectID) as T
        }
    }
}