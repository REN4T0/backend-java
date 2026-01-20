## Estágio 1: Build (Compilação)
# ALTERADO: Mudamos de temurin-17 para temurin-21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /code

# Copia apenas o pom.xml primeiro para cachear as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e compila
COPY src ./src
RUN mvn clean package -DskipTests

## Estágio 2: Runtime (Execução)
# ALTERADO: Mudamos de temurin-17 para temurin-21 aqui também
FROM eclipse-temurin:21-jre-jammy
WORKDIR /work/

# Copia a pasta da aplicação gerada pelo Quarkus
COPY --from=build /code/target/quarkus-app/ /work/

# Expõe a porta
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]