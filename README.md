# taller-especializacion
Taller Clase Especialización.

Para compilar se debe establecer en el pom.xml en la sesscion de la version de java para compilar de acuerdo a la configuración de su maquina. 
Si se tiene Java 1.7 o Java 1.8. 

Ejemplo:

Java 1.7

      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.3</version>
        <configuration>
          <source>1.7</source>
          <target>1.7</target>
        </configuration>
      </plugin>

Java 1.8

      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.3</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>