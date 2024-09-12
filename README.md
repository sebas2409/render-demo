# PASOS

1. Cambiar paths en el /infra/compose.yml, en el /infra/default.conf y en al application.yml del proyecto render
2. Si dentro de la carpeta infra no existe la carpeta es_data, crearla
3. docker compose up -d dentro de la carpeta infra
4. Se deberian de haber creado dos archivos .html en la carpeta static, si es asi eliminaros, si no se han creado algo
   ha fallado
5. docker compose up -d dentro de la carpeta infra
6. tienes que crear un indice llamado ssi_v1 con un alias
   ssi, en localhost:5601 tienes el kibana y puedes ejecutar los siguientes comandos en la consola del kibana
   ```elasticsearch
   ### VER TODOS LOS INDICES
    GET /_cat/indices
   
   ### CREAR INDICE
   PUT /ssi_v1
   {
      "settings": {
         "number_of_shards": 1,
         "number_of_replicas": 1
      },
      "mappings": {
         "properties": {
         }
      }
   }
   
   ### CREAR ALIAS
   POST /_aliases
   {
      "actions": [
         {
            "add": {
               "index": "ssi_v1",
               "alias": "ssi"
            }
         }
      ]
   }
   ```
7. dentro del proyecto render hay un archivo request.http hay dos endpoints para crear ssi lo puedes hacer desde postman
   si no tienes IntelliJ Premium
8. Levantar el proyecto de spring boot
9. desde el navegador acceder a http://localhost para llamar al varnish
10. desde el navegador acceder a http://localhost:8090 para llamar al nginx y ver la diferencia
11. ver los archivos estaticos generados en la carpeta static 