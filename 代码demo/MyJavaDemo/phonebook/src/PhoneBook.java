import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Phone {
    private String name;
    private String number;

    public Phone(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return name + "," + number;
    }
}

public class PhoneBook extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String PHONE_FILE_NAME = "C:\\Users\\zhou\\Desktop\\学校\\phone.txt";
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel numberLabel;
    private JTextField numberField;
    private JButton addButton;
    private JButton showButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton editButton;
    private JTextArea phoneListArea;

    public PhoneBook() {
        super("电话号码管理系统");
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        nameLabel = new JLabel("姓名：");
        inputPanel.add(nameLabel);

        nameField = new JTextField(20);
        inputPanel.add(nameField);

        numberLabel = new JLabel("电话号码：");
        inputPanel.add(numberLabel);

        numberField = new JTextField(20);
        inputPanel.add(numberField);
        addButton = new JButton("添加");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String number = numberField.getText();
                Phone phone = new Phone(name, number);
                addPhone(phone);
                nameField.setText("");
                numberField.setText("");
            }
        });
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        showButton = new JButton("显示所有电话号码");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPhoneList();
            }
        });
        buttonPanel.add(showButton);

        searchButton = new JButton("查询电话号码");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("请输入要查询的姓名：");
                Phone phone = searchPhone(name);
                if (phone != null) {
                    JOptionPane.showMessageDialog(null, phone.getNumber());
                } else {
                    JOptionPane.showMessageDialog(null, "找不到该姓名对应的电话号码");
                }
            }
        });
        buttonPanel.add(searchButton);

        deleteButton = new JButton("删除电话号码");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("请输入要删除的姓名：");
                deletePhone(name);
            }
        });
        buttonPanel.add(deleteButton);

        editButton = new JButton("修改电话号码");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name= JOptionPane.showInputDialog("请输入要修改的姓名：");
                Phone phone = searchPhone(name);
                if (phone != null) {
                    String newNumber = JOptionPane.showInputDialog("请输入新的电话号码：");
                    phoneListArea.setText("");
                    editPhone(name, newNumber);
                    showPhoneList();
                } else {
                    JOptionPane.showMessageDialog(null, "找不到该姓名对应的电话号码");
                }
            }
        });
        buttonPanel.add(editButton);

        add(buttonPanel, BorderLayout.CENTER);

        phoneListArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(phoneListArea);
        add(scrollPane, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        showPhoneList();
    }

    private void addPhone(Phone phone) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PHONE_FILE_NAME, true));
            writer.write(phone.toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showPhoneList();
    }
        //显示电话
    private void showPhoneList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PHONE_FILE_NAME));
            phoneListArea.setText("");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                phoneListArea.append(fields[0] + ": " + fields[1] + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        //判断电话拨有没有
    private Phone searchPhone(String name) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PHONE_FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(name)) {
                    Phone phone = new Phone(fields[0], fields[1]);
                    reader.close();
                    return phone;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //删除电话
    private void deletePhone(String name) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PHONE_FILE_NAME));
            ArrayList<String> phoneList = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (!fields[0].equals(name)) {
                    phoneList.add(line);
                }
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(PHONE_FILE_NAME, false));
            for (String phone : phoneList) {
                writer.write(phone);
                writer.newLine();
            }
            writer.close();

            showPhoneList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //修改电话
    private void editPhone(String name, String newNumber) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PHONE_FILE_NAME));
            ArrayList<String> phoneList = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(name)) {
                    Phone phone = new Phone(name, newNumber);
                    phoneList.add(phone.toString());
                } else {
                    phoneList.add(line);
                }
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(PHONE_FILE_NAME, false));
            for (String phone : phoneList) {
                writer.write(phone);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PhoneBook();
    }

    class Phone {
        private String name;
        private String number;

        public Phone(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }

        public String toString() {
            return name + "," + number;
        }
    }
}