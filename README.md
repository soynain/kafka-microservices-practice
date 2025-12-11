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


AVANCES 11 -12 - 2025

Debido a cierto rezago técnico derivado de una experiencia de secretario, me he puesto a indagar tecnologías en base
a mi última experiencia laboral (sin replicar nada exacto)

<img width="1910" height="1068" alt="image" src="https://github.com/user-attachments/assets/c76e23e7-2ecb-40bf-b746-e6e14f17b74c" />

Con esto hemos configurado apache kafka dentro de un contenedor, el cual hemos conectado con dos contenedores más por medio de una red externa
(bridge no puesto que, no están los proyectos en la misma carpeta).

Costó un guebote la configuración, ya me estaba desesperando porque ni chat gpt te da la respuesta. Si presentan problemas como yo, guiense
de este articulo de stack overflow que me salvó la vida https://stackoverflow.com/questions/71299048/kafka-consumer-is-not-receiving-messages-on-docker

Lo que prosigue es repasar los helpers de la libreria de kafka para poder implementar POJOS de acuerdo a los mensajes que se manden
por medio de Kafka, deserializarlos y ver que tan adaptales son esos escenarios, con eso tenemos la herramienta dominada.

Al final, wrapearemos esto en un cluster pequeño de kubernetes, implementando secrets y services para replicar (no implementaremos istio porque que gueba).

Se trata de aprender lo básico, no de armar mi ecosistema al 100
