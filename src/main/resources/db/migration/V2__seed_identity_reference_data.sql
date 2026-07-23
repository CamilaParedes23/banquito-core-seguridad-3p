INSERT IGNORE INTO PARAMETRO_SEGURIDAD (CODIGO,NOMBRE,VALOR_TEXTO,TIPO_DATO,DESCRIPCION) VALUES
('PASSWORD_MIN_LENGTH','Longitud minima password','10','INTEGER','Minimo de caracteres para passwords'),
('MAX_FAILED_ATTEMPTS','Intentos fallidos maximos','5','INTEGER','Bloqueo temporal despues de intentos fallidos'),
('ACCESS_TOKEN_MINUTES','Duracion access token','15','INTEGER','Minutos de validez del JWT'),
('REFRESH_TOKEN_DAYS','Duracion refresh token','7','INTEGER','Dias de validez refresh token');

INSERT IGNORE INTO ROL (CODIGO,NOMBRE,TIPO_ROL,DESCRIPCION) VALUES
('ADMIN_SEGURIDAD','Administrador de seguridad','INTERNO','Gestiona usuarios, roles y permisos'),
('CAJERO','Cajero de sucursal','INTERNO','Opera ventanilla'),
('OPERADOR_CONTABLE','Operador contable','INTERNO','Ejecuta tareas contables y EOD'),
('CLIENTE_PERSONA','Cliente persona natural','CLIENTE','Acceso de banca web personas'),
('CLIENTE_EMPRESA','Cliente empresa','CLIENTE','Acceso de banca web empresas'),
('SWITCH_SERVICE','Servicio Switch','SERVICIO','Cliente tecnico del Switch hacia Core'),
('CORE_SERVICE','Servicio Core interno','SERVICIO','Cliente tecnico entre microservicios Core');

INSERT IGNORE INTO PERMISO (CODIGO,NOMBRE,MODULO,ACCION,RECURSO) VALUES
('auth.user.manage','Gestionar usuarios','AUTH','MANAGE','USER'),
('core.customer.read','Consultar clientes','CUSTOMER','READ','CUSTOMER'),
('core.customer.create','Crear clientes','CUSTOMER','CREATE','CUSTOMER'),
('core.admin.config.read','Leer configuracion','ADMIN','READ','CONFIG'),
('core.admin.config.update','Actualizar configuracion','ADMIN','UPDATE','CONFIG'),
('core.account.balance.read','Consultar saldo','ACCOUNT','READ','BALANCE'),
('core.account.debit','Debitar cuenta','ACCOUNT','EXECUTE','DEBIT'),
('core.account.credit','Acreditar cuenta','ACCOUNT','EXECUTE','CREDIT'),
('core.account.transfer.p2p','Transferencia P2P','ACCOUNT','EXECUTE','TRANSFER'),
('core.teller.deposit','Deposito ventanilla','ACCOUNT','EXECUTE','TELLER_DEPOSIT'),
('core.teller.withdrawal','Retiro ventanilla','ACCOUNT','EXECUTE','TELLER_WITHDRAWAL'),
('core.reserve.create','Crear reserva pago masivo','ACCOUNT','CREATE','RESERVE'),
('core.reserve.consume','Consumir reserva pago masivo','ACCOUNT','EXECUTE','RESERVE_CONSUME'),
('core.reserve.release','Liberar reserva pago masivo','ACCOUNT','EXECUTE','RESERVE_RELEASE'),
('core.accounting.entry.create','Crear asiento contable','ACCOUNTING','CREATE','JOURNAL_ENTRY'),
('core.accounting.eod.run','Ejecutar EOD','ACCOUNTING','EXECUTE','EOD'),
('document.create','Crear documento','DOCUMENT','CREATE','DOCUMENT'),
('notification.send','Enviar notificacion','NOTIFICATION','EXECUTE','NOTIFICATION');

-- Asignaciones iniciales minimas de permisos por rol
INSERT IGNORE INTO ROL_PERMISO (ROL_ID, PERMISO_ID)
SELECT r.ID, p.ID FROM ROL r JOIN PERMISO p
WHERE (r.CODIGO='ADMIN_SEGURIDAD' AND p.CODIGO IN ('auth.user.manage'))
   OR (r.CODIGO='CAJERO' AND p.CODIGO IN ('core.customer.read','core.account.balance.read','core.teller.deposit','core.teller.withdrawal'))
   OR (r.CODIGO='OPERADOR_CONTABLE' AND p.CODIGO IN ('core.accounting.entry.create','core.accounting.eod.run'))
   OR (r.CODIGO='CLIENTE_PERSONA' AND p.CODIGO IN ('core.account.balance.read','core.account.transfer.p2p'))
   OR (r.CODIGO='CLIENTE_EMPRESA' AND p.CODIGO IN ('core.account.balance.read'))
   OR (r.CODIGO='SWITCH_SERVICE' AND p.CODIGO IN ('core.reserve.create','core.reserve.consume','core.reserve.release','core.account.credit','core.account.debit'))
   OR (r.CODIGO='CORE_SERVICE' AND p.CODIGO IN ('core.accounting.entry.create','document.create','notification.send'));

INSERT IGNORE INTO API_CLIENT (CLIENT_ID, CLIENT_SECRET_HASH, NOMBRE, SERVICIO_ORIGEN, TIPO_CLIENTE) VALUES
('banquito-core-api-gateway','CHANGE_ME_HASH','Core API Gateway','banquito-core-api-gateway','GATEWAY'),
('banquito-switch-api-gateway','CHANGE_ME_HASH','Switch API Gateway','banquito-switch-api-gateway','GATEWAY'),
('core-account-service','CHANGE_ME_HASH','Core Account Service','core-account-service','SERVICE'),
('core-accounting-service','CHANGE_ME_HASH','Core Accounting Service','core-accounting-service','SERVICE'),
('switch-pagos-internos-service','CHANGE_ME_HASH','Switch Pagos Internos','switch-pagos-internos-service','SERVICE');

INSERT IGNORE INTO API_CLIENT_SCOPE (API_CLIENT_ID, SCOPE)
SELECT c.ID, s.SCOPE FROM API_CLIENT c
JOIN (
  SELECT 'banquito-core-api-gateway' CLIENT_ID, 'gateway.core.route' SCOPE UNION ALL
  SELECT 'banquito-switch-api-gateway','gateway.switch.route' UNION ALL
  SELECT 'core-account-service','core.accounting.entry.create' UNION ALL
  SELECT 'core-account-service','notification.send' UNION ALL
  SELECT 'core-accounting-service','document.create' UNION ALL
  SELECT 'switch-pagos-internos-service','core.reserve.create' UNION ALL
  SELECT 'switch-pagos-internos-service','core.reserve.consume' UNION ALL
  SELECT 'switch-pagos-internos-service','core.reserve.release'
) s ON s.CLIENT_ID = c.CLIENT_ID;
