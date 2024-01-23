package com.example.apicallstoredataindatabase

data class MyProduct(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val products: List<Product>
)