/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import static java.nio.file.Files.lines;
import java.util.ArrayList;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.lines;
import static java.nio.file.Files.lines;

/**
 *
 * @author dvitoriano
 */
public class PersistenciaUsuario {

    public void gravarDados(Usuario usuario) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("usuarios.txt", true);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(usuario.getCPF() + ";");
        bw.write(usuario.getNomeUsuario() + ";");
        bw.write(usuario.getSenhaUsuario() + ";");
        bw.write(usuario.getFlagAtivo() + ";");
        bw.write("\n");
        bw.close();
    }

    public ArrayList<Usuario> lerDados() throws FileNotFoundException, IOException, Exception {
        InputStream input = new FileInputStream("usuarios.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String line = reader.readLine();
        while (line != null) {
            String[] params = line.split(";");
            Usuario usuario = new Usuario();
            usuario.setCPF(params[0]);
            usuario.setNomeUsuario(params[1]);
            usuario.setSenhaUsuario(params[2]);
            usuario.setFlagAtivo(params[3]);
            usuarios.add(usuario);
            line = reader.readLine();
        }
        return usuarios;
    }

    public void Atualizar(Usuario usuario) throws FileNotFoundException, IOException, Exception {
        ArrayList<Usuario> usuarios = lerDados();
        ArrayList<String> newLines = new ArrayList<>();
        Usuario linha = null;
        String buscar = usuario.getCPF();
        for (int i = 0; i < usuarios.size(); i++) {

            linha = usuarios.get(i);

            if (linha.getCPF().contains(buscar)) {
                linha.setCPF(usuario.getCPF());
                linha.setNomeUsuario(usuario.getNomeUsuario());
                linha.setSenhaUsuario(usuario.getSenhaUsuario());
                linha.setFlagAtivo(usuario.getFlagAtivo());
            }
            newLines.add(linha.getCPF()+ ";"
                    + linha.getNomeUsuario()+ ";"
                    + linha.getSenhaUsuario()+ ";"
                    + linha.getFlagAtivo()+ ";\n");
        }
        try (FileOutputStream fileOut = new FileOutputStream("usuarios.txt")) {
            for (String newLine : newLines) {
                fileOut.write(newLine.getBytes());
            }
            fileOut.close();
        }
    }

}
