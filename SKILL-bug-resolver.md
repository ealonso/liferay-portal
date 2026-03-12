---

name: bug-resolver
description: |
  Resuelve bugs de producto analizando tickets de Jira. Identifica el problema exacto, proporciona la solución con código listo para usar, y genera tests unitarios o de integración siguiendo los patrones del proyecto.

  Usa esta skill cuando el usuario pida ayuda para: resolver un bug, analizar un ticket de Jira, generar una solución con código y tests, entender dónde está el problema en el código, o crear tests para validar una corrección.

  Funciona especialmente bien con: tickets de LPD (Liferay Portal), proyectos Maven/Gradle, y código Java/JavaScript.

---

# Bug Resolver Skill

## Cómo usar esta Skill

El usuario simplemente comparte:

```
Ayúdame a resolver LPD-40919
Proyecto: /ruta/del/proyecto
```

O de forma más directa:

```
Me ayudas con este bug: https://liferay.atlassian.net/browse/LPD-40919
Ruta: /home/usuario/liferay-portal
```

## Tu flujo de trabajo

### Paso 1: Recopilar información

Pide al usuario (si no las tiene claras):
- 🎫 **Ticket Jira**: URL o clave (ej: LPD-40919)
- 📁 **Ruta del proyecto**: Dónde está el código (ej: /home/usuario/liferay-portal)
- 📝 **Descripción adicional** (opcional): Si tiene más detalles del problema

### Paso 2: Analizar el ticket

1. **Lee el título y descripción** del ticket

1. **Identifica**: Qué no funciona, en qué módulo, qué síntomas tiene

1. **Busca**: Pasos para reproducir, mensajes de error, logs

### Paso 3: Localizar el código

1. **Pregunta o deduce** dónde está el código afectado

1. **Busca patrones**: Nombres de clases, métodos mencionados

1. **Analiza la estructura**: Es un problema en lógica, validación, null pointer, etc.

Usa la ruta del proyecto para:
- Detectar si es Maven o Gradle
- Encontrar módulos (-test, -impl, -api)
- Identificar el lenguaje (Java, JavaScript, etc.)

### Paso 4: Diagnóstico

Explica:
- ❌ **Problema actual**: Qué está mal y por qué
- 📍 **Ubicación**: Archivo y línea aproximada
- 🔍 **Causa raíz**: Por qué ocurre el bug
- 💥 **Impacto**: Qué sistemas afecta

### Paso 5: Proporcionar solución

Proporciona código que:
- ✅ Sea **específico** para el proyecto
- ✅ **Copiar/pegar** sin modificaciones
- ✅ Siga los **patrones existentes**
- ✅ Incluya **comentarios claros**

Formato:

```java
// ANTES (problema)
public void metodo() {
    // código con bug
}

// DESPUÉS (solución)
public void metodo() {
    // código corregido
}
```

### Paso 6: Crear test

El test debe:
- ✅ Validar que el bug está **resuelto**
- ✅ Ser **unitario** o **integración** según corresponda
- ✅ Usar el **mismo framework** del proyecto
- ✅ Seguir el **naming** del proyecto

**Detecta el tipo de test:**
- **Unitario**: Si es lógica pura, métodos independientes
- **Integración**: Si involucra BD, APIs externas, múltiples módulos

**Busca ejemplos en el proyecto:**
- Archivos que terminan en `*Test.java` o `*.test.js`
- Paquetes `/test/` o módulos `-test`
- Imports usados (JUnit, TestNG, Jest, etc.)

Ejemplo para Java/Maven:

```java
@Test(expected = UserNotFoundException.class)
public void testGetUserNotFound() {
    userService.getUser(99999L);
}
```

### Paso 7: Instrucciones de integración

Explica cómo integrar:

1. **Dónde copiar** el código (archivo y ruta exacta)

1. **Qué reemplazar** (líneas viejas → nuevas)

1. **Cómo ejecutar** los tests

1. **Qué validar** para confirmar que funciona

## Ejemplo completo

### Input:

```
Ayúdame con LPD-40919
Liferay Portal: /home/usuario/liferay-portal
```

### Output esperado:

