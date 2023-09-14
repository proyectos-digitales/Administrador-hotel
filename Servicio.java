public class Servicio 
{
	private int id;
    private int lavanderia;
    private int almuerzo;
    private int cena;
	
	public Servicio(int id, int lavanderia, int almuerzo, int cena) 
    {
        this.id = id;
        this.lavanderia = lavanderia;
        this.almuerzo = almuerzo;
        this.cena = cena;
    }
	
	public int getId() 
	{
        return id;
    }

    public void setId(int id) 
	{
        this.id = id;
    }

    public int getLavanderia() 
	{
        return lavanderia;
    }

    public void setLavanderia(int lavanderia) 
	{
        this.lavanderia = lavanderia;
    }

    public int getAlmuerzo() 
	{
        return almuerzo;
    }

    public void setAlmuerzo(int almuerzo) 
	{
        this.almuerzo = almuerzo;
    }

    public int getCena() 
	{
        return cena;
    }

    public void setCena(int cena) 
	{
        this.cena = cena;
    }   
	
	@Override
    public String toString() 
    {
        return String.format("%nLavanderia: %d%nAlmuerzo: %d%nCena: %d", lavanderia, almuerzo, cena);
    }
}