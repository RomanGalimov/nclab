package com.netcracker.unc.view;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.netcracker.unc.model.Student;
import com.netcracker.unc.controller.Controller;
import com.netcracker.unc.rmi.Client;
import com.netcracker.unc.rmi.RemoteController;

public class EditStudentFrame extends JDialog implements ActionListener{

    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";

    private static final int PAD = 10;
    private static final int W_L = 100;
    private static final int W_T = 300;
    private static final int W_B = 120;
    private static final int H_B = 25;

    private final JTextPane txtFaculty = new JTextPane();
    private final JTextPane txtNOG = new JTextPane();
    private final JTextPane txtName = new JTextPane();
    private final JTextPane txtDOE = new JTextPane();

    private boolean save = false;

    public EditStudentFrame(){
        this(null);
    }

    public EditStudentFrame(Student student){
        setLayout(null);
        buildFields();
        initFields(student);
        buildButtons();
        setModal(true);
        setResizable(false);
        setBounds(300, 300, 450, 200);
        setVisible(true);
    }

    private void buildFields() {
        JLabel lbFaculty = new JLabel("Факультет:");
        lbFaculty.setHorizontalAlignment(SwingConstants.RIGHT);
        lbFaculty.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        add(lbFaculty);
        txtFaculty.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        txtFaculty.setBorder(BorderFactory.createEtchedBorder());
        add(txtFaculty);

        JLabel lbNOG = new JLabel("Группа:");
        lbNOG.setHorizontalAlignment(SwingConstants.RIGHT);
        lbNOG.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lbNOG);
        txtNOG.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtNOG.setBorder(BorderFactory.createEtchedBorder());
        add(txtNOG);

        JLabel lbName = new JLabel("Имя:");
        lbName.setHorizontalAlignment(SwingConstants.RIGHT);
        lbName.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lbName);
        txtName.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtName.setBorder(BorderFactory.createEtchedBorder());
        add(txtName);

        JLabel lbDOE = new JLabel("Дата зачисления:");
        lbDOE.setHorizontalAlignment(SwingConstants.RIGHT);
        lbDOE.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lbDOE);
        txtDOE.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtDOE.setBorder(BorderFactory.createEtchedBorder());
        add(txtDOE);
    }

    private void initFields(Student student) {
        if (student != null) {
            txtFaculty.setText(student.getGroup().getFaculty());
            txtNOG.setText(String.valueOf(student.getGroup().getNumberOfGroup()));
            txtName.setText(student.getNameOfStudent());
            txtDOE.setText(student.getDateOfEnrollment());
        }
    }

    private void buildButtons() {
        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        save = SAVE.equals(action);
        setVisible(false);
    }

    public boolean isSave() {
        return save;
    }

    public void createStudent(RemoteController control)  {
        try {
            control.createStudent(txtName.getText(), Integer.parseInt(txtNOG.getText()), txtFaculty.getText(), txtDOE.getText());
        } catch (Exception iae){
            JOptionPane.showMessageDialog(null,iae.getMessage(),"Inane error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
