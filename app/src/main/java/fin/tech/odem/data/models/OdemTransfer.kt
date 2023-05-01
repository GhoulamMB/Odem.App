package fin.tech.odem.data.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
class OdemTransfer(val id:String,
                   val amount:Double,

                   @Contextual val date: Date,
                   val type:TransactionType,
                   val fromName:String?,
                   val toName:String?
)


@Serializable
enum class TransactionType {
    Ongoing, Outgoing
}