# PASOS

1. Cambiar paths en el docker compose y en al application.yml del proyecto render
2. docker compose up -d dentro de la carpeta infra
3. Se deberian de haber creado dos archivos .html en la carpeta static, si es asi eliminaros, si no se han creado algo ha fallado
4. docker compose up -d dentro de la carpeta render
5. Puede que el elasticsearch se haya creado sin indices, asi que tienes que crear un indice llamado ssi_v1 con un alias ssi, en localhost:5601 tienes el kibana
6. dentro del proyecto render hay un archivo request.http hay dos endpoints para crear ssi lo puedes hacer desde postman si no tienes IntelliJ Premium
7. Levantar el proyecto de spring boot
8. desde el navegador acceder a http://localhost para llamar al varnish
9. desde el navegador acceder a http://localhost:8090 para llamar al nginx y ver la diferencia
10. ver los archivos estaticos generados en la carpeta static 