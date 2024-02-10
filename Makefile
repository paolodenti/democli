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

.PHONY: version
version:
	@cd $(ROOT_DIR) && \
VERSION="$$(./mvnw -q -Dexec.executable=echo -Dexec.args='$${project.version}' --non-recursive exec:exec)" && \
echo "$${VERSION}"

.PHONY: upgrade
upgrade:
	@cd $(ROOT_DIR) && \
VERSION="$$(./mvnw -q -Dexec.executable=echo -Dexec.args='$${project.version}' --non-recursive exec:exec)" && \
NEXTVERSION=$$(echo $${VERSION} | awk -F. -v OFS=. '{$$NF += 1 ; print}') && \
echo "$${NEXTVERSION}" | ./mvnw -q versions:set -DgenerateBackupPoms=false

.PHONY: test
test: clean style
	@cd $(ROOT_DIR) && \
./mvnw --no-transfer-progress test

.PHONY=build
build: clean
	@cd $(ROOT_DIR) && \
./mvnw native:compile -Pnative
