package app.klimatic.ui.utils

import app.klimatic.R

sealed class Theme(
    var id: Int,
    var displayColor: Int,
    var style: Int
) {

    companion object {

        fun getTheme(id: Int): Theme {
            return when (id) {
                0 -> Blue()
                1 -> Red()
                2 -> Green()
                3 -> Orange()
                4 -> Purple()
                else -> {
                    Blue()
                }
            }
        }

        fun getStyle(theme: Theme): Int {
            return when (theme) {
                is Blue -> theme.style
                is Red -> theme.style
                is Green -> theme.style
                is Orange -> theme.style
                is Purple -> theme.style
            }
        }
    }

    class Blue : Theme(0, R.color.blue_500, R.style.Theme_Klimatic_Blue)

    class Red : Theme(1, R.color.red_500, R.style.Theme_Klimatic_Red)

    class Green : Theme(2, R.color.green_500, R.style.Theme_Klimatic_Green)

    class Orange : Theme(3, R.color.orange_500, R.style.Theme_Klimatic_Orange)

    class Purple : Theme(4, R.color.purple_500, R.style.Theme_Klimatic_Purple)
}
