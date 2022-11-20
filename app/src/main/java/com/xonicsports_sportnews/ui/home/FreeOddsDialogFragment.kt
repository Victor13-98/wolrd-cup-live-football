package com.xonicsports_sportnews.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xonicsports_sportnews.R
import com.xonicsports_sportnews.dataClasses.FreePrediction
import com.xonicsports_sportnews.databinding.FragmentFreeOddsDialogBinding
import com.xonicsports_sportnews.ui.adapters.FreePredictionsAdapter


class FreeOddsDialogFragment : DialogFragment() {
    private lateinit var binding : FragmentFreeOddsDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFreeOddsDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val list = arguments?.getParcelableArrayList<FreePrediction>("list") as ArrayList<FreePrediction>
//        binding.mainCard.setBackgroundDrawable(
//            ContextCompat.getDrawable(
//                requireContext(),
//                R.drawable.button_background
//            )
//        )
        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = FreePredictionsAdapter(list, requireContext())
        }

        binding.closeButton.setOnClickListener {
            dismiss()
        }
        return  binding.root
    }
}