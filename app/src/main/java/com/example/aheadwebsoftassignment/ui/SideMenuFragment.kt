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

            Utils.loadImage(requireContext(), binding.profileIV, it.result.user_photo, R.drawable.ic_launcher_foreground)
            binding.profileTV.text = it.result.title

            val menusList = it.result.menus
            menusList.forEach{ menu ->
                if (menu.label == "Settings" || menu.label == "Privacy"
                    || menu.label == "Terms of Service" || menu.label == "Contact Us"){
                    helpList.add(menu)
                }else{
                    appList.add(menu)
                }
            }
            appAdapter.notifyDataSetChanged()
            helpAdapter.notifyDataSetChanged()
        }
    }

    private fun setUp(){
        featureList.add(Menu(label = "Message", defaultImage = R.drawable.comments))
        featureList.add(Menu(label = "Notifications", defaultImage = R.drawable.notification))

        val featureRV = binding.featureRV
        featureRV.layoutManager = GridLayoutManager(requireContext(), 2)
        featureRV.adapter = ItemAdapter(featureList)

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