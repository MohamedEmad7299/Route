package com.ug.route.ui.unused.six_boxes

data class ResetCode(
    val digit1: String,
    val digit2: String,
    val digit3: String,
    val digit4: String,
    val digit5: String,
    val digit6: String
) {
    fun getDigit(index: Int): String {
        return when (index) {
            0 -> digit1
            1 -> digit2
            2 -> digit3
            3 -> digit4
            4 -> digit5
            5 -> digit6
            else -> throw IndexOutOfBoundsException("Invalid digit index: $index")
        }
    }
}
