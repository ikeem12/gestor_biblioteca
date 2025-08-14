# Maven 

maven es una herramienta de gestion y construccion de proyecto java

**se encarga de:**

- Crear la estructura de carpetas estandar de un proyecto java.
- Gestionar dependencias(bibliotecas externas).
- Compilar el codigo.
- Ejecutar pruebas unitarias
- Empaquetar la aplicacion(en un .jar, .war, etc)
- Automatizar tareas repetitivas de desarrollo

en pocas palabras: Maven es como un asistente personal que construye, organiza y mantiene el proyecto

## pom.xml

pom.xml significa Project Object Model.

Es el archivo de configuración de Maven y le dice:

- Qué es el proyecto.
- Qué dependencias necesita.
- Qué versión de Java usa.
- Qué plugins o configuraciones adicionales usa para compilar, testear y 
empaquetar.

## Deglose de un pom.xml

````xml
<groupId>com.johan</groupId>
````
Identificador del grupo: normalmente es tu dominio al revés (ej. com.miempresa).

---

````xml
<artifactId>gestor_biblioteca</artifactId>
````
Identificador del artefacto: es el nombre de tu aplicación.
 
---

````xml
<version>1.0</version>
````
Versión del proyecto.

---

````xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
</properties>
````
Propiedades del proyecto:

- UTF-8 → codificación de archivos.
- 21 → Java 21 como versión para compilar.

---

````xml
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
</dependencies>
````
Aquí se definen dependencias.
En este caso, esta JUnit 4.11 para pruebas unitarias, y scope=test significa que solo se usará para correr tests, no en producción.

---

````xml
<build>
    <pluginManagement>
        <plugins>
            <!-- lista de plugins -->
        </plugins>
    </pluginManagement>
</build>
````
Esto define plugins de Maven:

- maven-compiler-plugin → compila el código.
- maven-jar-plugin → empaqueta en .jar.
- maven-surefire-plugin → ejecuta tests.
- maven-site-plugin → genera documentación del proyecto.