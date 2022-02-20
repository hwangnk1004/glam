package com.example.cupist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.cupist.ApplicationClass
import com.example.cupist.data.HomeItemType
import com.example.cupist.data.IntroductionDataUiModel
import com.example.cupist.databinding.PersonItemBinding
import com.example.cupist.databinding.TargetRecommendItemBinding

class HomeRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemList: List<IntroductionDataUiModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].itemType.typeCode
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when (HomeItemType.of(viewType)) {
        HomeItemType.TODAY,
        HomeItemType.TARGET_ITEM -> {
            TodayViewHolder(
                PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        HomeItemType.TARGET_LAYOUT -> {
            TargetViewHolder(
                TargetRecommendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        HomeItemType.ADDITIONAL -> {
            AddViewHolder(
                PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is TodayViewHolder -> {
                holder.onBind(itemList[position], Glide.with(ApplicationClass.context), position)
            }
            is TargetViewHolder -> {
                holder.onBind(itemList[position])
            }
            is AddViewHolder -> {
                holder.onBind(itemList[position], Glide.with(ApplicationClass.context), position)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class TodayViewHolder(var binding: PersonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: IntroductionDataUiModel, requestManager: RequestManager, position: Int) {
            binding.apply {
                confirmNegativeLayout.setOnClickListener {
                    item.onClick(item)
                }
                confirmPositiveLayout.setOnClickListener {
                    item.onClick(item)
                }
                todayRecommendLayout.isVisible = item.todayRecommend
                introduceLayout.isGone = item.introduction.isEmpty()
                personInfoLayout.isGone = item.introduction.isNotEmpty()
                requestManager.load("https://test.dev.cupist.de${item.pictures[0]}")
                    .transform(RoundedCorners(50))
                    .into(personImageIv)
                personNameTv.text = "${item.name}, ${item.age}"
                introduceTv.text = item.introduction
                personInfoTv.text = "${item.job} ${item.distance?.div(1000)}km"
                personTallTv.text = "${item.height}cm"
            }
        }
    }

    class TargetViewHolder(var binding: TargetRecommendItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: IntroductionDataUiModel) {
            binding.apply {
                glamRecommendButtonLayout.setOnClickListener { item.onClick(item) }
                superAttractiveButtonLayout.setOnClickListener { item.onClick(item) }
                bodyButtonLayout.setOnClickListener { item.onClick(item) }
                petButtonLayout.setOnClickListener { item.onClick(item) }
            }
        }
    }

    class AddViewHolder(var binding: PersonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: IntroductionDataUiModel, requestManager: RequestManager, position: Int) {
            binding.apply {
                confirmNegativeLayout.setOnClickListener {
                    item.onClick(item)
                }
                confirmPositiveLayout.setOnClickListener {
                    item.onClick(item)
                }
                todayRecommendLayout.isVisible = item.todayRecommend
                introduceLayout.isGone = item.introduction.isEmpty()
                personInfoLayout.isGone = item.introduction.isNotEmpty()

                requestManager.load("https://test.dev.cupist.de${item.pictures[0]}")
                    .transform(RoundedCorners(50))
                    .into(personImageIv)
                personNameTv.text = "${item.name}, ${item.age}"
                introduceTv.text = item.introduction
                personInfoTv.text = "${item.job} ${item.distance?.div(1000)}km"
                personTallTv.text = "${item.height}cm"
            }
        }
    }
}