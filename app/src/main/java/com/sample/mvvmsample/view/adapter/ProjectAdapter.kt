package com.sample.mvvmsample.view.adapter

import android.view.ViewGroup
import com.sample.mvvmsample.view.callback.ProjectClickCallback
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.mvvmsample.service.model.Project
import com.sample.mvvmsample.R
import com.sample.mvvmsample.databinding.ProjectListItemBinding

class ProjectAdapter(private val projectList: MutableList<Project>, private val projectClickCallback: ProjectClickCallback): RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = DataBindingUtil.inflate<ProjectListItemBinding>(LayoutInflater.from(parent.context), R.layout.project_list_item,
                        parent, false)

        binding.callback=projectClickCallback

        return ProjectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.project= projectList[position]
        holder.binding.executePendingBindings()
    }

    class ProjectViewHolder(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)
}