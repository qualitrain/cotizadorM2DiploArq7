package mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.pc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.Articulo;
import mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.TipoArticulo;

public class Pc extends Articulo {
	public static final String CVE_MARCA_PC = "marca";
	public static final String CVE_MODELO_PC = "modelo";
	public static final String CVE_SKU_PC = "sku";

    // Una pc esta compuesta de otros artículos
    private List<IComponentePc> listComponentes = new ArrayList<>();
       
    protected Pc(String marca, String modelo, String sku,
			List<IComponentePc> listComponentes) {
		super(marca, modelo, null, null, sku);
		this.listComponentes = new ArrayList<>(listComponentes);
	}

    @Override
	public TipoArticulo getTipo() { return TipoArticulo.PC; }   
    
    public void agregarComponente(IComponentePc componente) {
    	this.listComponentes.add(componente);
    }
    
    @Override
	public BigDecimal cotizar(Integer cantidad) {
//        if (precioBase == null) {
//            return BigDecimal.ZERO;
//        }
//        
        BigDecimal total = new BigDecimal("0.00");
        for(IComponentePc cmpI:this.listComponentes) {
        	total = total.add(cmpI.getArticulo().cotizar(1));
        }
        
        return total.multiply(new BigDecimal("0.8"));
    }
    
    public static PcBuilder getBuilder() {
    	return new PcBuilder();
    }

}
