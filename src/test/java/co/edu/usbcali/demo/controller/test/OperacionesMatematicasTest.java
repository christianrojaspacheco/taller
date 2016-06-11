package co.edu.usbcali.demo.controller.test;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.demo.dto.Resultado;

public class OperacionesMatematicasTest
{

	private static final Logger log = LoggerFactory.getLogger(OperacionesMatematicasTest.class);
	
	@Test
	public void sumarTest()
	{
		RestTemplate restTemplate = new RestTemplate();
		Resultado resultado = restTemplate.getForObject("http://localhost:8080/demoWeb/controller/operacionesMatematicas/sumar/20/10/", Resultado.class);
		assertNotNull("El resultado es null", resultado);
	}
	
	@Test
	public void restarTest()
	{
		RestTemplate restTemplate = new RestTemplate();
		Resultado resultado = restTemplate.getForObject("http://localhost:8080/demoWeb/controller/operacionesMatematicas/restar/20/10/", Resultado.class);
		assertNotNull("El resultado es null", resultado);
	}
	
	@Test
	public void multiplicarTest()
	{
		RestTemplate restTemplate = new RestTemplate();
		Resultado resultado = restTemplate.getForObject("http://localhost:8080/demoWeb/controller/operacionesMatematicas/multiplicar/20/10/", Resultado.class);
		assertNotNull("El resultado es null", resultado);
	}
	
	@Test
	public void dividirTest()
	{
		RestTemplate restTemplate = new RestTemplate();
		Resultado resultado = restTemplate.getForObject("http://localhost:8080/demoWeb/controller/operacionesMatematicas/dividir/10/4/", Resultado.class);
		assertNotNull("El resultado es null", resultado);
	}

}
