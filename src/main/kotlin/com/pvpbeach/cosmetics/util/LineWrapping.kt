package com.pvpbeach.cosmetics.util

import org.bukkit.ChatColor

val String.mommify: String
    get()
    {
        return this.split(" ")
            .map {
                "${it}-mommy"
            }.joinToString(" ") { "$it" }
    }

object LineWrapping
{
    fun wrapText(
        line: String,
        wrapLimit: Int,
        maxSplit: Int = 0,
        fullWords: Boolean = true
    ): Array<String>
    {
        if (line.length <= wrapLimit)
        {
            return arrayOf(line)
        }

        val lines = mutableListOf<String>()

        var current = ""
        var index = 0

        while (current.length < wrapLimit
            || (maxSplit == lines.size && maxSplit != 0)
            || (!current.endsWith(" ") && !fullWords)
        )
        {
            val maxLength = index == line.length

            if (!maxLength)
            {
                current += line[index++]
            }

            if (maxLength || (current.endsWith(" ") && current.length > wrapLimit))
            {
                if (lines.isNotEmpty())
                {
                    current = ChatColor.getLastColors(lines[lines.size - 1]) + current
                }

                if (!current.endsWith(" "))
                {
                    current += "-"
                }

                lines.add(current)
                current = ""

                if (lines.size == maxSplit)
                {
                    break
                }
            }
        }

        return lines.toTypedArray()
    }
}