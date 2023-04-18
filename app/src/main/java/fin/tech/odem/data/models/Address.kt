package fin.tech.odem.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Address(val street:String,val city:String,val zipCode:String)