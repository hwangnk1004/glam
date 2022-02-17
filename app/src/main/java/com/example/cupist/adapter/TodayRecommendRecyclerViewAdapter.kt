package com.example.cupist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.cupist.data.IntroductionData
import com.example.cupist.databinding.PersonItemBinding

class TodayRecommendRecyclerViewAdapter(
    private val requestManager: RequestManager
) : RecyclerView.Adapter<TodayRecommendRecyclerViewAdapter.TodayRecommendViewHolder>() {

    private lateinit var list: List<IntroductionData>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodayRecommendViewHolder {
        val binding =
            PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TodayRecommendViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TodayRecommendViewHolder,
        position: Int
    ) {
        holder.onBind(list[position], requestManager)
    }

    fun setItems(data: List<IntroductionData>) {
        list = data
    }

    fun getListData(): List<IntroductionData> {
        return list
    }

    fun addItem() {
        list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class TodayRecommendViewHolder(var binding: PersonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: IntroductionData, requestManager: RequestManager) {
            binding.apply {
                personItem = item
                introduceLayout.isGone = item.introduction.isNullOrEmpty()
                personInfoLayout.isGone = !item.introduction.isNullOrEmpty()

//                setImage(item, requestManager)
                requestManager.load("https://test.dev.cupist.de${item.pictures!![0]}")
                    .transform(RoundedCorners(50))
                    .into(personImageIv)
                personNameTv.text = "${item.name}, ${item.age}"
                introduceTv.text = item.introduction
                personInfoTv.text = "${item.job} ${item.distance?.div(1000)}km"
                personTallTv.text = "${item.height}cm"
            }
        }

        private fun setImage(item: IntroductionData, requestManager: RequestManager) {
            requestManager.load("https://test.dev.cupist.de${item.pictures!![0]}")
        }
    }


}
