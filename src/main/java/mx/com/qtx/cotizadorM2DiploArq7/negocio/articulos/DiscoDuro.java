package mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos;

import java.math.BigDecimal;

import mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.pc.IComponentePc;

public class DiscoDuro extends Articulo implements IComponentePc{
    private String capacidadAlmacenamiento;

    protected DiscoDuro(String marca, String modelo, BigDecimal precioBase, BigDecimal costo, String sku,
    		String capacidadAlmacenamiento) {
		super(marca, modelo, precioBase, costo, sku);
		this.capacidadAlmacenamiento = capacidadAlmacenamiento;
	}

    @Override
	public TipoArticulo getTipo() { return TipoArticulo.DISCO_DURO; }
    
    public void setCapacidadAlmacenamiento(String capacidadAlmacenamiento) {
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
    }
    public String getCapacidadAlmacenamiento() { 
    	return capacidadAlmacenamiento; 
    }
    
    @Override
    public BigDecimal cotizar(Integer cantidad) {
		return this.precioBase.multiply(BigDecimal.valueOf(cantidad));	    		
	}

	@Override
	public Articulo getArticulo() {
		return this;
	}
}
