package cz.stepes.githubviewer.util

object StringUtil {

    fun isSomeValid(vararg args: String?): Boolean {
        args.forEach {
            if (isValid(it)) {
                return true
            }
        }
        return false
    }

    private fun isValid(string: String?): Boolean {
        return string != null && string.isNotEmpty() && string.isNotBlank()
    }

    fun isNotValid(string: String?): Boolean {
        return isValid(string).not()
    }
}