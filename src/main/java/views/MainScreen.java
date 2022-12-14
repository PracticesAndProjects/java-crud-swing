package views;

import enums.ConfirmDialog;
import models.CustomTableModel;
import models.Endereco;
import repository.EnderecoRepository;
import services.EnderecoService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen {

	/* Declaração de vars */
	private JPanel             mainPanel;
	private JPanel             searchPanel;
	private JLabel             searchLabel;
	private JTextField         searchField;
	private JButton            consultarButton;
	private JPanel             buttonsPanel;
	private JButton            addBtn;
	private JButton            patchBtn;
	private JButton            deleteBtn;
	private JPanel             tablePanel;
	private JTable             mainTable;
	private JScrollPane        tableScrollPane;
	private CustomTableModel   dados;
	private EnderecoRepository enderecoRepository;
	private EnderecoService    enderecoService;


	//	Construtor
	public MainScreen () {

		/* Instancia camadas de serviço/repositorio */
		this.autowireInstances();

		/* Seta o model da tabela */
		enderecoService.setTableStructure(this.mainTable);

		/* Consulta inicial no DB */
		try {
			this.enderecoService.getAddressList(dados);
		} catch ( Exception exception ) {
			this.enderecoService.showErrorMessage("Não foi possível fazer a consulta inicial de" +
			                                      "endereços");
		}

		/* Evento de botão de adição de registro */
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				/* Criação do frame de inserção de dados */
				JFrame dataInsertionFrame = new JFrame("Inserir dados");
				dataInsertionFrame.setContentPane(new DataInserterScreen(dados, false,
					enderecoRepository, enderecoService
				).getMainPanel());
				dataInsertionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dataInsertionFrame.pack();
				dataInsertionFrame.setLocationRelativeTo(null); // Seta a janela para o meio da tela
				dataInsertionFrame.setResizable(false);
				dataInsertionFrame.setVisible(true);
			}
		});

		/* Evento do botão de exclusão de registro */
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {

				int selectedRow = mainTable.getSelectedRow();

				if ( selectedRow == -1 ) {
					enderecoService.showWarningMessage("Selecione um registro!");
					return;
				}

				/* Abre modal de confirmação e salva o valor selecionado em
				uma var */
				int confirmationResult =
					enderecoService.showConfirmationDialog("O registro selecionado " +
					                                       "será deletado, você tem certeza disso ?");

				/* Deleta o registro se for selecionado "sim" */
				if ( confirmationResult == ConfirmDialog.SIM ) {
					try {
						enderecoService.deleteAddress(dados, selectedRow);
						enderecoService.showMessage("Linha removida com sucesso!");
					} catch ( Exception ex ) {
						enderecoService.showMessage("Não foi possível remover a linha");
					}
				}
			}
		});

		/* Evento de edição de registro */
		patchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				JFrame dataInsertionFrame = new JFrame("Inserir dados");
				DataInserterScreen dataInserterScreen = new DataInserterScreen(dados, true,
					enderecoRepository, enderecoService
				);

				int selectedRow = mainTable.getSelectedRow();
				dataInserterScreen.setSelectedRow(selectedRow);

				if ( selectedRow == -1 ) {
					enderecoService.showWarningMessage("Selecione um registro!");
					return;
				}
				Endereco enderecoToEdit = new Endereco(dados, selectedRow);

				dataInserterScreen.setFieldsOnEdit(enderecoToEdit);
				dataInsertionFrame.setTitle("Edição de registro");
				dataInsertionFrame.setContentPane(dataInserterScreen.getMainPanel());
				dataInsertionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dataInsertionFrame.pack();
				dataInsertionFrame.setLocationRelativeTo(null); // Seta a janela para o meio da tela
				dataInsertionFrame.setVisible(true);

			}
		});

		consultarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				try {
					enderecoService.searchAddress(searchField.getText());
				} catch ( Exception exception ) {
					JOptionPane.showMessageDialog(
						null,
						"Não foi possível consultar o banco de " +
						"dados",
						"Consulta",
						JOptionPane.ERROR_MESSAGE
					                             );
				}
			}
		});

		/* FIM Construtor */
	}

	private void autowireInstances () {
		this.dados = new CustomTableModel();
		this.enderecoRepository = new EnderecoRepository();
		this.enderecoService = new EnderecoService(this.enderecoRepository, this.dados);
	}


	public JPanel getMainPanel () {
		return mainPanel;
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
	private void $$$setupUI$$$ () {
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mainPanel.setMaximumSize(new Dimension(680, 680));
		mainPanel.setMinimumSize(new Dimension(680, 680));
		mainPanel.setPreferredSize(new Dimension(680, 680));
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		searchPanel.setMaximumSize(new Dimension(670, 100));
		searchPanel.setMinimumSize(new Dimension(670, 100));
		searchPanel.setPreferredSize(new Dimension(670, 100));
		searchPanel.setRequestFocusEnabled(false);
		mainPanel.add(searchPanel);
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		panel1.setMaximumSize(new Dimension(680, 680));
		panel1.setMinimumSize(new Dimension(680, 680));
		panel1.setPreferredSize(new Dimension(665, 30));
		searchPanel.add(panel1);
		searchField = new JTextField();
		searchField.setMaximumSize(new Dimension(200, 30));
		searchField.setMinimumSize(new Dimension(200, 30));
		searchField.setPreferredSize(new Dimension(200, 30));
		panel1.add(searchField);
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel2.setPreferredSize(new Dimension(665, 40));
		searchPanel.add(panel2);
		consultarButton = new JButton();
		consultarButton.setText("Consultar");
		panel2.add(consultarButton);
		searchLabel = new JLabel();
		searchLabel.setText("Buscar pelo nome da rua");
		panel2.add(searchLabel);
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel3.setMaximumSize(new Dimension(670, 560));
		panel3.setMinimumSize(new Dimension(670, 560));
		panel3.setPreferredSize(new Dimension(670, 560));
		panel3.setRequestFocusEnabled(false);
		mainPanel.add(panel3);
		panel3.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(),
			null,
			TitledBorder.DEFAULT_JUSTIFICATION,
			TitledBorder.DEFAULT_POSITION,
			null,
			null
		                                                 ));
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
		buttonsPanel.setPreferredSize(new Dimension(655, 50));
		panel3.add(buttonsPanel);
		addBtn = new JButton();
		addBtn.setLabel("Incluir");
		addBtn.setText("Incluir");
		buttonsPanel.add(addBtn);
		patchBtn = new JButton();
		patchBtn.setLabel("Alterar");
		patchBtn.setText("Alterar");
		buttonsPanel.add(patchBtn);
		deleteBtn = new JButton();
		deleteBtn.setLabel("Excluir");
		deleteBtn.setText("Excluir");
		buttonsPanel.add(deleteBtn);
		final JSeparator separator1 = new JSeparator();
		separator1.setForeground(new Color(-297984));
		separator1.setPreferredSize(new Dimension(645, 1));
		buttonsPanel.add(separator1);
		tablePanel = new JPanel();
		tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		tablePanel.setPreferredSize(new Dimension(655, 490));
		panel3.add(tablePanel);
		tableScrollPane = new JScrollPane();
		tableScrollPane.setPreferredSize(new Dimension(640, 475));
		tableScrollPane.setVerticalScrollBarPolicy(20);
		tablePanel.add(tableScrollPane);
		mainTable = new JTable();
		mainTable.setAutoCreateColumnsFromModel(true);
		mainTable.setAutoCreateRowSorter(true);
		mainTable.setAutoResizeMode(4);
		mainTable.setAutoscrolls(false);
		mainTable.setCellSelectionEnabled(false);
		mainTable.setColumnSelectionAllowed(false);
		mainTable.setFillsViewportHeight(true);
		mainTable.setForeground(new Color(-7695970));
		mainTable.setMaximumSize(new Dimension(2147483647, 2147483647));
		mainTable.setName("Table");
		mainTable.setOpaque(true);
		mainTable.setPreferredScrollableViewportSize(new Dimension(645, 475));
		mainTable.setPreferredSize(new Dimension(645, 400));
		mainTable.setRequestFocusEnabled(false);
		mainTable.setRowSelectionAllowed(true);
		tableScrollPane.setViewportView(mainTable);
	}

	/** @noinspection ALL */
	public JComponent $$$getRootComponent$$$ () {return mainPanel;}

}