# Integracion activemq con spring-jms
Microservicio que se conecta con una activemq y queda a la espera de recibir mensajes.
Se proporciona un metodo POST para publicar mensajes.
#### Tecnologías
Java 11

Spring Boot 2.3.4.RELEASE

#### Configuración
Editar el fichero *application.yml* las entradas.
> **activemq.broker-url**: tcp://{ip donde está la imagen docker activemq corriendo}:61616
>
> **activemq.user**: usuario activemq
>
> **activemq.password**: contraseña activemq
>

#### Ejecución
Para probar el proceso hay que arracar primero activemq con ayuda de docker
> $ actimemq\\docker-compose up
>

Para comprobar que está correctamente funcionando activemq podemos entrar en el navegador a *http://{ip-activemq}:8161*
y nos pedirá el usuario/contraseña -> admin/admin por defecto para este ejemplo.

Despues levantamos la aplicación sprint-boot y ya podemos lanzar peticiones POST sobre el servicio *http://localhost:8080/publish* 
lo que realizará un publicado del mensaje en la cola rabbitmq y el proceso que está escuchando a la espera 
de mensajes de esa cola desencadenará una escritura del mensaje en el log del programa.


