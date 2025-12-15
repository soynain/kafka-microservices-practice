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

Avances 12-12-2025

Hemos ya configurado un ejemplo básico de serializer y deserializer. En base a esto nos toca simplemente jugar con particiones
y tópicos. Por lo que entiendo, puedes tener N tópicos, los tópicos pueden tener M particiones. Si dos consumers se suscriben a la misma partición,
recibirán mensajes en orden, de lo contrario no se puede asegurar el orden de los mensajes.

Si dos consumers pertenecen al mismo grupo y partición, recibirán los mensajes de sus particiones correspondientes. Si pertenecen
a diferentes grupos, pueden recibir los mensajes de su partición más los de otras particiones, ya que Kafka envía una copia de los mensajes
a todos los grupos y particiones.

entonces, haciendo un esquema para recordarlo después, asi funciona el tema nomás

<img width="1056" height="865" alt="image" src="https://github.com/user-attachments/assets/d300d1de-a76f-4176-bd19-5de416ab2b0e" />

Grupos agrupan consumidores que consumen de una misma partición. 

Ya entendí que pex y aquí listo los escenarios, ya será lo último de práctica para lo escencial, para avanzar a kubernetes.


Mismo grupo en dos consumidores = solo uno recibe el mensaje
<img width="1074" height="725" alt="image" src="https://github.com/user-attachments/assets/f7c941af-23bb-4e97-9f6b-4158faadeffd" />

dos grupos distintos en un consumidor - ambos reciben el mensaje
<img width="1261" height="764" alt="image" src="https://github.com/user-attachments/assets/8f3374ca-c4d5-458e-b959-00fd92f7282d" />


Si no deseas que un evento sea replicado en otros grupos, debes usar otro tópico. O al menos que
asignes un tópico por particiones, repartes dinamicamente en dos listeners tales particiones
con un mismo groupId
<img width="1411" height="753" alt="image" src="https://github.com/user-attachments/assets/b32f2896-cbbd-44b7-ac93-aa1f72793aba" />

si mandas al 2, los dos listeners se van a activar, si mandas a particion 1, solo uno se activara. Esto es menos frecuente,
ya que la finalidad de kafka es balancear


AVANCE BÁSIQUIN Y FINAL de topicos kafka:
Ahora desde kubernetes ya hemos configurado dos deployments con una replica cada uno por deployment.yml

Aquí lo que se hizo fue crear un docker como server de kafka y no incluir el mismo en los k8s del cluster de kube.
Esto para separar responsabilidades puesto que la configuración de kafka debe ser aparte.

Se aprendió lo básico, services y deployment. Con esto ya termina la finalidad de este repositorio.

El próximo paso es: configurar dos micros para comunicación REST, y configurar más alla los kubes para exponerlos
y que estos puedan comunicarse por host del aplicativo.

La finalidad es integrar más cosas e irnos adentrando a lo básico de cloud. No replicaremos arquitecturas precisas
porque no es la finalidad crear cascarones. Es agarrar conceptos para sobrevivir a un próximo trabajo.


Algunos comandos que anotaré aqui para recordar, asi como evidencia de la práctica:

<img width="1737" height="1000" alt="image" src="https://github.com/user-attachments/assets/55a0dc59-687b-4cfb-9b07-2c6883931d51" />

Comandos para alterar números de partición y enviar mensajes por tópico
/opt/kafka $ bin/kafka-topics.sh --topic micro1 --describe --bootstrap-server localhost:9092
Topic: micro1   TopicId: SX5slt3VTJqgGXPIo0P1gg PartitionCount: 1       ReplicationFactor: 1    Configs: 
        Topic: micro1   Partition: 0    Leader: 1       Replicas: 1     Isr: 1  Elr:    LastKnownElr: 
/opt/kafka $ bin/kafka-topics.sh --topic micro1 --alter --partitions=6  --bootstrap-server localhost:9092
/opt/kafka $ bin/kafka-console-producer.sh --topic micro1 --bootstrap-server localhost:9092 --property parse-key=true --property key.separator=:
>3:aa
>1:a


Para aplicar services y deploys:

kubectl apply -f <Directorio y nombre del yaml>

Para borrar services y deploys
 kubectl delete service micro2-nodeport
 kubectl delete deployment micro1

Checar logas de arranque de los pods (replicas de una aplicación)
kubectl logs -f -l app=micro2

Comandos de docker
IMPORTANTE: No debes generar tu docker image con composer para el caso de las imagenes que uses en kubernetes, o al menos no configura
los docker networks para que no tengas problemas despues si configuras tu kafka dentro del mismo
 docker build -t proy-micro1:1.0 .     

Para la comunicación entre docker y docker si yusalo
docker compose up --build -d  

Información check del pod
kubectl describe pod micro1-5764cfd5dc-lmhhc  


Si ya hay arquitecturas cedimentadas en kafka, estos fundamentos te servirán. Lo demás corre con IA y la documentación que veas enspring boot.

No profundizes hasta que sea necesario. Nota mental

Recuerda diferenciar los diferentes tipos de configuración, si es contenedor a contenedor
configura con network docker y pon el nombre del hostname interno del contenedor en el yaml,
de lo contrario configuralo con localhost y host.docker.internal para que puedas conectarlo con tus pods de kubernetes.

<img width="1907" height="1076" alt="image" src="https://github.com/user-attachments/assets/bcbd5b15-8ba8-45c9-8f54-c9ddc4b339e9" />



