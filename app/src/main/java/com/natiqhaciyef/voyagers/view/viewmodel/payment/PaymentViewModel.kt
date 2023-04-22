package com.natiqhaciyef.voyagers.view.viewmodel.payment

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.data.model.payment.PaymentDataModel
import com.natiqhaciyef.voyagers.data.model.UserModel
import com.natiqhaciyef.voyagers.util.functions.toMapForFirebase
import com.natiqhaciyef.voyagers.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(

) : BaseViewModel() {
    var users = mutableStateOf<List<UserModel>>(mutableListOf())
    val firestore = Firebase.firestore


    fun sendPaymentInfoToFirebase(paymentDataModel: PaymentDataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val paymentMap = hashMapOf<String, Any>()
            paymentMap["id"] = paymentDataModel.id
            paymentMap["paymentType"] = paymentDataModel.paymentType
            paymentMap["cvvCode"] = paymentDataModel.cvvCode
            paymentMap["expirationDate"] = paymentDataModel.expirationDate
            paymentMap["nameOnCard"] = paymentDataModel.nameOnCard
            paymentMap["numberOnCard"] = paymentDataModel.numberOnCard
            paymentMap["userModel"] = paymentDataModel.userModel.toMapForFirebase()

            firestore.collection("Payment")
                .document(paymentDataModel.nameOnCard)
                .set(paymentMap)
                .addOnSuccessListener {
                    // send data to room db
                }.addOnFailureListener {
                    Log.d("MYLOG", "${it.message} -> Error coused")
                }
        }
    }
}