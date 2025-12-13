package mx.com.qtxcotizadorM2DiploArq7.negocio;

import java.math.BigDecimal;

public class DiscoDuro extends Articulo {
    private String capacidadAlmacenamiento;

    public DiscoDuro(String marca, String modelo, BigDecimal precioBase, BigDecimal costo, String sku,
    		String capacidadAlmacenamiento) {
		super(marca, modelo, precioBase, costo, sku);
		this.capacidadAlmacenamiento = capacidadAlmacenamiento;
	}

	public TipoArticulo getTipo() { return TipoArticulo.DISCO_DURO; }
    
    public void setCapacidadAlmacenamiento(String capacidadAlmacenamiento) {
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
    }
    public String getCapacidadAlmacenamiento() { 
    	return capacidadAlmacenamiento; 
    }
    
    public BigDecimal cotizar(Integer cantidad) {
		return this.precioBase.multiply(BigDecimal.valueOf(cantidad));	    		
	}
}
