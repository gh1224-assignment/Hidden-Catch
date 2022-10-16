import javax.swing.*;
import java.awt.*;

public class Task1 extends Task {
    public Task1(JFrame jFrame, Container cp) {
        super(jFrame, cp);
    }

    @Override
    int getFrameWidth() {
        return 375;
    }

    @Override
    int getFrameHeight() {
        return 500;
    }

    @Override
    void addComponents() {
        JTextField tfCal = new JTextField(20);
        tfCal.setFont(new Font("", 0, 25));
        tfCal.setBounds(30, 50, 295, 60);
        cp.add(tfCal);

        String[] bList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "="};
        JButton[] numB = new JButton[bList.length];
        for (int i = 0; i < numB.length; i++) {
            numB[i] = new JButton(bList[i]);
            numB[i].setFont(new Font("", 0, 20));
            numB[i].setBounds((i % 3) * 95 + 50, (i / 3) * 65 + 150, 60, 40);
            cp.add(numB[i]);
        }
    }
}
