package com.android.tictactoe.model

import android.graphics.Color
import android.widget.Button
import android.widget.TextView

class GameBoard(
    val buttons: Array<Array<Button>>,
    val gameStatus: TextView
) {
    var isPlayerXTurn = true
    var board = Array(3) { CharArray(3) { ' ' } }

    fun startGame() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].apply {
                    text = ""
                    isEnabled = true
                    setOnClickListener { onButtonClick(this, i, j) }
                }
                buttons[i][j].setBackgroundColor(Color.WHITE)
            }
        }
        isPlayerXTurn = true
        board = Array(3) { CharArray(3) { ' ' } }
        gameStatus.text = "Player X"
    }
    private fun onButtonClick(button: Button, row: Int, col: Int) {
        if (board[row][col] != ' ') return

        board[row][col] = if (isPlayerXTurn) 'X' else 'O'
        button.text = board[row][col].toString()
        button.isEnabled = false

        if (isPlayerXTurn) {
            button.setTextColor(Color.WHITE)
            button.setBackgroundColor(Color.MAGENTA)
        } else {
            button.setTextColor(Color.WHITE)
            button.setBackgroundColor(Color.LTGRAY)
        }

        if (checkWin()) {
            gameStatus.text = "Player ${board[row][col]} Wins!"
            disableAllButtons()
        } else if (isBoardFull()) {
            gameStatus.text = "It's a Draw!"
        } else {
            isPlayerXTurn = !isPlayerXTurn
            gameStatus.text = "Player ${if (isPlayerXTurn) "X" else "O"}"
        }
    }

    fun checkWin(): Boolean {
        for (i in 0..2) {
            if ((board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') ||
                (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') ||
                (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')
            ) {
                return true
            }
        }

        return false
    }


    fun isBoardFull(): Boolean {
        return this.board.all { row -> row.all { cell -> cell != ' ' } }
    }

    fun disableAllButtons() {
        for (i in 0..2) {
            for (j in 0..2) {
                this.buttons[i][j].isEnabled = false
            }
        }
    }


}