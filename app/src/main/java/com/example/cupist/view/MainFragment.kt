package com.example.cupist.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cupist.R
import com.example.cupist.adapter.MainMultiRecyclerViewAdapter
import com.example.cupist.allinterface.TargetRecommendClickListener
import com.example.cupist.data.Item
import com.example.cupist.data.ViewType.Companion.ADD_RECOMMEND
import com.example.cupist.data.ViewType.Companion.TARGET_RECOMMEND
import com.example.cupist.data.ViewType.Companion.TODAY_RECOMMEND
import com.example.cupist.databinding.FragmentMainBinding
import com.example.cupist.viewmodel.MainViewModel

class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var itemList = arrayListOf<Item>()
    private lateinit var multiRecyclerviewAdapter: MainMultiRecyclerViewAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
        initTabLayout()
        initViewModel()
        subscribeUi()
        mainViewModel.fetchAllData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (childFragmentManager.fragments.size > 0) {
                        if (childFragmentManager.fragments.size == 1) {
                            isEnabled = false
                        }
                        childFragmentManager.popBackStack()
                    }
                }

            })

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
        mainViewModel.todayRecommend.observe(viewLifecycleOwner) {
            it.data?.forEachIndexed { index, todayData ->
                itemList.add(Item(TODAY_RECOMMEND, todayData))
            }
            itemList.add(Item(TARGET_RECOMMEND, null))

        }

        mainViewModel.addRecommend.observe(viewLifecycleOwner) {
            it.data?.forEachIndexed { index, todayData ->
                itemList.add(Item(ADD_RECOMMEND, todayData))
            }
            initRecyclerView()
        }

        mainViewModel.targetRecommend.observe(viewLifecycleOwner) {
            val itemDataList = arrayListOf<Item>()
            it.data?.forEachIndexed { index, todayData ->
                itemDataList.add(Item(ADD_RECOMMEND, todayData))
            }
            itemList.forEachIndexed { index, item ->
                itemDataList.add(item)
            }
            itemList = itemDataList
            updateRecyclerView()
        }
    }

    private fun initRecyclerView() {
        val recyclerView = binding.todayRecommendRv
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        multiRecyclerviewAdapter = MainMultiRecyclerViewAdapter(itemList, object :
            TargetRecommendClickListener {
            override fun targetRecommendClick() {
                mainViewModel.fetchTargetData()
            }

        })
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = multiRecyclerviewAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRecyclerView() {
        multiRecyclerviewAdapter.setItems(itemList)
        multiRecyclerviewAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.mainHamburgerIv -> {
                (activity as MainActivity).addFragment(this, ProfileFragment())
            }
        }

    }
}