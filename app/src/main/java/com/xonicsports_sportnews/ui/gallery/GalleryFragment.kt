package com.xonicsports_sportnews.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xonicsports_sportnews.dataClasses.PredictionResult
import com.xonicsports_sportnews.databinding.FragmentGalleryBinding
import com.xonicsports_sportnews.ui.adapters.ResultsAdapter
import com.xonicsports_sportnews.ui.home.HomeViewModel
import com.xonicsports_sportnews.ui.home.HomeViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class GalleryFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private lateinit var freePredictions: List<PredictionResult>
    private var _binding: FragmentGalleryBinding? = null
    private val factory: HomeViewModelFactory by instance()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.getCars(requireContext())
        homeViewModel.data.observe(viewLifecycleOwner, Observer { data ->

            freePredictions = data.predictionResults

            if (freePredictions.isNotEmpty()){
                _binding!!.recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = ResultsAdapter(freePredictions, requireContext())
                }
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}