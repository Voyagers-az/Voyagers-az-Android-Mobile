package com.natiqhaciyef.voyagers.data.model

data class CompanyModel(
    var id: Int,
    var name: String,
    var image: String,
    var tours: List<String>,
    var scope: String,
    var description: String,
    var link: String? = null
)
