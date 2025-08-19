# Arquitectura por capas

Este es el estilo más común y la base de muchos otros.  
La idea es dividir el sistema en **capas**, donde cada una tiene un rol bien definido.  

Regla de oro: **las capas superiores dependen de las inferiores, pero nunca al revés.**

---

## ¿Qué es una capa superior?

- Es la que está **más cerca del usuario o la entrada de datos**.  

- Interpreta qué quiere el usuario y le pasa la tarea a capas inferiores.  

Ejemplo: la capa de presentación (UI) que recibe una petición REST.

## ¿Qué es una capa inferior?

- Es la que está **más cerca del sistema, de los datos o del hardware**.  

- Hace el trabajo más "sucio" (guardar en base de datos, leer archivos, conectarse a APIs externas). 

Ejemplo: la capa de acceso a datos.

---

## Capas típicas 

Lo básico es pensar en 3 o 4 capas:

1. **Capa de Presentación (UI Layer)**  
- Donde interactúa el usuario o el cliente.  
- Solo recibe peticiones y devuelve respuestas.
- Ejemplo: controladores REST en Java con Spring Boot.  

2. **Capa de Negocio (Business Layer / Service Layer)**  
- Aquí va la lógica de negocio.  
- Aplica reglas, validaciones y procesos.  
- Ejemplo: `UserService` valida si un usuario puede registrarse.  

3. **Capa de Acceso a Datos (Data Access Layer / Repository Layer)**  
- Interactúa con la base de datos o fuentes externas.  
- Ejemplo: `UserRepository` usando JDBC o Hibernate.

4. **Capa de Infraestructura** (la más baja)  
- Drivers, conexiones, frameworks y configuraciones técnicas.  
- Ejemplo: el driver JDBC de MySQL.

---

## Flujo de dependencias

Las dependencias bajan, nunca suben:

[ Presentación ]
    ↓
[ Negocio / Servicios ]
    ↓
[ Acceso a datos ]
    ↓
[ Infraestructura ]

Con Inversión de Dependencias (DIP) puedo invertir la flecha cuando se necesita testear o desacoplar.

**NOTA**: Si la capa de presentación intentara saltarse al negocio y hablar directo con la base de datos, estaría rompiendo la 
arquitectura.

---

## Ventajas

1. **Organización clara** 
    Cada cosa tiene su lugar: presentación, lógica y datos.  
    Es más fácil entender el código y mantenerlo.

2. **Separación de responsabilidades (SoC)**  
   Si se cambia la base de datos, no hay que tocar la lógica de negocio ni la UI.  
   Cada capa se preocupa solo de su tarea.

3. **Facilita el mantenimiento**  
   Los cambios en una capa (ej. actualizar una librería de base de datos) no deberían romper otras capas.

4. **Reutilización**  
   Una capa de negocio puede usarse en distintos tipos de presentación (API REST, CLI, aplicación de escritorio).

5. **Base para otros estilos**  
   Clean Architecture y Hexagonal son evoluciones de esta.  

## Desventajas

1. **Rígida si no se diseña bien**  
   A veces obliga a pasar por varias capas aunque sea un cambio pequeño, lo que puede hacer más lento el desarrollo.

2. **Rendimiento**  
   Si hay demasiadas capas intermedias, cada llamada pasa por varias clases antes de llegar al resultado → sobrecarga.

3. **Romper las reglas es fácil**  
   Muchos terminan saltándose capas (“hago que el controlador llame directo a la base de datos”) y eso arruina la arquitectura.

4. **No siempre es la mejor opción**  
   En sistemas muy grandes, se puede volver monolítica y difícil de escalar horizontalmente.  
   En ese caso conviene pasar a microservicios o hexagonal.

5. **Puede llevar a “código burocrático”**  
   Mucho “pasa la petición de aquí para allá” sin lógica real, solo para cumplir con la capa.

---

## Escalar verticalmente y horizontalmente

**Escalar verticalmente** → hacer que una sola máquina sea más poderosa (más RAM, mejor CPU).
Ejemplo: “mi servidor se queda corto, le pongo más RAM”.

**Escalar horizontalmente** → en lugar de hacer más grande una sola máquina, añado más máquinas que trabajan juntas.
Ejemplo: “pongo 10 servidores web detrás de un balanceador de carga, y cada petición se reparte”.

Esto es clave porque los sistemas grandes del mundo (Netflix, Amazon, etc.) no dependen de un solo servidor gigante, sino de miles 
de servidores pequeños distribuidos.