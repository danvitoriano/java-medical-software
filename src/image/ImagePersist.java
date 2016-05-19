/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

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
public class ImagePersist {

    /**
     * record an user
     *
     * @param user
     * @throws java.io.FileNotFoundException
     */
    public void setData(Image user) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("images.txt", true);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(user.getIdImage() + ";");
        bw.write(user.getIdRecord() + ";");
        bw.write(user.getUrl() + ";");
        bw.write("\n");
        bw.close();
    }

    /**
     * list all users
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    public ArrayList<Image> readData() throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("images.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<Image> users = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            String[] params = line.split(";");
            Image user = new Image();
            user.setIdImage(params[0]);
            user.setIdRecord(params[1]);
            user.setUrl(params[2]);
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
    public void updateData(Image user) throws FileNotFoundException, IOException, Exception {
        ArrayList<Image> users = readData();
        ArrayList<String> newLines = new ArrayList<>();
        Image linha;
        String searchCpf = user.getIdImage();
        for (int i = 0; i < users.size(); i++) {

            linha = users.get(i);

            if (linha.getIdImage().contains(searchCpf)) {
                linha.setIdImage(user.getIdImage());
                linha.setIdRecord(user.getIdRecord());
                linha.setUrl(user.getUrl());
            }
            newLines.add(linha.getIdImage() + ";"
                    + linha.getIdRecord() + ";"
                    + linha.getUrl() + ";\n");
        }
        try (FileOutputStream fileOut = new FileOutputStream("images.txt")) {
            for (String newLine : newLines) {
                fileOut.write(newLine.getBytes());
            }
            fileOut.close();
        }
    }
    
    public String getCpf() throws Exception{
        ArrayList<Image> cpfs = readData();
        int i = 1;
        for (Image users : cpfs) {
            int userCpf = Integer.parseInt(users.getIdImage());
            if (userCpf>i) {
                i = userCpf + 1;
            }
        }
        return String.valueOf(i);
    }

}
