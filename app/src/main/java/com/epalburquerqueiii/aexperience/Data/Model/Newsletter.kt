package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Newsletter : Serializable {

    constructor(
        Id: Int?,
        Email: String?,
        Idtiponoticias: Int?

    ) {
        this.Id = Id
        this.Email = Email
        this.Idtiponoticias = Idtiponoticias

    }

    var Id: Int? = null
    var Email: String? = null
    var Idtiponoticias: Int? = null



}