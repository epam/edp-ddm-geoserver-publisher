# geoserver-publisher

This console application is used for creating following objects in GeoServer:
* workspace
* store
* layers

### Related components:
* PostgreSQL database
* GeoServer

### Usage
###### arguments
* `settings-file` - path of file with registry settings
###### environment variables
* `DB_HOST` - host of registry db
* `DB_NAME` - name of registry db
* `DB_PORT` - port of registry db
* `DB_SCHEMA` - schema of registry business db
* `GEOSERVER_LOGIN` - login of GeoServer admin to perform REST requests
* `GEOSERVER_PASSWORD` - password of GeoServer admin to perform REST requests
* `GEOSERVER_PUBLISHER_DB_USER` - db user to process schema
* `GEOSERVER_PUBLISHER_DB_PASSWORD` - password of db user to process schema
* `GEOSERVER_URL` - url of geoserver (without any path suffixes, example: http://localhost:8600)
* `STORE_DB_USER` - db user for GeoServer store usage
* `STORE_DB_PASSWORD` - password of db user for GeoServer store usage

### Local development:
###### Prerequisites:
* Registry is deployed in Postgres database (from env or with local citus + liquibase-ddm-ext registry deployment)
* GeoServer is configured and running (on env or via Docker 
  `docker run --name geoserver -e GEOSERVER_ADMIN_USER=...  -e GEOSERVER_ADMIN_PASSWORD=... -p 8680:8080 -d -t kartoza/geoserver`)

###### Steps:
1. (Optional) Package application into jar file with `mvn clean package`
2. Run application with your favourite IDE or via `java -jar ...` with jar file, created above, and passing parameters, required for usage

### License
geoserver-publisher is Open Source software released under the Apache 2.0 license.