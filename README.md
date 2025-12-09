# kafka-microservices-practice
Derivado de una oferta laboral que ando siguiendo, me dijeron que hiciera unas prácticas con Kafka.

Hasta ahora la herramienta resulta sencilla, quise romperme la cabeza para instalarlo en windows pero que flojera, no quiero
activar WSDL y aprovechando que tengo docker solo ambiente un docker image pre hecho

<img width="1847" height="1075" alt="image" src="https://github.com/user-attachments/assets/8ae51141-8b95-4c3c-9380-28a7dfd0244e" />


Basandome en la mera documentación de spring boot, hemos lograo crear nuestro primer listener, por medio de estos eventos
y de la anotación @KafkaListener podemos trigerear eventos a nuestra elección.

Lo cual me hace pensar, por medio de métodos void podemos encender services, hacer transacciones, 
trigerear peticiones entre otros micros (lo cual no deberia porque rompe el principio del patrón pub sub).

En mi experiencia laboral muchas veces se depende de replicas para mantener las comunicaciones entre los mismos,
péro si una transacción falla se te pierde. Kafka lo almacena y lo reintenta.

AVANCE 08 12 2025
Ya hice un consumer y producer super básico con dos micros de spring boot, en efecto recibe el evento y así de esa manera
aseguras la sincronización entre microservicios, ya que los mensajes los guarda kafka en su memoria interna y no depende
de los hilos del micro o del contenedor

<img width="1517" height="788" alt="image" src="https://github.com/user-attachments/assets/840199bf-8191-40e2-9d59-530eba509b12" />


<img width="896" height="360" alt="image" src="https://github.com/user-attachments/assets/cb30612a-9361-413b-9e6a-46294f008b27" />


Ahora solo haré una prueba extra conectando los dos micros por medio de dos contenedores diferentes. configurando los docker composer correspondientes
o docker files y con eso tendremos dos plantillas de micros sincronizados.
