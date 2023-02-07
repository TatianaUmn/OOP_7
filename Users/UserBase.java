package Users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserBase {
    ArrayList<User> recordUsers = new ArrayList<>();

    public ArrayList<User> getRecordusers() {
        return recordUsers;
    }

    public void setRecordusers(ArrayList<User> recordUsers) {
        this.recordUsers = recordUsers;
    }

    public User creatUser(){
        Scanner in=new Scanner(System.in);
        User user=new User();
        System.out.println("������� ���: ");
        user.setName(in.nextLine());
        System.out.println("������� ������");
        user.setBalance(in.nextInt());
        System.out.println("�������� ����:\n������������� - 1\n������������ - ������");
        if ((in.nextInt()) == 1) {
            user.setRole(Role.admin);
        } else {
            user.setRole(Role.user);
        }
        return user;
    }

    public void fillRecord(ArrayList<User> record) {
        boolean flag = true;
        while (flag) {
            System.out.println("�� ������ �������� ������ ��������? 1 - ��, 2 - ���");
            Scanner in = new Scanner(System.in);
            int a = in.nextInt();
            if (a == 1) {
                record.add(creatUser());
            } else if (a == 2) {
                System.out.println("���� ����� ���������");
                flag = false;
            } else {
                System.out.println("������� 1 - ��  ��� 2 - ���");
            }
        }
        setRecordusers(record);
    }

    public void outputToTxtFile(String filename, ArrayList<User> record) throws IOException {
        filename = filename + ".txt";
        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (User x : record) {
                fileWriter.write(x.toString());
                fileWriter.write("\n");
            }
            fileWriter.flush();
        }
    }

    public ArrayList<User> inputFile(String filename) throws IOException {
        String[] parsing;
        ArrayList<User> output = new ArrayList<>();
        java.io.File file = new File(filename + ".txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            parsing = in.nextLine().split(",");
            User user = new User();
            user.setName(parsing[0]);
            user.setRole(Role.valueOf(parsing[1]));
            user.setBalance(Integer.parseInt(parsing[2]));
            user.setFilms(Integer.parseInt(parsing[3]));
            output.add(user);
        }
        return output;
    }

    public User Worker(ArrayList<User> pb) {
        String a;
        System.out.println("������� ���� ���");
        Scanner in = new Scanner(System.in);
        a = in.nextLine();
        for (User person : pb) {
            if (person.getName().contains(a)) {
                return person;
            }
        }
        return null;
    }
}
