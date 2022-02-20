package com.example.cupist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cupist.R
import com.example.cupist.adapter.HomeRecyclerViewAdapter
import com.example.cupist.databinding.FragmentMainBinding
import com.example.cupist.viewmodel.MainViewModel

class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var multiRecyclerviewAdapter: HomeRecyclerViewAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
        initTabLayout()
        initRecyclerView()
        initViewModel()
        subscribeUi()
        mainViewModel.fetchAllData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun initClick() {
        binding.listener = this
    }

    private fun initTabLayout() {
        val tabLayout = binding.mainTabLayout
        val view = layoutInflater.inflate(R.layout.glam_logo, null)
        tabLayout.addTab(tabLayout.newTab().setCustomView(view))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_layout_item_near))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_layout_item_live))
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
    }

    private fun subscribeUi() {
        mainViewModel.homeAllData.observe(viewLifecycleOwner) {
            multiRecyclerviewAdapter.itemList = it
        }
    }

    private fun initRecyclerView() {
        multiRecyclerviewAdapter = HomeRecyclerViewAdapter()

        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = multiRecyclerviewAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE
                        || newState == RecyclerView.SCROLL_STATE_DRAGGING
                    ) {
                        if (recyclerView.canScrollVertically(1).not()) {
                            mainViewModel.fetchMore()
                        }
                    }
                }

            })
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.mainHamburgerIv -> {
                (activity as MainActivity).addChildFragment(ProfileFragment(), "profile")
            }
        }

    }
}