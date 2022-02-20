package com.example.cupist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.cupist.adapter.ProfilePhotoRecyclerViewAdapter
import com.example.cupist.allinterface.ProfileChooseListener
import com.example.cupist.databinding.FragmentProfileBinding
import com.example.cupist.dialog.ProfileDialogFragment
import com.example.cupist.profile.ProfileDialogFragmentData.makeArrayData
import com.example.cupist.profile.ProfileDialogFragmentData.makePhotoData
import com.example.cupist.util.Preference
import com.example.cupist.viewmodel.MainViewModel


class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
        initViewModel()
        setProfileData()
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

    private fun setProfileData() {
        Preference.initPreference(context)
        if (!Preference.getInstance().introduce.isNullOrEmpty()) {
            binding.profilePersonIntroduceEv.setText("${Preference.getInstance().introduce}")
        }
        if (!Preference.getInstance().job.isNullOrEmpty()) {
            binding.profilePersonJobAreaEv.setText("${Preference.getInstance().job}")
        }
        binding.profilePersonTallChooseTv.text = Preference.getInstance().height
        binding.profilePersonBodyChooseTv.text = Preference.getInstance().bodyType
        binding.profilePersonEducationChooseTv.text = Preference.getInstance().education
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
                binding.profilePersonSchoolShowTv.visibility = View.GONE
                binding.profilePersonSchoolTv.visibility = View.GONE
            }
        }
        profileViewModel.introduceText.observe(viewLifecycleOwner) {
            Preference.getInstance().introduce = it
        }

        profileViewModel.jobText.observe(viewLifecycleOwner) {
            Preference.getInstance().job = it
        }
    }

    private fun setImageView(data: List<String>?) {
        val recyclerView = binding.profilePhotoRecyclerView
        recyclerView.layoutManager =
            GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        val photoRecyclerViewAdapter = ProfilePhotoRecyclerViewAdapter(Glide.with(this))
        recyclerView.adapter = photoRecyclerViewAdapter
        photoRecyclerViewAdapter.setItems(makePhotoData(data))
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
                Preference.getInstance().height = data
            }
            1 -> {
                binding.profilePersonBodyChooseTv.text = data
                Preference.getInstance().bodyType = data
            }
            else -> {
                binding.profilePersonEducationChooseTv.text = data
                Preference.getInstance().education = data
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