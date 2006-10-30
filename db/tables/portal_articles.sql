CREATE TABLE `portal_articles` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `title` varchar(80) NOT NULL default '',
  `author` varchar(40) NOT NULL default '',
  `article` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Portal -> Artikel'; 

INSERT INTO `portal_articles` (`id`, `title`, `author`, `article`) VALUES (1, 'Befehle/Alarmstufen', 'Finrod Telperien', 'so da ich bereits mehrmals  gefragt wurde, was bei den Befehlen die Einstellmöglichkeiten sind / sein sollen, hier mal der Auszug aus dem alten Forum\r\n\r\n[b]Grau[/b]\r\nSchiff kann ohne Gegenwehr gekapert werden \r\n[b]Gruen[/b]\r\nSchiff erwidert feindliches Feuer nicht \r\nCrew wehrt sich bei Kaperversuchen \r\n[b]Gelb[/b]\r\nSchiff erwidert Feuer im Fall eines Angriffs \r\n[b]Hellblau[/b]\r\nSchiff feuert, wenn Schiffe des Siedlers im Betroffenen Sektor attackiert werden \r\n[b]Blau[/b]\r\nSchiff feuert, wenn verbuendete Schiffe im Betroffenen Sektor attackiert werden \r\n[b]Dunkelblau[/b]\r\nSchiff feuert, wenn verbuendete Schiffe im Betroffenen Sektor angreifen \r\n[b]Rot[/b]\r\nSchiff feuert, wenn nicht-verbuendete Schiffe in den Sektor einfliegen \r\n\r\nAllianzlevel : (nip) \r\n[b]Niemand[/b] : \r\nNur der Eigentuemer hat Befehlsgewalt ueber das Schiff \r\n[b]Praesident[/b] : \r\nEigentuemer und Praesident haben Befehlsgewalt ueber das Schiff \r\n[b]Minister[/b] : \r\nEigentuemer,Praesident und Minister haben Befehlsgewalt ueber das Schiff \r\n[b]Alle[/b] : \r\nAlle Allianzmitglieder haben Befehlsgewalt ueber das Schiff\r\n\r\nhoffe es hilft euch\r\n\r\nMfG\r\nFinrod Telperien');