```
═════════════════════════════════════════════════════════════
🐛 ANÁLISIS: LPD-40919 - NullPointerException en UserService
═════════════════════════════════════════════════════════════

📍 UBICACIÓN
Archivo: portal-kernel/src/main/java/com/liferay/portal/kernel/service/UserService.java
Línea: 156

❌ PROBLEMA ACTUAL
El método getUser() usa .get() sin validar si existe:
- Usuario no encontrado → excepción sin control
- Mensaje de error poco claro
- No hay forma de saber si falló o si regresa null

🔍 CAUSA RAÍZ
Optional.get() lanza NoSuchElementException si no hay elemento.
Debería usar .orElseThrow() con excepción personalizada.

═════════════════════════════════════════════════════════════
✅ SOLUCIÓN
═════════════════════════════════════════════════════════════

ANTES (línea 156):
public User getUser(Long userId) {
    return userRepository.findById(userId).get();  // ❌ NPE si no existe
}

DESPUÉS:
public User getUser(Long userId) throws UserNotFoundException {
    return userRepository.findById(userId)
        .orElseThrow(() -> 
            new UserNotFoundException("Usuario no encontrado: " + userId)
        );
}

═════════════════════════════════════════════════════════════
🧪 TEST UNITARIO
═════════════════════════════════════════════════════════════

Archivo: portal-kernel-test/src/test/java/.../UserServiceTest.java

@Test(expected = UserNotFoundException.class)
public void testGetUserNotFound() {
    // Setup
    Long nonExistentId = 99999L;

    // Act & Assert
    userService.getUser(nonExistentId);
}

@Test
public void testGetUserFound() {
    // Setup
    User expectedUser = new User(1L, "test@example.com");
    when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));

    // Act
    User result = userService.getUser(1L);

    // Assert
    assertEquals(expectedUser, result);
}

═════════════════════════════════════════════════════════════
📋 CÓMO INTEGRAR
═════════════════════════════════════════════════════════════

1. Abre: portal-kernel/src/main/java/com/liferay/portal/kernel/service/UserService.java
2. Reemplaza la línea 156 con el código "DESPUÉS"
3. Copia el test en: portal-kernel-test/src/test/java/.../UserServiceTest.java
4. Ejecuta: mvn test -Dtest=UserServiceTest
5. Verifica: ✓ Todos los tests pasan

═════════════════════════════════════════════════════════════
```

## Tips importantes

### Para proyectos Maven:
- Busca `pom.xml` para ver dependencias de test
- Módulos son carpetas con `src/main/java` y `src/test/java`
- Módulos de test terminan en `-test`

### Para proyectos Gradle:
- Lee `build.gradle` para dependencias
- Estructura similar a Maven
- Test con: `gradle test`

### Para código Java:
- Detecta: JUnit, TestNG, Mockito
- Busca `@Test`, `@Before`, `@Mock`
- Usa `when()`, `assertEquals()`, `verify()`

### Para código JavaScript:
- Detecta: Jest, Mocha, Testing Library
- Busca `describe()`, `it()`, `expect()`
- Usa `test()`, `toEqual()`, `toThrow()`

## Qué hacer si...

### El ticket no está disponible
- Pregunta al usuario que describe el problema
- "¿Qué error te da?", "¿Dónde sucede?", "¿Pasos para reproducir?"
- Procede igual sin la URL

### No puedes encontrar el código
- Pregunta: "¿En qué módulo está?", "¿Nombre de la clase?"
- Busca patrones en la estructura
- Ofrece alternativas: "Podría ser en UserService o UserImpl"

### El proyecto tiene estructura rara
- Explora directorios: `ls -la /ruta/proyecto`
- Busca `pom.xml`, `build.gradle`, `package.json`
- Adapta el flujo al proyecto real

### Hay múltiples posibles soluciones
- Presenta las opciones
- Explica pros/contras de cada una
- Recomenda la mejor
- Genera test para la solución recomendada

## Salida esperada

Siempre proporciona:

1. ✅ Análisis claro del problema

1. ✅ Ubicación exacta (archivo + línea)

1. ✅ Código antes/después

1. ✅ Test completo y funcional

1. ✅ Instrucciones de integración

1. ✅ Validación: cómo verificar que funciona

**El usuario debe poder copiar/pegar directamente sin preguntas adicionales.**

---

## Recuerda

- 🎯 **Sé específico**: No digas "hay un bug", di dónde y por qué
- 💾 **Código listo**: Copiar/pegar sin cambios
- 🧪 **Tests reales**: Que funcionen en su proyecto
- 📝 **Pasos claros**: Integración sin dudas
- ⚡ **Rápido**: Análisis en máximo 2-3 intercambios

¡A resolver bugs!