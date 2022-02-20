package com.example.cupist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.cupist.ApplicationClass
import com.example.cupist.allinterface.TargetRecommendClickListener
import com.example.cupist.data.Item
import com.example.cupist.data.ViewType.Companion.ADD_RECOMMEND
import com.example.cupist.data.ViewType.Companion.TARGET_RECOMMEND
import com.example.cupist.data.ViewType.Companion.TODAY_RECOMMEND
import com.example.cupist.databinding.PersonItemBinding
import com.example.cupist.databinding.TargetRecommendItemBinding

class MainMultiRecyclerViewAdapter(
    private val itemList: MutableList<Item>,
    private val targetClickListener: TargetRecommendClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var item = itemList

    override fun getItemViewType(position: Int): Int {
        return item[position].type
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when (viewType) {
        TODAY_RECOMMEND -> {
            TodayViewHolder(
                PersonItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), TODAY_RECOMMEND
            )
        }
        TARGET_RECOMMEND -> {
            TargetViewHolder(
                TargetRecommendItemBinding.inflate(
                    LayoutInflater.from(
                        parent
                            .context
                    ), parent, false
                ), TARGET_RECOMMEND
            )
        }
        else -> {
            AddViewHolder(
                PersonItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), ADD_RECOMMEND
            )
        }

    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is TodayViewHolder -> {
                holder.onBind(item[position], Glide.with(ApplicationClass.context), position)
            }
            is TargetViewHolder -> {
                holder.onBind(
                    item[position], Glide.with(ApplicationClass.context), position,
                    targetClickListener
                )
            }
            is AddViewHolder -> {
                holder.onBind(item[position], Glide.with(ApplicationClass.context), position)
            }
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setItems(data: MutableList<Item>) {
        item = data
    }

    class TodayViewHolder(var binding: PersonItemBinding, viewType: Int?) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Item, requestManager: RequestManager, position: Int) {
            binding.apply {
                confirmNegativeLayout.setOnClickListener {

                }
                confirmNegativeTextView.setOnClickListener {

                }
                confirmPositiveLayout.setOnClickListener {

                }
                confirmPositiveTextView.setOnClickListener {

                }
                introduceLayout.isGone = item.data?.introduction.isNullOrEmpty()
                personInfoLayout.isGone = !item.data?.introduction.isNullOrEmpty()
                requestManager.load("https://test.dev.cupist.de${item.data?.pictures!![0]}")
                    .transform(RoundedCorners(50))
                    .into(personImageIv)
                personNameTv.text = "${item.data!!.name}, ${item.data!!.age}"
                introduceTv.text = item.data!!.introduction
                personInfoTv.text = "${item.data!!.job} ${item.data!!.distance?.div(1000)}km"
                personTallTv.text = "${item.data!!.height}cm"
            }
        }

        fun deleteCard() {

        }
    }

    class TargetViewHolder(var binding: TargetRecommendItemBinding, viewType: Int?) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            item: Item,
            requestManager: RequestManager,
            position: Int,
            listener: TargetRecommendClickListener
        ) {
            binding.apply {
                glamRecommendButtonLayout.setOnClickListener { listener.targetRecommendClick() }
                superAttractiveButtonLayout.setOnClickListener { listener.targetRecommendClick() }
                bodyButtonLayout.setOnClickListener { listener.targetRecommendClick() }
                petButtonLayout.setOnClickListener { listener.targetRecommendClick() }
            }
        }
    }

    class AddViewHolder(var binding: PersonItemBinding, viewType: Int?) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Item, requestManager: RequestManager, position: Int) {
            binding.apply {
                todayRecommendLayout.visibility = View.GONE
                introduceLayout.isGone = item.data?.introduction.isNullOrEmpty()
                personInfoLayout.isGone = !item.data?.introduction.isNullOrEmpty()

                requestManager.load("https://test.dev.cupist.de${item.data?.pictures!![0]}")
                    .transform(RoundedCorners(50))
                    .into(personImageIv)
                personNameTv.text = "${item.data!!.name}, ${item.data!!.age}"
                introduceTv.text = item.data!!.introduction
                personInfoTv.text = "${item.data!!.job} ${item.data!!.distance?.div(1000)}km"
                personTallTv.text = "${item.data!!.height}cm"
            }
        }
    }
}