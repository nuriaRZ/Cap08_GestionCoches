package modeloGestionVentaCoches.gestionEntidadControlada;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeloGestionVentaCoches.utils.CacheImagenes;
import modeloGestionVentaCoches.ventaDeCoches.Coche;
import modeloGestionVentaCoches.ventaDeCoches.Fabricante;
import modeloGestionVentaCoches.ventaDeCoches.controladores.CocheControlador;
import modeloGestionVentaCoches.ventaDeCoches.controladores.FabricanteControlador;


public class PanelGestionCoche extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	
	JTextField jtfId = new JTextField(5);	
	JTextField jtfBastidor = new JTextField(40);
	JTextField jtfModelo = new JTextField(40);
	JTextField jtfColor = new JTextField(20);
	JComboBox<Fabricante> jcbIdFab = new JComboBox<Fabricante>();
	
	
	
	Coche actual = null;
	
	
	/**
	 * 
	 */
	public PanelGestionCoche () {
		actual = CocheControlador.getControlador().findFirst();
		construir();
		cargarDatosActual();
	}
	
	
	
	/**
	 * 
	 */
	private void construir () {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Inclusiï¿½n del panel de navegaciï¿½n
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 2;
	    c.insets = new Insets(0, 0, 25, 0);
	    this.add(getPanelNavegacion(), c);
		
	    // Inclusiï¿½n del campo "Identificador"
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.anchor = GridBagConstraints.EAST;
	    c.insets = new Insets(2, 2, 2, 2);
	    this.add(new JLabel("Identificador: "), c);
	    
		c.gridx = 1;
	    c.gridy = 1;
	    jtfId.setEnabled(false);
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfId, c);
		
		// Inclusiï¿½n del campo "IdFab"
		List<Fabricante> fabricantes = FabricanteControlador.getControlador().findAll();
		
		for (Fabricante f : fabricantes) {
			jcbIdFab.addItem(f);
		}
		
		c.gridx = 0;
	    c.gridy = 2;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Id Fabricante: "), c);
	    
		c.gridx = 1;
	    c.gridy = 2;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jcbIdFab, c);
		
		// Inclusiï¿½n del campo "Bastidor"
		c.gridx = 0;
	    c.gridy = 3;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Bastidor: "), c);
	    
		c.gridx = 1;
	    c.gridy = 3;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfBastidor, c);
		
		// Inclusiï¿½n del campo "Modelo"
		c.gridx = 0;
	    c.gridy = 4;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Modelo: "), c);
	    
		c.gridx = 1;
	    c.gridy = 4;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfModelo, c);
		
		// Inclusiï¿½n del campo "Color"
		c.gridx = 0;
	    c.gridy = 5;
	    c.anchor = GridBagConstraints.EAST;
	    this.add(new JLabel("Color: "), c);
	    
		c.gridx = 1;
	    c.gridy = 5;
	    c.anchor = GridBagConstraints.WEST;
		this.add(jtfColor, c);
		
		
		
		c.gridx = 0;
	    c.gridy = 6;
	    c.gridwidth = 2;
	    c.anchor = GridBagConstraints.CENTER;
	    c.insets = new Insets(25, 0, 0, 0);
		this.add(getPanelAcciones(), c);
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	private JPanel getPanelNavegacion () {
		JPanel pnl = new JPanel();

		pnl.setBackground(Color.decode("#c8d7e0"));
		
		JButton jbtPrimero = new JButton("Primero");
		asignarFuncion(jbtPrimero, LOAD_FIRST);
		
		JButton jbtAnterior = new JButton("Anterior");
		asignarFuncion(jbtAnterior, LOAD_PREV);
		
		JButton jbtSiguiente = new JButton("Siguiente");
		asignarFuncion(jbtSiguiente, LOAD_NEXT);

		JButton jbtUltimo = new JButton("Ultimo");
		asignarFuncion(jbtUltimo, LOAD_LAST);
		
		pnl.setLayout(new FlowLayout());
		pnl.add(jbtPrimero);
		pnl.add(jbtAnterior);
		pnl.add(jbtSiguiente);
		pnl.add(jbtUltimo);
		
		return pnl;
	}

	
	/**
	 * 
	 * @return
	 */
	private JPanel getPanelAcciones () {
		JPanel pnl = new JPanel();
		
		pnl.setBackground(Color.decode("#c8d7e0"));

		JButton jbtNuevo = new JButton("Nuevo");
		asignarFuncion(jbtNuevo, NEW);
		
		JButton jbtGuardar = new JButton("Guardar");
		asignarFuncion(jbtGuardar, SAVE);
		
		JButton jbtEliminar = new JButton("Eliminar");
		asignarFuncion(jbtEliminar, REMOVE);
		
		
		pnl.setLayout(new FlowLayout());
		pnl.add(jbtNuevo);
		pnl.add(jbtGuardar);
		pnl.add(jbtEliminar);
		
		return pnl;
	}

	
	/**
	 * 
	 */
	private void nuevo () {
		limpiarPantalla();
		JOptionPane.showMessageDialog(this, "Por favor, introduzca los datos del nuevo registro");
	}
	

	/**
	 * 
	 */
	private void limpiarPantalla() {
		this.jtfId.setText("");
		this.jcbIdFab.setSelectedIndex(0);
		this.jtfBastidor.setText("");
		this.jtfModelo.setText("");
		this.jtfColor.setText("");
		
	}
	
	
	/**
	 * @throws ParseException 
	 * 
	 */
	private void guardar () throws ParseException {
		Coche nuevoRegistro = new Coche();
		
		if (this.jtfId.getText().trim().equals("")) 
			nuevoRegistro.setId(0);
		else 
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));
		
		nuevoRegistro.setFabricante( (Fabricante) this.jcbIdFab.getSelectedItem());		
		nuevoRegistro.setBastidor(this.jtfBastidor.getText());
		nuevoRegistro.setModelo(this.jtfModelo.getText());
		nuevoRegistro.setColor(this.jtfColor.getText());
		
		
		if (nuevoRegistro.getId() == 0) {
			CocheControlador.getControlador().persist(nuevoRegistro);
		}
		else {
			CocheControlador.getControlador().merge(nuevoRegistro);
		}
		
		this.jtfId.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");
		
		this.actual = nuevoRegistro;
	}
	
	
	/**
	 * 
	 * @return
	 */
	private Coche eliminar () {
		String respuestas[] = new String[] {"Sí", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, 
				"¿Realmente desea eliminar el registro?", "Eliminación del registro", 
		        JOptionPane.OK_CANCEL_OPTION, 
		        JOptionPane.OK_CANCEL_OPTION, 
		        CacheImagenes.getCacheImagenes().getIcono("confirm.png"), 
		        respuestas, respuestas[1]);

	    if(opcionElegida == 0) {
	    	Coche nuevoAMostrar = CocheControlador.getControlador().findPrevious(actual);
	    	if (nuevoAMostrar == null) {
	    		nuevoAMostrar = CocheControlador.getControlador().findNext(actual);
	    	}
	    	CocheControlador.getControlador().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminación correcta");

	    	if (nuevoAMostrar != null) {
	    		actual = nuevoAMostrar;
	    	}
	    	else {
	    		limpiarPantalla();
	    	} 
	    }
	    return actual;
	}
	
	
	/**
	 * 
	 * @param jbt
	 * @param funcion
	 */
	private void asignarFuncion (JButton jbt, final int funcion) {
		jbt.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0) {
				
				Coche obtenido = null;
				if (funcion == LOAD_FIRST) 
					obtenido = CocheControlador.getControlador().findFirst();
				if (funcion == LOAD_PREV) 
					obtenido = CocheControlador.getControlador().findPrevious(actual);
				if (funcion == LOAD_NEXT) 
					obtenido = CocheControlador.getControlador().findNext(actual);
				if (funcion == LOAD_LAST) 
					obtenido = CocheControlador.getControlador().findLast();
				if (funcion == NEW) 
					nuevo();
				if (funcion == SAVE)
					try {
						guardar();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (funcion == REMOVE) 
					obtenido = eliminar();
				
				if (obtenido != null) {
					actual = obtenido;
					cargarDatosActual();
				}
			}});
	}
	

	/**
	 * 
	 */
	private void cargarDatosActual () {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (this.actual != null) {
			this.jtfId.setText("" + this.actual.getId());
			this.jcbIdFab.setSelectedItem(this.actual.getFabricante());
			this.jtfBastidor.setText(this.actual.getBastidor());
			this.jtfModelo.setText(this.actual.getModelo());
			this.jtfColor.setText(this.actual.getColor());
			
			
		}
	}
}
