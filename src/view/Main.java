package view;

import model.FileMM1;
import model.FileMM1K;
import model.FileMMS;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        FileMMS fileMMS = new FileMMS(10, 12, 2);
        System.out.println("q0:" + fileMMS.q0());
        System.out.println(fileMMS.L());
        System.out.println(fileMMS.Lq());
        System.out.println(fileMMS.W());
        System.out.println(fileMMS.Wq());
        System.out.println(fileMMS.qJ(1));
        System.out.println(fileMMS.qJ(2));
        System.out.println(fileMMS.qJ(3));
        System.out.println(fileMMS.probaTempsSejour((double) 1/6));

        /*
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button = new JButton("Press");
        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);*/
    }
}
