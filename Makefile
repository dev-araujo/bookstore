-include .env
export

PORT ?= 8080

PSQL_HOST = $(shell echo "$(DB_URL)" | sed -E 's|^jdbc:postgresql://||; s|[:/].*$$||')
PSQL_DB = $(shell echo "$(DB_URL)" | sed -E 's|^[^/]*//[^/]*/||; s|\?.*$$||')
PSQL_CONN = host=$(PSQL_HOST) dbname=$(PSQL_DB) user=$(DB_USER) sslmode=require

.PHONY: help run stop port conns build test clean env check-env db tables books

help:
	@echo "run     - start the application (mvn spring-boot:run)"
	@echo "stop    - stop the application (closes the connection pool)"
	@echo "port    - show which process is using port $(PORT)"
	@echo "conns   - count the open connections to the database"
	@echo "build   - compile, test and package (mvn -q verify)"
	@echo "test    - run the tests only"
	@echo "clean   - remove the target directory"
	@echo "env     - check that the .env variables are set"
	@echo "db      - open a psql shell on the Neon database"
	@echo "tables  - list the tables of the database"
	@echo "books   - select every row of tb_books"

run:
	mvn spring-boot:run

stop:
	@pkill -TERM -f "[B]ookstoreApplication" && echo "SIGTERM sent to the application" \
		|| echo "the application is not running"

port:
	@ss -ltnp 2>/dev/null | grep ":$(PORT)" || echo "port $(PORT) is free"

conns:
	@echo "open connections to the database: $$(ss -tn state established '( dport = :5432 )' | tail -n +2 | wc -l)"

build:
	mvn -q verify

test:
	mvn test

clean:
	mvn clean

env: check-env
	@echo "DB_URL=$(DB_URL)"
	@echo "DB_USER=$(DB_USER)"
	@echo "DB_PASSWORD is set"

check-env:
	@test -f .env || { echo ".env not found"; exit 1; }
	@test -n "$(DB_URL)" || { echo "DB_URL is not set"; exit 1; }
	@test -n "$(DB_USER)" || { echo "DB_USER is not set"; exit 1; }
	@test -n "$(DB_PASSWORD)" || { echo "DB_PASSWORD is not set"; exit 1; }

db: check-env
	@PGPASSWORD="$(DB_PASSWORD)" psql "$(PSQL_CONN)"

tables: check-env
	@PGPASSWORD="$(DB_PASSWORD)" psql "$(PSQL_CONN)" -c "\dt"

books: check-env
	@PGPASSWORD="$(DB_PASSWORD)" psql "$(PSQL_CONN)" -c "select * from tb_books;"
