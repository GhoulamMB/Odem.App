package fin.tech.odem.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Wallet(val id:String,val balance:Double,val transactions:Array<OdemTransfer>)