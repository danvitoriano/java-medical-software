/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paciente;

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
public class PersistenciaPaciente {

    public void gravarDados(Paciente paciente) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("pacientes.txt", true);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(paciente.getCPF() + ";");
        bw.write(paciente.getNomePaciente() + ";");
        bw.write(paciente.getSenhaPaciente() + ";");
        bw.write(paciente.getFlagAtivo() + ";");
        bw.write(paciente.getTipo() + ";");
        bw.write("\n");
        bw.close();
    }

    public ArrayList<Paciente> lerDados() throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("pacientes.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
        String line = reader.readLine();
        while (line != null) {
            String[] params = line.split(";");
            Paciente paciente = new Paciente();
            paciente.setCPF(params[0]);
            paciente.setNomePaciente(params[1]);
            paciente.setSenhaPaciente(params[2]);
            paciente.setFlagAtivo(params[3]);
            paciente.setTipo(params[4]);
            pacientes.add(paciente);
            line = reader.readLine();
        }
        return pacientes;
    }

    public void Atualizar(Paciente paciente) throws FileNotFoundException, IOException, Exception {
        ArrayList<Paciente> pacientes = lerDados();
        ArrayList<String> newLines = new ArrayList<>();
        Paciente linha = null;
        String buscar = paciente.getCPF();
        for (int i = 0; i < pacientes.size(); i++) {

            linha = pacientes.get(i);

            if (linha.getCPF().contains(buscar)) {
                linha.setCPF(paciente.getCPF());
                linha.setNomePaciente(paciente.getNomePaciente());
                linha.setSenhaPaciente(paciente.getSenhaPaciente());
                linha.setFlagAtivo(paciente.getFlagAtivo());
                linha.setTipo(paciente.getTipo());
            }
            newLines.add(linha.getCPF()+ ";"
                    + linha.getNomePaciente()+ ";"
                    + linha.getSenhaPaciente()+ ";"
                    + linha.getFlagAtivo()+ ";"
                    + linha.getTipo()+ ";\n");
        }
        try (FileOutputStream fileOut = new FileOutputStream("pacientes.txt")) {
            for (String newLine : newLines) {
                fileOut.write(newLine.getBytes());
            }
            fileOut.close();
        }
    }

}
