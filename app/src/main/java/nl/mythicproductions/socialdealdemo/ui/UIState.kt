package nl.mythicproductions.socialdealdemo.ui


/**
 * A sealed class that can be used to represent the state of loading data.
 * This can be used to represent the state of loading data in a UI, for example.
 * The state can be one of the following:
 * - [UIState.Idle]: No data is available and nothing is happening.
 * - [UIState.Loading]: Data is being loaded.
 * - [UIState.Reloading]: Data is being reloaded, but the previous data is still available for display.
 * - [UIState.Success]: Data has been loaded successfully.
 * - [UIState.Error]: An error occurred while loading data.
 */
sealed class UIState<out T> {
    /**
     * A loading state that can be used to indicate that no data is available and nothing is happening.
     */
    data object Idle : UIState<Nothing>()

    /**
     * A loading state that can be used to indicate that data is being loaded.
     */
    data object Loading : UIState<Nothing>()

    /**
     * A loading state that can be used to indicate that data is being reloaded.
     * This is used to indicate that the data is being reloaded, but the previous data is still available for display.
     */
    data class Reloading<T>(val data: T) : UIState<T>()

    /**
     * A loading state that can be used to indicate that data has been loaded successfully.
     */
    data class Success<T>(val data: T) : UIState<T>() {
        /**
         * Convert the current state to a [Reloading] state.
         */
        fun toReloading(): Reloading<T> {
            return Reloading(data)
        }
    }

    /**
     * A loading state that can be used to indicate that an error occurred while loading data.
     */
    data class Error(val message: String) : UIState<Nothing>()
}