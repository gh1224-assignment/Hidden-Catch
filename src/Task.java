import javax.swing.*;
import java.awt.*;

public abstract class Task {
    protected JFrame jFrame;
    protected Container cp;

    public Task(JFrame jFrame, Container cp) {
        this.jFrame = jFrame;
        this.cp = cp;
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