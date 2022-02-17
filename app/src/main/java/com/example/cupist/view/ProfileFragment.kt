package com.example.cupist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cupist.R
import com.example.cupist.allinterface.ProfileChooseListener
import com.example.cupist.databinding.FragmentProfileBinding
import com.example.cupist.dialog.ProfileDialogFragment
import com.example.cupist.profile.ProfileDialogFragmentData
import com.example.cupist.profile.ProfileDialogFragmentData.makeArrayData
import com.example.cupist.viewmodel.MainViewModel

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
        initViewModel()
        subscribeUi()
        profileViewModel.fetchProfileData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun initClick() {
        binding.profile = this
    }

    private fun initViewModel() {
        profileViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.profileViewModel = profileViewModel
    }

    private fun subscribeUi() {
        profileViewModel.profile.observe(viewLifecycleOwner) {
            val data = it.data
            binding.profileData = data
            setImageView(data?.pictures)
            if (data?.school.isNullOrEmpty()) {
                binding.profilePersonSchoolShowTv.visibility = View.INVISIBLE
                binding.profilePersonSchoolTv.visibility = View.INVISIBLE
            }
        }
    }

    private fun setImageView(data: List<String>?) {
        Glide.with(this)
            .load("https://test.dev.cupist.de${data?.get(0)}")
            .into(binding.profilePhotoTv)
    }

    private fun showDialog(type: Int) {
        val dialog = ProfileDialogFragment(
            type,
            makeArrayData(type, profileViewModel.profile.value?.meta),
            object : ProfileChooseListener {
                override fun onChoose(type: Int, data: String) {
                    setChooseData(type, data)
                }
            })
        dialog.setTargetFragment(this, 0)
        fragmentManager?.let { dialog.show(it, "glam") }
    }

    private fun setChooseData(type: Int, data: String) {
        when (type) {
            0 -> {
                binding.profilePersonTallChooseTv.text = data
                binding.profilePersonTallChooseTv.setTextColor(resources.getColor(R.color.glam_blue))
            }
            1 -> {
                binding.profilePersonBodyChooseTv.text = data
                binding.profilePersonBodyChooseTv.setTextColor(resources.getColor(R.color.glam_blue))
            }
            else -> {
                binding.profilePersonEducationChooseTv.text = data
                binding.profilePersonEducationChooseTv.setTextColor(resources.getColor(R.color.glam_blue))
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.profileBackIv -> {
                (activity as MainActivity).replaceFragment(this)
            }
            binding.profilePersonTallChooseTv -> {
                showDialog(0)
            }
            binding.profilePersonBodyChooseTv -> {
                showDialog(1)
            }
            binding.profilePersonEducationChooseTv -> {
                showDialog(2)
            }
        }
    }

}