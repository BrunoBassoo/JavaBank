package ControllerGerente;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import DB.conexao_banco;
import views.CadastrarNovoCliente;
import DB.DB_Cliente;
/**
 *
 * @author uniebrunosilva
 */
public class ControllerCadastroCliente {
    private CadastrarNovoCliente view;

    public ControllerCadastroCliente(CadastrarNovoCliente view) {
        this.view = view;
    }
    
    // salvar novo cliente
    public void salvarNovoCliente(){
        //pegando os valores que a pessoa colocou nos campos
        String nome = view.getEntrada_nome().getText();
        String senha = view.getEntrada_senha().getText();
        String CPF = view.getEntrada_cpf().getText();
        if(nome.equals("") && senha.equals("") && CPF.equals("")){
            JOptionPane.showMessageDialog(view,"SELECIONE OS CAMPOS CORRETAMENTE!", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else{
            // pessando os valores para o objeto Cliente
            Cliente cliente = new Cliente(nome,senha,CPF);
            String cliente_cpf = cliente.getCpf();
            conexao_banco conexao = new conexao_banco();
            try{
                Connection conn = conexao.getConnection();
                DB_Cliente db = new DB_Cliente(conn);
                try{
                    db.inserir(cliente);
                }catch(SQLException e){
                    e.printStackTrace();
                }
            } catch(SQLException e){
                JOptionPane.showMessageDialog(view, "Erro de conexão, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}

