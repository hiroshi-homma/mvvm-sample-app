package com.sample.mvvmsample.view.ui

import com.sample.mvvmsample.view.adapter.ProjectAdapter
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.sample.mvvmsample.view.callback.ProjectClickCallback
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sample.mvvmsample.databinding.FragmentProjectListBinding
import com.sample.mvvmsample.R
import com.sample.mvvmsample.service.model.Project
import com.sample.mvvmsample.viewmodel.ProjectListViewModel

class ProjectListFragment: Fragment() {
    private var projectAdapter: ProjectAdapter? = null
    private var binding: FragmentProjectListBinding? = null
    private var projectList= mutableListOf<Project>()
    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)

        projectAdapter = ProjectAdapter(projectList, projectClickCallback)
        binding?.projectList?.adapter = projectAdapter
        binding?.isLoading = true

        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        // Update the list when the data changes
        viewModel.getProjectListObservable().observe(this, Observer<List<Project>> { projects ->
            if (projects != null) {
                binding?.isLoading=false
                projectList.clear()
                projectList.addAll(projects.toMutableList())
                projectAdapter?.notifyDataSetChanged()
            }
        })
    }

    private val projectClickCallback = object : ProjectClickCallback {
        override fun onClick(view: View, project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(project)
            }
        }
    }
    companion object {
        val TAG = "ProjectListFragment"
    }
}