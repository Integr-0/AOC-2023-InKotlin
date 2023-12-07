import utils.*

/**
 * # Day 07
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val lines = readInput("Day07")
    val day07 = Day07(lines)

    day07.solvePart1()
    day07.solvePart2()
}

class Day07(private val lines: List<String>) {
    fun solvePart1() {
        var finalVal = 0
        val games: MutableList<DeckData> = mutableListOf()
        for (l in lines) {
            games += evalDeckData(l, lines.indexOf(l))
        }

        val sorted = sortGames(games)
        for (d in sorted) {
            val rank = sorted.reversed().indexOf(d)+1
            finalVal += d.bid*rank
        }

        finalVal.println("Part 1 Total: {val}")
    }

    fun solvePart2() {
        var finalVal = 0
        val games: MutableList<DeckData> = mutableListOf()
        for (l in lines) {
            games += evalDeckData2(l, lines.indexOf(l))
        }

        val sorted = sortGames(games)
        for (d in sorted) {
            val rank = sorted.reversed().indexOf(d)+1
            finalVal += d.bid*rank
        }

        finalVal.println("Part 2 Total: {val}")
    }

    private fun sortGames(games: List<DeckData>): List<DeckData> {
        return games.sortedWith { o1: DeckData, o2: DeckData -> if (o1.isWorthMoreThan(o2)) 1 else -1 }.toList()
    }

    private fun DeckData.isWorthMoreThan(data: DeckData): Boolean {
        return if (this.type > data.type) {
            true
        } else if (this.type == data.type) {
            if (this.strengths.one < data.strengths.one) {
                true
            } else if (this.strengths.one == data.strengths.one) {
                if (this.strengths.two < data.strengths.two) {
                    true
                } else if (this.strengths.two == data.strengths.two) {
                    if (this.strengths.three < data.strengths.three) {
                        true
                    } else if (this.strengths.three == data.strengths.three) {
                        if (this.strengths.four < data.strengths.four) {
                            true
                        } else if (this.strengths.four == data.strengths.four) {
                            if (this.strengths.five < data.strengths.five) {
                                true
                            } else if (this.strengths.five == data.strengths.five) {
                                throw NullPointerException("Strange number comparison!")
                            } else false
                        } else false
                    } else false
                } else false
            } else false
        } else false
    }

    private fun evalDeckData2(line: String, index: Int): DeckData {
        val charMap: MutableMap<Char, Int> = mutableMapOf()

        var amountOf5s = 0
        var amountOf4s = 0
        var amountOf3s = 0
        var amountOf2s = 0
        var amountOf1s = 0

        val deck = line.split(" ")[0]
        val bid = line.split(" ")[1].toInt()
        for (c in deck) {
            charMap[c] = charMap.getOrDefault(c, 0)+1
        }

        for (c in charMap.keys) {
            val value = charMap.getValue(c)
            if (c != 'J') {
                when (value) {
                    1 -> amountOf1s++
                    2 -> amountOf2s++
                    3 -> amountOf3s++
                    4 -> amountOf4s++
                    5 -> amountOf5s++
                }
            }
        }

        var jokerAmount = charMap['J']
        var type: DeckType

        if (jokerAmount != 0 && jokerAmount != null) {
            type = when (jokerAmount) {
                5 -> {
                    DeckType.FiveOfAKind
                }
                4 -> {
                    DeckType.FiveOfAKind
                }
                3 -> {
                    if (amountOf2s == 1) {
                        DeckType.FiveOfAKind
                    } else {
                        DeckType.FourOfAKind
                    }
                }
                2 -> {
                    if (amountOf3s == 1) {
                        DeckType.FiveOfAKind
                    } else if (amountOf2s == 1) {
                        DeckType.FourOfAKind
                    } else {
                        DeckType.ThreeOfAKind
                    }
                }
                1 -> {
                    if (amountOf4s == 1) {
                        DeckType.FiveOfAKind
                    } else if (amountOf3s == 1) {
                        DeckType.FourOfAKind
                    } else if (amountOf2s == 2) {
                        DeckType.FullHouse
                    } else if (amountOf2s == 1) {
                        DeckType.ThreeOfAKind
                    } else DeckType.OnePair
                }
                else -> throw NullPointerException("Mistake while assigning Joker Card!")
            }
        } else {
            type = if (amountOf5s == 1) {
                DeckType.FiveOfAKind
            } else if (amountOf4s == 1) {
                DeckType.FourOfAKind
            } else if (amountOf3s == 1) {
                if (amountOf2s == 1) {
                    DeckType.FullHouse
                } else {
                    DeckType.ThreeOfAKind
                }
            } else if (amountOf2s == 2) {
                DeckType.TwoPair
            } else if (amountOf2s == 1) {
                DeckType.OnePair
            } else DeckType.HighCard
        }


        return DeckData(
            type,
            CardStrength(
                evalChar2(deck[0]),
                evalChar2(deck[1]),
                evalChar2(deck[2]),
                evalChar2(deck[3]),
                evalChar2(deck[4]),
            ),
            index,
            bid
        )
    }

    private fun evalDeckData(line: String, index: Int): DeckData {
        val charMap: MutableMap<Char, Int> = mutableMapOf()

        var amountOf5s = 0
        var amountOf4s = 0
        var amountOf3s = 0
        var amountOf2s = 0
        var amountOf1s = 0

        val deck = line.split(" ")[0]
        val bid = line.split(" ")[1].toInt()
        for (c in deck) {
            charMap[c] = charMap.getOrDefault(c, 0)+1
        }

        for (c in charMap.keys) {
            val value = charMap.getValue(c)

            when (value) {
                1 -> amountOf1s++
                2 -> amountOf2s++
                3 -> amountOf3s++
                4 -> amountOf4s++
                5 -> amountOf5s++
            }
        }

        var type: DeckType = if (amountOf5s == 1) {
            DeckType.FiveOfAKind
        } else if (amountOf4s == 1) {
            DeckType.FourOfAKind
        } else if (amountOf3s == 1) {
            if (amountOf2s == 1) {
                DeckType.FullHouse
            } else {
                DeckType.ThreeOfAKind
            }
        } else if (amountOf2s == 2) {
            DeckType.TwoPair
        } else if (amountOf2s == 1) {
            DeckType.OnePair
        } else DeckType.HighCard

        return DeckData(
            type,
            CardStrength(
                evalChar(deck[0]),
                evalChar(deck[1]),
                evalChar(deck[2]),
                evalChar(deck[3]),
                evalChar(deck[4]),
            ),
            index,
            bid
        )
    }

    private fun evalChar(char: Char): Int {
        return when (char) {
            'A' -> 13
            'K' -> 12
            'Q' -> 11
            'J' -> 10
            'T' -> 9
            '9' -> 8
            '8' -> 7
            '7' -> 6
            '6' -> 5
            '5' -> 4
            '4' -> 3
            '3' -> 2
            '2' -> 1
            else -> 0
        }
    }

    private fun evalChar2(char: Char): Int {
        return when (char) {
            'A' -> 13
            'K' -> 12
            'Q' -> 11
            'T' -> 10
            '9' -> 9
            '8' -> 8
            '7' -> 7
            '6' -> 6
            '5' -> 5
            '4' -> 4
            '3' -> 3
            '2' -> 2
            'J' -> 1
            else -> 0
        }
    }

    enum class DeckType(value: Int) {
        FiveOfAKind(7),
        FourOfAKind(6),
        FullHouse(5),
        ThreeOfAKind(4),
        TwoPair(3),
        OnePair(2),
        HighCard(1)
    }

    data class DeckData(
        val type: DeckType,
        val strengths: CardStrength,
        val index: Int,
        val bid: Int
    )
    data class CardStrength(
        val one: Int,
        val two: Int,
        val three: Int,
        val four: Int,
        val five: Int
    )
}

