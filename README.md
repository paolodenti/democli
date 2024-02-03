# Native Spring Boot CLI example

CLI Native test project using Spring Boot and Picocli.

## Build

```shell
make build
```

## Run

### Examples

* `./target/democli -h`
* `./target/democli pom.xml`
* `cat pom.xml | ./target/democli`
* `./target/democli --algorithm SHA-1 pom.xml`

## Development initial setup

```shell
make setup
make compile
```
