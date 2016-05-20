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
     * record an record
     *
     * @param record
     * @throws java.io.FileNotFoundException
     */
    public void setData(Record record) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("records.txt", true);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(record.getCpf() + ";");
        bw.write(record.getId() + ";");
        bw.write(record.getAnamnese() + ";");
        bw.write(record.getDt() + ";");
        bw.write(record.getIdUser() + ";");
        bw.write("\n");
        bw.close();
    }

    /**
     * list all records
     *
     * @param cpfPatient
     * @return
     * @throws java.io.FileNotFoundException
     */
    public ArrayList<Record> readData(String cpfPatient) throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("records.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<Record> records = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            String[] params = line.split(";");
            String cpf = params[0];
            String id = params[1];
            String anamnese = params[2];
            String dt = params[3];
            String idUser = params[4];

            if (cpfPatient == null || cpf.equals(cpfPatient)) {
                Record record = new Record();
                record.setCpf(cpf);
                record.setId(id);
                record.setAnamnese(anamnese);
                record.setDt(dt);
                record.setIdUser(idUser);

                records.add(record);
            }

            line = reader.readLine();
        }
        return records;
    }

    /**
     * update an user
     *
     * @param user
     * @throws java.io.FileNotFoundException
     */
    public void updateData(Record user) throws FileNotFoundException, IOException, Exception {
        ArrayList<Record> users = readData(null);
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
                linha.setIdUser(user.getIdUser());
            }
            newLines.add(linha.getCpf() + ";"
                    + linha.getId() + ";"
                    + linha.getAnamnese() + ";"
                    + linha.getDt() + ";"
                    + linha.getIdUser() + ";\n");
        }
        try (FileOutputStream fileOut = new FileOutputStream("records.txt")) {
            for (String newLine : newLines) {
                fileOut.write(newLine.getBytes());
            }
            fileOut.close();
        }
    }

    public String getCpf() throws Exception {
        ArrayList<Record> cpfs = readData(null);
        int i = 1;
        for (Record users : cpfs) {
            int userCpf = Integer.parseInt(users.getCpf());
            if (userCpf > i) {
                i = userCpf + 1;
            }
        }
        return String.valueOf(i);
    }

}
