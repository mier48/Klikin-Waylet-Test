package com.albertomier.klikinwaylettest.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.albertomier.klikinwaylettest.R
import com.albertomier.klikinwaylettest.databinding.FragmentShopDetailBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopDetailFragment : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    private var _binding: FragmentShopDetailBinding? = null
    private val binding get() = _binding!!

    private val args: ShopDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.shop = args.shop
        if (!args.shop.photos.isNullOrEmpty()) {
            binding.shopImage.load(args.shop.photos[0].url) {
                placeholder(R.mipmap.placeholder)
                transformations(RoundedCornersTransformation(8f))
            }
        }

        binding.address.text = "${args.shop.address.street}, ${args.shop.address.city}"
        binding.navigateTo.setOnClickListener {
            val gmmIntentUri =
                Uri.parse("google.navigation:q=${args.shop.latitude},${args.shop.longitude}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.shop.name

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val shopLocation = LatLng(args.shop.latitude, args.shop.longitude)
        map.addMarker(MarkerOptions().position(shopLocation).title(args.shop.name))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(shopLocation, 16f), 1000, null)
    }
}