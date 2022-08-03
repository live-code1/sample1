package com.sample.basicapp.view

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.sample.basicapp.R
import com.sample.basicapp.util.Utils
import com.sample.basicapp.util.toast
import com.sample.basicapp.view.adapter.FruitsRcvAdapter
import com.sample.basicapp.viewmodel.AuthViewModel
import com.v4csolutions.spaapp.data.network.ResultResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val searchViewModel: AuthViewModel by viewModels()
    private var mAdapter:FruitsRcvAdapter?= null
    private var mProgressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showProgressDialog()
        rcv_fruits.layoutManager=GridLayoutManager(this, 2)
        mAdapter=FruitsRcvAdapter(this)
        rcv_fruits.adapter= mAdapter
        getFruits()
    }

    private fun getFruits(){
        searchViewModel.getFruits(
        ).observe(this) { resultResponse ->
            when (resultResponse.status) {
                ResultResponse.Status.SUCCESS -> {
                    val serverResponse = resultResponse.data?.body()
                    if (serverResponse ?: "" != "") {
                        Log.e("success1 fruits size", (serverResponse?.fruits?.size?:0).toString())

                        if (serverResponse?.fruits?.size?:0>0){

                            mAdapter?.swap(serverResponse?.fruits)
                            mAdapter?.notifyDataSetChanged()
                        }




                    } else {
                        toast("Something went wrong")
                    }
                    hideProgressDialog()

                }
                ResultResponse.Status.ERROR -> {
                    Log.e("failed", resultResponse.message.toString())
                    hideProgressDialog()
                }
                else -> {
                    Log.e("failed", resultResponse.message.toString())
                    hideProgressDialog()
                }
            }
        }
    }

    fun showProgressDialog(){
        mProgressDialog =
            ProgressDialog(
                this,
                R.style.AppCompatAlertDialogStyle
            )
        mProgressDialog?.isIndeterminate = false
        mProgressDialog?.setCancelable(false)
        Utils.showProgressDialog(
            mProgressDialog,
            "",
            getString(R.string.please_wait)
        )
    }

    private fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog?.isShowing!!) {
            Utils.hideProgressDialog(mProgressDialog)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        hideProgressDialog()
    }
}