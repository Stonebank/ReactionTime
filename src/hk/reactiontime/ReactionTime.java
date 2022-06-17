package hk.reactiontime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ReactionTime extends JFrame implements MouseListener {

    private final JButton button;

    private long reactionStart;

    private long resultStart;
    private long resultFinish;

    public ReactionTime() {

        this.button = new JButton("Wait for green...");
        this.button.setFont(new Font("Times new roman", Font.BOLD, 36));
        this.button.setBackground(Color.RED);
        this.button.setOpaque(true);
        this.button.setBorderPainted(false);
        this.button.addMouseListener(this);
        this.button.setEnabled(false);

        this.setTitle("Reaction time test by Hassan K");
        this.add(button);
        this.setPreferredSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        this.reactionStart = generateSeconds(10, 5);

        this.checkTime();

    }

    private void restart() {
        button.setText("Wait for green...");
        button.setBackground(Color.RED);
        button.setEnabled(false);
        reactionStart = generateSeconds(10, 5);
        resultStart = 0;
        resultFinish = 0;
        checkTime();
    }

    private void checkTime() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                resultStart = System.currentTimeMillis();
                button.setEnabled(true);
                button.setText("Click!");
                button.setBackground(Color.GREEN);
                timer.cancel();
            }
        };
        timer.scheduleAtFixedRate(timerTask, TimeUnit.SECONDS.toMillis(reactionStart), 1);
    }

    private int generateSeconds(int max, int min) {
        if (min < 0 || max < 0)
            throw new IllegalArgumentException("ERROR! Minimum or maximum value cannot be lower than 0.");
        if (min >= max)
            throw new IllegalArgumentException("ERROR! Minimum value cannot be higher than or equal to Maximum value");
        return (int) (Math.random() * (max - min) + min);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!button.isEnabled())
            return;
        if (resultFinish > 0) {
            restart();
            return;
        }
        resultFinish = System.currentTimeMillis() - resultStart;
        button.setText(resultFinish + " ms (click to restart)");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
