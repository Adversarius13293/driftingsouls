CREATE TABLE `ammo` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `name` varchar(30) NOT NULL default '',
  `description` tinytext NOT NULL,
  `replaces` int(11) unsigned NOT NULL default '0',
  `res1` int(11) NOT NULL default '0',
  `res2` int(11) NOT NULL default '0',
  `res3` int(11) NOT NULL default '0',
  `type` varchar(10) NOT NULL default 'rak',
  `damage` smallint(6) unsigned NOT NULL default '0',
  `shielddamage` smallint(6) unsigned NOT NULL default '0',
  `subdamage` smallint(5) unsigned NOT NULL default '0',
  `trefferws` smallint(6) unsigned NOT NULL default '0',
  `smalltrefferws` tinyint(3) unsigned NOT NULL default '50',
  `torptrefferws` tinyint(3) unsigned NOT NULL default '0',
  `subws` smallint(5) unsigned NOT NULL default '0',
  `shotspershot` tinyint(3) unsigned NOT NULL default '1',
  `areadamage` tinyint(3) unsigned NOT NULL default '0',
  `destroyable` float(3,2) unsigned NOT NULL default '0.00',
  `flags` int(10) unsigned NOT NULL default '0',
  `itemid` tinyint(3) unsigned NOT NULL default '0',
  `dauer` float NOT NULL default '0.01',
  `buildcosts` varchar(120) NOT NULL default '0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,',
  `picture` varchar(20) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Raketen, Flak, Torpedomunition'; 

