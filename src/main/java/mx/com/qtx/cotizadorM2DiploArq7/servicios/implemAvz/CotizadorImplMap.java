package mx.com.qtx.cotizadorM2DiploArq7.servicios.implemAvz;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.Articulo;
import mx.com.qtx.cotizadorM2DiploArq7.servicios.core.ICotizador;

public class CotizadorImplMap implements ICotizador {
	
	private Map<Articulo,Integer> mapItems;

	public CotizadorImplMap() {
		super();
		this.mapItems = new HashMap<>();
	}

	@Override
	public void cotizar() {
	       BigDecimal total = BigDecimal.ZERO;
	       
	       System.out.println();
	       System.out.println("-".repeat(55));
	       System.out.println(" ".repeat(22) + "Cotización");
	       System.out.println("-".repeat(55));
	       for(Entry<Articulo, Integer>  item :this.mapItems.entrySet()) {
	            Articulo art = item.getKey();
	            int cantidad = item.getValue();
	            BigDecimal subtotal = art.cotizar(cantidad);
	            System.out.printf("%5d %-35s %12.2f\n", cantidad,
	            		                                art.getTipo().toString().toLowerCase() 
	            		                                   + " " + art.getModelo(), 
	            		                                subtotal);
	            total = total.add(subtotal);
	       }
	       System.out.println("-".repeat(55));
	       
	       System.out.print(" ".repeat(37));
	       System.out.printf("TOTAL = %9.2f\n\n", total);

	}

	@Override
	public void agregarItemCotizacion(Articulo articulo, Integer cantidad) {
		this.mapItems.put(articulo, cantidad);
	}

}
