package com.nhulox.eds.sqlitecrud.models


/** * Created by nhulox97 on 29,mayo,2019 */
class Note {
    var id: Int? = null
    var title: String? = null
    var content: String? = null

    constructor(id: Int, title: String, content: String){
        this.id = id
        this.title = title
        this.content = content
    }
}