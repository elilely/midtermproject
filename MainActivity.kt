package lv.rtu.em201rdb118.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {


    private lateinit var control: GameControl
    private lateinit var startButton: Button
    private lateinit var firstPlayerPoints: TextView
    private lateinit var secondPlayerPoints: TextView
    private lateinit var reset: Button
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        control = GameControl()
        firstPlayerPoints = findViewById(R.id.FirstPlayerPoints)
        secondPlayerPoints = findViewById(R.id.SecondPlayerPoints)
        reset = findViewById(R.id.button)

        one = findViewById(R.id.button00)
        one.setOnClickListener { buttonTapped(one, GameControl.MovePosition(0, 0)) }

        two = findViewById(R.id.button01)
        two.setOnClickListener { buttonTapped(two, GameControl.MovePosition(0, 1)) }

        three = findViewById(R.id.button02)
        three.setOnClickListener { buttonTapped(three, GameControl.MovePosition(0, 2)) }

        four = findViewById(R.id.button10)
        four.setOnClickListener { buttonTapped(four, GameControl.MovePosition(1, 0)) }

        five = findViewById(R.id.button11)
        five.setOnClickListener { buttonTapped(five, GameControl.MovePosition(1, 1)) }

        six = findViewById(R.id.button12)
        six.setOnClickListener { buttonTapped(six, GameControl.MovePosition(1, 2)) }

        seven = findViewById(R.id.button20)
        seven.setOnClickListener { buttonTapped(seven, GameControl.MovePosition(2, 0)) }

        eight = findViewById(R.id.button21)
        eight.setOnClickListener { buttonTapped(eight, GameControl.MovePosition(2, 1)) }

        nine = findViewById(R.id.button22)
        nine.setOnClickListener { buttonTapped(nine, GameControl.MovePosition(2, 2)) }

        reset.setOnClickListener {
            reset.visibility = View.GONE
            control.reset()
            resetbuttons()
        }

        updatePlayersPoints()
    }

    private fun updatePlayersPoints() {
        firstPlayerPoints.text = "x: ${control.firstPlayerPoints}"
        secondPlayerPoints.text = "o: ${control.secondPlayerPoints}"
    }


    private fun buttonTapped(btm: TextView, position: GameControl.MovePosition) {
        if (btm.text.isEmpty()) {
            btm.text = control.runningPlayerMark
            val winLine = control.moves(position)
            if (winLine != null) {
                updatePlayersPoints()
                disableButtons()
                startButton.visibility = View.VISIBLE
                Winner(winLine)
            }
        }
    }

    private fun disableButtons() {
        one.isEnabled = false
        two.isEnabled = false
        three.isEnabled = false
        four.isEnabled = false
        five.isEnabled = false
        six.isEnabled = false
        seven.isEnabled = false
        eight.isEnabled = false
        nine.isEnabled = false
    }

    private fun Winner(winLine: GameControl.WinLine) {
        val (winButtons, background) = when (winLine) {
            GameControl.WinLine.ROW_0 -> Pair(listOf(one, two, three), R.drawable.horizontal_line)
            GameControl.WinLine.ROW_1 -> Pair(listOf(four, five, six), R.drawable.horizontal_line)
            GameControl.WinLine.ROW_2 -> Pair(listOf(seven, eight, nine), R.drawable.horizontal_line)
            GameControl.WinLine.COLUMN_0 -> Pair(listOf(one, four, seven), R.drawable.vertical_line)
            GameControl.WinLine.COLUMN_1 -> Pair(listOf(two, five, eight), R.drawable.vertical_line)
            GameControl.WinLine.COLUMN_2 -> Pair(listOf(three, six, nine), R.drawable.vertical_line)
            GameControl.WinLine.LEFT_DIAGONAL -> Pair(listOf(one, five, nine),
                R.drawable.diagonal_line_to_left
            )
            GameControl.WinLine.RIGHT_DIAGONAL -> Pair(listOf(three, five, seven),
                R.drawable.diagonal_line_to_right
            )
        }

        winButtons.forEach { btm ->
            btm.background = ContextCompat.getDrawable(MainActivity@ this, background)
        }
    }

    private fun resetbuttons() {
        one.text = ""
        one.background = null
        one.isEnabled = true

        two.text = ""
        two.background = null
        two.isEnabled = true

        three.text = ""
        three.background = null
        three.isEnabled = true

        four.text = ""
        four.background = null
        four.isEnabled = true

        five.text = ""
        five.background = null
        five.isEnabled = true

        six.text = ""
        six.background = null
        six.isEnabled = true

        seven.text = ""
        seven.background = null
        seven.isEnabled = true

        eight.text = ""
        eight.background = null
        eight.isEnabled = true

        nine.text = ""
        nine.background = null
        nine.isEnabled = true
    }

}

