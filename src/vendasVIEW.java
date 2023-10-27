import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class vendasVIEW extends JFrame {

    private JList<String> productList;

    public vendasVIEW() {
        initialize();
        carregarProdutosVendidos();
    }

    private void initialize() {
        setTitle("Tela de Vendas");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fechar apenas a janela de vendas ao clicar no botão fechar

        productList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(productList);
        getContentPane().add(scrollPane);

        JButton btnAtualizar = new JButton("Atualizar Produtos");
        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                carregarProdutosVendidos();
            }
        });
        getContentPane().add(btnAtualizar, "South");
    }

    private void carregarProdutosVendidos() {
        ProdutosDAO produtosDAO = new ProdutosDAO();
        ArrayList<ProdutosDTO> produtosVendidos = produtosDAO.listarProdutosVendidos();

        DefaultListModel<String> model = new DefaultListModel<>();
        for (ProdutosDTO produto : produtosVendidos) {
            String item = "Nome: " + produto.getNome() + ", Valor: " + produto.getValor();
            model.addElement(item);
        }

        productList.setModel(model);
    }

    // Mantenha esse método se você deseja usar setVisible(true) diretamente
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            carregarProdutosVendidos();
        }
    }
}