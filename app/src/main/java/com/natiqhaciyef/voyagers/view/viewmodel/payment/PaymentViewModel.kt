package com.natiqhaciyef.voyagers.view.viewmodel.payment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.voyagers.data.local.repository.PaymentRepository
import com.natiqhaciyef.voyagers.data.model.payment.PaymentDataModel
import com.natiqhaciyef.voyagers.data.model.db.PaymentDBModel
import com.natiqhaciyef.voyagers.util.functions.toMapForFirebase
import com.natiqhaciyef.voyagers.util.functions.toSQLiteMutableMap
import com.natiqhaciyef.voyagers.util.functions.toSQLiteString
import com.natiqhaciyef.voyagers.util.functions.toUserModelForFirebase
import com.natiqhaciyef.voyagers.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    val repository: PaymentRepository
) : BaseViewModel() {
    var payments = mutableStateOf<List<PaymentDBModel>>(mutableListOf())
    var paymentModels = mutableStateOf<List<PaymentDataModel>>(mutableListOf())

    init {
        getPaymentDBModels()
    }

    private fun getPaymentDBModels() {
        viewModelScope.launch(Dispatchers.Main) {
            payments.value = repository.getAllPaymentMethods()
            paymentModels.value = getPayment()
        }
    }

    private fun getPayment(): List<PaymentDataModel> {
        val list = mutableListOf<PaymentDataModel>()
        payments.value.forEach {
            list.add(
                PaymentDataModel(
                    id = it.id,
                    paymentType = it.paymentType,
                    nameOnCard = it.nameOnCard,
                    numberOnCard = it.numberOnCard,
                    expirationDate = it.expirationDate,
                    cvvCode = it.cvvCode,
                    userModel = it.userModel.toSQLiteMutableMap().toUserModelForFirebase()
                )
            )
        }

        return list
    }


    fun insertPayment(payment: PaymentDataModel) {
        val paymentDBModel = PaymentDBModel(
            id = payment.id,
            paymentType = payment.paymentType,
            nameOnCard = payment.nameOnCard,
            numberOnCard = payment.numberOnCard,
            expirationDate = payment.expirationDate,
            cvvCode = payment.cvvCode,
            userModel = payment.userModel.toMapForFirebase().toSQLiteString()
        )

        viewModelScope.launch(Dispatchers.Main) {
            repository.insertPaymentMethod(paymentDBModel)
        }
    }

    fun deletePayment(payment: PaymentDataModel) {
        val paymentDBModel = PaymentDBModel(
            id = payment.id,
            paymentType = payment.paymentType,
            nameOnCard = payment.nameOnCard,
            numberOnCard = payment.numberOnCard,
            expirationDate = payment.expirationDate,
            cvvCode = payment.cvvCode,
            userModel = payment.userModel.toMapForFirebase().toSQLiteString()
        )

        viewModelScope.launch(Dispatchers.Main) {
            repository.deletePaymentMethod(paymentDBModel)
        }
    }

    fun updateCardInfo(id:Int, payment: PaymentDataModel){
        val paymentDBModel = PaymentDBModel(
            id = id,
            paymentType = payment.paymentType,
            nameOnCard = payment.nameOnCard,
            numberOnCard = payment.numberOnCard,
            expirationDate = payment.expirationDate,
            cvvCode = payment.cvvCode,
            userModel = payment.userModel.toMapForFirebase().toSQLiteString()
        )

        viewModelScope.launch(Dispatchers.Main) {
            repository.updatePaymentMethod(id, paymentDBModel)
        }
    }
}