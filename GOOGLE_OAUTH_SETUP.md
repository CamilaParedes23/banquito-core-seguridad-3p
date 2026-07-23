# Google OAuth en Identity Access Service

La integración mantiene el login tradicional y no modifica el esquema de base de datos.

## Configuración obligatoria

Declarar el Client ID de la aplicación web creada en Google Auth Platform:

```bash
export GOOGLE_OAUTH_CLIENT_ID="replace-with-client-id.apps.googleusercontent.com"
```

Los valores de emisor y JWKS ya tienen defaults oficiales de Google y solo deben
sobrescribirse si Google cambia su configuración:

```bash
export GOOGLE_OAUTH_ISSUER="https://accounts.google.com"
export GOOGLE_OAUTH_JWK_SET_URI="https://www.googleapis.com/oauth2/v3/certs"
```

## Endpoint

```http
POST /api/v1/auth/google
Content-Type: application/json

{
  "credential": "GOOGLE_ID_TOKEN"
}
```

El servicio valida firma, expiración, emisor, audiencia y `email_verified`.
Luego busca un usuario existente mediante `EMAIL`, comprueba que esté activo y
emite el mismo access token y refresh token internos utilizados por el login
tradicional.

La cuenta Google no crea usuarios automáticamente. El correo verificado debe
existir previamente y ser único en `USUARIO_IDENTIDAD`.

## Respuestas de seguridad

- `401 GOOGLE_TOKEN_INVALID`: token inválido o expirado.
- `401 GOOGLE_EMAIL_NOT_VERIFIED`: Google no verificó el correo.
- `403 GOOGLE_USER_NOT_REGISTERED`: el correo no existe en BanQuito.
- `403 AUTH_USER_NOT_ACTIVE`: el usuario existe, pero está inactivo o bloqueado.

Nunca registrar ni enviar a logs el valor de `credential`.
