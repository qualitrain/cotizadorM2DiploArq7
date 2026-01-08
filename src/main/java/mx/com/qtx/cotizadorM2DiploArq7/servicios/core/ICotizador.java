package mx.com.qtx.cotizadorM2DiploArq7.servicios.core;

import mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.Articulo;

public interface ICotizador {
	void cotizar();
	void agregarItemCotizacion(Articulo articulo, Integer cantidad);
}
