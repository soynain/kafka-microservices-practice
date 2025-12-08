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