INSERT INTO `ammo` (`id`, `name`, `description`, `replaces`, `res1`, `res2`, `res3`, `type`, `damage`, `shielddamage`, `subdamage`, `trefferws`, `smalltrefferws`, `torptrefferws`, `subws`, `shotspershot`, `areadamage`, `destroyable`, `flags`, `itemid`, `dauer`, `buildcosts`, `picture`) VALUES (1, 'Flakmagazine', 'Die Standard-Flakmunition: \r\nKaliber 10,5 - Titanlegierung\r\nSprengkraft 20 kg TNT', 0, 21, 0, 0, 'flak', 6, 3, 0, 50, 65, 4, 0, 5, 1, 0.00, 0, 150, 0.05, '0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0', 'open.gif');
INSERT INTO `ammo` (`id`, `name`, `description`, `replaces`, `res1`, `res2`, `res3`, `type`, `damage`, `shielddamage`, `subdamage`, `trefferws`, `smalltrefferws`, `torptrefferws`, `subws`, `shotspershot`, `areadamage`, `destroyable`, `flags`, `itemid`, `dauer`, `buildcosts`, `picture`) VALUES (2, 'GTM MX-64 Rockeye', 'Die GTM MX-64 Rockeye ist eine schnellere, zielgenauere und mit gr&ouml;&szlig;erem Sprengkopf ausgestattete Variante der MX-50, welche zur Zeit des GroÃŸen Krieges verwendet wurde. ', 0, 21, 0, 0, 'rak', 45, 25, 0, 80, 50, 0, 0, 1, 0, 0.00, 0, 151, 0.1, '0,2,0,3,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0', 'rockeye.gif');
INSERT INTO `ammo` (`id`, `name`, `description`, `replaces`, `res1`, `res2`, `res3`, `type`, `damage`, `shielddamage`, `subdamage`, `trefferws`, `smalltrefferws`, `torptrefferws`, `subws`, `shotspershot`, `areadamage`, `destroyable`, `flags`, `itemid`, `dauer`, `buildcosts`, `picture`) VALUES (3, 'GTM-13 Helios', 'Keine Beschreibung verfügbar', 0, 16, 0, 0, 'torp', 6800, 6800, 0, 45, 14, 0, 0, 1, 0, 1.00, 0, 152, 1.9, '0,0,18,0,0,29,18,10,10,8,0,2,0,0,0,0,0,0,0', 'heliosbombe.gif');
INSERT INTO `ammo` (`id`, `name`, `description`, `replaces`, `res1`, `res2`, `res3`, `type`, `damage`, `shielddamage`, `subdamage`, `trefferws`, `smalltrefferws`, `torptrefferws`, `subws`, `shotspershot`, `areadamage`, `destroyable`, `flags`, `itemid`, `dauer`, `buildcosts`, `picture`) VALUES (5, 'Schwere Flakmagazine', 'Schwere-Flakmunition: \r\nKaliber 15,5 - Adamatiumlegierung,\r\nSprengkraft 35 kg TNT', 0, 21, 0, 0, 'heavyflak', 10, 5, 0, 50, 65, 8, 0, 5, 2, 0.00, 0, 154, 0.15, '0,0,0,1,3,0,3,0,0,0,0,0,0,0,0,0,0,0,0', 'open.gif');
INSERT INTO `ammo` (`id`, `name`, `description`, `replaces`, `res1`, `res2`, `res3`, `type`, `damage`, `shielddamage`, `subdamage`, `trefferws`, `smalltrefferws`, `torptrefferws`, `subws`, `shotspershot`, `areadamage`, `destroyable`, `flags`, `itemid`, `dauer`, `buildcosts`, `picture`) VALUES (7, 'GTM-4 Hornet', 'Die Hornet-Schwarmraketen sind zielsuchend und tödlich für Jäger und Bomber', 0, 42, 0, 0, 'rak', 50, 25, 0, 95, 58, 0, 0, 4, 0, 0.00, 0, 155, 0.3, '0,12,2,3,12,0,0,4,4,0,0,0,0,0,0,0,0,0,0,0', 'hornet.gif');
INSERT INTO `ammo` (`id`, `name`, `description`, `replaces`, `res1`, `res2`, `res3`, `type`, `damage`, `shielddamage`, `subdamage`, `trefferws`, `smalltrefferws`, `torptrefferws`, `subws`, `shotspershot`, `areadamage`, `destroyable`, `flags`, `itemid`, `dauer`, `buildcosts`, `picture`) VALUES (12, 'GTM-10 Piranha', 'Die Piranha ist die dritte Generation der alten Synaptic sowie Havoc-Raketen. Die Piranha erm&ouml;glicht einem Bombern einen effektiven Schutz vor angreifenden J&auml;gergruppen indem sie bei der Z&uuml;ndung alles im Umkreis von mehreren Metern zerst&ou', 0, 70, 0, 0, 'rak', 150, 160, 0, 95, 100, 0, 0, 1, 2, 0.00, 1, 153, 0.5, '0,20,2,0,20,0,10,5,5,5,0,0,0,0,0,0,0,0,0,0', 'piranha.gif');
INSERT INTO `ammo` (`id`, `name`, `description`, `replaces`, `res1`, `res2`, `res3`, `type`, `damage`, `shielddamage`, `subdamage`, `trefferws`, `smalltrefferws`, `torptrefferws`, `subws`, `shotspershot`, `areadamage`, `destroyable`, `flags`, `itemid`, `dauer`, `buildcosts`, `picture`) VALUES (14, 'GTM-11 Infyrno', 'Multihoming Rakete gegen JÃ¤ger und Bomber', 24, 61, 0, 0, 'rak', 150, 122, 0, 95, 60, 0, 0, 2, 3, 0.00, 0, 161, 1.5, '0,45,18,0,45,16,10,12,12,8,0,2,0,0,0,0,0,0,0', 'infyrno.gif');
INSERT INTO `ammo` (`id`, `name`, `description`, `replaces`, `res1`, `res2`, `res3`, `type`, `damage`, `shielddamage`, `subdamage`, `trefferws`, `smalltrefferws`, `torptrefferws`, `subws`, `shotspershot`, `areadamage`, `destroyable`, `flags`, `itemid`, `dauer`, `buildcosts`, `picture`) VALUES (20, 'NTF EM-Plasma', 'EM-Plasma ist primär zur Lahmlegung von Schilden gedacht - die Wirkung auf die normale Panzerung ist denkbar schlecht.', 0, 53, 0, 0, 'hhplasma', 500, 24000, 0, 80, 20, 0, 0, 1, 0, 0.00, 0, 168, 1, '0,20,0,2,20,5,10,5,0,0,0,0,0,0,0,0,0,0,0,0', 'emplasmashell.png');
INSERT INTO `ammo` (`id`, `name`, `description`, `replaces`, `res1`, `res2`, `res3`, `type`, `damage`, `shielddamage`, `subdamage`, `trefferws`, `smalltrefferws`, `torptrefferws`, `subws`, `shotspershot`, `areadamage`, `destroyable`, `flags`, `itemid`, `dauer`, `buildcosts`, `picture`) VALUES (33, 'Bergbausprengstoff', 'Der Bergbausprengstoff ersetzt das bisherige riskante Verfahren der Nutzung von Helios-Gefechtsk&ouml;pfen f&uuml;r den Asteroiden-Ausbau.', 0, 11, 0, 0, 'bum', 6800, 6800, 0, 0, 0, 0, 0, 1, 0, 1.00, 0, 182, 1, '0,0,15,10,10,20,8,0,10,0,0,1,0,0,0,0,0,0,0', 'explosive_large.png');