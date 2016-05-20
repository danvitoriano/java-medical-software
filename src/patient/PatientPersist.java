/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;

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
public class PatientPersist {

    /**
     * patient an user
     *
     * @param user
     * @throws java.io.FileNotFoundException
     */
    public void setData(Patient user) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("patients.txt", true);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(user.getCpf() + ";");
        bw.write(user.getName() + ";");
        bw.write(user.getActive() + ";");
        bw.write("\n");
        bw.close();
    }

    /**
     * list all users
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    public ArrayList<Patient> readData() throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("patients.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<Patient> users = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            String[] params = line.split(";");
            Patient user = new Patient();
            user.setCpf(params[0]);
            user.setName(params[1]);
            user.setActive(params[2]);
            users.add(user);
            line = reader.readLine();
        }
        return users;
    }
    
    public ArrayList<Patient> readData(String cpfPatient) throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("patients.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<Patient> records = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            String[] params = line.split(";");
            String cpf = params[0];
            String name = params[1];
            String active = params[2];
            
            if(cpfPatient ==  null || cpf.equals(cpfPatient)){
                Patient patient = new Patient();
                patient.setCpf(cpf);
                patient.setName(name);
                patient.setActive(active);
                
                records.add(patient);
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
    public void updateData(Patient user) throws FileNotFoundException, IOException, Exception {
        ArrayList<Patient> users = readData();
        ArrayList<String> newLines = new ArrayList<>();
        Patient linha;
        String searchCpf = user.getCpf();
        for (int i = 0; i < users.size(); i++) {

            linha = users.get(i);

            if (linha.getCpf().contains(searchCpf)) {
                linha.setCpf(user.getCpf());
                linha.setName(user.getName());
                linha.setActive(user.getActive());
            }
            newLines.add(linha.getCpf() + ";"
                    + linha.getName() + ";"
                    + linha.getActive() + ";\n");
        }
        try (FileOutputStream fileOut = new FileOutputStream("patients.txt")) {
            for (String newLine : newLines) {
                fileOut.write(newLine.getBytes());
            }
            fileOut.close();
        }
    }
    
    public String getCpf() throws Exception{
        ArrayList<Patient> cpfs = readData();
        int i = 1;
        for (Patient users : cpfs) {
            int userCpf = Integer.parseInt(users.getCpf());
            if (userCpf>i) {
                i = userCpf + 1;
            }
        }
        return String.valueOf(i);
    }

}
