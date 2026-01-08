package mx.com.qtx.cotizadorM2DiploArq7.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author hp835
 * @version 1.0
 * @created 03-ene.-2026 12:42:30 p. m.
 */
public class PcBuilder {

	private static final int MAX_DISCOS = 4;
	private static final int MAX_MONITORES = 2;
	private static final int MAX_TARJETAS = 1;
	private List<DiscoDuro> lstDiscos;
	private List<Monitor> lstMonitores;
	private List<TarjetaVideo> lstTarjetas;
	private Map<String,Object> parametros;
	
	private static List<String> nomParamsValidos = List.of(Pc.CVE_MARCA_PC, Pc.CVE_MODELO_PC, Pc.CVE_SKU_PC);

	public PcBuilder(){
		this.lstDiscos = new ArrayList<>();
		this.lstMonitores = new ArrayList<>();
		this.lstTarjetas = new ArrayList<>();
		this.parametros = new HashMap<>();
	}

	/**
	 * 
	 * @param nombre
	 * @param valor
	 */
	public PcBuilder agregarParametro(String nombre, Object valor){
		if(parametroInvalido(nombre)) {
			throw new RuntimeException("Nombre de Parámetro inválido:" + nombre);
		}
		this.parametros.put(nombre, valor);
		return this;
	}

	private boolean parametroInvalido(String nombre) {
		if (!PcBuilder.nomParamsValidos.contains(nombre))
			return true;
		return false;
	}

	/**
	 * 
	 * @param disco
	 */
	public PcBuilder agregarDiscoDuro(IComponentePc disco){
		if(disco instanceof DiscoDuro)
			this.lstDiscos.add((DiscoDuro) disco);
		else {
			throw new RuntimeException("Se intenta agregar como disco duro un componente "
					+ "que no lo es:" + disco + ", tipo:" + disco.getClass().getName());
		}
		return this;
	}

	/**
	 * 
	 * @param monitor
	 */
	public PcBuilder agregarMonitor(IComponentePc monitor){
		if(monitor instanceof Monitor)
			this.lstMonitores.add((Monitor) monitor);
		else {
			throw new RuntimeException("Se intenta agregar como monitor un componente "
					+ "que no lo es:" + monitor + ", tipo:" + monitor.getClass().getName());
		}
		return this;
	}

	/**
	 * 
	 * @param tarjeta
	 */
	public PcBuilder agregarTarjetaVideo(IComponentePc tarjeta){
		if(tarjeta instanceof TarjetaVideo)
			this.lstTarjetas.add( (TarjetaVideo) tarjeta);
		else {
			throw new RuntimeException("Se intenta agregar como tarjeta de video un componente "
					+ "que no lo es:" + tarjeta + ", tipo:" + tarjeta.getClass().getName());
		}
		return this;
	}

	public Pc build(){
		if(this.configuracionValida()) {
			List<IComponentePc> lstComponentes = new ArrayList<>();
			lstComponentes.addAll(lstDiscos);
			lstComponentes.addAll(lstMonitores);
			lstComponentes.addAll(lstTarjetas);
			Pc nuevaPc = new Pc(
					(String) parametros.get(Pc.CVE_MARCA_PC), 
					(String) parametros.get(Pc.CVE_MODELO_PC), 
					(String) parametros.get(Pc.CVE_SKU_PC), 
					lstComponentes);
			return nuevaPc;
		}
		throw new RuntimeException("Configuración inválida: " + this.validarConfiguracion());
	}

	public Map<String,String> validarConfiguracion(){
		Map<String,String> errores = new TreeMap<>();
		if(this.lstDiscos.isEmpty()) {
			errores.put("Discos duro(s)", "No especificados");
		}
		if(this.lstDiscos.size() > MAX_DISCOS) {
			errores.put("Discos duro(s)", "Se excede el máximo (" + MAX_DISCOS + ")."
					+ " Hay " + this.lstDiscos.size());			
		}
		
		if(this.lstMonitores.isEmpty()) {
			errores.put("Monitor(es)", "No especificados");
		}
		if(this.lstMonitores.size() > MAX_MONITORES) {
			errores.put("Monitor(es)", "Se excede el máximo (" + MAX_MONITORES + ")."
					+ " Hay " + this.lstMonitores.size());			
		}
		
		if(this.lstTarjetas.isEmpty()) {
			errores.put("Tarjeta(s) de Video", "No especificados");
		}
		if(this.lstTarjetas.size() > MAX_TARJETAS) {
			errores.put("Tarjeta(s) de Video", "Se excede el máximo (" + MAX_TARJETAS + ")."
					+ " Hay " + this.lstTarjetas.size());			
		}
		
		if(!this.parametros.keySet().containsAll(nomParamsValidos)) {
			List<String> paramsFaltantes = nomParamsValidos.stream()
			                .filter(paramI->this.parametros.containsKey(errores) == false)
			                .toList();
			errores.put("parametros", "Faltan los parámetros:" + paramsFaltantes);
		}
		return errores;
	}

	private boolean configuracionValida(){
		if(this.validarConfiguracion().isEmpty())
			return true;
		return false;
	}

}