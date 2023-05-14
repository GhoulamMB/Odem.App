package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        // The refresh method can be called on load as well for the first data
        //  load
        refresh()
    }

    fun refresh() {
        fun refresh() {
            // Don't set _isRefreshing to true here as this will be called on init,
            //  the pull to refresh api will handle setting _isRefreshing to true
            viewModelScope.launch {
                // Set _isRefreshing to false to indicate the refresh is complete
                _isRefreshing.emit(false)
            }
        }
    }

}