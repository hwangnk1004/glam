package com.example.cupist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.cupist.data.IntroductionData
import com.example.cupist.data.ViewType
import com.example.cupist.databinding.PersonItemBinding
import com.example.cupist.databinding.TargetRecommendItemBinding

class MainMultiRecyclerViewAdapter() :
    RecyclerView.Adapter<MainMultiRecyclerViewAdapter.MultiViewHolder>() {

    private lateinit var list: List<IntroductionData>
    private var viewTypeData: Int? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MultiViewHolder = when (viewType) {
        viewTypeData -> {
            MultiViewHolder(binding<PersonItemBinding>(parent, ViewType.TODAY_RECOMMEND))
        }
        viewTypeData -> {
            MultiViewHolder(binding<TargetRecommendItemBinding>(parent, ViewType.TARGET_RECOMMEND))
        }
        else -> {
            MultiViewHolder(binding<PersonItemBinding>(parent, ViewType.ADD_RECOMMEND))
        }

    }

    override fun onBindViewHolder(
        holder: MultiViewHolder,
        position: Int
    ) {
        when (val binding = holder.binding) {
            is PersonItemBinding -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItem(data: List<IntroductionData>, viewType: Int) {
        list = data
        viewTypeData = viewType
    }

    class MultiViewHolder(var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: IntroductionData, requestManager: RequestManager) {
            binding.apply {

            }
        }
    }

    private fun <T : ViewDataBinding> binding(parent: ViewGroup, viewType: Int) =
        DataBindingUtil.inflate<T>(LayoutInflater.from(parent.context), viewType, parent, false)
}