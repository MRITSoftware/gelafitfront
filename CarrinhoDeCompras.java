import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarrinhoDeCompras extends JFrame {

    private DefaultListModel<ItemCarrinho> modelCarrinho;
    private JList<ItemCarrinho> listaCarrinho;

    public CarrinhoDeCompras() {
        // Configurações básicas da janela
        setTitle("Carrinho de Compras");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout
        setLayout(new BorderLayout());

        // Lista de itens no carrinho
        modelCarrinho = new DefaultListModel<>();
        listaCarrinho = new JList<>(modelCarrinho);
        listaCarrinho.setCellRenderer(new ItemCarrinhoCellRenderer());

        JScrollPane scrollPane = new JScrollPane(listaCarrinho);
        add(scrollPane, BorderLayout.CENTER);

        // Botão para finalizar compra
        JButton btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para finalizar a compra
                JOptionPane.showMessageDialog(null, "Compra finalizada!");
            }
        });

        add(btnFinalizarCompra, BorderLayout.SOUTH);
    }

    public void adicionarItemCarrinho(ItemCarrinho item) {
        modelCarrinho.addElement(item);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
                carrinho.setVisible(true);
            }
        });
    }

    class ItemCarrinhoCellRenderer extends JLabel implements ListCellRenderer<ItemCarrinho> {

        public ItemCarrinhoCellRenderer() {
            setOpaque(true);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ItemCarrinho> list, ItemCarrinho value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.toString());
            return this;
        }
    }

    class ItemCarrinho {
        private String nome;
        private int quantidade;
        private double precoUnitario;

        public ItemCarrinho(String nome, int quantidade, double precoUnitario) {
            this.nome = nome;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
        }

        public String getNome() {
            return nome;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public double getPrecoUnitario() {
            return precoUnitario;
        }

        public double getSubtotal() {
            return quantidade * precoUnitario;
        }

        @Override
        public String toString() {
            return String.format("%s - Quantidade: %d - Preço Unitário: R$%.2f - Subtotal: R$%.2f",
                    nome, quantidade, precoUnitario, getSubtotal());
        }
    }
}
