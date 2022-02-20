package com.example.cupist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.cupist.databinding.ProfilePhotoItemBinding

class ProfilePhotoRecyclerViewAdapter(
    private val requestManager: RequestManager
) :
    RecyclerView.Adapter<ProfilePhotoRecyclerViewAdapter.PhotoViewHolder>() {

    private lateinit var list: List<String?>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
        val binding =
            ProfilePhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PhotoViewHolder,
        position: Int
    ) {
        holder.onBind(list[position], requestManager)
    }

    fun setItems(data: List<String?>) {
        list = data
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class PhotoViewHolder(var binding: ProfilePhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String?, requestManager: RequestManager) {
            binding.apply {
                if (!item.isNullOrEmpty()) {
                    requestManager.load("https://test.dev.cupist.de${item}")
                        .into(binding.profilePhotoTv)
                }
            }
        }
    }


}
