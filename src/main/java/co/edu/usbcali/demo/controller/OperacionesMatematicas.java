package co.edu.usbcali.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.dto.Resultado;

@RestController
@RequestMapping("/operacionesMatematicas")
public class OperacionesMatematicas
{
	@RequestMapping(value="/sumar/{numero1}/{numeroDos}", method=RequestMethod.GET)
	public Resultado sumar(@PathVariable("numero1")Double n1, @PathVariable("numeroDos")Double n2) 
	{
		Resultado result = new Resultado();
		result.setValor(n1 + n2);
		return result;
	}
	
	@RequestMapping(value="/restar/{numero1}/{numeroDos}", method=RequestMethod.GET)
	public Resultado restar(@PathVariable("numero1")Double n1, @PathVariable("numeroDos")Double n2) 
	{
		Resultado result = new Resultado();
		result.setValor(n1 - n2);
		return result;
	}
	
	@RequestMapping(value="/multiplicar/{numero1}/{numeroDos}", method=RequestMethod.GET)
	public Resultado multiplicar(@PathVariable("numero1")Double n1, @PathVariable("numeroDos")Double n2) 
	{
		Resultado result = new Resultado();
		result.setValor(n1 * n2);
		return result;
	}
	
	@RequestMapping(value="/dividir/{numero1}/{numeroDos}", method=RequestMethod.GET)
	public Resultado dividir(@PathVariable("numero1")Double n1, @PathVariable("numeroDos")Double n2) 
	{
		Resultado result = new Resultado();
		result.setValor(n1 / n2);
		return result;
	}
	
}
