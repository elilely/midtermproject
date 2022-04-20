package lv.rtu.em201rdb118.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class GameStartMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_start_menu)

        val startButton = findViewById<Button>(R.id.StartButton)
        val firstPlayerName = findViewById<EditText>(R.id.FirstPlayer)
        val secondPlayerName = findViewById<EditText>(R.id.SecondPlayer)

        startButton.setOnClickListener {
            val getFirstPlayerName = firstPlayerName.text.toString()
            val getSecondPlayerName = secondPlayerName.text.toString()

            if (getFirstPlayerName.isEmpty() || getSecondPlayerName.isEmpty()) {
                Toast.makeText(this@GameStartMenu, "Please enter player name", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this@GameStartMenu, MainActivity::class.java)
                intent.putExtra("FirstPlayer", getFirstPlayerName)
                intent.putExtra("SecondPlayer", getSecondPlayerName)
                startActivity(intent)
            }
        }
    }

}