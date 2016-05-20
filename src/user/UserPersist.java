/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author dvitoriano
 */
public class UserPersist {

    /**
     * Record User
     *
     * @param user pass user to be recorded
     * @throws java.io.FileNotFoundException File Not Found
     */
    public void setData(User user) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("users.txt", true);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(user.getCpf() + ";");
        bw.write(user.getName() + ";");
        bw.write(user.getPwd() + ";");
        bw.write(user.getActive() + ";");
        bw.write(user.getProfile() + ";");
        bw.write("\n");
        bw.close();
    }

    /**
     * Return a List of Users
     *
     * @return return a list of User
     * @throws java.io.FileNotFoundException File Not Found
     */
    public ArrayList<User> readData() throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("users.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<User> users = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            String[] params = line.split(";");
            User user = new User();
            user.setCpf(params[0]);
            user.setName(params[1]);
            user.setPwd(params[2]);
            user.setActive(params[3]);
            user.setProfile(params[4]);
            users.add(user);
            line = reader.readLine();
        }
        return users;
    }

    /**
     * Update Exist User
     *
     * @param user pass user to be updated
     * @throws java.io.FileNotFoundException File Not Found
     */
    public void updateData(User user) throws FileNotFoundException, IOException, Exception {
        ArrayList<User> users = readData();
        ArrayList<String> newLines = new ArrayList<>();
        User linha;
        String searchCpf = user.getCpf();
        for (int i = 0; i < users.size(); i++) {

            linha = users.get(i);

            if (linha.getCpf().contains(searchCpf)) {
                linha.setCpf(user.getCpf());
                linha.setName(user.getName());
                linha.setPwd(user.getPwd());
                linha.setActive(user.getActive());
                linha.setProfile(user.getProfile());
            }
            newLines.add(linha.getCpf() + ";"
                    + linha.getName() + ";"
                    + linha.getPwd() + ";"
                    + linha.getActive() + ";"
                    + linha.getProfile() + ";\n");
        }
        try (FileOutputStream fileOut = new FileOutputStream("users.txt")) {
            for (String newLine : newLines) {
                fileOut.write(newLine.getBytes());
            }
            fileOut.close();
        }
    }
    
    /**
     * Return User Row by CPF
     *
     * @return return user row position
     * @throws java.lang.Exception User Exception
     */
    public String getCpf() throws Exception{
        ArrayList<User> cpfs = readData();
        int i = 1;
        for (User users : cpfs) {
            int userCpf = Integer.parseInt(users.getCpf());
            if (userCpf>i) {
                i = userCpf + 1;
            }
        }
        return String.valueOf(i);
    }

}
