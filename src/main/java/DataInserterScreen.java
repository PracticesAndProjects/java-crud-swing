import extensions.CustomTableModel;
import models.Endereco;
import repository.EnderecoRepository;
import services.EnderecoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataInserterScreen {
	private CustomTableModel dados;

	public DataInserterScreen(CustomTableModel dados, boolean isEditing,
	                          EnderecoRepository enderecoRepository, EnderecoService enderecoService) {
		/* Seta se o modal é de edição */
		this.isEditing = isEditing;

		/* Modelo de dados a ser manipulado (vem da MainScreen) */
		this.dados = dados;

		/* Evento de salvar registro */
		salvarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/* Gera objeto com os valores dos inputs*/
				Endereco endereco = new Endereco(
					 cepField.getText(),
					 ruaField.getText(),
					 bairroField.getText(),
					 cidadeField.getText(),
					 ufField.getText()
				);

				/* Verifica se algum deles está vazio e para a execução, se sim, mostrando aviso */
				if(endereco.isInvalid()){
					JOptionPane.showMessageDialog(null, "Campos vazios");
					return;
				}

				boolean failed;
				if (isEditing) {
					failed = enderecoRepository.patchAddress(dados, endereco, selectedRow);

				} else {
					/* se não estiverem vazio, adiciona o registro */
					failed = !enderecoService.createAddress(endereco);
				}

				/* Procura a janela pai desse panel e faz o dispose da mesma */
				disposeFrame();

				/* Aviso de dado salvo com sucesso */
				if (!failed) saveSuccessDialog();
				if (failed) saveFailedDialog();
			}
		});
	}

	private void disposeFrame() {
		/* Procura a janela pai desse panel e faz o dispose da mesma */
		JFrame topLevel = (JFrame) mainPanel.getTopLevelAncestor();
		topLevel.setVisible(false);
		topLevel.dispose();
	}

	private void saveSuccessDialog() {
		JOptionPane.showMessageDialog(null, "Dados salvos com " +
			 "sucesso!");
	}

	private void saveFailedDialog() {
		JOptionPane.showMessageDialog(null, "Dados não puderam ser salvos!", "Erro", JOptionPane.WARNING_MESSAGE);
	}

	public void setFieldsOnEdit(Endereco endereco) {
		getCepField().setText(endereco.getCEP());
		getRuaField().setText(endereco.getRua());
		getBairroField().setText(endereco.getBairro());
		getCidadeField().setText(endereco.getCidade());
		getUfField().setText(endereco.getUf());
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	private int                selectedRow;
	private boolean            isEditing;
	private JPanel             mainPanel;
	private JTextField         cepField;
	private JButton            salvarButton;
	private JTextField         ruaField;
	private JTextField         bairroField;
	private JTextField         ufField;
	private JTextField         cidadeField;
	private EnderecoRepository enderecoRepository;


	public int getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public JTextField getCepField() {
		return cepField;
	}

	public void setCepField(JTextField cepField) {
		this.cepField = cepField;
	}

	public JTextField getRuaField() {
		return ruaField;
	}

	public void setRuaField(JTextField ruaField) {
		this.ruaField = ruaField;
	}

	public JTextField getBairroField() {
		return bairroField;
	}

	public void setBairroField(JTextField bairroField) {
		this.bairroField = bairroField;
	}

	public JTextField getUfField() {
		return ufField;
	}

	public void setUfField(JTextField ufField) {
		this.ufField = ufField;
	}

	public JTextField getCidadeField() {
		return cidadeField;
	}

	public void setCidadeField(JTextField cidadeField) {
		this.cidadeField = cidadeField;
	}

	{
		// GUI initializer generated by IntelliJ IDEA GUI Designer
		// >>> IMPORTANT!! <<<
		// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		mainPanel.setMaximumSize(new Dimension(500, 250));
		mainPanel.setMinimumSize(new Dimension(500, 250));
		mainPanel.setPreferredSize(new Dimension(500, 250));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel1.setMaximumSize(new Dimension(490, 55));
		panel1.setMinimumSize(new Dimension(490, 55));
		panel1.setPreferredSize(new Dimension(490, 55));
		mainPanel.add(panel1);
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(0, 0));
		panel2.setPreferredSize(new Dimension(150, 45));
		panel1.add(panel2);
		final JLabel label1 = new JLabel();
		label1.setText("CEP");
		panel2.add(label1, BorderLayout.NORTH);
		cepField = new JTextField();
		panel2.add(cepField, BorderLayout.SOUTH);
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout(0, 0));
		panel3.setPreferredSize(new Dimension(325, 45));
		panel1.add(panel3);
		final JLabel label2 = new JLabel();
		label2.setText("RUA");
		panel3.add(label2, BorderLayout.NORTH);
		ruaField = new JTextField();
		panel3.add(ruaField, BorderLayout.SOUTH);
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel4.setMaximumSize(new Dimension(490, 55));
		panel4.setMinimumSize(new Dimension(490, 55));
		panel4.setPreferredSize(new Dimension(490, 55));
		mainPanel.add(panel4);
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new BorderLayout(0, 0));
		panel5.setPreferredSize(new Dimension(325, 45));
		panel4.add(panel5);
		final JLabel label3 = new JLabel();
		label3.setText("BAIRRO");
		panel5.add(label3, BorderLayout.NORTH);
		bairroField = new JTextField();
		panel5.add(bairroField, BorderLayout.SOUTH);
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel6.setMaximumSize(new Dimension(490, 55));
		panel6.setMinimumSize(new Dimension(490, 55));
		panel6.setPreferredSize(new Dimension(490, 55));
		mainPanel.add(panel6);
		final JPanel panel7 = new JPanel();
		panel7.setLayout(new BorderLayout(0, 0));
		panel7.setPreferredSize(new Dimension(150, 45));
		panel6.add(panel7);
		final JLabel label4 = new JLabel();
		label4.setText("UF");
		panel7.add(label4, BorderLayout.NORTH);
		ufField = new JTextField();
		panel7.add(ufField, BorderLayout.SOUTH);
		final JPanel panel8 = new JPanel();
		panel8.setLayout(new BorderLayout(0, 0));
		panel8.setPreferredSize(new Dimension(325, 45));
		panel6.add(panel8);
		final JLabel label5 = new JLabel();
		label5.setText("CIDADE");
		panel8.add(label5, BorderLayout.NORTH);
		cidadeField = new JTextField();
		panel8.add(cidadeField, BorderLayout.SOUTH);
		final JPanel panel9 = new JPanel();
		panel9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
		panel9.setMaximumSize(new Dimension(490, 55));
		panel9.setMinimumSize(new Dimension(490, 55));
		panel9.setPreferredSize(new Dimension(490, 55));
		mainPanel.add(panel9);
		salvarButton = new JButton();
		salvarButton.setText("Salvar");
		panel9.add(salvarButton);
	}

	/** @noinspection ALL */
	public JComponent $$$getRootComponent$$$() {
		return mainPanel;
	}
}
