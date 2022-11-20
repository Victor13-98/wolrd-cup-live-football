package com.xonicsports_sportnews.ui.home

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.xonicsports_sportnews.R
import com.xonicsports_sportnews.dataClasses.Plan
import com.xonicsports_sportnews.databinding.FragmentValidatePaymentBinding
import com.google.gson.Gson
import java.util.*
import kotlin.properties.Delegates


class ValidatePaymentFragment : DialogFragment() {
    private lateinit var binding: FragmentValidatePaymentBinding
    private lateinit var myClipboard: ClipboardManager
    private var position by Delegates.notNull<Int>()
    private lateinit var plan: List<Plan>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentValidatePaymentBinding.inflate(inflater, container, false)

        plan = arguments?.getParcelableArrayList<Plan>("list") as List<Plan>
        position = requireArguments().getInt("position")
        Toast.makeText(requireContext(), plan[position].toString(), Toast.LENGTH_LONG).show()

        binding.pasteButton.setOnClickListener {
            val textView = binding.textViewMessage
            myClipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData: ClipData = myClipboard.primaryClip!!
            if (clipData.getItemAt(0) == null) {
                binding.messageTextInputLayout.error = "Copy your confirmation first"
            } else {
                val item = clipData.getItemAt(0)
                textView.setText(item.text.toString())
            }
        }

        binding.validateButton.setOnClickListener {
            validateMessage()
        }



        binding.continueButton.setOnClickListener {
            findNavController().navigateUp();
        }
        return binding.root
    }


    private fun validateMessage() {
        val mpesaMessage = binding.textViewMessage.text.toString()
        binding.messageTextInputLayout.error = null
//        val messageCode = mpesaMessage.substring(0, 10)
        val messageCode = mpesaMessage

        if (!isValid(messageCode)) {
            binding.messageTextInputLayout.error = "Please enter valid M-PESA Message"
            return
        }

        if (mpesaMessage.indexOf(plan[position].amount) == -1) {
            binding.messageTextInputLayout.error = "Please enter valid M-PESA Message"
            return
        }

        val validateMessage = "Football Highway"
        if (mpesaMessage.lowercase(Locale.ROOT)
                .indexOf(validateMessage.lowercase(Locale.ROOT)) == -1
        ) {
            binding.messageTextInputLayout.error = "Please enter valid M-PESA Message"
            return
        }

        val calendar = Calendar.getInstance()
        val today = calendar.time

        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time
        Toast.makeText(requireContext(), "$today - $tomorrow", Toast.LENGTH_LONG).show()

        binding.progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            binding.progressBar.visibility = View.INVISIBLE
            binding.confirmed.visibility = View.VISIBLE
            Toast.makeText(requireContext(), "Message Validated", Toast.LENGTH_SHORT).show()
            binding.pasteButton.visibility = View.GONE
            binding.validateButton.visibility = View.GONE
            binding.continueButton.visibility = View.VISIBLE

        }, 3000)

    }

    private fun isValid(s: String): Boolean {
        val n = Regex(".*[0-9].*")
        val a = Regex(".*[A-Z].*")
        return s.matches(n) && s.matches(a)
    }

    private fun saveData() {
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(plan)
        editor.putString("plan", json)
        editor.apply()
        Toast.makeText(requireContext(), "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
    }

}


