package com.albertomier.klikinwaylettest.ui.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.albertomier.klikinwaylettest.KlikinWayletTestApp
import com.albertomier.klikinwaylettest.R
import com.albertomier.klikinwaylettest.core.LATITUDE_KEY
import com.albertomier.klikinwaylettest.core.LONGITUDE_KEY
import com.albertomier.klikinwaylettest.core.PERMISSION_REQUEST_CODE
import com.albertomier.klikinwaylettest.core.Utils
import com.albertomier.klikinwaylettest.databinding.ActivityMainBinding
import com.albertomier.klikinwaylettest.domain.model.Shop
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ShopListFragment.ShopListFragmentActions {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()) {
                    when (PackageManager.PERMISSION_GRANTED) {
                        grantResults[0] -> {
                            requestLocationPermission()
                        }
                        grantResults[1] -> {
                            requestLocationPermission()
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Es necesario aceptar el permiso de ubicación",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    private fun requestLocationPermission() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (fragment is ShopListFragment) {
            fragment.requestLocationPermission()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onItemClick(shop: Shop) {
        findNavController(R.id.nav_host_fragment).navigate(
            ShopListFragmentDirections.actionShopListFragmentToShopDetailFragment(
                shop
            )
        )
    }
}