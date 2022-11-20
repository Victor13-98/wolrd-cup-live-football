package com.xonicsports_sportnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xonicsports_sportnews.dataClasses.FreePrediction
import com.xonicsports_sportnews.dataClasses.Plan
import com.xonicsports_sportnews.databinding.FragmentHomeBinding
import com.xonicsports_sportnews.ui.adapters.PackageAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class HomeFragment : Fragment(), KodeinAware, PackageAdapter.onClickItemListener {
    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataList: List<Plan>
    private lateinit var freePredictions: List<FreePrediction>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        homeViewModel.getCars(requireContext())
        homeViewModel.data.observe(viewLifecycleOwner, Observer { data ->
            dataList = data.plans
            freePredictions = data.freePredictions
            if (dataList.isNotEmpty()) {
                _binding!!.recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = PackageAdapter(dataList, requireContext(), this)
                }
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickItem(position: Int) {


       if (position == 0){

           val bundle = bundleOf("list" to freePredictions)
           val freeOddsDialogFragment = FreeOddsDialogFragment()
           freeOddsDialogFragment.arguments = bundle
           freeOddsDialogFragment.show(childFragmentManager, "FreeOddsDialogFragment")
       }else {
           val bundle = bundleOf("plans" to dataList)
           bundle.putInt("Position", position)
           val subscribeDialogFragment = SubscribeDialogFragment()
           subscribeDialogFragment.arguments = bundle
           subscribeDialogFragment.show(childFragmentManager, "SubscribeDialogFragment")
       }


    }


}