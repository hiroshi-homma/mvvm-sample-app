package com.sample.mvvmsample.view.callback

import android.view.View
import com.sample.mvvmsample.service.model.Project

interface ProjectClickCallback {
    fun onClick(view: View, project: Project)
}