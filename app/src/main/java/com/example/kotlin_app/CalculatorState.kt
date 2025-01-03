package com.example.kotlin_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class CalculatorState(
    var n1s: String = "",
    var n2s: String = "",
    var rs: String = "",
) : Parcelable, Serializable {
    fun withN1S(s: String): CalculatorState {
        return this.copy(n1s = s)
    }

    fun withN2S(s: String): CalculatorState {
        return this.copy(n2s = s)
    }

    fun sum(): CalculatorState {
        return this.copy(rs = (n1s.toDouble() + n2s.toDouble()).toString())
    }
    fun sub(): CalculatorState {
        return this.copy(rs = (n1s.toDouble() - n2s.toDouble()).toString())
    }
    fun mul(): CalculatorState {
        return this.copy(rs = (n1s.toDouble() * n2s.toDouble()).toString())
    }
    fun div(): CalculatorState {
        return this.copy(rs = (n1s.toDouble() / n2s.toDouble()).toString())
    }

}