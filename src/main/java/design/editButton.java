package design;
import java.awt.event.ActionListener;
import javax.swing.*;


import exceptions.*;

import java.awt.event.ActionEvent;
import java.awt.*;

import team.*;
/**
 * Редактирование информации об игроке
 */
public class editButton implements ActionListener, IRoles {
    private JFrame owner;
    private Team theBest;
    private MyModel model;
    /**
     * 
     * @param owner Предыдущий фрейм
     * @param theBest Команда
     * @param model Отображаемая таблица
     */
    editButton(JFrame owner, Team theBest, MyModel model){
        this.owner = owner;
        this.theBest = theBest;
        this.model = model;
    }
    public void actionPerformed(ActionEvent e){
        JDialog editSmallBox = new JDialog(owner, "Изменить информацию", true);
        JTextField smallField = new JTextField(4);
        smallField.setHorizontalAlignment(JTextField.RIGHT);
        JPanel content = new JPanel();
        JLabel text = new JLabel("Введите ID игрока: ");
        //сначала найти игрока, потом вывести о нем информацию 
        //затем с помощью сеттеров поменять инфу
        smallField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    Footballer boy = theBest.find(Integer.parseInt(smallField.getText()));
                    editSmallBox.dispose();
                    JPanel commonPanel = new JPanel();

                    JDialog editBigBox = new JDialog(owner, "Правки информации", true);
                    JLabel boyID = new JLabel("ID: "+boy.getID());
                    JLabel boyName = new JLabel("Имя:");
                    JLabel boyLastName = new JLabel("Фамилия:");
                    JLabel boyRole = new JLabel("Специализация:");
                    JLabel boyClub = new JLabel("Клуб:");
                    JLabel boyCity = new JLabel("Город:");
                    JLabel boyGoals = new JLabel("Кол-во голов:");
                    JLabel boySalary = new JLabel("Зарплата:");

                    JTextField newName = new JTextField(boy.getName());
                    JTextField newLastName = new JTextField(boy.getLastName());
                    //Role ComboBox
                    int first, second, third;
                    if (boy.getRole() == GOALKEEPER) {
                        first = 1;
                        second = 2;
                        third = 3;
                    }
                    else if (boy.getRole() == STRIKER){
                        first = 0;
                        second = 2;
                        third = 3;
                    }
                    else if (boy.getRole() == HALFBACK){
                        first = 0;
                        second = 1;
                        third = 3;
                    }
                    else{
                        first = 0;
                        second = 1;
                        third = 2;
                    }
                    JComboBox<String> newRole = new JComboBox<String>(new String[]{
                        roles[boy.getRole()], roles[first], roles[second], roles[third]
                    });
                    JTextField newClub = new JTextField(boy.getClub());
                    JTextField newCity = new JTextField(boy.getCity());
                    JTextField newGoals = new JTextField(Integer.toString(boy.getGoals()));
                    JTextField newSalary = new JTextField(Integer.toString(boy.getSalary()));

                    newName.setPreferredSize(new Dimension(100, 20));
                    newLastName.setPreferredSize(new Dimension(100, 20));
                    newClub.setPreferredSize(new Dimension(100, 20));
                    newCity.setPreferredSize(new Dimension(100, 20));
                    newGoals.setPreferredSize(new Dimension(100, 20));
                    newSalary.setPreferredSize(new Dimension(100, 20));

                    JPanel IDPanel = new JPanel();
                    JPanel namePanel = new JPanel();
                    JPanel lastNamePanel = new JPanel();
                    JPanel rolePanel = new JPanel();
                    JPanel clubPanel = new JPanel();
                    JPanel cityPanel = new JPanel();
                    JPanel goalsPanel = new JPanel();
                    JPanel salaryPanel = new JPanel();
                    JPanel addButtonPanel = new JPanel();

                    JButton changePlayer = new JButton("Изменить");

                    IDPanel.add(boyID);
                    namePanel.add(boyName);
                    namePanel.add(newName);
                    lastNamePanel.add(boyLastName);
                    lastNamePanel.add(newLastName);
                    rolePanel.add(boyRole);
                    rolePanel.add(newRole);
                    clubPanel.add(boyClub);
                    clubPanel.add(newClub);
                    cityPanel.add(boyCity);
                    cityPanel.add(newCity);
                    goalsPanel.add(boyGoals);
                    goalsPanel.add(newGoals);
                    salaryPanel.add(boySalary);
                    salaryPanel.add(newSalary);
                    addButtonPanel.add(changePlayer);

                    commonPanel.setLayout(new BoxLayout(commonPanel, BoxLayout.Y_AXIS));
                    commonPanel.add(IDPanel, BorderLayout.CENTER);
                    commonPanel.add(namePanel, BorderLayout.AFTER_LAST_LINE);
                    commonPanel.add(lastNamePanel);
                    commonPanel.add(rolePanel);
                    commonPanel.add(clubPanel);
                    commonPanel.add(cityPanel);
                    commonPanel.add(goalsPanel);
                    commonPanel.add(salaryPanel);
                    commonPanel.add(addButtonPanel);

                    changePlayer.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            String neededRole = (String)newRole.getSelectedItem();
                            int neededRoleInt;
                            if (neededRole.equals(roles[0])) neededRoleInt = 0;
                            else if (neededRole.equals(roles[1])) neededRoleInt = 1;
                            else if (neededRole.equals(roles[2])) neededRoleInt = 2;
                            else neededRoleInt = 3;

                            try{
                                boy.setName(newName.getText());
                                boy.setLastName(newLastName.getText());
                                boy.setRole(neededRoleInt);
                                boy.setClub(newClub.getText());
                                boy.setCity(newCity.getText());
                                boy.setGoals(Integer.parseInt(newGoals.getText()));
                                boy.setSalary(Integer.parseInt(newSalary.getText()));
                                boy.isAllRight();
                                JOptionPane.showMessageDialog(editBigBox, "Информация изменена", "", 
                                JOptionPane.INFORMATION_MESSAGE);
                                editBigBox.dispose();
                                while (model.getRowCount()>0) model.removeRow(0);
                                model.showTable(theBest);
                            }
                            catch(NumberFormatException exNum){
                                JOptionPane.showMessageDialog(editBigBox, "В одном из чисел\nобнаружены буквы", "", 
                                JOptionPane.ERROR_MESSAGE);
                            }
                            catch(IllegalArgumentException exCity){
                                JOptionPane.showMessageDialog(editBigBox, exCity.getMessage(), "", 
                                JOptionPane.ERROR_MESSAGE);
                            }
                            catch(ArithmeticException exClub){
                                JOptionPane.showMessageDialog(editBigBox, exClub.getMessage(), "", 
                                JOptionPane.ERROR_MESSAGE);
                            }
                            catch(WrongNameException exName){
                                JOptionPane.showMessageDialog(editBigBox, exName.getMessage(), "", 
                                JOptionPane.ERROR_MESSAGE);
                            }
                            catch(WrongLastNameException exLName){
                                JOptionPane.showMessageDialog(editBigBox, exLName.getMessage(), "", 
                                JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    });
                    
                    editBigBox.setContentPane(commonPanel);
                    editBigBox.setSize(240, 320);
                    editBigBox.setResizable(false);
                    editBigBox.setLocationRelativeTo(null);
                    editBigBox.setVisible(true);
                }
                catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(editSmallBox, "Нет такого игрока", "", 
                    JOptionPane.ERROR_MESSAGE);
                }
                catch(NumberFormatException exNum){
                    JOptionPane.showMessageDialog(editSmallBox, "Некорректные данные", "", 
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        content.add(text, BorderLayout.NORTH);
        content.add(smallField);
        editSmallBox.setContentPane(content);
        editSmallBox.setSize(300, 130);
        editSmallBox.setResizable(false);
        editSmallBox.setLocationRelativeTo(null);;
        editSmallBox.setVisible(true);

    }
}
