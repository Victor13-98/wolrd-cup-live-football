package com.xonicsports_sportnews.ui.home

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.xonicsports_sportnews.R
import com.xonicsports_sportnews.dataClasses.Plan
import com.xonicsports_sportnews.databinding.FragmentSubscribeDialogBinding


class SubscribeDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentSubscribeDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubscribeDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val plans = arguments?.getParcelableArrayList<Plan>("plans") as ArrayList<Plan>
        val position = arguments?.getInt("Position")
        val name = plans[position!!].name
        val amount = plans[position].amount
        val days = plans[position].accessDays
        val odds = plans[position].odds

        binding.copyTillButton.setOnClickListener {
            val myClipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val text = "9828195"
            val myClip = ClipData.newPlainText("text", text)
            myClipboard.setPrimaryClip(myClip)
            Toast.makeText(requireActivity(), "Till Number Copied", Toast.LENGTH_LONG)
                .show()
        }



        binding.alreadyPaidButton.setOnClickListener {
//            val validatePaymentFragment = ValidatePaymentFragment()
//            validatePaymentFragment.show(childFragmentManager, "ValidatePaymentFragment")
            val bundle = bundleOf("list" to plans)
            bundle.putInt("position", position)
            findNavController().navigate(R.id.validatePaymentFragment, bundle)
            dismiss()
        }

        if (position == 1) {
//            binding.mainCard.background = ContextCompat.getDrawable(
//                requireContext(),
//                R.drawable.card_background
//            )
//            binding.copyTillButton.background = ContextCompat.getDrawable(
//                requireContext(),
//                R.drawable.card_background
//            )
        }

        if (position == 2) {
//            binding.mainCard.background = ContextCompat.getDrawable(
//                requireContext(),
//                R.drawable.basic_card_background
//            )
//            binding.copyTillButton.background = ContextCompat.getDrawable(
//                requireContext(),
//                R.drawable.basic_card_background
//            )
            binding.mainCard.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.payment_fragment_background
            )
            binding.copyTillButton.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.payment_fragment_background
            )
        }

        if (position == 3) {
            binding.mainCard.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.premium_background
            )
            binding.copyTillButton.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.premium_background
            )
        }

        if (position == 4) {
            binding.mainCard.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.payment_fragment_background
            )
            binding.copyTillButton.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.payment_fragment_background
            )
        }

        if (position == 5) {
            binding.mainCard.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.jackpot_background
            )
            binding.copyTillButton.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.jackpot_background
            )
        }


        binding.amount.text = amount
        binding.planName.text = name
        binding.accessDays.text = days
        binding.odds.text = odds

        binding.closeButton.setOnClickListener {
            dismiss()
        }
        return binding.root

    }

}