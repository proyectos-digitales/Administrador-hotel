import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;	
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class VentanaPrincipal extends JFrame
{		
    private final JTextArea monitorBusqueda;
	private final DefaultListModel<String> modelo;	
	private final BaseDatos baseDatos;
	private int indiceBusqueda;
		
	public VentanaPrincipal()
    {        
        super.setTitle("Ejemplo");	
		super.setLayout(new BorderLayout(5, 5));
        super.setSize(600, 400);
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		super.setLocationRelativeTo(null);
		
		Font fuente = new Font("Arial", Font.PLAIN, 14);
		
		this.monitorBusqueda = new JTextArea();	
		this.monitorBusqueda.setEditable(false);
		this.monitorBusqueda.setFont(fuente);
		this.monitorBusqueda.setForeground(Color.BLUE);
				
		this.modelo = new DefaultListModel<>();	

		this.baseDatos = new BaseDatos(); 
		
		super.add(crearPanelNorte(), BorderLayout.NORTH);		
		super.add(crearPanelOeste(), BorderLayout.WEST);
	    super.add(crearPanelCentral(), BorderLayout.CENTER);	
	}
	
	private JPanel crearPanelNorte()
    {		
		JPanel panel = new JPanel();
		
		JLabel etiqueta = new JLabel("Administrador Hotelero");
		etiqueta.setFont(new Font("Sans Serif", Font.BOLD, 20));
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta.setForeground(Color.DARK_GRAY);	
		
		panel.add(etiqueta);
		
		return panel;
	}
	
	private JPanel crearPanelOeste()
    {		
		GridLayout layout = new GridLayout(4, 1, 5, 5); 
		
		JPanel panel = new JPanel(layout);		
		
		panel.add(crearBotonHabitacion());        
		panel.add(crearBotonPasajeros());
		panel.add(crearBotonServicios());
		panel.add(crearBotonSalir());
				
		return panel;
	}	
	
	private JButton crearBotonHabitacion()
    {	
		Icon icono = new ImageIcon("img/habitaciones.png");
		
		JButton boton = new JButton(icono);
		boton.setBackground(Color.LIGHT_GRAY); 
		boton.setToolTipText("Habitaciones");
		
		boton.addActionListener(new ActionListener() 
        {
			@Override
            public void actionPerformed(ActionEvent arg0) 
            {				
				modelo.clear();
				indiceBusqueda = 1;
				monitorBusqueda.setText("");
				
				Object[] opciones = {"Disponibles", "Por persona", "Por tipo" , "Por numero"};
				
				int indiceOpcionHabitaciones = JOptionPane.showOptionDialog(null, "Buscar habitacion", "Ejemplo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
						
				switch(indiceOpcionHabitaciones)
                {
                    case 0:
                        for (Habitacion habitacionesTodas : baseDatos.listaHabitacionesDisponobles()) 
                        {
                            modelo.addElement(habitacionesTodas.toString());
                        }                        
                        break;
						
					case 1:
						String nombrePasajero = JOptionPane.showInputDialog(null, "Nombre pasajero");
						
						Habitacion habitacion = baseDatos.obtenerHabitacionPorPasajero(nombrePasajero);
						
						if(habitacion != null)
                        {	
							modelo.addElement(habitacion.toString());
						}
						break;	
						
					case 2:	
						Object[] tipos = {"1 doble 2 simple", "1 doble 1 simple", "2 simples"};
						
						String tipoHabitacion = (String)JOptionPane.showInputDialog(null, "Uno", "dos", JOptionPane.INFORMATION_MESSAGE, null, tipos, tipos[0]);
													
						String tipo = tipoHabitacion == null ? "vacio" : tipoHabitacion;

						for (Habitacion habitacionTipo : baseDatos.listaHabitacionesDisponoblesPorTipo(tipo)) 
                        {
                            modelo.addElement(habitacionTipo.toString());
                        }        	
						break;
					
					case 3:
						Object[] listaNumeroHabitacion = {10, 11, 12, 13, 20, 21, 22, 23, 30, 31};
						
						Object objetoSeleccionado = JOptionPane.showInputDialog(null, "Buscar habitacion", "Habitacion", JOptionPane.INFORMATION_MESSAGE, null, listaNumeroHabitacion, listaNumeroHabitacion[0]);
									
						if(objetoSeleccionado != null)
                        {
							int numeroHabitacionSeleccionada = (int)objetoSeleccionado;
							
							Habitacion habitacionPorNumero = baseDatos.obtenerHabitacionPorNumero(numeroHabitacionSeleccionada);
																				
							modelo.addElement(habitacionPorNumero.toString());													
						}
						else
                        {    
                            monitorBusqueda.setText("No se encontro resultado!");
                        }         						
						break;
						
					default:
						break;	
				}		
			}
		});
		
		return boton;
	}	
	
	private JButton crearBotonPasajeros()
    {
		Icon icono = new ImageIcon("img/pasajeros.png");
        
		JButton boton = new JButton(icono);
		boton.setBackground(Color.LIGHT_GRAY);            
		boton.setToolTipText("Pasajeros");
		
		boton.addActionListener(new ActionListener() 
        {
			@Override
            public void actionPerformed(ActionEvent arg0) 
            {				
				modelo.clear();
				indiceBusqueda = 2;
				monitorBusqueda.setText("");
				
				Object[] opciones = {"Todos", "Borrar", "Cargar", "Asignar"};
				
				int indiceOpcionPasajero = JOptionPane.showOptionDialog(null, "Buscar Pasajero", "Pasajero", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
						
				switch(indiceOpcionPasajero)
                {
					case 0:  
						for (Pasajero pasajero : baseDatos.listaPasajeros()) 
						{	
							modelo.addElement(pasajero.toString());
						}					 
                        break;
					
					case 1: 
						String nombrePasajero = JOptionPane.showInputDialog(null, "Nombre pasajero a borrar");
						
						if(baseDatos.borrarPasajero(nombrePasajero))
                        {
                            monitorBusqueda.setText("Pasajero borrado!"); 
                        }
                        else
                        {
                            monitorBusqueda.setText("No se encontro pasajero!");
                        }           
						break;
					
					case 2:
                        JTextField nombreApellido = new JTextField();
                        JTextField numeroTarjeta = new JTextField();
						
						Object[] entradas = {"Nombre y apellido", nombreApellido, "Numero tarjeta", numeroTarjeta};
						
						JOptionPane.showConfirmDialog(null, entradas, "Ingresar pasajero" ,JOptionPane.OK_OPTION);
						
						Pasajero pasajero = new Pasajero(0, nombreApellido.getText(), numeroTarjeta.getText());
						
						baseDatos.crearPasajero(pasajero);
						
						monitorBusqueda.setText(String.format("Pasajero Ingresado%nNombre y apellido: %s%nNumero tarjeta: %s", nombreApellido.getText(), numeroTarjeta.getText()));
						break;
					
					case 3:
						Integer[] numeroHabitacion = {10, 11, 12, 13, 20, 21, 22, 23, 30, 31};                
                        JComboBox<Integer> selectorHabitacion = new JComboBox<>(numeroHabitacion);
						
						JComboBox<String> selectorPasajero = new JComboBox<>(baseDatos.nombresPasajeros());
						
						Object[] entradasDesplegables = {"Habitacion", selectorHabitacion, "Pasajero", selectorPasajero};

						int seleccion = JOptionPane.showConfirmDialog(null, entradasDesplegables, "Agregar servicio", JOptionPane.OK_OPTION);
																	   
						if(seleccion == 0)
                        {
                            baseDatos.asignarPasajeroHabitacion(selectorPasajero.getSelectedItem().toString(), (int)selectorHabitacion.getSelectedItem());
                            
                            monitorBusqueda.setText("El pasajero, se asigno a la habitacion!.");
                        }
                        else
                        {
                            monitorBusqueda.setText("El pasajero, no se asigno a la habitacion!");               
                        }                          						
						break;
						
					default:
						break;
				}					
			}
		});
		
		return boton;
	}
		
	private JButton crearBotonServicios()
    {
		Icon icono = new ImageIcon("img/servicios.png");
        
		JButton boton = new JButton(icono);
		boton.setBackground(Color.LIGHT_GRAY);       
		boton.setToolTipText("Servicios");
		
		boton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
				modelo.clear();
				indiceBusqueda = 3;
				monitorBusqueda.setText("");
				
				Integer[] numeroHabitacion = {10, 11, 12, 13, 20, 21, 22, 23, 30, 31};                
                JComboBox<Integer> selectorHabitacion = new JComboBox<>(numeroHabitacion);
				
				String[] servicio = {"Lavanderia", "Almuerzo", "Cena"};                
                JComboBox<String> selectorServicio = new JComboBox<>(servicio);
				
				Object[] entradas = {"Habitacion", selectorHabitacion, "Servicio", selectorServicio};
				
				int seleccion = JOptionPane.showConfirmDialog(null, entradas, "Agregar servicio", JOptionPane.OK_OPTION);
									
				if(seleccion == 0)
				{
					baseDatos.modificarServicio(selectorHabitacion.getSelectedIndex(), selectorServicio.getSelectedIndex());
																
					monitorBusqueda.setText("El servicio se cargo correctamente !");											
				}
				else
				{
					monitorBusqueda.setText("No se cargo el servicio !");
				}
			}
		});
		
		return boton;
	}
	
	private JButton crearBotonSalir()
    {
		Icon icono = new ImageIcon("img/salir.png");
        
		JButton boton = new JButton(icono);
		boton.setBackground(Color.LIGHT_GRAY);    
		boton.setToolTipText("Salir");
		
		boton.addActionListener(new ActionListener() 
        {
			@Override
            public void actionPerformed(ActionEvent arg0) 
            {
				System.exit(0);
			}
		});
		
		return boton;
	} 
	
	private JPanel crearPanelCentral()
    {
        GridLayout layout = new GridLayout(2, 1, 5, 5);
		
		JPanel panel = new JPanel(layout); 
		
		panel.add(crearLista());
		
		panel.add(monitorBusqueda);
		        
        return panel;
	}	
	
	private JList<String> crearLista()
    {
		JList<String> lista = new JList<>(modelo);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		lista.addListSelectionListener(new ListSelectionListener() 
        {
			@Override
            public void valueChanged(ListSelectionEvent arg0) 
            {	
				if (lista.getSelectedValue() != null) 
                {
					switch(indiceBusqueda)
                    {
                        case 1:
                            int idHabitacion = Integer.parseInt(lista.getSelectedValue().substring(4, 6).strip());
                            listaHabitaciones(idHabitacion);
                            break;
                            
                        case 2:
                            buscarPasajeroPorNombre(lista.getSelectedValue());
                            break;
                            
                        default:
                            break;
					}
				}				
			}
		});
				
		return lista;
	}
	
	private void listaHabitaciones(int id)
    {
		Habitacion habitacion = baseDatos.obtenerHabitacionPorId(id);

		int idPasajero = habitacion.getIdPasajero();
		Pasajero pasajero = baseDatos.obtenerPasajeroPorId(idPasajero);
        
		int idServicio = habitacion.getIdServicio();
		Servicio servicio = baseDatos.obtenerServicioPorId(idServicio);

		String tipo = tipoHabitacion(habitacion.getCamas());

		monitorBusqueda.setText(String.format("Habitacion numero: %d", habitacion.getNumeroHabitacion()));
		monitorBusqueda.append(String.format("%nTipo Habitacion: %s", tipo));
		monitorBusqueda.append(String.format("%nNombre pasajero: %s", pasajero.getNombreApellido()));
        
		monitorBusqueda.append(servicio.toString());					
	}
	
	private void buscarPasajeroPorNombre(String nombre)
    {
		Pasajero pasajero = baseDatos.obtenerPasajeroPorNombre(nombre);
		monitorBusqueda.setText(String.format("Nombre y apellido: %s", pasajero.getNombreApellido()));
        monitorBusqueda.append(String.format("%nNumero tarjeta: %s", pasajero.getNumeroTarjeta()));
	}
	
	private String tipoHabitacion(int indiceTipo)
    {
		switch(indiceTipo)
        {
            case 1:
                return "1 doble 2 simple";
                
            case 2:
                return "1 doble 1 simple";
                
            case 3:
                return "2 simples";
            
            default:
                return null;
        }
	}
}