package com.albertomier.klikinwaylettest.core

import android.app.AlertDialog
import android.content.Context
import android.location.Location
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

object Utils {

    fun distanceInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val startPoint = Location("locationA")
        startPoint.latitude = lat1
        startPoint.longitude = lon1

        val endPoint = Location("locationB")
        endPoint.latitude = lat2
        endPoint.longitude = lon2

        return (startPoint.distanceTo(endPoint) * 0.001)
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

    fun showErrorDialog(titleId: Int, messageId: Int, context: Context) {
        AlertDialog.Builder(context)
            .setTitle(titleId)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                //dissmiss
            }
            .create()
            .show()
    }

    fun showDialog(
        titleId: Int,
        messageId: Int,
        context: Context,
        onAcceptButton: () -> Unit,
        onCancelButton: () -> Unit
    ) {
        AlertDialog.Builder(context)
            .setTitle(titleId)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                onAcceptButton()
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                onCancelButton()
            }
            .create()
            .show()
    }

    fun showAlertDialog(
        titleId: Int,
        messageId: Int,
        context: Context,
        onAcceptButton: () -> Unit
    ) {
        AlertDialog.Builder(context)
            .setTitle(titleId)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
                onAcceptButton()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setCancelable(false)
            .create()
            .show()
    }
}