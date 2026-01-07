package mx.com.qtxcotizadorM2DiploArq7.servicios;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.com.qtxcotizadorM2DiploArq7.negocio.Articulo;

public class Cotizador implements ICotizador{

    private List<Articulo> lstArticulos = new ArrayList<>();
    private List<Integer> lstCantidades = new ArrayList<>();

    public void agregarItemCotizacion(Articulo articulo, Integer cantidad) {
        lstArticulos.add(articulo);
        lstCantidades.add(cantidad);
    }

    public void cotizar() {
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < lstArticulos.size(); i++) {
            Articulo art = lstArticulos.get(i);
            int cantidad = lstCantidades.get(i);

            BigDecimal subtotal = art.cotizar(cantidad);
            System.out.println(art.getModelo() + " x " + cantidad + " : " + subtotal);

            total = total.add(subtotal);
        }

        System.out.println("TOTAL = " + total);
    }
}
