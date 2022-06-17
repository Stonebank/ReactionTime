package hk.reactiontime

import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.util.*
import java.util.concurrent.TimeUnit
import javax.swing.JButton
import javax.swing.JFrame

class ReactionTime : JFrame(), MouseListener {

    private val button = JButton("Click anywhere to start")

    private var reactionStart: Int = 0

    private var resultStart: Long = 0
    private var resultFinish: Long = 0

    init {

        this.button.font = Font("Times New Roman", Font.BOLD, 36)
        this.button.background = Color.BLACK
        this.button.foreground = Color.WHITE
        this.button.isOpaque = true
        this.button.isBorderPainted = false
        this.button.addMouseListener(this)

        this.title = "Reaction time by Hassan K"
        this.add(button)
        this.preferredSize = Dimension(800, 600)
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.pack()

    }

    private fun restart() {
        button.text = "Wait for green..."
        button.background = Color.RED
        button.isEnabled = false
        reactionStart = (5..10).random()
        resultStart = 0
        resultFinish = 0
        checkTime()
    }

    private fun checkTime() {

        val timer = Timer()
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                resultStart = System.currentTimeMillis()
                button.isEnabled = true
                button.text = "Click!"
                button.background = Color.GREEN
                timer.cancel()
            }
        }
        timer.scheduleAtFixedRate(timerTask, TimeUnit.SECONDS.toMillis(reactionStart.toLong()), 1)

    }

    override fun mouseClicked(e: MouseEvent?) {
        if (!button.isEnabled)
            return
        if (resultFinish > 0) {
            restart()
            return
        }
        if (reactionStart == 0) {
            reactionStart = (5..10).random()
            button.text = "Wait for green..."
            button.background = Color.RED
            button.isEnabled = false
            checkTime()
            return
        }
        resultFinish = System.currentTimeMillis() - resultStart
        button.text = "$resultFinish ms (click to restart)"
    }

    override fun mousePressed(e: MouseEvent?) {

    }

    override fun mouseReleased(e: MouseEvent?) {

    }

    override fun mouseEntered(e: MouseEvent?) {

    }

    override fun mouseExited(e: MouseEvent?) {

    }

}