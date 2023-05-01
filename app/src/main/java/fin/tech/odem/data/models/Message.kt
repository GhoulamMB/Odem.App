package fin.tech.odem.data.models

import android.os.Parcelable
import com.ramcosta.composedestinations.navargs.NavTypeSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
@Parcelize
data class Message(
    val content:String,
    @Contextual
    val timestamp:Date,
    val isClientMessage:Boolean
) : Parcelable