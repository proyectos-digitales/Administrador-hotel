import java.util.ArrayList;

public class BaseDatos 
{	
	private final ArrayList<Habitacion> habitaciones;
	private final ArrayList<Pasajero> pasajeros;
	private final ArrayList<Servicio> servicios;
	
	public BaseDatos() 
    {
        this.habitaciones = new ArrayList<>(); 
		this.pasajeros = new ArrayList<>();
		this.servicios = new ArrayList<>();
				
		generarDatos();
	}
	
	private void generarDatos()
    {  
		habitaciones.add(new Habitacion(1, 10, 1, 0, 1));
        habitaciones.add(new Habitacion(2, 11, 1, 1, 4));
		habitaciones.add(new Habitacion(3, 12, 2, 2, 2));
		habitaciones.add(new Habitacion(4, 13, 2, 3, 0));
		habitaciones.add(new Habitacion(5, 20, 2, 4, 0));
        habitaciones.add(new Habitacion(6, 21, 2, 5, 3));
        habitaciones.add(new Habitacion(7, 22, 3, 6, 0));
        habitaciones.add(new Habitacion(8, 23, 3, 7, 0));
        habitaciones.add(new Habitacion(9, 30, 3, 8, 0));
        habitaciones.add(new Habitacion(10, 31, 3, 9, 0));
		
		pasajeros.add(new Pasajero(1, "Pepe, Perez", "100-102"));
        pasajeros.add(new Pasajero(2, "Sandra, Sanchez", "100-103"));
        pasajeros.add(new Pasajero(3, "Juan, Juarez", "100-104"));
        pasajeros.add(new Pasajero(4, "Silvia, Silva", "100-105"));
		
		for (int i = 0; i < 10; i++) 
        {
            this.servicios.add(new Servicio(i, 0, 0, 0));
        }		
	}
	
	public ArrayList<Habitacion> listaHabitacionesDisponobles()
    {		
		ArrayList<Habitacion> lista = new ArrayList<>();
		
		for (Habitacion habitacion : habitaciones) 
        {
			if(habitacion.getIdPasajero() == 0)
            {
                lista.add(habitacion);
            }
		}
		
		return lista;
	}
	
	public ArrayList<Pasajero> listaPasajeros()
    {
        return pasajeros;
    }
	
	public void modificarServicio(int idHabitacion, int tipoServicio)
    {	
		for (Servicio servicio : servicios) 
        {
			if(servicio.getId() == idHabitacion)
            {	
				switch(tipoServicio)
                {
                    case 0:
                        int lavanderia = servicio.getLavanderia() + 1;
                        servicio.setLavanderia(lavanderia);
                        break;
                    
                    case 1:
                        int almuerzo = servicio.getAlmuerzo() + 1;
                        servicio.setAlmuerzo(almuerzo);
                        break;    
                    
                    case 2:
                        int cena = servicio.getCena() + 1;
                        servicio.setCena(cena);
                        break;   
                        
                    default:
                        break;
                }
			}
		}
	}
	
	public Habitacion obtenerHabitacionPorPasajero(String nombrePasajero) 
    {	
		int id = -1;
		
		for (Pasajero pasajero : pasajeros) 
        {			
			if(pasajero.getNombreApellido().equalsIgnoreCase(nombrePasajero))
            {
				id = pasajero.getId();
                break;
			}
		}
		
		for(Habitacion habitacion : habitaciones)
        {
			if(habitacion.getIdPasajero() == id)
            {
				return habitacion;
			}	
		}
		
		return null;
	}
	
	public Habitacion obtenerHabitacionPorId(int id)
    {
		for (Habitacion habitacion : habitaciones) 
        {
			if(habitacion.getId() == id)
            {
				return habitacion;
			}	
		}	
		
		return null;
	}
	
	public Pasajero obtenerPasajeroPorId(int id)
    {
        for (Pasajero pasajero : pasajeros) 
        {
            if(pasajero.getId() == id)
            {
                return pasajero;
            }
        }
		
		return new Pasajero(0, "Sin Pasajero", "0");
	}
	
	public Servicio obtenerServicioPorId(int id)
    {        
        for (Servicio servicio : servicios) 
        {
            if(servicio.getId() == id)
            {
                return servicio;
            }
        }
        
        return null;
    }	
	
	public Pasajero obtenerPasajeroPorNombre(String nombre)
    {
        for (Pasajero pasajero : pasajeros) 
        {
            if(pasajero.getNombreApellido().equals(nombre))
            {
                return pasajero;
            }
        }
        
        return null;
    }	
	
	public ArrayList<Habitacion> listaHabitacionesDisponoblesPorTipo(String tipo)
    {
		ArrayList<Habitacion> lista = new ArrayList<>();
		
		for (Habitacion habitacion : habitaciones) 
        {			
			if(habitacion.getCamas() == tipoHabitacion(tipo) && habitacion.getIdPasajero() == 0)
            {
				lista.add(habitacion);
			}
		}
		
		return lista;
	}
	
	private int tipoHabitacion(String tipo)
    {
        switch(tipo)
        {
            case "1 doble 2 simple":
                return 1;
                
            case "1 doble 1 simple":
                return 2;
                
            case "2 simples":
                return 3;
            
            default:
                return 0;
        }
    }
	
	public boolean borrarPasajero(String nombre)
    {		
		Pasajero psj = null;
		
		for (Pasajero pasajero : pasajeros) 
        {
			if(pasajero.getNombreApellido().equalsIgnoreCase(nombre))
            {		
				psj = pasajero;   
			}
		}
		
		return pasajeros.remove(psj);
	}
	
	public Habitacion obtenerHabitacionPorNumero(int numero)
    {	
		for (Habitacion habitacion : habitaciones) 
        {
            if(habitacion.getNumeroHabitacion() == numero)
            {
                return habitacion;
            }
        }
        
        return null;	
	}
	
	public void crearPasajero(Pasajero pasajero)
    {
		int ultimoId = pasajeros.size();
		
		pasajero.setId(ultimoId);
		
		pasajeros.add(pasajero);
	}
	
	public String[] nombresPasajeros()
    {
		int cantidadPasajeros = pasajeros.size();
		
		String[] nombres = new String[cantidadPasajeros];
		
		for (int i = 0; i < pasajeros.size(); i++) 
        {
            nombres[i] = pasajeros.get(i).getNombreApellido();
        }
		
        return nombres;
	}		
	
	public void asignarPasajeroHabitacion(String nombrePasajero, int numeroHabitacion)
    {
		int idPasajero = obtenerPasajeroPorNombre(nombrePasajero).getId();
		
		for (Habitacion habitacion : habitaciones) 
        {
            if(habitacion.getIdPasajero() == idPasajero)
            {
				 habitacion.setId_pasajero(0);
			}
		}

		for (Habitacion habitacion : habitaciones) 
        {
            if(habitacion.getIdPasajero() == 0 && habitacion.getNumeroHabitacion() == numeroHabitacion)
            {
				habitacion.setId_pasajero(idPasajero);
                break;
			}
        }	
	}
}
