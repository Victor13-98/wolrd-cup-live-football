package com.xonicsports_sportnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xonicsports_sportnews.dataClasses.PaidPrediction
import com.xonicsports_sportnews.databinding.FragmentAccountFragmentBinding
import com.xonicsports_sportnews.ui.adapters.PaidPredictionsAdapter
import com.xonicsports_sportnews.utils.SharedPreferenceUtils
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class AccountFragmentFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private var binding: FragmentAccountFragmentBinding? = null
    private val factory: HomeViewModelFactory by instance()
    private lateinit var paidPrediction: List<PaidPrediction>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountFragmentBinding.inflate(inflater,  container, false)

        val homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        homeViewModel.getCars(requireContext())
        homeViewModel.data.observe(viewLifecycleOwner, Observer { data ->
            paidPrediction = data.paidPredictions
            val sharedPreferenceUtils = SharedPreferenceUtils(requireContext())
            binding!!.phone.text = sharedPreferenceUtils.getPhoneNumber()
            binding!!.plan.text = sharedPreferenceUtils.getPlan()
            binding!!.startDate.text = sharedPreferenceUtils.getStartDay()
            binding!!.endDate.text = sharedPreferenceUtils.getEndDay()

            if (paidPrediction.isNotEmpty()){
                binding!!.recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = PaidPredictionsAdapter(paidPrediction, requireContext())
                }
            }
        })
        return binding!!.root
    }

}