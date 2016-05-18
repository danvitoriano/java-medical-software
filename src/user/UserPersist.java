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
     * record an user
     *
     * @param user
     * @throws java.io.FileNotFoundException
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
     * list all users
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    public ArrayList<User> readData() throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("users.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<User> users = new ArrayList<User>();
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
     * update an user
     *
     * @param user
     * @throws java.io.FileNotFoundException
     */
    public void updateData(User user) throws FileNotFoundException, IOException, Exception {
        ArrayList<User> users = readData();
        ArrayList<String> newLines = new ArrayList<>();
        User linha = null;
        String buscar = user.getCpf();
        for (int i = 0; i < users.size(); i++) {

            linha = users.get(i);

            if (linha.getCpf().contains(buscar)) {
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

    
    

}
