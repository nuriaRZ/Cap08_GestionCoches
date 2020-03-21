package modeloGestionVentaCoches.gestionEntidadControlada;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JToolTip;

import modeloGestionVentaCoches.utils.Apariencia;
import modeloGestionVentaCoches.utils.CacheImagenes;
import modeloGestionVentaCoches.gestionEntidadControlada.ToolBar;


public class VentanaPrincipal extends JFrame {

	public static int ANCHO = 800;
	public static int ALTO = 600;
	public static String TITULO_APLICACION = "Gestión de venta de coches";

	private CacheImagenes cacheImagenes;
	public static BufferedImage iconoApp;
	static JTabbedPaneGestion jTabbedPane = new JTabbedPaneGestion();
	public static VentanaPrincipal instance = null;
	
	
	// Establecer la apariencia típica de Windows
	static {
		Apariencia.setAparienciasOrdenadas(Apariencia.aparienciasOrdenadas);
	}
	
	public static VentanaPrincipal getInstance() {
		if (instance == null) {
			instance = new VentanaPrincipal();
		}
		return instance;
	}
	
	public JTabbedPane getJTabbedPane() {
		return this.jTabbedPane;
	}
	
	

	
	
	public VentanaPrincipal () {
		super (TITULO_APLICACION);
		
		cacheImagenes = new CacheImagenes();
		iconoApp = cacheImagenes.getImagen("nave.gif");
		setIconImage(iconoApp);
		
		this.setExtendedState(JFrame.NORMAL);
		this.setMinimumSize(new Dimension(ANCHO, ALTO));
		
		agregarGestionCierreAplicacion();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jTabbedPane, BorderLayout.CENTER);
		this.add(new ToolBar(), BorderLayout.NORTH);
		this.setJMenuBar(getMyMenuBar());
		
		// Construcción elementos básicos sobre el ContentPanel
		//this.setContentPane(new JTabbedPaneGestion());
	}
	
	private JMenuBar getMyMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuGestion = new JMenu("Gestión");
		
		String opciones[] = new String[] {"Concesionario", "Fabricante", "Cliente", "Coche", "Venta"};
		
		for (int i = 0; i < opciones.length; i++) {
			final int iFinal = i;
			JMenuItem item = new JMenuItem(opciones[i]);
			item.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					jTabbedPane.setSelectedIndex(iFinal);
					
				}
			});
			menuGestion.add(item);
		}
		menuBar.add(menuGestion);
		return menuBar;
	}

	
		
	/**
	 * 
	 */
	private void agregarGestionCierreAplicacion () {
		// Configuración del evento de cerrado
		// Para más información debes estudiar Javadoc WindowListener y WindowAdapter
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				String posiblesRespuestas[] = {"Sí","No"};
				// En esta opción se utiliza un showOptionDialog en el que personalizo el icono mostrado
				int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente desea cerrar la aplicación?", TITULO_APLICACION, 
				        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, cacheImagenes.getIcono("confirm.png"), posiblesRespuestas, posiblesRespuestas[1]);
			    if(opcionElegida == 0) {
			      System.exit(0);          
			    }
			}
		});
	}
}
