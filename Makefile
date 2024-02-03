ROOT_DIR:=$(shell dirname $(realpath $(firstword $(MAKEFILE_LIST))))

define checkstyle
#!/bin/sh
make style
exit $?
endef
export checkstyle

.PHONY=setup
setup:
	@cd $(ROOT_DIR) && \
echo "$$checkstyle" > .git/hooks/pre-push && chmod 755 .git/hooks/pre-push && echo "setup completed"

.PHONY=style
style:
	@cd $(ROOT_DIR) && \
./mvnw --no-transfer-progress checkstyle:check

.PHONY=clean
clean:
	@cd $(ROOT_DIR) && \
./mvnw --no-transfer-progress clean

.PHONY=compile
compile:
	@cd $(ROOT_DIR) && \
./mvnw --no-transfer-progress compile

.PHONY=build
build: clean
	@cd $(ROOT_DIR) && \
./mvnw native:compile -Pnative
