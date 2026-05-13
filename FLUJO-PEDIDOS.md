

## PROCESO DE INICIAR Y FINALIZAR UN PEDIDO EN RESTAURANTE


Como cliente quiero poder iniciar un pedido sobre un restaurante, debo poder añadir platos a mi pedido, quitar platos de mi pedido, y finalizar pedido.


1. restaurante-detalle.html (OK)
    * Botón "Iniciar pedido"  /pedidos/nuevo?restauranteId=1

2. PedidoController (OK)
    * @GetMapping("pedidos/nuevo")   para entrar al formulario
    * return pedido-form.html

3. pedido-form.html (OK)
    * restaurante precargado
    * numero mesa
    * numero de comensales
    * comentarios/sugerencias/alergias
    * Botón Enviar (POST/pedidos)

4. PedidoController
    * @PostMapping("Pedidos")
    * save
    * "redirect:/pedidos/" + pedido.getId();

5. pedido-detalle.html
    * Sección con platos Añadibles al pedido
    * En cada plato hay botón de Añadir --> POST /pedidos/1/lineas?platoId=1

6. PedidoController
    * @PostMapping("/pedidos/1/lineas")
    * crea un Linea Pedido apuntando al Pedido y al Plato y lo guarda
    *  "redirect:/pedidos/" + pedido.getId();

7. pedido-detalle.html (NUEVO)
    * Poder modificar cantidad Plato  --> POST /pedidos/{id}/lineas/{id} cantidad
    * Poder eliminar un LineaPedido --> GET /pedidos/{id}/lineas/{id}/borrar (OK)

8. pedido-detalle.html Botón Finalizar apunta GET /pedidos/{id}/finalizar

9. PedidoController
    * @GetMapping("/pedidos/{id}/finalizar")
        * NUEVO: requestparam Double propina
    * setEstadoPedido SERVIDO
    * recalcular precio
    * save
    *  "redirect:/pedidos/" + pedido.getId();