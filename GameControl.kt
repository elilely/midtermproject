package lv.rtu.em201rdb118.tictactoe


class GameControl {

    private var runningPlayer = 1
    var firstPlayerPoints = 0
    var secondPlayerPoints = 0

    val runningPlayerMark: String
        get() {
            return if (runningPlayer == 1) "x" else "o"
        }

    private var state = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    data class MovePosition(val row: Int, val column: Int)

    enum class WinLine {
        ROW_0,
        ROW_1,
        ROW_2,
        COLUMN_0,
        COLUMN_1,
        COLUMN_2,
        LEFT_DIAGONAL,
        RIGHT_DIAGONAL,
    }

    fun moves(position: MovePosition): WinLine? {
        state[position.row][position.column] = runningPlayer
        val winLine = gameEndedCheck()

        if (winLine == null) {
            runningPlayer = 3 - runningPlayer
        } else {
            when (runningPlayer) {
                1 -> firstPlayerPoints++
                2 -> secondPlayerPoints++
            }
        }
        return winLine
    }

    fun reset() {
        state = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        runningPlayer = 1
    }

    private fun gameEndedCheck(): WinLine? {
        if (state[0][0] == runningPlayer && state[0][1] == runningPlayer && state[0][2] == runningPlayer) {
            return WinLine.ROW_0
        } else if (state[1][0] == runningPlayer && state[1][1] == runningPlayer && state[1][2] == runningPlayer) {
            return WinLine.ROW_1
        } else if (state[2][0] == runningPlayer && state[2][1] == runningPlayer && state[2][2] == runningPlayer) {
            return WinLine.ROW_2
        } else if (state[0][0] == runningPlayer && state[1][0] == runningPlayer && state[2][0] == runningPlayer) {
            return WinLine.COLUMN_0
        } else if (state[0][1] == runningPlayer && state[1][1] == runningPlayer && state[2][1] == runningPlayer) {
            return WinLine.COLUMN_1
        } else if (state[0][2] == runningPlayer && state[1][2] == runningPlayer && state[2][2] == runningPlayer) {
            return WinLine.COLUMN_2
        } else if (state[0][0] == runningPlayer && state[1][1] == runningPlayer && state[2][2] == runningPlayer) {
            return WinLine.LEFT_DIAGONAL
        } else if (state[0][2] == runningPlayer && state[1][1] == runningPlayer && state[2][0] == runningPlayer) {
            return WinLine.RIGHT_DIAGONAL
        }
        return null
    }

    private fun secondGameEndedCheck(): Boolean {
        state.forEach { row ->
            val hasWon = row.all { player -> player == runningPlayer }
            if (hasWon) return true
        }

        for (i: Int in state.indices) {
            val hasWon = state[i].all { player -> player == runningPlayer }
            if (hasWon) return true
        }

        for (i in state.indices) {
            if (state[i][i] != runningPlayer) return false
        }

        for (i in state.size until 0) {
            if (state[i][i] != runningPlayer) return false
        }

        return true
    }
}