//Roles y usuario admin
INSERT INTO roles (ROLE) VALUES ('ROLE_EPI');
INSERT INTO roles (ROLE) VALUES ('ROLE_ADMIN');
INSERT INTO roles (ROLE) VALUES ('ROLE_PASSWORD');
INSERT INTO roles (ROLE) VALUES ('ROLE_SENSITIVE');
INSERT INTO users (username, credentialsNonExpired, accountNonLocked, changePasswordNextLogin, completeName, created,createdBy, accountNonExpired, email, enabled, lastAccess, lastCredentialChange, modified, modifiedBy, password) VALUES ('admin', b'1', b'1', b'0', 'Administrador del Sistema', '2019-11-08 00:00:00', 'admin', b'1', 'waviles@clintonhealthaccess.org', b'1', '2019-11-08 00:00:00', '2019-11-08 00:00:00', '2019-11-08 00:00:00', 'admin', '6c36dc262b0e44be5811c2296669fc65643aec9dcaa4a76501e0a9508b633fd01ee59a207f8c6d68');
INSERT INTO userroles (ROLE, username, deviceid, status, pasive, recordDate,recordUser) VALUES ('ROLE_ADMIN', 'admin', 'admin', '2', '0', '2019-11-08 00:00:00', 'admin');
INSERT INTO userroles (ROLE, username, deviceid, status, pasive, recordDate,recordUser) VALUES ('ROLE_PASSWORD', 'admin', 'admin', '2', '0', '2019-11-08 00:00:00', 'admin');
INSERT INTO userroles (ROLE, username, deviceid, status, pasive, recordDate,recordUser) VALUES ('ROLE_EPI', 'admin', 'admin', '2', '0', '2019-11-08 00:00:00', 'admin');
INSERT INTO userroles (ROLE, username, deviceid, status, pasive, recordDate,recordUser) VALUES ('ROLE_SENSITIVE', 'admin', 'admin', '2', '0', '2019-11-08 00:00:00', 'admin');

//Mensajes plantilla
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('title', NULL, NULL, 'SISVIG', '0', 0, '0', 'SISVIG');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('heading', NULL, NULL, 'Dashboard', '0', 0, '0', 'Tablero de mando');


//Mensajes men�
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dashboards', NULL, NULL, 'Dashboards', '0', 0, '0', 'Tableros');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('front', NULL, NULL, 'Front', '0', 0, '0', 'Portada');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('epid', NULL, NULL, 'Epidemiology', '0', 0, '0', 'Epidemiolog�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('cases', NULL, NULL, 'Case Managenent', '0', 0, '0', 'Manejo de casos');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('surveillance', NULL, NULL, 'Surveillance', '0', 0, '0', 'Vigilancia');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('profile', NULL, NULL, 'My profile', '0', 0, '0', 'Mi perfil');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('messages', NULL, NULL, 'Messages', '0', 0, '0', 'Mensajes');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('logout', NULL, NULL, 'Logout', '0', 0, '0', 'Salir');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('about', NULL, NULL, 'MINSA Panam�', '0', 0, '0', 'MINSA Panam�');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('help', NULL, NULL, 'Help', '0', 0, '0', 'Ayuda');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('filters', NULL, NULL, 'Filters', '0', 0, '0', 'Filtros');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('language', NULL, NULL, 'Languaje', '0', 0, '0', 'Lenguaje');

