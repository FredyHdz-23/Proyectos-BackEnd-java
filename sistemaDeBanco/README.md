# Sistema de Banco en Java

## Descripción

Este proyecto es una aplicación de consola desarrollada en **Java** que simula el funcionamiento básico de un sistema bancario. Permite gestionar cuentas bancarias, realizar operaciones financieras y mantener un historial de movimientos.

El objetivo del proyecto es aplicar conceptos fundamentales de **Programación Orientada a Objetos (OOP)** y buenas prácticas de diseño en Java, como:

* Encapsulación
* Uso de `enum`
* Manejo de excepciones
* Patrón **Singleton**
* Separación de responsabilidades entre clases
* Registro de historial de operaciones

---

# Funcionalidades

El sistema permite realizar las siguientes operaciones:

### 1. Crear cuenta

Permite registrar una nueva cuenta bancaria con:

* Número de cuenta (exactamente **6 dígitos**)
* Tipo de cuenta
* Saldo inicial

### 2. Buscar cuenta

Permite consultar una cuenta existente utilizando su número.

### 3. Depositar dinero

Agrega dinero al saldo de una cuenta.

### 4. Retirar dinero

Permite retirar dinero si el saldo es suficiente.

### 5. Transferir dinero

Permite transferir dinero entre dos cuentas del sistema.

La transferencia genera dos movimientos:

* **Salida** en la cuenta origen
* **Entrada** en la cuenta destino

### 6. Imprimir historial

Muestra todos los movimientos realizados en una cuenta.

### 7. Mostrar cuentas

Lista todas las cuentas registradas en el banco.

### 8. Eliminar cuenta

Permite eliminar una cuenta del sistema.

---

# Estructura del proyecto

El proyecto está organizado en varias clases que separan responsabilidades.

```
src/
 ├── Main.java
 ├── Banco.java
 ├── Cuenta.java
 ├── Movimiento.java
 ├── TipoOperaciones.java
 └── TipoCuenta.java
```

## Main

Se encarga de:

* Mostrar el menú
* Leer datos del usuario
* Validar entradas
* Llamar a los métodos del banco

## Banco

Es la clase central del sistema.

Responsabilidades:

* Almacenar todas las cuentas
* Buscar cuentas
* Realizar operaciones bancarias
* Coordinar transferencias

Implementa el patrón **Singleton** para asegurar que exista **un solo banco en el sistema**.

## Cuenta

Representa una cuenta bancaria.

Contiene:

* Número de cuenta
* Tipo de cuenta
* Saldo
* Lista de movimientos

Además maneja el historial de operaciones realizadas.

## Movimiento

Representa una operación financiera realizada en una cuenta.

Contiene:

* ID del movimiento
* Tipo de operación
* Monto
* Información de la cuenta relacionada
* Registro de saldo antes y después

## TipoOperaciones

`enum` que define los tipos de operaciones posibles:

* DEPOSITAR
* RETIRAR
* TRANSFERENCIA_ENTRADA
* TRANSFERENCIA_SALIDA

## TipoCuenta

`enum` que define los tipos de cuenta disponibles:

* AHORROS
* CORRIENTE

---

# Validaciones implementadas

El sistema incluye varias validaciones para evitar operaciones inválidas.

### Número de cuenta

Debe tener exactamente **6 dígitos**.

Ejemplo válido:

```
123456
```

### Montos

* Deben ser positivos
* No se permite retirar más dinero del saldo disponible

### Tipo de cuenta

Debe coincidir con uno de los valores del `enum`.

Ejemplo:

```
AHORROS
CORRIENTE
```

### Manejo de errores

El sistema utiliza excepciones como:

```
IllegalArgumentException
```

para controlar errores de entrada o lógica.

---

# Ejemplo de uso

```
*** Bienvenido al Banco de tus sueños ***

1. Crear Cuenta
2. Buscar cuenta
3. Depositar
4. Retirar
5. Transferir
6. Imprimir historial
7. Mostrar cuentas
8. Eliminar cuenta
9. Salir
```

Ejemplo de transferencia:

```
Cuenta origen: 123456
Cuenta destino: 654321
Monto: 50000

Transferencia realizada correctamente.
```

---

# Conceptos de programación aplicados

Este proyecto implementa varios conceptos importantes de desarrollo en Java:

### Programación Orientada a Objetos

* Clases
* Objetos
* Encapsulación
* Separación de responsabilidades

### Enumeraciones (`enum`)

Se utilizan para representar valores constantes del sistema.

### Manejo de excepciones

Se usan para validar entradas y evitar estados inválidos.

### Patrón Singleton

La clase `Banco` utiliza este patrón para garantizar que exista una única instancia del banco.

### Colecciones

Se utilizan estructuras como:

```
ArrayList
```

para almacenar:

* cuentas
* historial de movimientos

---

# Posibles mejoras futuras

Algunas mejoras que podrían implementarse en el futuro:

* Persistencia de datos en archivos o base de datos
* Interfaz gráfica
* Autenticación de usuarios
* Generación de reportes
* Uso de `Map` para mejorar la búsqueda de cuentas
* API REST para convertir el sistema en un backend

---

# Cómo ejecutar el proyecto

1. Clonar el repositorio

```
git clone <url-del-repositorio>
```

2. Compilar el proyecto

```
javac *.java
```

3. Ejecutar la aplicación

```
java Main
```

---

# Autor
**Fredy Alexander Hernández Castañeda**  

Proyecto desarrollado como práctica para reforzar conceptos de **Java y programación orientada a objetos**, simulando un sistema bancario simple en consola.
