package com.testing.banktask.presenter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.tabs.TabLayout
import com.testing.banktask.BaseApplication
import com.testing.banktask.R
import com.testing.banktask.adapter.SearchHistoryAdapter
import com.testing.banktask.databinding.FragmentMainBinding
import com.testing.banktask.di.MainViewModelFactory
import com.testing.common.dbentities.SearchHistoryData
import com.testing.utils.getBaseColor
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var mainViewModel: MainViewModel

    private val adapter = SearchHistoryAdapter()

    private val tabLayoutListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {

            tab?.let {
                when (it.position) {
                    0 -> {
                        binding.bankConstraintLayout.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                    1 -> {
                        binding.bankConstraintLayout.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) { }

        override fun onTabReselected(tab: TabLayout.Tab?) { }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.bankUrlTextView.setOnClickListener {
            val uri = "https://${binding.bankUrlTextView.text}"
            callIntentButtonPressed(Intent.ACTION_VIEW, uri)
        }
        binding.bankPhoneTextView.setOnClickListener {
            val uri = "tel:${binding.bankPhoneTextView.text}"
            callIntentButtonPressed(Intent.ACTION_DIAL, uri)
        }
        binding.bankNameTextView.setOnClickListener {
            val uri = "http://maps.google.com/maps?q=loc:${binding.latitudeTextView.text},${binding.longitudeTextView.text}"
            callIntentButtonPressed(Intent.ACTION_VIEW, uri)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text != null) {
                    mainViewModel.fetchBankCustomer(text)

                    updateSearchHistoryList(text)
                }
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }
        })

        mainViewModel.fetchAllSearchHistory().observe(viewLifecycleOwner) {

            adapter.submitList(it)
        }

        binding.tabLayout.addOnTabSelectedListener(tabLayoutListener)
        observeGetBankCustomerEvent()
        observerShowMessageEvent()
    }

    private fun callIntentButtonPressed(action: String, uriString: String) {
        if (uriString == "" || uriString == "?") return

        val intent = Intent(action, Uri.parse(uriString))
        startActivity(intent)
    }

    private fun updateSearchHistoryList(value: String) {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val date = dateFormat.format(Calendar.getInstance().time)

        val searchHistoryData = SearchHistoryData(
            id = 0,
            queryValue = value,
            requestDate = date
        )
        mainViewModel.addSearchHistory(searchHistoryData)



        mainViewModel.fetchAllSearchHistory().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun observeGetBankCustomerEvent() = mainViewModel.getBankCustomerEntity
        .observe(viewLifecycleOwner) { bankCustomerEntity ->

            if (bankCustomerEntity == null) {
                binding.schemeTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.brandTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.lengthTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.latitudeTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.longitudeTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.emojiTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.countryTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.bankNameTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.bankUrlTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.bankPhoneTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.luhnYesTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.luhnNoTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.debitTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.creditTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.prepaidYesTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))
                binding.prepaidNoTextView.setTextColor(resources.getBaseColor(R.color.default_text, null))

                binding.schemeTextView.text = "?"
                binding.brandTextView.text = "?"
                binding.lengthTextView.text = "?"
                binding.latitudeTextView.text = "?"
                binding.longitudeTextView.text = "?"
                binding.emojiTextView.text = "?"
                binding.countryTextView.text = ""
                binding.bankNameTextView.text = "?"
                binding.bankUrlTextView.text = "?"
                binding.bankPhoneTextView.text = "?"

                return@observe
            }

            binding.schemeTextView.text = bankCustomerEntity.scheme?.let {
                binding.schemeTextView.setTextColor(Color.BLACK)
                bankCustomerEntity.scheme
            } ?: "?"

            binding.brandTextView.text = bankCustomerEntity.brand?.let {
                binding.brandTextView.setTextColor(Color.BLACK)
                bankCustomerEntity.brand
            } ?: "?"

            binding.lengthTextView.text = bankCustomerEntity.number?.length?.let {
                binding.lengthTextView.setTextColor(Color.BLACK)
                bankCustomerEntity.number?.length.toString()
            } ?: "?"

            binding.latitudeTextView.text = bankCustomerEntity.country?.latitude?.let {
                binding.latitudeTextView.setTextColor(Color.BLACK)
                bankCustomerEntity.country?.latitude.toString()
            } ?: "?"

            binding.longitudeTextView.text = bankCustomerEntity.country?.longitude?.let {
                binding.longitudeTextView.setTextColor(Color.BLACK)
                bankCustomerEntity.country?.longitude.toString()
            } ?: "?"

            binding.emojiTextView.text = bankCustomerEntity.country?.emoji?.let {
                binding.emojiTextView.setTextColor(Color.BLACK)
                bankCustomerEntity.country?.emoji.toString()
            } ?: "?"

            binding.countryTextView.text = bankCustomerEntity.country?.name?.let {
                binding.countryTextView.setTextColor(Color.BLACK)
                bankCustomerEntity.country?.name.toString()
            } ?: ""

            binding.bankNameTextView.text = bankCustomerEntity.bank?.name?.let {
                binding.bankNameTextView.setTextColor(Color.BLACK)
                bankCustomerEntity.bank?.name.toString()
            } ?: "?"

            binding.bankUrlTextView.text = bankCustomerEntity.bank?.url?.let {
                binding.bankUrlTextView.setTextColor(Color.BLUE)
                bankCustomerEntity.bank?.url.toString()
            } ?: "?"

            binding.bankPhoneTextView.text = bankCustomerEntity.bank?.phone?.let {
                binding.bankPhoneTextView.setTextColor(Color.BLACK)
                bankCustomerEntity.bank?.phone.toString()
            } ?: "?"

            bankCustomerEntity.number?.luhn?.let {
                if (bankCustomerEntity.number?.luhn == true) {
                    binding.luhnYesTextView.setTextColor(Color.BLACK)
                } else {
                    binding.luhnNoTextView.setTextColor(Color.BLACK)
                }
            }

            bankCustomerEntity.type?.let {
                if (bankCustomerEntity.type == resources.getString(R.string.debit)) {
                    binding.debitTextView.setTextColor(Color.BLACK)
                } else {
                    binding.creditTextView.setTextColor(Color.BLACK)
                }
            }

            bankCustomerEntity.prepaid?.let {
                if (bankCustomerEntity.prepaid == true) {
                    binding.prepaidYesTextView.setTextColor(Color.BLACK)
                } else {
                    binding.prepaidNoTextView.setTextColor(Color.BLACK)
                }
            }
        }

    private fun observerShowMessageEvent() = mainViewModel.showMessageEvent
        .observe(viewLifecycleOwner) { message ->
            message?.let {
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            }
        }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}