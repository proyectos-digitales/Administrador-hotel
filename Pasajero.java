public class Pasajero 
{
	private int id;
    private String nombreApellido;
    private String numeroTarjeta;
	
	public Pasajero(int id, String nombreApellido, String numeroTarjeta) 
    {
        this.id = id;
        this.nombreApellido = nombreApellido;
        this.numeroTarjeta = numeroTarjeta;
    }
	
	public int getId() 
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }   

    public String getNombreApellido() 
    {
        return nombreApellido;
    }

    public String getNumeroTarjeta() 
	{
        return numeroTarjeta;
    }
	
	@Override
    public String toString() 
    {
        return nombreApellido;
    }       
}