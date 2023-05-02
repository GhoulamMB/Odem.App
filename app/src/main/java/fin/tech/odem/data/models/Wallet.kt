package fin.tech.odem.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Wallet(val id:String, var balance:Double, var transactions:Array<OdemTransfer>)