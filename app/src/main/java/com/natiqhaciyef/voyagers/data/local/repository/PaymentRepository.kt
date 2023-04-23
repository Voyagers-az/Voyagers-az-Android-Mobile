package com.natiqhaciyef.voyagers.data.local.repository

import com.natiqhaciyef.voyagers.data.local.source.PaymentDataSource
import com.natiqhaciyef.voyagers.data.model.db.PaymentDBModel


class PaymentRepository(var ds: PaymentDataSource) {

    suspend fun getAllPaymentMethods() =
        ds.getAllPaymentMethods()

    suspend fun getPaymentMethodByCardNumber(cardNumber: String) =
        ds.getPaymentMethodByCardNumber(cardNumber)

    suspend fun getPaymentMethodByName(nameOnCard: String) =
        ds.getPaymentMethodByName(nameOnCard)

    suspend fun insertPaymentMethod(paymentDBModel: PaymentDBModel) =
        ds.insertPaymentMethod(paymentDBModel)

    suspend fun deletePaymentMethod(paymentDBModel: PaymentDBModel) =
        ds.deletePaymentMethod(paymentDBModel)
}