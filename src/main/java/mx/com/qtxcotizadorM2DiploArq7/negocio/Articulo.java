package mx.com.qtxcotizadorM2DiploArq7.negocio;
import java.math.BigDecimal;

public abstract class Articulo {
    private String marca;
    private String modelo;
    protected BigDecimal precioBase;
    private BigDecimal costo;
    private String sku;   
    
    public Articulo(String marca, String modelo, BigDecimal precioBase, BigDecimal costo, String sku) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.precioBase = precioBase;
		this.costo = costo;
		this.sku = sku;
	}
	// ----- Getters y Setters -----

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }

    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }

    // Las subclases CONCRETAS deben implementar este método!!
    public abstract TipoArticulo getTipo();
//    public void setTipo(TipoArticulo tipo) { this.tipo = tipo; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    // ----- Lógica de cotización -----

    public BigDecimal cotizar(Integer cantidad) {
//    	switch(this.tipo) {
//	    	case DISCO_DURO->{
//	    		return this.cotizarDiscoDuro(cantidad);
//	    	}
//	    	case MONITOR->{
//	    		return this.cotizarMonitor(cantidad);
//	    	}
//	    	case TARJETA_VIDEO->{
//	    		return this.cotizarTarjetaVideo(cantidad);
//	    	}
//	    	case PC->{
//	    		return this.cotizarPc(cantidad);
//	    	}
//	    	default->{
	    		return this.precioBase.multiply(BigDecimal.valueOf(cantidad));	    		
//	    	}
//    	}
     }
      
}
