package feps;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class MaintenenceTable extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblManutTable;
	private GroupLayout groupLayout;
	private JTabbedPane tabbedPane;
	// private Dimension dimension = new Dimension(1366, 768);
	private static final int MIN_WIDTH = 1366;
	private static final int MIN_HEIGHT = 768;
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	public MaintenenceTable() {
		buildPanel();
		initializeComponents();
		buildGroupLayout();
	}

	private void buildPanel() {
		setSize(dimension);
		groupLayout = new GroupLayout(this);
	}

	private void initializeComponents() {
		lblManutTable = new JLabel("Manutenção de Tabelas");
		lblManutTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblManutTable.setFont(new Font("Stencil", Font.PLAIN, 70));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.add("   Modelos Cockpit   ", new ModeloCockpit());
		tabbedPane.add("   Peças Variantes   ", new PecaVariante());
		tabbedPane.add("   Status Chamada de Produção   ", new StatusCockpit());
		tabbedPane.add("   Status Cockpit   ", new StatusCockpit());
		tabbedPane.add("   Cadastro de Turnos   ", new Turno());

		tabbedPane.setFont(new Font("Stencil", Font.PLAIN, 20));
	}

	private void buildGroupLayout() {
		buildHorizontalLayout();
		buildVerticalLayout();
		setLayout(groupLayout);
	}

	private void buildHorizontalLayout() {
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(calculate(10, MIN_WIDTH, dimension.width))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblManutTable, GroupLayout.PREFERRED_SIZE,
										calculate(1346, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE)
								.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE,
										calculate(1346, MIN_WIDTH, dimension.width), GroupLayout.PREFERRED_SIZE))));
	}

	private void buildVerticalLayout() {
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(calculate(10, MIN_HEIGHT - 80, dimension.height))
						.addComponent(lblManutTable, GroupLayout.PREFERRED_SIZE,
								calculate(100, MIN_HEIGHT - 80, dimension.height - 80), GroupLayout.PREFERRED_SIZE)
						.addGap(calculate(60, MIN_HEIGHT - 80, dimension.height - 80)).addComponent(tabbedPane,
								GroupLayout.PREFERRED_SIZE, calculate(500, MIN_HEIGHT - 80, dimension.height - 80),
								GroupLayout.PREFERRED_SIZE)));
	}

	private int calculate(double value, double min, double size) {
		value = (value / min) * size;

		return (int) value;
	}
}
