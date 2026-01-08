package mx.com.qtx.cotizadorM2DiploArq7.ius;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.Articulo;
import mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.TipoArticulo;
import mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.pc.IComponentePc;
import mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.pc.Pc;
import mx.com.qtx.cotizadorM2DiploArq7.negocio.articulos.pc.PcBuilder;
import mx.com.qtx.cotizadorM2DiploArq7.servicios.core.ICotizador;
import mx.com.qtx.cotizadorM2DiploArq7.servicios.implemAvz.CotizadorImplMap;
import mx.com.qtx.cotizadorM2DiploArq7.servicios.implemBasicas.Cotizador;

public class TestCotizador {

    public static void main(String[] args) {
    	try {
    		testCotizador();
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		List.of(ex.getStackTrace()).stream().forEach(stI->{
    			System.out.println(stI.getClassName() + "." 
    							+ stI.getMethodName() 
    							+ " linea " + stI.getLineNumber() 
    							+ " en " + stI.getFileName());
    			});
    	}
    }

    public static void testCotizador() {
    	
    	Map<String,Object> mapParams = Map.of(Articulo.CVE_MARCA,"Seagate",
    			                              Articulo.CVE_MODELO, "Disco SSD 1TB",
    			                              Articulo.CVE_PRECIO_BASE, new BigDecimal("1200"),
    			                              Articulo.CVE_COSTO, new BigDecimal("600"),
    			                              Articulo.CVE_SKU,"X-SSD-1",
    			                              Articulo.CVE_CAPACIDAD_ALM,"1Tb");
    	Articulo disco = Articulo.crearArticulo(TipoArticulo.DISCO_DURO,mapParams);
    	
//    	DiscoDuro disco = new DiscoDuro("Seagate","Disco SSD 1TB",new BigDecimal("1200"),
//    			new BigDecimal("600"),"X-SSD-1","1Tb");

    	Map<String,Object> mapParamsTv = Map.of(
    			Articulo.CVE_MARCA,"Nvidia",
                Articulo.CVE_MODELO, "NAV-500",
                Articulo.CVE_PRECIO_BASE, new BigDecimal("800"),
                Articulo.CVE_COSTO, new BigDecimal("400"),
                Articulo.CVE_SKU,"NV-16-23",
                Articulo.CVE_MEMORIA,"16GB");
    	
    	Articulo ram = Articulo.crearArticulo(TipoArticulo.TARJETA_VIDEO, mapParamsTv);
    	
//        TarjetaVideo ram = new TarjetaVideo("Nvidia","NAV-500",new BigDecimal("800"),new BigDecimal("400"),"NV-16-23",
//        		"16GB");
    	
    	Map<String,Object> mapParamsMon = Map.of(
    			Articulo.CVE_MARCA,"Sony",
                Articulo.CVE_MODELO, "Rambo 2",
                Articulo.CVE_PRECIO_BASE, new BigDecimal("15000"),
                Articulo.CVE_COSTO, new BigDecimal("7000"),
                Articulo.CVE_SKU,"RAMBO-2026-23");
    	
    	Articulo monitor = Articulo.crearArticulo(TipoArticulo.MONITOR, mapParamsMon);

    	PcBuilder builderPc = Pc.getBuilder();
    	
    	Pc pc = builderPc.agregarParametro(Pc.CVE_MARCA_PC, "Dell")
		    	          .agregarParametro(Pc.CVE_MODELO_PC, "PC Gamer")
		    	          .agregarParametro(Pc.CVE_SKU_PC, "DGAME-3411")
		    	          .agregarDiscoDuro((IComponentePc)disco)
		    	          .agregarTarjetaVideo((IComponentePc)ram)
		    	          .agregarMonitor((IComponentePc)monitor)
		    	          .build();
    	
//        Pc pc = new Pc("Dell","PC Gamer", "DGAME-3411", List.of((IComponentePc) disco,(IComponentePc)ram));

        // Crear cotizador
//        ICotizador cot = new Cotizador();
        ICotizador cot = new CotizadorImplMap();
        cot.agregarItemCotizacion(pc, 1);
        cot.cotizar();
        
    	Map<String,Object> mapParamsTv2 = Map.of(
    			Articulo.CVE_MARCA,"Nvidia",
                Articulo.CVE_MODELO, "X-600",
                Articulo.CVE_PRECIO_BASE, new BigDecimal("2000.00"),
                Articulo.CVE_COSTO, new BigDecimal("700.00"),
                Articulo.CVE_SKU,"X-66-23-1",
                Articulo.CVE_MEMORIA,"16GB");
    	
    	Articulo tarjetaVideo = Articulo.crearArticulo(TipoArticulo.TARJETA_VIDEO, mapParamsTv2);  	
    	
//        TarjetaVideo tarjetaVideo = new TarjetaVideo("Nvidia","X-600",new BigDecimal("2000.00"),new BigDecimal("700.00"),"X-66-23-1",
//        		"16GB");
        
        // Crear OTRO cotizador
//        Cotizador cotizador2 = new Cotizador();
        ICotizador cotizador2 = new CotizadorImplMap();

        cotizador2.agregarItemCotizacion(tarjetaVideo, 5);
        cotizador2.agregarItemCotizacion(monitor, 3);

         cotizador2.cotizar();
    }
}
