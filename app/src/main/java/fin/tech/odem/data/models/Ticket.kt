package fin.tech.odem.data.models

import android.os.Parcelable
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
@Parcelize
data class Ticket(
    val id:String,
    @Contextual
    val creationDate: Date,
    val status:Int,
    @Contextual
    val closeDate: Date,
    val handledBy:String,
    var messages:Array<Message>
) : Parcelable