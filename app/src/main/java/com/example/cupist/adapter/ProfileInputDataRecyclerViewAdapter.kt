package com.example.cupist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.cupist.ApplicationClass
import com.example.cupist.R
import com.example.cupist.allinterface.ProfileChooseListener
import com.example.cupist.databinding.DailogProfileItemBinding

class ProfileInputDataRecyclerViewAdapter(
    private val type: Int,
    private val array: List<String>,
    private val chooseListener: ProfileChooseListener
) :
    RecyclerView.Adapter<ProfileInputDataRecyclerViewAdapter.ProfileInputDataViewHolder>() {

    private val list: List<String> = array
    private val clickListener: ProfileChooseListener = chooseListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileInputDataViewHolder {

        val binding =
            DailogProfileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ProfileInputDataViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProfileInputDataViewHolder,
        position: Int
    ) {
        holder.onBind(type, list[position], clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ProfileInputDataViewHolder(var binding: DailogProfileItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(type: Int, item: String, chooseListener: ProfileChooseListener) {
            binding.apply {
                dialogProfileItemTv.text = item
                dialogProfileItemTv.setOnClickListener {
                    dialogProfileItemTv.setTextColor(
                        getColor(
                            ApplicationClass.context,
                            R.color.glam_blue
                        )
                    )

                    chooseListener.onChoose(type, item)
                }
            }
        }
    }


}