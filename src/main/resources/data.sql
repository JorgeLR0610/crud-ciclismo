-- Configuración para MySQL
SET FOREIGN_KEY_CHECKS = 0;

-- Inserción de datos en tabla EQUIPOS
INSERT INTO `Equipos` (`nombreEquipo`, `nombreDirTec`) VALUES
('TIGER''S', 'ELDA IRASEMA BAEZA GUEVARA'),
('LOBOS', 'GERMAN ACOSTA CORRALES'),
('CHAMPIONS', 'ROSA HILDA TREJO LARA'),
('SNTE', 'JESUS HERMOSILLO'),
('OLD''S', 'ANGEL GABRIEL'),
('SIXTEN', 'REFUGIO CARRERA'),
('SICILIANOS', 'SANDRA CARRILLO'),
('GERMANOS', 'HERNAN DE LA GARZA'),
('COMUNICATIVOS', 'JESUS JOSE CONTRERAS MARTINEZ'),
('CUARENTAS', 'TIBURCIO LOPEZ'),
('ARVIC''S', 'PAZ RODRIGUEZ');

-- Inserción de datos en tabla CICLISTAS
INSERT INTO `Ciclistas` (`nombre`, `ap`, `am`, `edad`, `noEquipo`) VALUES
('ROCIO', 'NEVAREZ', 'GONZALEZ', 30, 1),
('ALFREDO', 'GOMEZ', 'FONG', 40, 2),
('MONICA', 'VILLA', 'MORALES', 35, 1),
('LUIS', 'LUJAN', 'VEGA', 36, 10),
('DAVID', 'VALTIERREZ', 'ANGEL', 45, 10),
('VICTOR', 'GONZALEZ', 'MIRANDA', 39, 2),
('SANDRA', 'RODRIGUEZ', 'RIOS', 36, 3),
('ALICIA', 'ROBLES', 'RUIZ', 36, 4),
('ALFREDO', 'NUÑEZ', 'BAEZA', 31, 1),
('ELIZARDO', 'PORRAS', 'ARMENDARIZ', 42, 5),
('HORACIO', 'CORRAL', 'MENDOZA', 35, 5),
('JUAN CARLOS', 'SANDOVAL', 'MORENO', 37, 9);

-- Inserción de datos en tabla ETAPAS
INSERT INTO `Etapas` (`km`, `salida`, `llegada`, `pais`) VALUES
(100, 'DELICIAS', 'CHIHUAHUA', 'MEXICO'),
(20, 'DELICIAS', 'MEOQUI', 'MEXICO'),
(500, 'MADRID', 'BARCELONA', 'ESPAÑA'),
(150, 'PARIS', 'TOLOUSE', 'FRANCIA'),
(200, 'MORELIA', 'SINSUNSAN', 'MEXICO'),
(20, 'GOMEZ', 'TORREON', 'MEXICO');

-- Inserción de datos en tabla PUERTOS
INSERT INTO `Puertos` (`nombrePuerto`, `altura`, `categoria`) VALUES
('PUERTO DE PALOS', 1000, 1),
('PUERTO PEÑASCO', 1500, 2),
('PUERTO PRINCESS', 2100, 3),
('CAÑITAS', 1100, 1),
('CATALUÑA', 2000, 3),
('LOS ANGELES', 1750, 3),
('MIAMI', 1434, 2),
('VERACRUZ', 1200, 1);

-- Inserción de datos en tabla MAILLOTS
INSERT INTO `Maillots` (`tipo`, `color`, `premio`) VALUES
('LIDER DE LA ETAPA', 'AMARILLO', 1000000),
('CAMPEON MUNDIAL', 'ARCOIRIS', 10000000),
('L.E. DE MENOR EDAD', 'BLANCO', 5000000);

-- Inserción de datos en tabla CONCENTRADO
-- Nota: Ajusta los valores de dorsal, noetapa, codigo e idPuerto según los IDs generados automáticamente
INSERT INTO `Concentrado` (`Dorsal`, `No. de etapa`, `CodigoMaillot`, `ID del puerto`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 2, 3, 2),
(4, 2, 2, 4),
(12, 3, 3, 3),
(11, 3, 1, 5);

-- Restaurar verificación de foreign keys
SET FOREIGN_KEY_CHECKS = 1;