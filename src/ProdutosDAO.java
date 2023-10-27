
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    String lolo;

    
    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setInt(2, produto.getValor());
            preparedStatement.setString(3, produto.getStatus());

            preparedStatement.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar o produto: " + e.getMessage());
        }
    } 
    
        public void venderProduto(int idProduto) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, idProduto);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto com ID " + idProduto + " vendido com sucesso!");
            } else {
                System.out.println("Produto não encontrado ou não disponível para venda.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao vender produto: " + e.getMessage());
        }
    }

    
    
 
    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getInt("valor"));
                produto.setStatus(resultSet.getString("status"));
                
                
                listagem.add(produto);
            }          
        } catch (SQLException e) {
            System.err.println("Erro ao listar os produtos: " + e.getMessage());
        }

        return listagem;
    }
    
        public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();

        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getInt("valor"));
                produto.setStatus(resultSet.getString("status"));

                produtosVendidos.add(produto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar os produtos vendidos: " + e.getMessage());
        }

        return produtosVendidos;
    }
}



    
    
    
   

