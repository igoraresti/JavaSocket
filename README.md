# JavaSocket

Este proyecto es una aplicación cliente-servidor simple utilizando Sockets en Java. El servidor recibe un mensaje del cliente, lo convierte a mayúsculas y lo devuelve.

## Estructura del Proyecto

El proyecto ha sido reestructurado para seguir el estándar de Maven:

- `src/main/java/javasocket/`: Contiene el código fuente de la aplicación.
- `src/test/java/javasocket/`: Contiene las pruebas unitarias.
- `pom.xml`: Archivo de configuración de Maven con las dependencias y plugins necesarios.

## Refactorización para Testabilidad

Se han realizado los siguientes cambios para facilitar las pruebas unitarias y alcanzar el 100% de cobertura:

- **Teclado.java**: Se añadió un método `setReader(BufferedReader)` para permitir la inyección de flujos de entrada en las pruebas, evitando la dependencia directa de `System.in`.
- **Client.java y Server.java**: Se añadieron constructores que permiten especificar el host y el puerto. Se utilizó el bloque `try-with-resources` para asegurar el cierre correcto de los sockets y flujos.
- **ClientApp.java y ServerApp.java**: Se mantuvieron como puntos de entrada simples para ejecutar las aplicaciones.

## Requisitos

- Java 21 o superior.
- Maven 3.x.

## Cómo ejecutar

### Compilar el proyecto

```bash
mvn compile
```

### Ejecutar las pruebas y generar el reporte de cobertura

```bash
mvn test jacoco:report
```

El reporte de cobertura se generará en `target/site/jacoco/index.html`.

### Ejecutar el Servidor

```bash
mvn exec:java -Dexec.mainClass="javasocket.ServerApp"
```

### Ejecutar el Cliente

```bash
mvn exec:java -Dexec.mainClass="javasocket.ClientApp"
```

## Cobertura de Código

El proyecto cuenta con una cobertura de código del **100%**, verificada mediante el plugin JaCoCo.
