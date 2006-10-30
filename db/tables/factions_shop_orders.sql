CREATE TABLE `factions_shop_orders` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `shopentry_id` smallint(5) unsigned NOT NULL default '0',
  `user_id` int(11) NOT NULL default '0',
  `price` int(10) unsigned NOT NULL default '1',
  `count` int(10) unsigned NOT NULL default '1',
  `status` tinyint(3) unsigned NOT NULL default '0',
  `date` int(10) unsigned NOT NULL default '0',
  `adddata` text,
  PRIMARY KEY  (`id`),
  KEY `shopentry_id` (`shopentry_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
