package com.example.intershalatrainingapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.intershalacourseapp.R
import com.example.intershalacourseapp.util.ConnectionManager
import com.example.intershalacourseapp.util.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.view.*

class Profile_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.profile_fragment, container, false)

        view.check_connection_btn.setOnClickListener {
            var message = ConnectionManager().checkConnection(activity as Context)
            val alertDialog = androidx.appcompat.app.AlertDialog.Builder(activity as Context)
            if (message) {
                alertDialog.setTitle("Internet")
                alertDialog.setMessage("Connection is Ok")
                alertDialog.setNegativeButton("Success" as CharSequence) { dialog, which ->
                }

                alertDialog.show()
            } else {
                alertDialog.setTitle("Error")
                alertDialog.setMessage("Can't found Internet")
                alertDialog.setPositiveButton("Try after sometime" as CharSequence) { dialog, which ->


                }
                alertDialog.show()
            }


        }
        return view
    }
}