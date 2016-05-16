/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paciente;

import java.util.ArrayList;

/**
 *
 * @author dvitoriano
 */
public class RepositorioPaciente {
    private ArrayList<Paciente> lista;
    private static RepositorioPaciente instanciaRep;
PersistenciaPaciente persistenciaPaciente = new PersistenciaPaciente();
    
    public static RepositorioPaciente obterInstancia(){
        if(instanciaRep == null){
            instanciaRep = new RepositorioPaciente();
        }
        return instanciaRep;
    }
    
    public ArrayList<Paciente> listarTodos() throws Exception{
        this.lista = persistenciaPaciente.lerDados();
        return this.lista;
    }
    
    public void inserir(Paciente paciente) throws Exception{
        if (paciente == null){
            throw new Exception("Paciente não existe");
        }
        if (paciente.getCPF() == null){
            throw new Exception("Informe o CPF");
        }
        if (paciente.getCPF().trim().equals("")){
            throw new Exception("Informe o CPF");
        }
        if (paciente.getNomePaciente() == null){
            throw new Exception("Informe o nome");
        }
        if (paciente.getNomePaciente().trim().equals("")){
            throw new Exception("Informe o nome");
        }
        if (paciente.getSenhaPaciente() == null){
            throw new Exception("Informe a senha");
        }
        if (paciente.getSenhaPaciente().trim().equals("")){
            throw new Exception("Informe a senha");
        }
        if (paciente.getFlagAtivo() == null) {
            throw new Exception("Ativo?");
        }
        if (paciente.getFlagAtivo().trim().equals("")) {
            throw new Exception("Entre com 0 ou 1");
        }
        if (paciente.getTipo() == null) {
            throw new Exception("Tipo");
        }
        if (paciente.getTipo().trim().equals("")) {
            throw new Exception("c");
        }
        if (this.verificaExistencia(paciente) >= 0){
            throw new Exception("Paciente já cadastrado");
        }
        persistenciaPaciente.gravarDados(paciente);
    }
        
    public void atualizar(Paciente paciente) throws Exception{
        if (paciente == null){
            throw new Exception("Paciente não existe");
        }
        if (paciente.getCPF() == null){
            throw new Exception("Informe o CPF");
        }
        if (paciente.getCPF().trim().equals("")){
            throw new Exception("Informe o CPF");
        }
        if (paciente.getNomePaciente() == null){
            throw new Exception("Informe o nome");
        }
        if (paciente.getNomePaciente().trim().equals("")){
            throw new Exception("Informe o nome");
        }
        if (paciente.getSenhaPaciente() == null){
            throw new Exception("Informe a senha");
        }
        if (paciente.getSenhaPaciente().trim().equals("")){
            throw new Exception("Informe a senha");
        }
        if (paciente.getFlagAtivo() == null) {
            throw new Exception("Ativo?");
        }
        if (paciente.getFlagAtivo().trim().equals("")) {
            throw new Exception("Entre com 0 ou 1");
        }
        if (paciente.getTipo() == null) {
            throw new Exception("Tipo");
        }
        if (paciente.getTipo().trim().equals("")) {
            throw new Exception("informe o tipo");
        }
        if (this.verificaExistencia(paciente) == -1){
            throw new Exception("Paciente não cadastrado");
        }
        persistenciaPaciente.Atualizar(paciente);
        
        //this.lista.set(this.verificaExistencia(paciente), paciente);   
   }
    
    public int verificaExistencia(Paciente paciente){
        int retorno = -1;
        for (int i = 0; i < this.lista.size(); i++){
            if (paciente.getCPF().trim().equals(this.lista.get(i).getCPF().trim())){
                retorno = i;
                break;
            }
        }
        return retorno;
    }
    
    
}
