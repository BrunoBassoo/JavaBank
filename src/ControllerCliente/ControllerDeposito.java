
package ControllerCliente;

import DB.DB_Cliente;
import DB.conexao_banco;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import views.Deposito;

/**
 *
 * @author T-Gamer
 */
public class ControllerDeposito { 
    private Deposito view;

    public ControllerDeposito(Deposito view) {
        this.view = view;
    }
    public void depositar(){
        String CPF = view.getTxtCpfCliente().getText();
        String senha = view.getTxtSenhaCliente().getText();
        double valor_deposito = Double.parseDouble(view.getEntrada_valor().getText());

        Cliente cliente = new Cliente(CPF,senha);
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            ResultSet res = db.consultarCliente(cliente);
            if(res.next()){
                cliente.setSaldo(res.getDouble("saldo"));
                db.deposito(cliente, valor_deposito);
            }
        } catch( SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }
}

