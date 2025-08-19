# Arquitectura de software

una arquitectura en software es como el plano de una casa.
No es el ladrillo (el código), sino cómo se organiza y se conectan esos ladrillos para que todo funcione bien, sea mantenible y
escalable.

Si me pongo a escribir código sin pensar en la arquitectura, es como empezar a construir una casa poniendo ladrillos al azar: se 
puede levantar algo, pero probablemente será frágil, difícil de ampliar y un caos de mantener.

## Cosas claves de una arquitectura

- **Estructura** → define cómo se organizan las partes de el sistema.

Ejemplo: separar la parte que muestra cosas al usuario (UI) de la parte que guarda datos (DB).

- **Comunicación** → cómo se hablan esas partes entre sí.

Ejemplo: un servicio llama a un repositorio, o un microservicio expone una API REST.

- **Principios** → reglas para mantener el orden.

Ejemplo: “la capa de presentación no debería hablar directamente con la base de datos”.

- **Escalabilidad y mantenimiento** → el objetivo final.

Si mañana se quiere cambiar de MySQL a PostgreSQL, o añadiruna nueva funcionalidad, la arquitectura debería facilitarlo.

### En resumen:

Una arquitectura de software es la forma en la que organiza, se divide y se conecta el código para que el sistema sea entendible, 
fácil de mantener y escalable.

# estilos de arquitectura

hay varios estilos de arquitecturas con sus ventajas y desventajas, es importante saber cuando usar cada uno:

1. Arquitectura por capas (Layered Architecture)
2. Arquitectura Limpia (Clean Architecture)
3. Hexagonal (Ports & Adapters)