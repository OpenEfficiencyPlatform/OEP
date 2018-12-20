# Configuration

The configuration files need to be installed in the `conf` folder (without subdirectories). If using 
docker, then create a sub-directory for each organization (e.g. sanfrancisco) and copy the 
`oei.properties.template` file into the directory, remove the `.template`, and populate the file
with the correct credentials.

Use the following command when launching an instance to save your credentials into the right place.

```bash
docker run -it --rm -v "$(pwd)/conf/sanfrancisco/oei.properties":/opt/mule/conf/oei.properties oei
``` 
