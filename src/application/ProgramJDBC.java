package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class ProgramJDBC {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement( // inserir e atualiza dados
					"UPDATE seller " +
					"SET BaseSalary = BaseSalary + ?" // no caso aqui é um calculo é ele mesmo
					// mais o valor que vamos colocar posteriormente se o salario
					// for 100 e colocarmos 20, vai fic 120
							// aqui vocÊ passa a coluna e o valor que vai atualizar
					// representado por ?
					+"WHERE " // where é sempre importante para filtrar oque vai ser mudado
					+ "(DepartamentId = ?)");
			st.setDouble(1, 200.0); // aqui o primeiro parametro
			// quer dizer que estou falando da primeira ? que é o salario
			// e o segundo parametro eu passo o valor que quero atualizar esse
			// salario
			st.setInt(2, 2); // aqui estou dizendo que vou atualizar o salario
			// whre departament (que seria o segundo?, por isso a chave é 2) 
			// é igual a 2
			
			int rows = st.executeUpdate(); // aqui ele executa o comando retorna
			// quantas linhas foram afetadas, é como se fosse o commit do banco
			
			System.out.println("Done, rows affected: " + rows);
	
						
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
