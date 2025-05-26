package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window=new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("dung dap chai nhi?");

        Gamepanel gamepanel = new Gamepanel();
        window.add(gamepanel);

        window.pack();

        window.setVisible(true);
        window.setLocationRelativeTo(null);


        gamepanel.setUpGame();

        gamepanel.startGameThread();


    }
}
