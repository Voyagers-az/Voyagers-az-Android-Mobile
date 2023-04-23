package com.natiqhaciyef.voyagers.data.local.source

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.natiqhaciyef.voyagers.data.model.db.PaymentDBModel
import com.natiqhaciyef.voyagers.data.roomdb.PaymentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PaymentDataSource(var paymentDao: PaymentDao) {

    suspend fun getAllPaymentMethods() =
        withContext(Dispatchers.IO) {
            paymentDao.getAllPaymentMethods()
        }

    suspend fun getPaymentMethodByCardNumber(cardNumber: String) =
        withContext(Dispatchers.IO) {
            paymentDao.getPaymentMethodByCardNumber(cardNumber)
        }

    suspend fun getPaymentMethodByName(nameOnCard: String) =
        withContext(Dispatchers.IO) {
            paymentDao.getPaymentMethodByName(nameOnCard)
        }

    suspend fun insertPaymentMethod(paymentDBModel: PaymentDBModel) =
        withContext(Dispatchers.IO) {
            paymentDao.insertPaymentMethod(paymentDBModel)
        }

    suspend fun deletePaymentMethod(paymentDBModel: PaymentDBModel) =
        withContext(Dispatchers.IO) {
            paymentDao.deletePaymentMethod(paymentDBModel)
        }

}