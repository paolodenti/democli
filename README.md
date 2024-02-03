# Native Spring Boot CLI example

CLI Native test project using Spring Boot and Picocli.

Apply a message digest to a file or stdin, using the provided algorithm
(SHA-512 if not specified).

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
