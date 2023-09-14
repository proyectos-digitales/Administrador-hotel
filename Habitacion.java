public class Habitacion 
{
    private int id;
    private int numeroHabitacion;
    private int camas;
    private int idServicio;
    private int idPasajero;
	
	public Habitacion(int id, int numeroHabitacion, int camas, int idServicio, int idPasajero) 
    {        
        this.id = id;
        this.numeroHabitacion = numeroHabitacion;
        this.camas = camas;
        this.idServicio = idServicio;
        this.idPasajero = idPasajero;
    }   
	
	public int getId() 
	{
        return id;
    }

    public void setId(int id) 
	{
        this.id = id;
    }

    public int getNumeroHabitacion() 
	{
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) 
	{
        this.numeroHabitacion = numeroHabitacion;
    }

    public int getCamas() 
	{
        return camas;
    }

    public void setCamas(int camas) 
	{
        this.camas = camas;
    }

    public int getIdServicio() 
	{
        return idServicio;
    }

    public void setIdServicio(int idServicio) 
	{
        this.idServicio = idServicio;
    }

    public int getIdPasajero() 
	{
        return idPasajero;
    }

    public void setId_pasajero(int idPasajero) 
	{
        this.idPasajero = idPasajero;
    }
	
	@Override
    public String toString() 
    {
        return String.format("ID: %d - Habitacion numero: %d", id, numeroHabitacion);
    }
}