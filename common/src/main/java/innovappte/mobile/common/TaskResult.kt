package innovappte.mobile.common


sealed class TaskResult {
    data class Success<T>(val data: T) : TaskResult()
    data class Failure<E>(val error: E) : TaskResult()
}
