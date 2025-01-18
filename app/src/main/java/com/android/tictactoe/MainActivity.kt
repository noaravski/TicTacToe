package com.android.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.android.tictactoe.model.GameBoard


class MainActivity : ComponentActivity() {

    private lateinit var gameBoard: GameBoard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()

        val buttons = Array(3) { row ->
            Array(3) { col ->
                findViewById<Button>(
                    resources.getIdentifier(
                        "button${row * 3 + col + 1}",
                        "id",
                        packageName
                    )
                )
            }
        }
        val gameStatus = findViewById<TextView>(R.id.gameStatus)

        gameBoard = GameBoard(buttons, gameStatus)

        findViewById<Button>(R.id.resetButton).setOnClickListener { gameBoard.startGame() }
        gameBoard.startGame()
    }
}