//Mensajes utils
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('not', NULL, NULL, 'Notification', '0', 0, '0', 'Notificaci�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('session.expiring', NULL, NULL, 'your session is about to expire', '0', 0, '0', 'Su sesi�n est� a punto de expirar!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('session.expiring.confirm', NULL, NULL, 'do you want to continue with your session?', '0', 0, '0', 'Quiere continuar con su sesi�n?');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('session.expiring.time', NULL, NULL, 'your session will close in�', '0', 0, '0', 'Su sesi�n se cerrar� en');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('session.keep', NULL, NULL, 'keep session', '0', 0, '0', 'Mantener sesi�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('seconds', NULL, NULL, 'seconds', '0', 0, '0', 'segundos');



//Mensajes login
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login', NULL, NULL, 'Login', '0', 0, '0', 'Ingresar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.username', NULL, NULL, 'Username', '0', 0, '0', 'Nombre de usuario');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.accountExpired', NULL, NULL, 'User account has expired', '0', 0, '0', 'Cuenta de usuario ha expirado!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.accountLocked', NULL, NULL, 'User account is locked', '0', 0, '0', 'Cuenta de usuario est� bloqueada!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.accountNotLocked', NULL, NULL, 'User account is not locked', '0', 0, '0', 'Cuenta de usuario est� desbloqueada!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.badCredentials', NULL, NULL, 'User name or password is incorred', '0', 0, '0', 'Nombre de usuario o contrase�a incorrectos!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.credentialsExpired', NULL, NULL, 'User credentials have expired', '0', 0, '0', 'Credenciales de usuario han expirado!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.forgot.password', NULL, NULL, 'Forgot password?', '0', 0, '0', 'Olvid� contrase�a?');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.maxSessionsOut', NULL, NULL, 'You have an active session! You can�t create another one!', '0', 0, '0', 'Tiene una sesi�n activa! No puede crear otra!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.password', NULL, NULL, 'Password', '0', 0, '0', 'Contrase�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.userDisabled', NULL, NULL, 'User is disabled', '0', 0, '0', 'Usuario esta inactivo!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.userEnabled', NULL, NULL, 'User is enabled', '0', 0, '0', 'Usuario esta activo!');

//filtros
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('year', NULL, NULL, 'Year', '0', 0, '0', 'A�o');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('level', NULL, NULL, 'Level', '0', 0, '0', 'Nivel');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('all', NULL, NULL, 'All', '0', 0, '0', 'Todos');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('region.res', NULL, NULL, 'Region of residence', '0', 0, '0', 'Regi�n de residencia');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('region.samp', NULL, NULL, 'Region', '0', 0, '0', 'Regi�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('province.samp', NULL, NULL, 'Province', '0', 0, '0', 'Provincia');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('district.samp', NULL, NULL, 'District', '0', 0, '0', 'Distrito');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('correg.samp', NULL, NULL, 'Corregimiento', '0', 0, '0', 'Corregimiento');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('local.samp', NULL, NULL, 'Locality', '0', 0, '0', 'Localidad');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('foci.samp', NULL, NULL, 'Foci', '0', 0, '0', 'Foco');


//configuracion
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('timeview', NULL, NULL, 'View by', '0', 0, '0', 'Ver por');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('week.samp', NULL, NULL, 'Week', '0', 0, '0', 'Semana');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('week.dos', NULL, NULL, 'Onset week', '0', 0, '0', 'Semana de inicio de s�ntomas');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('day.samp', NULL, NULL, 'Day', '0', 0, '0', 'D�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('day.dos', NULL, NULL, 'Onset day', '0', 0, '0', 'Fecha de inicio de s�ntomas');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('month.samp', NULL, NULL, 'Month', '0', 0, '0', 'Mes');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('month.dos', NULL, NULL, 'Onset month', '0', 0, '0', 'Mes de inicio de s�ntomas');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ou.required', NULL, NULL, 'OU is required!', '0', 0, '0', 'El nombre de la unidad organizativa (localidad) es requerido!');



//Mensajes portada
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('confirmed', NULL, NULL, 'Confirmed Cases', '0', 0, '0', 'CASOS CONFIRMADOS');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('samples', NULL, NULL, 'Number of samples', '0', 0, '0', 'NUMERO MUESTRAS');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('localities', NULL, NULL, 'Active localities', '0', 0, '0', 'LOCALIDADES C/BUSQ');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('update', NULL, NULL, 'Update', '0', 0, '0', 'Actualizar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('select', NULL, NULL, 'Select value', '0', 0, '0', 'Seleccionar..');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('total', NULL, NULL, 'Total', '0', 0, '0', 'Total');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('totcases', NULL, NULL, 'Cases', '0', 0, '0', 'Casos');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('totsamples', NULL, NULL, 'Samples', '0', 0, '0', 'Muestras');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('refresh', NULL, NULL, 'Refresh', '0', 0, '0', 'Reiniciar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('canal', NULL, NULL, 'Canal', '0', 0, '0', 'Canal end�mico');	
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('tipobusq', NULL, NULL, 'Tipo de b�squeda', '0', 0, '0', 'Tipo de b�squeda');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('active', NULL, NULL, 'Active', '0', 0, '0', 'Activa');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('pasive', NULL, NULL, 'Pasive', '0', 0, '0', 'Pasiva');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('reactive', NULL, NULL, 'Reactive', '0', 0, '0', 'Reactiva');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('survey', NULL, NULL, 'Survey', '0', 0, '0', 'Encuesta');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('sdtb', NULL, NULL, 'ND', '0', 0, '0', 'SD');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('tipoesp', NULL, NULL, 'Specie', '0', 0, '0', 'Tipo de especie');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('notfound', NULL, NULL, 'Not found', '0', 0, '0', 'No encontrado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('notfoundmessage', NULL, NULL, 'Sorry, the page you\'re looking for can\'t be found', '0', 0, '0', 'Lo sentimos, la p�gina que solicit� no pudo ser encontrada.');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('denied', NULL, NULL, 'Access denied', '0', 0, '0', 'Acceso denegado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('deniedmessage', NULL, NULL, 'Sorry, the page you\'re looking for isn\'t available with your credentials', '0', 0, '0', 'Lo sentimos, la p�gina que solicit� no esta disponible con sus credenciales.');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('505', NULL, NULL, 'Something�s wrong!', '0', 0, '0', 'Ha ocurrido un error!');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('travel', NULL, NULL, 'Travel history', '0', 0, '0', 'Historia de viaje documentada');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('yes', NULL, NULL, 'Yes', '0', 0, '0', 'Si');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('no', NULL, NULL, 'No', '0', 0, '0', 'No');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('clas', NULL, NULL, 'Cases Classification %', '0', 0, '0', '% Casos Clasificados');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('cpdr', NULL, NULL, 'PDR with GG %', '0', 0, '0', '% PDR con Gota Gruesa');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('trat', NULL, NULL, 'Treatment rate', '0', 0, '0', 'Tasa de tratamiento');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('tratcomp', NULL, NULL, 'Treatment end rate', '0', 0, '0', 'Tasa de finalizaci�n de tratamiento');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('diasdx', NULL, NULL, 'Time between symptoms and dx', '0', 0, '0', 'Tiempo entre s�ntomas y diagn�stico');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('diastx', NULL, NULL, 'Time between dx and tx', '0', 0, '0', 'Tiempo entre diagn�stico y tratamiento');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('users', NULL, NULL, 'users', '0', 0, '0', 'Usuarios');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('admin', NULL, NULL, 'admin', '0', 0, '0', 'Administrar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('add', NULL, NULL, 'Create', '0', 0, '0', 'Agregar');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('unlock', NULL, NULL, 'unlock', '0', 0, '0', 'Desbloquear');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('user.created', NULL, NULL, 'user created', '0', 0, '0', 'Usuario creado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('user.updated', NULL, NULL, 'user updated', '0', 0, '0', 'Usuario actualizado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('usercred', NULL, NULL, 'password expired', '0', 0, '0', 'Contrase�a vencida');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('userdesc', NULL, NULL, 'user description', '0', 0, '0', 'Descripci�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('useremail', NULL, NULL, 'user email', '0', 0, '0', 'Correo');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('userexp', NULL, NULL, 'expired account', '0', 0, '0', 'Cuenta vencida');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('userlock', NULL, NULL, 'locked', '0', 0, '0', 'Bloqueado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('username', NULL, NULL, 'username', '0', 0, '0', 'Usuario');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('userroles', NULL, NULL, 'roles', '0', 0, '0', 'Roles');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('usuarioAdded', NULL, NULL, 'user added', '0', 0, '0', 'Usuario agregado!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('usuarioAll', NULL, NULL, 'all users are already added', '0', 0, '0', 'Todos los usuarios ya est�n agregados!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('usuarioDisabled', NULL, NULL, 'user is inactivated', '0', 0, '0', 'Usuario est� inactivo!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('usuarioEnabled', NULL, NULL, 'user is activated', '0', 0, '0', 'Usuario est� activo!');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('enable', NULL, NULL, 'Enable', '0', 0, '0', 'Habilitar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('enabled', NULL, NULL, 'Enabled', '0', 0, '0', 'Habilitado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('disabled', NULL, NULL, 'Disabled', '0', 0, '0', 'Deshabilitado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('end', NULL, NULL, 'End', '0', 0, '0', 'Finalizar');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('access', NULL, NULL, 'User access', '0', 0, '0', 'Accesos de usuario');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('actions', NULL, NULL, 'Actions', '0', 0, '0', 'Acciones');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('save', NULL, NULL, 'save', '0', 0, '0', 'Guardar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('cancel', NULL, NULL, 'Cancel', '0', 0, '0', 'Cancelar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ask.chgpass', NULL, NULL, 'Demand password change', '0', 0, '0', 'Exigir cambio de contrase�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('pass.updated', NULL, NULL, 'Password updated', '0', 0, '0', 'Su contrase�a ha sido actualizada');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('password.repeat', NULL, NULL, 'Repeat Password', '0', 0, '0', 'Repita la contrase�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('Pattern.password.format', NULL, NULL, 'At least 8 characters combining uppercase, lowercase, numbers and special characters', '0', 0, '0', 'Al menos 8 caracteres combinando may�sculas, min�sculas, numeros y caracteres especiales');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('process.errors', NULL, NULL, 'errors have occurred in the process ', '0', 0, '0', 'Han ocurrido errores en el proceso!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('process.success', NULL, NULL, 'the process has been successfully completed', '0', 0, '0', 'El proceso se ha completado exitosamente!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('process.wait', NULL, NULL, 'please wait', '0', 0, '0', 'Por favor espere..');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_ADMIN', NULL, NULL, 'administrator ', '0', 0, '0', 'Administrador');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_PASSWORD', NULL, NULL, 'Change password', '0', 0, '0', 'Cambio de contrase�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_EPI', NULL, NULL, 'Epidemiologist', '0', 0, '0', 'Epidemi�logo');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('changepass', NULL, NULL, 'Change password', '0', 0, '0', 'Cambiar contrase�a..');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('edit', NULL, NULL, 'Edit', '0', 0, '0', 'Editar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('empty', NULL, NULL, 'Blank', '0', 0, '0', 'En blanco');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('lock', NULL, NULL, 'Lock', '0', 0, '0', 'Bloquear');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('locked', NULL, NULL, 'Locked', '0', 0, '0', 'Bloqueado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('disable', NULL, NULL, 'Disable', '0', 0, '0', 'Deshabilitar');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('createdBy', NULL, NULL, 'Created by', '0', 0, '0', 'Creado por');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dateAdded', NULL, NULL, 'Date', '0', 0, '0', 'Fecha');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dateCreated', NULL, NULL, 'Date created', '0', 0, '0', 'Fecha creacion');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dateCredentials', NULL, NULL, 'Last change of password', '0', 0, '0', 'Ultimo cambio de contrasena');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dateModified', NULL, NULL, 'Date of modification', '0', 0, '0', 'Fecha de modificaci�n');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('lastAccess', NULL, NULL, 'Last access', '0', 0, '0', 'Ultimo acceso');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('confirm', NULL, NULL, 'Confirm', '0', 0, '0', 'Confirmar');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rolAdded', NULL, NULL, 'Added role', '0', 0, '0', 'Rol agregado!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rolAll', NULL, NULL, 'all roles are already added', '0', 0, '0', 'Todos los roles ya est�n agregados!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rolDisabled', NULL, NULL, 'role is inactive', '0', 0, '0', 'Rol esta inactivo!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rolEnabled', NULL, NULL, 'role is active', '0', 0, '0', 'Rol esta activo!');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('export', NULL, NULL, 'Export', '0', 0, '0', 'Exportar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('notifs', NULL, NULL, 'Notifications', '0', 0, '0', 'Notificaciones');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('maps', NULL, NULL, 'Maps', '0', 0, '0', 'Mapas');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('casesxreg', NULL, NULL, 'Cases by region', '0', 0, '0', 'Casos por regi�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('casesxdist', NULL, NULL, 'Cases by district', '0', 0, '0', 'Casos por distrito');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('casesxcorr', NULL, NULL, 'Cases by coreg', '0', 0, '0', 'Casos por corregimiento');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('casesxloc', NULL, NULL, 'Cases by localities', '0', 0, '0', 'Casos por localidades');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('foci', NULL, NULL, 'Foci', '0', 0, '0', 'Focos');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('code', NULL, NULL, 'Code', '0', 0, '0', 'C�digo');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('name', NULL, NULL, 'Name', '0', 0, '0', 'Descripci�n');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('lastYear', NULL, NULL, 'Last Year', '0', 0, '0', 'A�o Pasado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('thisYear', NULL, NULL, 'This Year', '0', 0, '0', 'Este A�o');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('today', NULL, NULL, 'Today', '0', 0, '0', 'Hoy');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('yesterday', NULL, NULL, 'Yesterday', '0', 0, '0', 'Ayer');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('7day', NULL, NULL, 'Last 7 Days', '0', 0, '0', '�ltimos 7 D�as');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('30day', NULL, NULL, 'Las 30 Days', '0', 0, '0', '�ltimos 30 D�as');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('lastMonth', NULL, NULL, 'Last Month', '0', 0, '0', 'Mes Pasado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('thisMonth', NULL, NULL, 'This Month', '0', 0, '0', 'Este Mes');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('noResults', NULL, NULL, 'There are no results', '0', 0, '0', 'No hay registros!');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('audittrail', NULL, NULL, 'Audit Trail', '0', 0, '0', 'Bitacora de cambios');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('entityClass', NULL, NULL, 'Class', '0', 0, '0', 'Clase');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('entityName', NULL, NULL, 'Name', '0', 0, '0', 'Nombre');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('entityProperty', NULL, NULL, 'Property', '0', 0, '0', 'Propiedad');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('entityPropertyNewValue', NULL, NULL, 'New value', '0', 0, '0', 'Nuevo valor');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('entityPropertyOldValue', NULL, NULL, 'Old value', '0', 0, '0', 'Valor anterior');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('modifiedBy', NULL, NULL, 'Modified by', '0', 0, '0', 'Modificado por');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('class pa.gob.minsa.sisvigmalariatablero.users.model.Authority', NULL, NULL, 'Role', '0', 0, '0', 'Rol');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('class pa.gob.minsa.sisvigmalariatablero.users.model.UserSistema', NULL, NULL, 'User', '0', 0, '0', 'Usuario');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('addedBy', NULL, NULL, 'Added by', '0', 0, '0', 'Agregado por');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('session', NULL, NULL, 'session ID', '0', 0, '0', 'Id de sesion');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ipaddress', NULL, NULL, 'IP address', '0', 0, '0', 'Direccion IP');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('logindate', NULL, NULL, 'Login date', '0', 0, '0', 'Fecha ingreso');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('logoutdate', NULL, NULL, 'Logout date', '0', 0, '0', 'Fecha salida');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('logouturl', NULL, NULL, 'Logout url', '0', 0, '0', 'URL salida');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_ADMIN', NULL, NULL, 'administrator ', '0', 0, '0', 'Administrador');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_PASSWORD', NULL, NULL, 'Change password', '0', 0, '0', 'Cambio de contraseña');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_EPI', NULL, NULL, 'epidemiologist ', '0', 0, '0', 'Espidemiólogo');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_SENSITIVE', NULL, NULL, 'Sensitive', '0', 0, '0', 'Info sensible');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('sensitive', NULL, NULL, 'Export with personal data', '0', 0, '0', 'Exportar con datos personales');



