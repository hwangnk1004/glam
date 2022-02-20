package com.example.cupist.view

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cupist.R
import com.example.cupist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainBnv.itemIconTintList = null
        initMainFragment()
    }

    private fun initMainFragment() {
        supportFragmentManager.beginTransaction().add(R.id.main_frame_layout, MainFragment())
            .commit()
    }

    fun addFragment(fragment: Fragment, fragmentData: Fragment) {
        fragment.childFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, fragmentData)
            .addToBackStack("${fragmentData.tag}")
            .commit()
    }

    fun replaceFragment(fragment: Fragment) {
        fragment.parentFragmentManager.popBackStack()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}