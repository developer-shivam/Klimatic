package app.klimatic.ui.locationchooser.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.klimatic.R
import app.klimatic.data.model.weather.Location
import app.klimatic.ui.base.BaseFragment
import app.klimatic.ui.locationchooser.presentation.adapter.LocationAdapter
import app.klimatic.ui.search.presentation.viewmodel.SearchViewModel
import app.klimatic.ui.utils.handleState
import app.klimatic.ui.utils.hasPermissions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_location_chooser.etSearchQuery
import kotlinx.android.synthetic.main.fragment_location_chooser.rvLocations
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationChooserFragment : BaseFragment(), ActivityCompat.OnRequestPermissionsResultCallback {

    private val searchViewModel by viewModel<SearchViewModel>()

    private val locationViewModel by viewModel<LocationViewModel>()

    private var fusedLocationClient: FusedLocationProviderClient? = null

    private val locationCallback by lazy {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation.run {
                    searchViewModel.searchLocationByLatLon(latitude, longitude)
                }
                stopLocationService()
            }
        }
    }

    private val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            val acceptedPermissions = map.values.filter { it == true }
            if (acceptedPermissions.size == permissions.size) {
                fetchLocation()
            }
        }

    override fun getLayoutResource(): Int = R.layout.fragment_location_chooser

    private val locationAdapter by lazy {
        LocationAdapter(onItemClickAction)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        requestPermissions()
        setupLocationsRecyclerView()
        setupObservers()
        setupSearch()
    }

    private fun fetchLocation() {
        context?.let {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
        }
        startLocationService()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationService() {
        fusedLocationClient?.requestLocationUpdates(
            getLocationRequest(),
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun stopLocationService() {
        fusedLocationClient?.removeLocationUpdates(locationCallback)
    }

    private fun getLocationRequest(): LocationRequest {
        return LocationRequest.create().apply {
            interval = 60000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private fun setupSearch() {
        etSearchQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
                if (!TextUtils.isEmpty(query)) {
                    if (query!!.length > 2) {
                        searchViewModel.searchLocationByQuery(query.toString())
                    }
                } else {
                    searchViewModel.searchLocationByQuery()
                }
            }
        })
    }

    private fun setupLocationsRecyclerView() {
        rvLocations?.run {
            layoutManager =
                LinearLayoutManager(context)
            adapter = locationAdapter
        }
    }

    private fun setupObservers() {
        searchViewModel.locations.observe(viewLifecycleOwner, Observer { state ->
            handleState(
                state,
                {
                    locationAdapter.submitList(it)
                },
                { errorCode: Int? ->
                    Log.d(javaClass.name, "Error: $errorCode")
                }
            )
        })
    }

    private fun requestPermissions() {
        if (context.hasPermissions(permissions))
            fetchLocation()
        else
            requestPermissionLauncher.launch(permissions)
    }

    private var onItemClickAction: (location: Location) -> Unit = { location ->
        if (!TextUtils.isEmpty(location.name)) {
            locationViewModel.setCurrentSelectedLocation(location.name!!)
            requireActivity().setResult(Activity.RESULT_OK)
            requireActivity().finish()
        }
    }

    override fun onStop() {
        stopLocationService()
        super.onStop()
    }
}
