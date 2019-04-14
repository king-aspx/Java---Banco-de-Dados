package com.kingaspx.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Banco {

    ConectaBanco conecta = new ConectaBanco();

    public void inserir(String usuario, String senha) {
        conecta.conexao(); // abrimos a conexão
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO login (usuario, senha) VALUES (?, ?)");
            pst.setString(1, usuario);
            pst.setString(1, senha);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar dados. Tente Novamente!");
            e.printStackTrace();
        } finally { //independente se der certo ou errado, ele fecha a conexão
            conecta.desconecta();
//          fechamos porquê várias instâncias aberta de uma conexão, pode deixar o seu sistema lento
//            fora que há um limite de conexões abertas no banco, então sempre que abrir, feche!
        }
    }

    public void editar(String usuario, String senha, int id) {
        conecta.conexao(); // abrimos a conexão
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE login SET usuario= ?, senha= ? WHERE id=? ");
            pst.setString(1, usuario);
            pst.setString(2, senha);
            pst.setInt(3, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados. Tente Novamente!");
            e.printStackTrace();
        } finally { //independente se der certo ou errado, ele fecha a conexão
            conecta.desconecta();
//          fechamos porquê várias instâncias aberta de uma conexão, pode deixar o seu sistema lento
//            fora que há um limite de conexões abertas no banco, então sempre que abrir, feche!
        }
    }

    public void deletar(int id) {
        conecta.conexao(); // abrimos a conexão
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("DELETE FROM login WHERE id= '" + id + "'");
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados. Tente Novamente!");
            e.printStackTrace();
        } finally { //independente se der certo ou errado, ele fecha a conexão
            conecta.desconecta();
//          fechamos porquê várias instâncias aberta de uma conexão, pode deixar o seu sistema lento
//            fora que há um limite de conexões abertas no banco, então sempre que abrir, feche!
        }
    }

    public void buscar() {
        conecta.conexao(); // abrimos a conexão
        try {
            conecta.executaSQL("SELECT * FROM login");
            System.out.println();
            System.out.println("Usuários Cadastrados");

            while (conecta.rs.next()) {
                String nome = conecta.rs.getString("usuario");
                String senha = conecta.rs.getString("senha");
                
                System.out.println("Usuário: " + nome);
                System.out.println("Senha: " + senha);
                
                System.out.println();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados. Tente Novamente!");
            e.printStackTrace();
        } finally { //independente se der certo ou errado, ele fecha a conexão
            conecta.desconecta();
//          fechamos porquê várias instâncias aberta de uma conexão, pode deixar o seu sistema lento
//            fora que há um limite de conexões abertas no banco, então sempre que abrir, feche!
        }
    }
    
    public static void main(String[] args) {
        new Banco().buscar();
    }

}
