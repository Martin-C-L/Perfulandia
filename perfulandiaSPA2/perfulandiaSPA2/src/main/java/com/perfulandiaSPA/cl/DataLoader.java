package com.perfulandiaSPA.cl;

import com.perfulandiaSPA.cl.model.*;
import com.perfulandiaSPA.cl.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
        
        //crear sucursal
        for(int i = 0; i < 3; i++){
            Sucursal sucursal = new Sucursal();
            sucursal.setNombre(faker.address().fullAddress());
            sucursal.setDireccion(faker.address().fullAddress());
            sucursalRepository.save(sucursal);
        }

        //crear usuario
        for(String rol : List.of("user", "admin")){
            Usuario usuario = new Usuario();
            usuario.setNombre(faker.name().firstName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setContraseña(faker.internet().password());
            usuario.setRol(rol);
            usuarioRepository.save(usuario);
        }

        //crear pedido
        for(int i = 0; i < 5; i++){
            Pedido pedido = new Pedido();
            pedido.setClienteId(faker.number().numberBetween(1, 100));
            pedido.setEstado("pendiente");
            pedido.setFechaCreacion(faker.date().birthday().toString());
            pedidoRepository.save(pedido);
        }

         //crear envio dirrecion_destino - estado - fecha_envio - pedido_id - traportista
         for(int i = 0; i < 5; i++){
            Envio Envio = new Envio();
            Envio.setDireccionDestino(faker.address().fullAddress());
            Envio.setEstado("pendiente");
            Envio.setFechaEnvio(faker.date().birthday().toString());
            Envio.setPedidoId(faker.number().numberBetween(1, 100));
            Envio.setTransportista(faker.name().firstName());
            envioRepository.save(Envio);
        }

        

        //crear producto
        for (int i = 0; i < 5; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().paragraph());
            producto.setPrecio(faker.number().randomDouble(2, 1, 100));
            producto.setStock(faker.number().numberBetween(1, 100));
            producto.setCategoria(faker.commerce().department());
            Sucursal sucursal = sucursalRepository.findById(random.nextInt(3) + 1).orElseThrow();
            producto.setSucursal(sucursal);
            productoRepository.save(producto);
        }

    }
}
