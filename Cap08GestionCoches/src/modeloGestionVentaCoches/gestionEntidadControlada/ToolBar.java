package modeloGestionVentaCoches.gestionEntidadControlada;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import modeloGestionVentaCoches.utils.CacheImagenes;



public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	public ToolBar () {
		this.add(creaBoton("", "next.png", "Concesionario"));
		this.add(creaBoton("", "next.png", "Fabricante"));
		this.add(creaBoton("", "next.png", "Cliente"));
		this.add(creaBoton("", "next.png", "Coche"));
		this.add(creaBoton("", "next.png", "Venta"));
		
	}
	
	
	/**
	 * 
	 * @param titulo
	 * @param icono
	 * @return
	 */
	private JButton creaBoton(String titulo, String icono, String toolTip) {
        JButton jbt = new JButton();
        
        jbt.setText(titulo);
        jbt.setToolTipText(toolTip);
        
        jbt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Has hecho clic en el botón: \"" + toolTip + "\"");
            }
        });
        
        try {
        	jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));  
          } catch (Exception ex) {
        	  ex.printStackTrace();
          }
        return jbt;
	}
	
	
	/**
	 * 
	 * @param titulo
	 * @param icono
	 * @return
	 */
	private JToggleButton creaToggleBoton(String titulo, String icono, String toolTip) {
        JToggleButton jbt = new JToggleButton();
        
        jbt.setText(titulo);
        jbt.setToolTipText(toolTip);
        
        jbt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Has hecho clic en el toggle botón: \"" + toolTip + "\" - Ahora está seleccionado: " + jbt.isSelected());
            }
        });
        
        try {
        	jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));  
          } catch (Exception ex) {
        	  ex.printStackTrace();
          }
        return jbt;
	}
	
	
	/**
	 * 
	 * @param opciones
	 * @return
	 */
	private JComboBox creaComboBox (String opciones[]) {
		JComboBox<String> jcb = new JComboBox<String>(opciones);
		jcb.setMaximumSize(new Dimension(100, 75));
		return jcb;
	}
}

