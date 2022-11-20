package com.xonicsports_sportnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.xonicsports_sportnews.databinding.FragmentAlertDialogFragmentBinding


class AlertDialogFragment : DialogFragment() {

    private var binding : FragmentAlertDialogFragmentBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAlertDialogFragmentBinding.inflate(inflater, container, false)

        return binding!!.root
    }

}