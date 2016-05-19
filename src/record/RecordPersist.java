/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package record;

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
public class RecordPersist {

    /**
     * record an user
     *
     * @param user
     * @throws java.io.FileNotFoundException
     */
    public void setData(Record user) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("records.txt", true);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(user.getCpf() + ";");
        bw.write(user.getId() + ";");
        bw.write(user.getAnamnese() + ";");
        bw.write(user.getDt() + ";");
        bw.write("\n");
        bw.close();
    }

    /**
     * list all users
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    public ArrayList<Record> readData() throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("records.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<Record> users = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            String[] params = line.split(";");
            Record user = new Record();
            user.setCpf(params[0]);
            user.setId(params[1]);
            user.setAnamnese(params[2]);
            user.setDt(params[3]);
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
    public void updateData(Record user) throws FileNotFoundException, IOException, Exception {
        ArrayList<Record> users = readData();
        ArrayList<String> newLines = new ArrayList<>();
        Record linha;
        String searchCpf = user.getCpf();
        for (int i = 0; i < users.size(); i++) {

            linha = users.get(i);

            if (linha.getCpf().contains(searchCpf)) {
                linha.setCpf(user.getCpf());
                linha.setId(user.getId());
                linha.setAnamnese(user.getAnamnese());
                linha.setDt(user.getDt());
            }
            newLines.add(linha.getCpf() + ";"
                    + linha.getId() + ";"
                    + linha.getAnamnese() + ";"
                    + linha.getDt() + ";\n");
        }
        try (FileOutputStream fileOut = new FileOutputStream("records.txt")) {
            for (String newLine : newLines) {
                fileOut.write(newLine.getBytes());
            }
            fileOut.close();
        }
    }
    
    public String getCpf() throws Exception{
        ArrayList<Record> cpfs = readData();
        int i = 1;
        for (Record users : cpfs) {
            int userCpf = Integer.parseInt(users.getCpf());
            if (userCpf>i) {
                i = userCpf + 1;
            }
        }
        return String.valueOf(i);
    }

}
