import javax.swing.*;

public class Task4 extends Task {
    public Task4(JFrame jFrame) {
        super(jFrame);
    }

    @Override
    int getFrameWidth() {
        return 385;
    }

    @Override
    int getFrameHeight() {
        return 285;
    }

    @Override
    void addComponents() {
        cp.add(new JLabel("섭씨온도")).setBounds(35, 65, 80, 20);
        cp.add(new JLabel("화씨온도")).setBounds(35, 115, 80, 20);

        JTextField tfC = new JTextField();
        tfC.setBounds(125, 65, 100, 20);
        cp.add(tfC);
        JTextField tfF = new JTextField();
        tfF.setBounds(125, 115, 100, 20);
        cp.add(tfF);

        JButton convertB = new JButton("바꾸기");
        convertB.setBounds(255, 75, 75, 50);
        cp.add(convertB);
        convertB.addActionListener(e -> {
            if (tfC.getText().isEmpty())
                tfF.setText("");
            else {
                String amount = tfC.getText();
                double amountVal = Double.valueOf(amount);
                tfF.setText(String.format("%.2f", amountVal * 9 / 5 + 32));
            }
        });
    }
}
