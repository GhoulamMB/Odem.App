package fin.tech.odem.data.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Ticket(val id:String, @Contextual val creationDate:Date, val status:Status,@Contextual val closeDate: Date, val handledBy:String)


@Serializable
enum class Status {
    Open, Closed
}