//package com.labor.View;
//
//import com.labor.Controller.RegistrationSystem;
//
//import java.util.Scanner;
//
//public class View {
//    private RegistrationSystem regSys;
//
//
//    public View(RegistrationSystem regSys){
//        this.regSys = regSys;
//    }
//
//    public void welcomeMessage(){
//        System.out.print("\n The program has started. Please select an option from below: \n");
//    }
//
//    public void showOptions(){
//        System.out.print("1. read data form file\n");
//        System.out.print("2. enroll students\n");
//        System.out.print("3. modify data\n");
//        System.out.print("4. save data\n");
//        System.out.print("5. show data\n");
//        System.out.print("5. exit");
//    }
//
//    public void readData(){
//
//    }
//
//    public void run(){
//        int option = 0;
//        welcomeMessage();
//
//        do{
//            showOptions();
//            System.out.println("Enter option number: ");
//            Scanner scanner = new Scanner(System.in);
//            option = scanner.nextInt();
//
//            switch (option){
//                case 1:
//                    readData();
//                    break;
//                case 2:
//                    enrollStudents();
//                    break;
//                case 3:
//                    modifyData;
//                    break;
//                case 4:
//                    saveData();
//                    break;
//                case 5:
//                    exitProgram();
//                    break;
//                default:
//                    option = 0;     //exit
//            }
//        }
//        while (option != 0);
//    }
//}
