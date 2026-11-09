package com.pnc.jetpackcomposedemos.features.artists.data

import com.pnc.jetpackcomposedemos.features.orders.data.OrderDataSource
import com.pnc.jetpackcomposedemos.features.orders.data.OrderLineItemDto
import retrofit2.http.GET

interface RemoteOrderDataSource : OrderDataSource {

    @GET("Order/customer")
    override suspend fun getOrders(): List<OrderLineItemDto>
}