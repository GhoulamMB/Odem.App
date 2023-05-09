package fin.tech.odem.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
@Parcelize
class OdemTransfer(val amount:Double,
                   @Contextual
                   val date: Date,
                   val type:Int,
                   val fromName:String?,
                   val toName:String?
) : Parcelable