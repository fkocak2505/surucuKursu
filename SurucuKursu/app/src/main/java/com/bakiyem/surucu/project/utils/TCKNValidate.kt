package com.bakiyem.surucu.project.utils

object TCKNValidate {
    fun isTCKN(tckn: String?): Boolean {
        var isValid = false
        if (tckn != null && tckn.length == 11 && isInt(tckn)) {
            var aTcNo: Long
            val bTcNo: Long
            val control1: Long
            val control2: Long
            val tcNo: Long = tckn.toLong()
            aTcNo = tcNo / 100
            bTcNo = tcNo / 100
            val n1: Long = aTcNo % 10
            aTcNo /= 10
            val n2: Long = aTcNo % 10
            aTcNo /= 10
            val n3: Long = aTcNo % 10
            aTcNo /= 10
            val n4: Long = aTcNo % 10
            aTcNo /= 10
            val n5: Long = aTcNo % 10
            aTcNo /= 10
            val n6: Long = aTcNo % 10
            aTcNo /= 10
            val n7: Long = aTcNo % 10
            aTcNo /= 10
            val n8: Long = aTcNo % 10
            aTcNo /= 10
            val n9: Long = aTcNo % 10
            control1 = (10 - ((n1 + n3 + n5 + n7 + n9) * 3 + (n2 + n4 + n6 + n8)) % 10) % 10
            control2 =
                (10 - ((n2 + n4 + n6 + n8 + control1) * 3 + (n1 + n3 + n5 + n7 + n9)) % 10) % 10
            isValid = bTcNo * 100 + control1 * 10 + control2 == tcNo
        }
        return isValid
    }

    private fun isInt(s: String): Boolean // assuming integer is in decimal number system
    {
        for (a in s.indices) {
            if (a == 0 && s[a] == '-') continue
            if (!Character.isDigit(s[a])) return false
        }
        return true
    }
}