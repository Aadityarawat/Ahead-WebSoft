package com.example.aheadwebsoftassignment.ui

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aheadwebsoftassignment.R
import com.example.aheadwebsoftassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var drawer: LinearLayout
    private lateinit var mainContent: FrameLayout
    private var isDrawerOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, SideMenuFragment())
            .commit()

        drawer = binding.customDrawer
        mainContent = binding.mainContent

        val displayMetrics = resources.displayMetrics
        val drawerWidth = (displayMetrics.widthPixels * 0.8).toInt()

        drawer.layoutParams.width = drawerWidth
        drawer.requestLayout()


        binding.menuButton.setOnClickListener {
            toggleDrawer()
        }

        binding.mainContent.setOnClickListener {
            if (isDrawerOpen) {
                closeDrawer()
            }
        }
    }

    private fun toggleDrawer(){
        if (isDrawerOpen) {
            binding.menuButton.setImageResource(R.drawable.menu)
            closeDrawer()
        } else {
            binding.menuButton.setImageResource(R.drawable.backbutton)
            openDrawer()
        }
    }

    private fun openDrawer() {
        drawer.animate().translationX(0f).setDuration(300).start()

        val scaledOffset = drawer.width * 0.9f // Adjust for scale
        mainContent.animate()
            .scaleX(0.8f)
            .scaleY(0.8f)
            .translationX(scaledOffset)
            .setDuration(300)
            .start()


        isDrawerOpen = true
    }

    private fun closeDrawer() {
        drawer.animate().translationX(-drawer.width.toFloat()).setDuration(300).start()

        mainContent.animate()
            .scaleX(1f)
            .scaleY(1f)
            .translationX(0f)
            .setDuration(300)
            .start()

        isDrawerOpen = false
    }
}