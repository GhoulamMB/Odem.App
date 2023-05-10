package fin.tech.odem.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Parcelize
@Serializable
data class TransferRequest(val id:String,
                           val amount:Double,
                           val from:String,
                           val to:String,
                           var checked:Boolean,
                           val reason:String,
                           @Contextual
                           val timeStamp:Date) : Parcelable