package com.zzz.movie.domain

import com.zzz.core.ui.domain.DomainError

sealed class NetworkError : DomainError() {
    data object BadRequest : NetworkError()
    data object Serialization : NetworkError()
    data class CustomError(val errorMsg : String) : NetworkError()
    data object Internal : NetworkError()
    data object Unknown : NetworkError()
}