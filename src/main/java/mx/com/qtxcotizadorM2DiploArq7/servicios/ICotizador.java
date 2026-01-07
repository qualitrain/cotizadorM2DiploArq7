package mx.com.qtxcotizadorM2DiploArq7.servicios;

import mx.com.qtxcotizadorM2DiploArq7.negocio.Articulo;

public interface ICotizador {
	void cotizar();
	void agregarItemCotizacion(Articulo articulo, Integer cantidad);
}
