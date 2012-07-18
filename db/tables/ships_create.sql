CREATE TABLE IF NOT EXISTS `ships` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT 'noname',
  `type` int(11) NOT NULL DEFAULT '0',
  `modules` int(11) DEFAULT NULL,
  `cargo` text NOT NULL,
  `x` int(11) NOT NULL DEFAULT '1',
  `y` int(11) NOT NULL DEFAULT '1',
  `system` tinyint(4) NOT NULL DEFAULT '1',
  `status` varchar(255) NOT NULL DEFAULT '',
  `crew` int(11) unsigned NOT NULL DEFAULT '0',
  `e` int(11) unsigned NOT NULL DEFAULT '0',
  `s` int(11) unsigned NOT NULL DEFAULT '0',
  `hull` int(11) unsigned NOT NULL DEFAULT '1',
  `shields` int(11) unsigned NOT NULL DEFAULT '0',
  `ablativeArmor` int(10) unsigned NOT NULL DEFAULT '0',
  `heat` text NOT NULL,
  `engine` int(11) NOT NULL DEFAULT '100',
  `weapons` int(11) NOT NULL DEFAULT '100',
  `comm` int(11) NOT NULL DEFAULT '100',
  `sensors` int(11) NOT NULL DEFAULT '100',
  `docked` varchar(20) NOT NULL DEFAULT '',
  `alarm` int(11) NOT NULL DEFAULT '0',
  `fleet` int(11) DEFAULT NULL,
  `battle` smallint(5) unsigned DEFAULT NULL,
  `battleAction` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `jumptarget` varchar(100) NOT NULL DEFAULT '',
  `oncommunicate` text,
  `lock` varchar(9) DEFAULT NULL,
  `visibility` mediumint(9) DEFAULT NULL,
  `onmove` text,
  `respawn` tinyint(4) DEFAULT NULL,
  `unitcargo` varchar(120) DEFAULT NULL,
  `version` int(10) unsigned NOT NULL DEFAULT '0',
  `nahrungcargo` int(11) NOT NULL DEFAULT '0',
  `scriptData_id` int(11) DEFAULT NULL,
  einstellungen_id int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `coords` (`system`,`x`,`y`),
  KEY `status` (`status`),
  KEY `type` (`type`),
  KEY `battle` (`battle`),
  KEY `docked` (`docked`),
  KEY `owner` (`owner`,`id`),
  KEY `ships_fk_ship_fleets` (`fleet`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 PACK_KEYS=0;
