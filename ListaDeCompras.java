import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaDeCompras extends JFrame {

    private DefaultListModel<Produto> modelProdutos;
    private JList<Produto> listaProdutos;

    public ListaDeCompras() {
        // Configurações básicas da janela
        setTitle("Lista de Compras");
        setSize(320, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Layout
        setLayout(new BorderLayout());

        // Botões de navegação entre as categorias
        JPanel panelBotoes = new JPanel();
        JButton btnSuco = new JButton("Suco");
        JButton btnIsotonico = new JButton("Isotônico");
        JButton btnBarraCereal = new JButton("Barra de Cereal");

        panelBotoes.add(btnSuco);
        panelBotoes.add(btnIsotonico);
        panelBotoes.add(btnBarraCereal);

        add(panelBotoes, BorderLayout.NORTH);

        // Lista de produtos
        modelProdutos = new DefaultListModel<>();
        listaProdutos = new JList<>(modelProdutos);
        listaProdutos.setLayoutOrientation(JList.VERTICAL_WRAP);
        listaProdutos.setVisibleRowCount(-1);

        listaProdutos.setCellRenderer(new ProdutoCellRenderer());

        JScrollPane scrollPane = new JScrollPane(listaProdutos);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para voltar
                JOptionPane.showMessageDialog(null, "Botão Voltar pressionado");
            }
        });

        // Adiciona o botão Voltar na parte inferior
        add(btnVoltar, BorderLayout.SOUTH);

        // Ação dos botões
        btnSuco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popularListaProdutosSuco();
            }
        });

        btnIsotonico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popularListaProdutosIsotonico();
            }
        });

        btnBarraCereal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popularListaProdutosCereal();
            }
        });

        // Ação ao clicar na lista de produtos
        listaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = listaProdutos.locationToIndex(evt.getPoint());
                if (index != -1) {
                    Produto produtoSelecionado = modelProdutos.getElementAt(index);
                    exibirDetalhesProduto(produtoSelecionado);
                }
            }
        });
    }

    private void popularListaProdutosSuco() {
        modelProdutos.clear();

        adicionarProduto("Suco de Goiaba", 2.99, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\sucodegoiapa.png");
        adicionarProduto("Suco de Laranja", 1.99, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\sucodelaranja.png");
        adicionarProduto("Suco de Limão", 2.49, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\sucodelimao.png");
        adicionarProduto("Suco de Morango", 3.99, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\sucodemorango.png");
        adicionarProduto("Suco Verde", 2.79, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\sucodepera.png");
        adicionarProduto("Suco de Pera", 2.29, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\sucoverde.png");
    }

    private void popularListaProdutosIsotonico() {
        modelProdutos.clear();

        adicionarProduto("Gatorade Amarelo", 4.49, "C:\\\\Users\\\\sakia\\\\OneDrive\\\\Área de Trabalho\\\\tela de compra\\\\assests\\\\gatoreamarelo.png");
        adicionarProduto("Gatorade Vermelho", 4.49, "C:\\\\Users\\\\sakia\\\\OneDrive\\\\Área de Trabalho\\\\tela de compra\\\\assests\\\\gatorevermelho.jpg");
        adicionarProduto("Jungle", 3.99, "C:\\\\Users\\\\sakia\\\\OneDrive\\\\Área de Trabalho\\\\tela de compra\\\\assests\\\\jungle.png");
        adicionarProduto("Nitrix", 5.99, "C:\\\\Users\\\\sakia\\\\OneDrive\\\\Área de Trabalho\\\\tela de compra\\\\assests\\\\nitrix.png");
        adicionarProduto("Power", 4.99, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\power.png");
        adicionarProduto("TNT", 3.49, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\tnt.png");
    }

    private void popularListaProdutosCereal() {
        modelProdutos.clear();

        adicionarProduto("Cereal Roxo", 6.99, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\cereal roxo.jpg");
        adicionarProduto("Cereal Avelã", 5.99, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\cereal-avela-dp.webp");
        adicionarProduto("Cereal Bolo de Chocolate", 7.99, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\cereal-bolo-de-chocolate-dp.webp");
        adicionarProduto("Cereal Morango", 6.49, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\cereal-morango-dp.webp");
        adicionarProduto("Ritter Vermelho", 8.99, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\ritter vermelho.jpg");
        adicionarProduto("Ritter", 8.99, "C:\\Users\\sakia\\OneDrive\\Área de Trabalho\\tela de compra\\assests\\ritter.webp");
    }

    private void adicionarProduto(String nome, double preco, String caminhoImagem) {
        ImageIcon imagem = redimensionarImagem(caminhoImagem);
        Produto produto = new Produto(nome, preco, imagem);
        modelProdutos.addElement(produto);
    }

    private void exibirDetalhesProduto(Produto produto) {
    DetalhesProdutoFrame detalhesFrame = new DetalhesProdutoFrame(produto, this);
    detalhesFrame.setVisible(true);
    }


    private ImageIcon redimensionarImagem(String caminho) {
        ImageIcon imagemOriginal = new ImageIcon(caminho);
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemRedimensionada);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ListaDeCompras().setVisible(true);
            }
        });
    }

    class ProdutoCellRenderer extends JLabel implements ListCellRenderer<Produto> {

        public ProdutoCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(JLabel.CENTER);
            setVerticalAlignment(JLabel.CENTER);
            setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Produto> list, Produto value, int index, boolean isSelected, boolean cellHasFocus) {
            setIcon(value.getImagem());
            return this;
        }
    }

class DetalhesProdutoFrame extends JFrame {

    private ListaDeCompras listaDeCompras; // Referência à janela da lista de compras

    public DetalhesProdutoFrame(Produto produto, ListaDeCompras listaDeCompras) {
        this.listaDeCompras = listaDeCompras; // Inicializa a referência à janela da lista de compras

        setTitle("Detalhes do Produto");
        setSize(300, 400); // Tamanho fixo da janela
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Exibe a imagem, nome e preço na nova janela
        JLabel labelImagem = new JLabel();
        labelImagem.setIcon(redimensionarImagem(produto.getImagem(), 200, 200)); // Redimensiona a imagem para 200x200 pixels
        JLabel labelNome = new JLabel("Nome: " + produto.getNome());
        JLabel labelPreco = new JLabel("Preço: R$" + produto.getPreco());

        // Cria um painel com layout BoxLayout na direção Y (vertical)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelImagem);
        panel.add(labelNome);
        panel.add(labelPreco);

        // Adiciona o botão "Comprar" ao painel
        JButton btnComprar = new JButton("Comprar");
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho: " + produto.getNome());
            }
        });
        panel.add(btnComprar);

        // Adiciona o botão "Voltar" ao painel
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaListaDeCompras();
            }
        });
        panel.add(btnVoltar);

        // Adiciona o painel à janela
        add(panel);
    }

    private ImageIcon redimensionarImagem(ImageIcon imagemOriginal, int largura, int altura) {
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemRedimensionada);
    }

    private void voltarParaListaDeCompras() {
        this.dispose(); // Fecha a janela de detalhes do produto
        listaDeCompras.setVisible(true); // Torna a janela de lista de compras visível novamente
    }
}

    class Produto {
        private String nome;
        private double preco;
        private ImageIcon imagem;

        public Produto(String nome, double preco, ImageIcon imagem) {
            this.nome = nome;
            this.preco = preco;
            this.imagem = imagem;
        }

        public String getNome() {
            return nome;
        }

        public double getPreco() {
            return preco;
        }

        public ImageIcon getImagem() {
            return imagem;
        }

        @Override
        public String toString() {
            return nome;
        }
    }
}
