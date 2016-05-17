/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paciente;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author sgrand
 */
public class ConsultarPacientes {
    private static ConsultarPacientes instanciaRep;
    public static ConsultarPacientes obterInstancia(){
        if(instanciaRep == null){
            instanciaRep = new ConsultarPacientes();
        }
        return instanciaRep;
    }
    
    public ArrayList<Paciente> lerDados() throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("pacientes.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<Paciente> usuarios = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            String[] params = line.split(";");
            Paciente usuario = new Paciente();
            usuario.setCPF(params[0]);
            usuario.setNomeUsuario(params[1]);
            usuario.setFlagAtivo(params[2]);
            usuario.setDataDeNascimento(params[3]);
            usuario.setSexo(params[4]);
            usuario.setEndereco(params[5]);
            usuarios.add(usuario);
            line = reader.readLine();
        }
        return usuarios;
    }
    
}
