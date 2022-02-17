package com.example.cupist.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cupist.R
import com.example.cupist.adapter.ProfileInputDataRecyclerViewAdapter
import com.example.cupist.allinterface.ProfileChooseListener
import com.example.cupist.databinding.DialogProfileBinding
import com.example.cupist.profile.ProfileDialogFragmentData.getDeviceSize

class ProfileDialogFragment(
    val type: Int,
    val array: List<String>,
    val chooseListener: ProfileChooseListener
) : DialogFragment(), View.OnClickListener {

    lateinit var binding: DialogProfileBinding
    private var profileInputDataRvAdapter: ProfileInputDataRecyclerViewAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitleTv()
        initViewSetting()
        initRv()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_profile, container, false)
        return binding.root
    }

    private fun initTitleTv() {
        binding.dialogProfileTitleTv.text = when (type) {
            0 -> "키"
            1 -> "체형"
            else -> "학력"
        }
    }

    private fun initRv() {
        binding.dialogProfileRv.layoutManager = LinearLayoutManager(activity?.applicationContext)
        profileInputDataRvAdapter =
            ProfileInputDataRecyclerViewAdapter(type, array, object : ProfileChooseListener {
                override fun onChoose(type: Int, content: String) {
                    dismiss()
                    chooseListener.onChoose(type, content)
                }

            })
        binding.dialogProfileRv.adapter = profileInputDataRvAdapter
    }

    private fun initViewSetting() {
        val deviceSize = getDeviceSize(requireContext())
        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = dialog?.window!!.attributes
        if (type == 2) lp.height = deviceSize.y / 3
        dialog?.window!!.attributes = lp
    }

    override fun onClick(v: View?) {

    }
}