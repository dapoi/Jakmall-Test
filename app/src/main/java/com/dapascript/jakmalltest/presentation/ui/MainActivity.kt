package com.dapascript.jakmalltest.presentation.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapascript.jakmalltest.data.repository.model.ChildItem
import com.dapascript.jakmalltest.databinding.ActivityMainBinding
import com.dapascript.jakmalltest.presentation.adapter.ParentAdapter
import com.dapascript.jakmalltest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels()
    private val parentAdapter = ParentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapter()
        initViewModel()

        binding.swipeRefresh.apply {
            setOnRefreshListener {
                Handler(Looper.getMainLooper()).postDelayed({
                    initAdapter()
                    initViewModel()
                    isRefreshing = false
                }, 1000)
            }
        }
    }

    private fun initAdapter() {
        binding.rvParent.apply {
            adapter = parentAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        parentAdapter.setOnItemClickListener(object : ParentAdapter.OnItemClickListener {
            override fun onItemClicked(data: ChildItem) {
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                alertDialog.setMessage(data.childTitle)
                alertDialog.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                alertDialog.show()
            }
        })
    }

    private fun initViewModel() {
        mainViewModel.getParentItem().observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    stateLoading(true)
                }
                is Resource.Success -> {
                    stateLoading(false)
                    parentAdapter.parentList(resource.data!!)
                }
                is Resource.Error -> {
                    stateLoading(false)
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        mainViewModel.getChildItem().observe(this) {
            parentAdapter.childList(it)
        }
    }

    private fun stateLoading(state: Boolean) {
        binding.apply {
            if (state) {
                progressBar.isVisible = true
                rvParent.isVisible = false
            } else {
                progressBar.isVisible = false
                rvParent.isVisible = true
            }
        }
    }
}