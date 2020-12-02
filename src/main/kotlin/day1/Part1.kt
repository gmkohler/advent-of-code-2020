package day1

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/**
 * the Elves in accounting just need you to fix your expense report (your puzzle input); apparently,
 * something isn't quite adding up.  Specifically, they need you to find the two entries that sum to 2020 and then
 * multiply those two numbers together.
 *
 * For example, suppose your expense report contained the following:
 *      1721
 *      979
 *      366
 *      299
 *      675
 *      1456
 * In this list, the two entries that sum to 2020 are 1721 and 299.  Multiplying them together produces
 * 1721 * 299 = 514579, so the correct answer is 514579.
 *
 * Of course, your expense report is much larger. Find the two entries that sum to 2020; what do you get if you
 * multiply them together?
 */

const val TOTAL = 2020

fun main() {
    val (numbers, complementaryNumbers) = BufferedReader(FileReader(
//        Hack taken from Cotel/AdventOfCode on GitHub.
        File(object {}::class.java.getResource("/day1/input.txt").file)
    ))
        .useLines { sequence ->
            sequence
                .map(String::toInt) // [12, 34, 2008]
                .map { it to TOTAL - it } // [ 12 -> 2008, 34 -> 1986, 2008 -> 12 ]
                .unzip() // < [12, 34, 2008], [2008, 1986, 12] >
        }

    val matches = (complementaryNumbers intersect numbers)
    val match = (matches.firstOrNull() ?: throw Exception("no matches"))

    assert(matches.contains(TOTAL - match)) { "both numbers are in the original set" }

    println("found $match and ${(TOTAL - match)} which multiply to ${match * (TOTAL - match)}")
}
