package com.example.aheadwebsoftassignment.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aheadwebsoftassignment.R
import com.example.aheadwebsoftassignment.data.Menu
import com.example.aheadwebsoftassignment.databinding.FragmentSideMenuBinding
import com.example.aheadwebsoftassignment.others.Utils
import com.example.aheadwebsoftassignment.viewModel.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SideMenuFragment : Fragment() {
    private val binding by lazy { FragmentSideMenuBinding.inflate(layoutInflater) }
    private val featureList = ArrayList<Menu>()
    private val appList = ArrayList<Menu>()
    private val helpList = ArrayList<Menu>()
    private lateinit var viewModel : ViewModel
    private lateinit var featureAdapter : ItemAdapter
    private lateinit var helpAdapter : ItemAdapter
    private lateinit var appAdapter : ItemAdapter
    var flag = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        viewModel.getItems()
        setUp()
        observer()
        return binding.root
    }

    private fun observer(){
        viewModel.itemLiveData.observe(viewLifecycleOwner) {
            helpList.clear()
            appList.clear()
            featureList.clear()

            Utils.loadCircularImage(requireContext(), binding.profileIV, it.result.user_photo, R.drawable.signal)
            binding.profileTV.text = it.result.title

            val menusList = it.result.menus
            menusList.forEach{ menu ->
                when (menu.label) {
                    "Messages", "Notifications" -> {
                        featureList.add(menu)
                    }
                    "Settings", "Privacy", "Terms of Service", "Contact Us" -> {
                        helpList.add(menu)
                    }
                    "Rate Us" -> {
                        Utils.loadImage(requireContext(), binding.rateIV, menu.icon, R.drawable.signal)

                    }
                    else -> {
                        appList.add(menu)
                    }
                }

                if(menu.label == "FAVOURITES"){
                    Log.d("FAVOURITES","${menu.icon}")
                }
            }
            appAdapter.notifyDataSetChanged()
            helpAdapter.notifyDataSetChanged()
            featureAdapter.notifyDataSetChanged()
        }
    }

    private fun setUp(){
        /*featureList.add(Menu(label = "Message", defaultImage = R.drawable.comments))
        featureList.add(Menu(label = "Notifications", defaultImage = R.drawable.notification))*/

        val featureRV = binding.featureRV
        featureRV.layoutManager = GridLayoutManager(requireContext(), 2)
        featureAdapter = ItemAdapter(featureList)
        featureRV.adapter = featureAdapter

        val appRV = binding.appRV
        appRV.layoutManager = GridLayoutManager(requireContext(), 2)
        appAdapter = ItemAdapter(appList)
        appRV.adapter = appAdapter

        val helpRV = binding.helpRV
        helpRV.layoutManager = GridLayoutManager(requireContext(), 2)
        helpAdapter = ItemAdapter(helpList)
        helpRV.adapter = helpAdapter

        binding.seeMoreFL.setOnClickListener {
            flag = !flag

            if (flag){
                binding.seeMoreTV.text = "See Less"
            }
            else binding.seeMoreTV.text = "See More"
            appAdapter.seeMoreClick()
        }
    }

}