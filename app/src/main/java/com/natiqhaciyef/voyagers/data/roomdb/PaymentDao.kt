package com.natiqhaciyef.voyagers.data.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.natiqhaciyef.voyagers.data.model.db.PaymentDBModel

@Dao
interface PaymentDao {

    @Query("SELECT * FROM payment_table")
    suspend fun getAllPaymentMethods(): List<PaymentDBModel>

    @Query("SELECT * FROM payment_table WHERE numberOnCard = :cardNumber")
    suspend fun getPaymentMethodByCardNumber(cardNumber: String): List<PaymentDBModel>

    @Query("SELECT * FROM payment_table WHERE nameOnCard = :nameOnCard")
    suspend fun getPaymentMethodByName(nameOnCard: String): List<PaymentDBModel>

    @Insert
    suspend fun insertPaymentMethod(paymentDBModel: PaymentDBModel)

    @Delete
    suspend fun deletePaymentMethod(paymentDBModel: PaymentDBModel)

}