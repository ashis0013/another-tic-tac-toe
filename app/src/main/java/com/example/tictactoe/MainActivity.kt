package com.example.tictactoe
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private var isPlayer1 = true
    private val array = Array(3) { Array(3) { 0 } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBindings()
    }

    private fun setupBindings() {
        findViewById<MaterialButton>(R.id.reset_button).setOnClickListener {
            reset()
        }
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val id = "box_${i}$j"
                findViewById<LinearLayout>(
                    resources.getIdentifier(
                        id,
                        "id",
                        this.packageName
                    )
                ).setOnClickListener {
                    it.foreground = if (isPlayer1) ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_cross
                    ) else ContextCompat.getDrawable(this, R.drawable.ic_ring)
                    array[i][j] = if (isPlayer1) 1 else 2
                    isPlayer1 = !isPlayer1
                    checkGameOver()
                }
            }
        }
    }

    private fun checkGameOver() {
        if (checkForWin()) {
            val winner = if (isPlayer1) "Player 2" else "Player 1"
            Toast.makeText(this, "$winner won", Toast.LENGTH_SHORT).show()
            reset()
        }
    }

    private fun reset() {
        isPlayer1 = true
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                array[i][j] = 0
                val id = "box_${i}$j"
                findViewById<LinearLayout>(
                    resources.getIdentifier(
                        id,
                        "id",
                        this.packageName
                    )
                ).foreground = null
            }
        }
    }

    private fun checkForWin(): Boolean {

        for (i in 0..2) {
            if ((array[i][0] == array[i][1]) &&
                (array[i][0] == array[i][2]) &&
                (array[i][0] != 0)
            ) return true
        }

        for (i in 0..2) {
            if (
                (array[0][i] == array[1][i]) &&
                (array[0][i] == array[2][i]) &&
                (array[0][i] != 0)
            ) return true
        }

        if (
            (array[0][0] == array[1][1]) &&
            (array[0][0] == array[2][2]) &&
            (array[0][0] != 0)
        ) return true

        if (
            (array[0][2] == array[1][1]) &&
            (array[0][2] == array[2][0]) &&
            (array[0][2] != 0)
        ) return true

        return false

    }
}