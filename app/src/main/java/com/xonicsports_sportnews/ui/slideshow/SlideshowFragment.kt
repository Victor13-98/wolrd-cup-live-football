package com.xonicsports_sportnews.ui.slideshow

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.xonicsports_sportnews.R
import com.xonicsports_sportnews.dataClasses.User
import com.xonicsports_sportnews.databinding.FragmentSlideshowBinding
import com.xonicsports_sportnews.ui.home.HomeViewModel
import com.xonicsports_sportnews.ui.home.HomeViewModelFactory
import com.xonicsports_sportnews.utils.SharedPreferenceUtils
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SlideshowFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()
    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!
    private lateinit var users : List<User>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        homeViewModel.getCars(requireContext())
        homeViewModel.data.observe(viewLifecycleOwner, Observer { data ->

            users = data.users

//            if (data.paidPredictions.isNotEmpty()){
//                binding.layoutPhone.visibility = View.VISIBLE
//            }

        })

        binding.buttonLogIn.setOnClickListener {
           confirmCode()
        }


        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun confirmCode() {
        binding.phoneTextInputLayout.error = null
        val phonePresent = false
        val phoneNumber = binding.textViewPhone.text.trim().toString()
        if (!PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
            binding.phoneTextInputLayout.error = "Valid Phone Number Required"
            return
        }
        for (user in users) {
           if (phoneNumber == user.phone){
               val sharedPreferenceUtils = SharedPreferenceUtils(requireContext())
               sharedPreferenceUtils.savePhoneNumber(user.phone)
               sharedPreferenceUtils.savePlan(user.plan)
               sharedPreferenceUtils.saveStartDay(user.startDate)
               sharedPreferenceUtils.saveEndDay(user.endDate)
               findNavController().navigate(R.id.accountFragmentFragment)
           }
        }

    }
}