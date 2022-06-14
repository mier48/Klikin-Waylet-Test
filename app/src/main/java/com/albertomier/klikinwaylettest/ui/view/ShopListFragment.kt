package com.albertomier.klikinwaylettest.ui.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertomier.klikinwaylettest.KlikinWayletTestApp
import com.albertomier.klikinwaylettest.R
import com.albertomier.klikinwaylettest.core.LATITUDE_KEY
import com.albertomier.klikinwaylettest.core.LONGITUDE_KEY
import com.albertomier.klikinwaylettest.core.Utils
import com.albertomier.klikinwaylettest.data.network.ApiResponseStatus
import com.albertomier.klikinwaylettest.databinding.FragmentShopListBinding
import com.albertomier.klikinwaylettest.domain.model.Shop
import com.albertomier.klikinwaylettest.ui.adapter.CategoryAdapter
import com.albertomier.klikinwaylettest.ui.adapter.ShopAdapter
import com.albertomier.klikinwaylettest.ui.viewmodel.ShopListViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToLong

@AndroidEntryPoint
class ShopListFragment : Fragment() {

    private var _binding: FragmentShopListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShopListViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var location: Location
    private val locationPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                    requestLocationPermission()
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                    requestLocationPermission()
                }
                else -> {
                    // No location access granted.
                    Toast.makeText(
                        requireContext(),
                        "Es necesario aceptar el permiso de ubicación",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    interface ShopListFragmentActions {
        fun onItemClick(shop: Shop)
    }

    private lateinit var shopListFragmentActions: ShopListFragmentActions

    override fun onAttach(context: Context) {
        super.onAttach(context)
        shopListFragmentActions = try {
            context as ShopListFragmentActions
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implements LoginFragmentActions")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopListBinding.inflate(inflater, container, false)
        val root: View = binding.root

            binding.shopList.layoutManager = LinearLayoutManager(requireContext())
            binding.categoryList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

            requestLocationPermission()

            viewModel.status.observe(requireActivity()) { status ->
                when (status) {
                    is ApiResponseStatus.Error -> {
                        binding.progress.isVisible = false
                        Toast.makeText(
                            requireActivity(),
                            getString(status.messageId),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is ApiResponseStatus.Loading -> binding.progress.isVisible = true
                    is ApiResponseStatus.Success -> binding.progress.isVisible = false
                }
            }

            viewModel.shopList.observe(requireActivity()) { shopList ->
                binding.layout1.setTitle(shopList.size.toString())
                binding.shopList.adapter =
                    ShopAdapter(shopList) { shop -> shopListFragmentActions.onItemClick(shop) }
            }

            viewModel.categoryList.observe(requireActivity()) { categoryList ->
                binding.categoryList.adapter =
                    CategoryAdapter(categoryList) { category -> onCategorySelected(category) }
            }

        return root
    }

    private fun onCategorySelected(category: String) {
viewModel.filterList(category)
    }

    fun requestLocationPermission() {
        when {
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
//                    if (location != null) {
//                        setupLocation(location)
//                    } else {
                    KlikinWayletTestApp.preference.also {
                        it.setLong(LATITUDE_KEY, 43.3509192.roundToLong())
                        it.setLong(LONGITUDE_KEY, (-3.4954478).roundToLong())
                    }
                    viewModel.getShopList()
                    Toast.makeText(
                        requireActivity(),
                        "Error con la localización",
                        Toast.LENGTH_LONG
                    ).show()
                    // }
                }
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                Utils.showDialog(
                    R.string.information,
                    R.string.permission_request,
                    requireContext(),
                    {
                        locationPermissionRequest.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    },
                    {})
            }
            else -> {
                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    private fun setupLocation(location: Location) {
        this.location = location
        Toast.makeText(
            requireActivity(),
            "${location.latitude}, ${location.longitude}",
            Toast.LENGTH_LONG
        ).show()

        KlikinWayletTestApp.preference.also {
            it.setLong(LATITUDE_KEY, location.latitude.toLong())
            it.setLong(LONGITUDE_KEY, location.longitude.toLong())
        }

        viewModel.getShopList()
    }

    override fun onResume() {
        super.onResume()
        requestLocationPermission()
    }
}