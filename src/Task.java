import javax.swing.*;
import java.awt.*;

public abstract class Task {
    protected JFrame jFrame;
    protected Container cp;

    public Task(JFrame jFrame) {
        this.jFrame = jFrame;
        this.cp = jFrame.getContentPane();
    }

    abstract int getFrameWidth();

    abstract int getFrameHeight();

    abstract void addComponents();

    public final void doTask() {
        jFrame.setSize(getFrameWidth(), getFrameHeight());
        cp.setLayout(null);

        addComponents();
    }
}