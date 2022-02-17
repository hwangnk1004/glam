package com.example.cupist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cupist.adapter.MainMultiRecyclerViewAdapter
import com.example.cupist.adapter.TodayRecommendRecyclerViewAdapter
import com.example.cupist.data.IntroductionData
import com.example.cupist.data.ViewType
import com.example.cupist.databinding.FragmentMainBinding
import com.example.cupist.viewmodel.MainViewModel

class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
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

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
    }

    private fun subscribeUi() {
        mainViewModel.todayRecommend.observe(viewLifecycleOwner) {
            drawTodayRecyclerview(it.data)
        }

        mainViewModel.addRecommend.observe(viewLifecycleOwner) {

        }

        mainViewModel.targetRecommend.observe(viewLifecycleOwner) {

        }

        mainViewModel.profile.observe(viewLifecycleOwner) {

        }
    }

    private fun drawTodayRecyclerview(data: List<IntroductionData>?) {
        if (data.isNullOrEmpty()) return
        val recyclerView = binding.todayRecommendRv
//        val multiRecyclerviewAdapter = MainMultiRecyclerViewAdapter()
//        recyclerView.adapter = multiRecyclerviewAdapter
//        multiRecyclerviewAdapter.addItem(data, ViewType.TODAY_RECOMMEND)


        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        val todayRecommendAdapter = TodayRecommendRecyclerViewAdapter(Glide.with(this))
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = todayRecommendAdapter
        todayRecommendAdapter.setItems(data)
    }

    private fun converterArrayList(data: List<IntroductionData>?): ArrayList<IntroductionData> {
        val resultData = ArrayList<IntroductionData>()
        data?.forEachIndexed { _, introductionData ->
            resultData.add(introductionData)
        }
        return resultData
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.mainHamburgerIv -> {
                (activity as MainActivity).addFragment(this, ProfileFragment())
            }
        }

    }


}