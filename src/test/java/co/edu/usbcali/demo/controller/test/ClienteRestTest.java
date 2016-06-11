package co.edu.usbcali.demo.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.demo.dto.ClienteDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ClienteRestTest
{

    private long cliId = 111111L;
    
    @Test
    public void atest()
    {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCliDireccion("Carrrera 22");
        clienteDTO.setCliId(cliId);
        clienteDTO.setCliMail("ddfas@gmail.com");
        clienteDTO.setCliNombre("PEPE");
        clienteDTO.setCliTelefono("112233");
        clienteDTO.setTdocCodigo(10L);
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(
            "http://localhost:8080/demoWeb/controller/cliente/grabar", clienteDTO);
    }
    

    @Test
    public void btest()
    {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCliDireccion("Carrrera 22 modificada");
        clienteDTO.setCliId(cliId);
        clienteDTO.setCliMail("ddfas@gmail.com");
        clienteDTO.setCliNombre("PEPE");
        clienteDTO.setCliTelefono("112233");
        clienteDTO.setTdocCodigo(10L);
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(
            "http://localhost:8080/demoWeb/controller/cliente/modificar", clienteDTO);
    }
    
    @Test
    public void ctest()
    {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCliId(cliId);
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(
            "http://localhost:8080/demoWeb/controller/cliente/consultarClienteClientePorId/"+clienteDTO.getCliId(),
            ClienteDTO.class);
    }
    
    @Test
    public void etest()
    {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCliId(cliId);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(
            "http://localhost:8080/demoWeb/controller/cliente/borrar/"+ clienteDTO.getCliId());
        
    }

}
