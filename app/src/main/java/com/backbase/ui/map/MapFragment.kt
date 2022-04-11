package com.backbase.ui.map

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : SupportMapFragment() {

    private val args: MapFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync {
            it.clear()
            with(args.cityModel) {
                val cityLatLng = LatLng(
                    latitude.toDouble(),
                    longitude.toDouble()
                )

                it.addMarker(MarkerOptions().position(cityLatLng).title(name))
                it.moveCamera(CameraUpdateFactory.newLatLng(cityLatLng))
            }
        }
    }
}
