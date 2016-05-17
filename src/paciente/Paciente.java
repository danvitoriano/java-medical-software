/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paciente;

import usuario.Usuario;

/**
 *
 * @author sgrand
 */
public class Paciente extends Usuario {
    private String endereco, sexo, dataDeNascimento;

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the nomeUsuario to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
