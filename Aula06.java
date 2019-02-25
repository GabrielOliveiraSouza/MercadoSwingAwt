
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Aula06 extends JPanel {
	private JPanel pnPrincipal, pnTable;
	private JButton btRemover, btAdicionar;
	private JScrollPane scrollTable;
	private JTable table; // declaração da tabela
	private JLabel lbNumero, lbTotal, lbProduto, lbPrecoUnitario, lbQtde, lbdin;
	private JTextField tfNumero, tfTotal, tfProduto, tfPrecoUnitario, tfQtde, tfdin;
	private JProgressBar pb1;
	DecimalFormat df = new DecimalFormat("#,###.00"); // classe para formatar um campo da tabela;
	double total;

	public Aula06() {
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {

		setLayout(null);
		pb1 = new JProgressBar();
		lbdin = new JLabel("Dinheiro");
		lbProduto = new JLabel("Produto");
		lbQtde = new JLabel("Quantidade");
		lbPrecoUnitario = new JLabel("Preço Unitário");
		lbNumero = new JLabel("Número do pedido:");
		lbTotal = new JLabel("Total de Pedidos: ");
		tfProduto = new JTextField();
		tfdin = new JTextField();
		tfPrecoUnitario = new JTextField();
		tfQtde = new JTextField();
		tfNumero = new JTextField();
		tfTotal = new JTextField();
		tfTotal.setEnabled(false);// textfield editavel ou não
		tfTotal.setHorizontalAlignment(JTextField.CENTER);// alinhamento do texto no textfield (esquerda, direita,
															// centro)
		btAdicionar = new JButton("Adicionar");
		btAdicionar.setToolTipText("Adiciona um item ao pedido");
		btRemover = new JButton("Remover");
		btRemover.setToolTipText("Remove os itens selecionados");
		pb1.setBounds(20, 360, 100, 25);
		lbdin.setBounds(260, 15, 80, 25);
		tfdin.setBounds(310, 15, 80, 25);
		lbProduto.setBounds(15, 30, 100, 25);
		lbQtde.setBounds(225, 40, 100, 25);
		lbPrecoUnitario.setBounds(310, 40, 100, 25);
		lbNumero.setBounds(15, 10, 120, 25);
		lbTotal.setBounds(278, 360, 100, 25);
		tfProduto.setBounds(15, 65, 200, 25);
		tfQtde.setBounds(225, 65, 50, 25);
		tfPrecoUnitario.setBounds(310, 65, 80, 25);
		tfNumero.setBounds(130, 10, 50, 25);
		tfTotal.setBounds(375, 360, 100, 25);
		btAdicionar.setBounds(15, 100, 100, 22);
		btRemover.setBounds(125, 100, 100, 22);
		pnPrincipal = new JPanel();
		pnPrincipal.setLayout(null);
		pnPrincipal.setBounds(0, 0, 500, 400);
		pnPrincipal.add(lbNumero);
		pnPrincipal.add(lbTotal);
		pnPrincipal.add(pb1);
		pnPrincipal.add(lbdin);
		pnPrincipal.add(tfdin);
		pnPrincipal.add(tfNumero);
		pnPrincipal.add(tfTotal);
		pnPrincipal.add(lbProduto);
		pnPrincipal.add(tfProduto);
		pnPrincipal.add(lbQtde);
		pnPrincipal.add(tfQtde);
		pnPrincipal.add(lbPrecoUnitario);
		pnPrincipal.add(tfPrecoUnitario);
		pnTable = new JPanel(new BorderLayout());
		pnTable.setBorder(new TitledBorder("Itens do Pedido"));// linhas 64 e 65 definem a borda do painel , com o nome
																// dele (nesse caso itens do pedido)
		scrollTable = new JScrollPane();
		df.setMinimumFractionDigits(2);// minimo de numeros depois da virgula
		df.setMaximumFractionDigits(2);// minimo de núemros depois da virgula
		pnPrincipal.add(btAdicionar);
		pnPrincipal.add(btRemover);

		DefaultTableModel tableModel = new DefaultTableModel(new String[] { "Produto", "Qtde", "Preço Un.", "Total" },
				0) { // cria o layout da tabela , os itens do vetor são os nomes da coluna, o 0
						// indica que a tabela saíra sem nenhuma linha
			public boolean isCellEditable(int row, int col) { // método para dizer se a celula será editavel ou não
				if (col == 3) {
					return false;
				}
				return true;
			}
		};
		table = new JTable(tableModel);// instancia a tabela e adiciona o modelo de tabela criado anteriomente a ela
		DefaultTableCellRenderer alinhaDireita = new DefaultTableCellRenderer();
		alinhaDireita.setHorizontalAlignment(SwingConstants.RIGHT);// a variável alinhaDireita alinha o conteudo da
																	// tabela a direita
		table.getColumnModel().getColumn(0).setPreferredWidth(150); // define o tamanho da coluna
		table.getColumnModel().getColumn(0).setResizable(true);// define se a coluna é editavel ou não ;
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);// alinha o conteúdo da ceula a direita;
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setCellRenderer(alinhaDireita);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// linhas 97 e 98 define que as colunas não podem ser
														// reorganizadas com arraste e solte
		scrollTable.setViewportView(table);
		pnTable.add(scrollTable);
		pnTable.setBounds(10, 130, 470, 230);
		pnPrincipal.add(pnTable);
		add(pnPrincipal);

	}

	private void definirEventos() {
		btAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfProduto.getText().equals("") || tfQtde.getText().equals("")
						|| tfPrecoUnitario.getText().equals("")) {
					JOptionPane.showMessageDialog(pnTable, "Preencha todos os campos");
					return;

				}
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.addRow(new Object[] { tfProduto.getText(), tfQtde.getText(), tfPrecoUnitario.getText(), "" + df
						.format(Integer.parseInt(tfQtde.getText()) * Double.parseDouble(tfPrecoUnitario.getText())) });// linhas
																														// 114
																														// a
																														// 116
																														// adicionam
																														// uma
																														// linha
																														// na
																														// tabela
				limparCampos();
				calcularTotal();
				progress();
			}

		});
		btRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] linhas = table.getSelectedRows();
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				for (int i = (linhas.length - 1); i >= 0; --i) { // esse for percorre o vetor de linhas , verifica qual
																	// linha é selecionada e exclui
					dtm.removeRow(linhas[i]);

				}

				calcularTotal();
				progress();
			}

		});

	}

	public void progress() {
		int b = (int) (total);
		pb1.setValue(b);

		if (pb1.getValue() <= 25) {
			pb1.setBackground(Color.WHITE);
			pb1.setForeground(Color.green);
		}
		if (pb1.getValue() >= 26 && pb1.getValue() <= 60) {
			pb1.setBackground(Color.WHITE);
			pb1.setForeground(Color.yellow);
		}
		if (pb1.getValue() >= 61) {
			pb1.setBackground(Color.WHITE);
			pb1.setForeground(Color.red);
		}
		if (total > 100) {
			JOptionPane.showMessageDialog(pnPrincipal, "O limite foi ultrapassado");
		}
	}

	private void calcularTotal() {
		total = 0;
		for (int linha = 0; linha < table.getRowCount(); linha++) {// for para tirar os pontoos, e virguglas do numero
																	// para calcular total
			String valor = " " + table.getValueAt(linha, 3);
			valor = valor.replace(".", "");
			valor = valor.replace(",", ".");
			total += Double.parseDouble(valor);
		}
		tfTotal.setText("" + df.format(total));
	}

	private void limparCampos() {
		tfProduto.setText("");
		tfQtde.setText("");
		tfPrecoUnitario.setText("");
		tfProduto.requestFocus();
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Area de Texto");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Aula06());
		frame.setBounds(300, 300, 500, 500);
		frame.setVisible(true);
	}
}