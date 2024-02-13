import java.io.File
import kotlin.math.abs


fun main() {

    val text1 = File("In1.txt").readText()
    val text2 = File("In2.txt").readText()

   println("The answer for day1 part 1 is: "+ day1_1(text1))
   println("The answer for day1 part 2 is: "+ day1_2(text1))

    println("The answer for day2 part 1 is: "+ day2(text2, 1))
    println("The answer for day2 part 2 is: "+ day2(text2, 2))

}

fun day2(text2: String, part: Int): String {
    val lines = text2.lines()
    val keypad: Array<CharArray>
    val max: Int
    var a: Int
    var b: Int
    var code: String = ""
    if(part == 2) {
        val first: CharArray = charArrayOf('0', '0', '1', '0', '0')
        val second: CharArray = charArrayOf('0', '2', '3', '4', '0')
        val third: CharArray = charArrayOf('5', '6', '7', '8', '9')
        val fourth: CharArray = charArrayOf('0', 'A', 'B', 'C', '0')
        val fifth: CharArray = charArrayOf('0', '0', 'D', '0', '0')

        keypad = arrayOf(first, second, third, fourth, fifth)
        max = 4
        a = 0
        b = 2
    }else{
        val first: CharArray = charArrayOf('1', '2', '3')
        val second: CharArray = charArrayOf('4', '5', '6')
        val third: CharArray = charArrayOf('7', '8', '9')

        keypad = arrayOf(first, second, third)
        max = 2
        a = 1
        b = 1
    }

    lines.forEach {
        it.forEach({ it ->
            if(it == 'U' && b>0 && keypad[a][b-1]!= '0'){
                b = b-1
            }else if(it == 'D' && b<max && keypad[a][b+1]!= '0'){
                b = b+1
            }else if (it == 'L' && a>0 && keypad[a-1][b]!= '0'){
                a = a -1
            }else if(it == 'R' && a <max && keypad[a+1][b]!= '0'){
                a = a+1
            }
        })

        code=code+keypad[b][a]
    }

    return code
}

private fun day1_2(text:String): Int{
    var (a, b) = Pair(0, 0)
    var face = "N"
    val values = text.split(", ")
    var visited = arrayOf<Pair<Int, Int>>()

    visited += Pair(a, b)
    var foundFirst = false

    for (value in values) {
        if(foundFirst){break}
        val dir = value[0]
        var steps = value.substring(1).toInt()
        if (dir == 'R') {
            if (face == "N") {
                face = "E"
                while (steps > 0) {
                    a = a + 1
                    if (ifContains(visited, Pair(a, b))) {
                        return abs(a)+ abs(b)
                    } else {
                        visited += Pair(a, b)
                    }
                    steps = steps - 1
                }
            } else if (face == "S") {
                face = "W"
                while (steps > 0) {
                    a = a - 1
                    if (ifContains(visited, Pair(a, b))) {
                        return abs(a)+ abs(b)
                    } else {
                        visited += Pair(a, b)
                    }
                    steps = steps - 1
                }
            } else if (face == "E") {
                face = "S"
                while (steps > 0) {
                    b = b + 1
                    if (ifContains(visited, Pair(a, b))) {
                        return abs(a)+ abs(b)
                    } else {
                        visited += Pair(a, b)
                    }
                    steps = steps - 1
                }
            } else if (face == "W") {
                face = "N"
                while (steps > 0) {
                    b = b - 1
                    if (ifContains(visited, Pair(a, b))) {
                        return abs(a)+ abs(b)
                    } else {
                        visited += Pair(a, b)
                    }
                    steps = steps - 1
                }
            }
        } else if (dir == 'L') {
            if (face == "N") {
                face = "W"
                while (steps > 0) {
                    a = a - 1
                    if (ifContains(visited, Pair(a, b))) {
                        return abs(a)+ abs(b)
                    } else {
                        visited += Pair(a, b)
                    }
                    steps = steps - 1
                }
            } else if (face == "S") {
                face = "E"
                while (steps > 0) {
                    a = a + 1
                    if (ifContains(visited, Pair(a, b))) {
                        return abs(a)+ abs(b)
                    } else {
                        visited += Pair(a, b)
                    }
                    steps = steps - 1
                }
            } else if (face == "E") {
                face = "N"
                while (steps > 0) {
                    b = b - 1
                    if (ifContains(visited, Pair(a, b))) {
                        return abs(a)+ abs(b)
                    } else {
                        visited += Pair(a, b)
                    }
                    steps = steps - 1
                }
            } else if (face == "W") {
                face = "S"
                while (steps > 0) {
                    b = b + 1
                    if (ifContains(visited, Pair(a, b))) {
                        return abs(a)+ abs(b)
                    } else {
                        visited += Pair(a, b)
                    }
                    steps = steps - 1
                }
            }
        }

    }
    return -1
}

private fun day1_1(text:String):Int {
    var (a, b) = Pair(0, 0)
    var face = "N"
    val values = text.split(", ")

    for (value in values) {
        val dir = value[0]
        var steps = value.substring(1).toInt()
        if (dir == 'R') {
            if (face == "N") {
                face = "E"
                a = a + steps
            } else if (face == "S") {
                face = "W"
                a = a - steps
            } else if (face == "E") {
                face = "S"
                b = b + steps
            } else if (face == "W") {
                face = "N"
                b = b - steps
            }
        } else if (dir == 'L') {
            if (face == "N") {
                face = "W"
                a = a - steps
            } else if (face == "S") {
                face = "E"
                a = a + steps
            } else if (face == "E") {
                face = "N"
                b = b - steps
            } else if (face == "W") {
                face = "S"
                    b = b + steps
            }
        }

    }

    return abs(a)+ abs(b)
}


fun ifContains(array: Array<Pair<Int, Int>>, loc: Pair<Int, Int>): Boolean{

    for(element in array){
        if(element == loc){
            return true
        }
    }
    return false


}