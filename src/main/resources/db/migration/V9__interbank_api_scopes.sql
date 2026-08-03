-- R9-K.1: scopes mínimos para integración interbancaria.
-- No modifica autenticación Google, login tradicional ni scopes On-Us existentes.

INSERT IGNORE INTO PERMISO (CODIGO, NOMBRE, MODULO, ACCION, RECURSO) VALUES
('core.interbank.confirm', 'Confirmar transferencia interbancaria saliente', 'ACCOUNT', 'EXECUTE', 'INTERBANK_CONFIRM'),
('core.interbank.receive', 'Recibir transferencia interbancaria entrante', 'ACCOUNT', 'EXECUTE', 'INTERBANK_INCOMING'),
('core.interbank.read', 'Consultar transferencia interbancaria', 'ACCOUNT', 'READ', 'INTERBANK_TRANSFER'),
('core.interbank.reverse', 'Rechazar o compensar transferencia interbancaria', 'ACCOUNT', 'EXECUTE', 'INTERBANK_REVERSE');

INSERT IGNORE INTO ROL_PERMISO (ROL_ID, PERMISO_ID)
SELECT r.ID, p.ID
FROM ROL r
JOIN PERMISO p
WHERE r.CODIGO = 'SWITCH_SERVICE'
  AND p.CODIGO IN (
      'core.interbank.confirm',
      'core.interbank.read',
      'core.interbank.reverse'
  );

INSERT IGNORE INTO API_CLIENT (CLIENT_ID, CLIENT_SECRET_HASH, NOMBRE, SERVICIO_ORIGEN, TIPO_CLIENTE, ESTADO)
VALUES
('bank-banquill-interbank-client', 'CHANGE_ME_HASH', 'Banco BanQuill Interbank Client', 'bank-banquill-core', 'EXTERNAL', 'INACTIVO');

INSERT IGNORE INTO API_CLIENT_SCOPE (API_CLIENT_ID, SCOPE, DESCRIPCION)
SELECT c.ID, x.SCOPE, x.DESCRIPCION
FROM API_CLIENT c
JOIN (
    SELECT 'switch-pagos-internos-service' CLIENT_ID,
           'core.interbank.confirm' SCOPE,
           'Permite confirmar una transferencia Off-Us liquidada por el banco receptor' DESCRIPCION
    UNION ALL
    SELECT 'switch-pagos-internos-service',
           'core.interbank.read',
           'Permite consultar estado de transferencias interbancarias'
    UNION ALL
    SELECT 'switch-pagos-internos-service',
           'core.interbank.reverse',
           'Permite rechazar o compensar una transferencia Off-Us'
    UNION ALL
    SELECT 'bank-banquill-interbank-client',
           'core.interbank.receive',
           'Permite a Banco BanQuill enviar transferencias entrantes a BanQuito'
    UNION ALL
    SELECT 'bank-banquill-interbank-client',
           'core.interbank.read',
           'Permite a Banco BanQuill consultar el estado de sus transferencias'
) x ON x.CLIENT_ID = c.CLIENT_ID;
