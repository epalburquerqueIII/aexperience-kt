package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable
import java.util.*

class Evento : Serializable {

    constructor(
        ID: Int?,
        Title: String?,
        Date: String?,
        Description: String?,
        Tipo: String?,
        Image: String?,
        Imageslice: String?,
        Author: String?,
        Identificator: String?,
        Categorias: String?,
        Tags: String?,
        Body: String?) {
        this.ID = ID
        this.Title = Title
        this.Date = Date
        this.Description = Description
        this.Tipo = Tipo
        this.Image = Image
        this.Imageslice = Imageslice
        this.Author = Author
        this.Identificator = Identificator
        this.Categorias = Categorias
        this.Tags= Tags
        this.Body = Body
    }

    var ID: Int? = null
    var Title: String?
    var Date: String?
    var Description: String?
    var Tipo: String?
    var Image: String?
    var Imageslice: String?
    var Author: String?
    var Identificator: String?
    var Categorias: String?
    var Tags: String?
    var Body: String?

}
