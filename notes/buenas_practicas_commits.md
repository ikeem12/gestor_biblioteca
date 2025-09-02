# Buenas Prácticas para Commits

Un historial de commits limpio y claro facilita la colaboración y el
mantenimiento de cualquier proyecto.

------------------------------------------------------------------------

## Reglas principales

1.  **Commits pequeños y enfocados**

    -   Un commit debe contener un cambio lógico único (ej: arreglar un
        bug, añadir una función, actualizar docs).
    -   No mezclar varias cosas en un mismo commit.

2.  **Mensajes claros y descriptivos**

    -   Explicar **qué hiciste y por qué**, no cómo.
    -   Ejemplos:
        -   `fix: corrige error en validación de email`
        -   `feat: agrega endpoint de registro de usuarios`
        -   `docs: añade guía de instalación en README`

3.  **Usar un estilo consistente (Conventional Commits)**

        tipo(scope): descripción breve

    **Tipos comunes:**

    -   `feat`: nueva funcionalidad\
    -   `fix`: corrección de bug\
    -   `docs`: documentación\
    -   `style`: cambios de formato\
    -   `refactor`: cambios internos sin alterar comportamiento\
    -   `test`: añadir o modificar tests\
    -   `chore`: mantenimiento (dependencias, config, etc.)

4.  **Primera línea corta + detalles opcionales**

    -   Primera línea ≤ 72 caracteres.

    -   Ejemplo:

            fix(auth): corrige validación en login

            El error ocurría porque no se manejaba el caso de contraseña vacía.
            Ahora se valida antes de hacer la consulta a la base de datos.

5.  **Idioma**

    -   Inglés si es proyecto internacional.
    -   Español si es un proyecto personal/local.

6.  **Commits frecuentes, no gigantes**

    -   Evita commits enormes y difíciles de revisar.
    -   Pero tampoco abuses con commits irrelevantes de 1 línea.

7.  **Prueba el código antes de commitear**

    -   Asegúrate de no romper la rama principal.

------------------------------------------------------------------------

## Ejemplo de flujo de commits

1.  Creas una nueva función →\
    `feat(order): implementa cálculo de total con descuentos`

2.  Agregas tests →\
    `test(order): añade pruebas unitarias para descuentos`

3.  Corriges un bug detectado →\
    `fix(order): corrige error en descuento negativo`

------------------------------------------------------------------------

