CREATE TABLE `portal_facts` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `class` varchar(16) NOT NULL default '',
  `title` varchar(64) NOT NULL default '',
  `text` text NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `class` (`class`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Kleine Artikel zu diversen Dingen aus FS und DS (Schiffe/Ges';
 
INSERT INTO `portal_facts` (`id`, `class`, `title`, `text`) VALUES (1, 'ship', 'GTB Zeus', '[imgrf]http://ds.hamburg051.server4free.de/rds/data/articles/gtb_zeus.gif[/imgrf]Der GTB Zeus ist einer der schnellsten im Einsatz befindlichen GCP-Bomber. Er ersetzt die alten Athena-Bomber der GTA die bereits kurz nach dem Großen Krieg außer Dienst gestellt worden waren. Auch wenn er geringfügig langsamer ist als die alte Athena, so erreicht der Zeus durch seine bessere Bewaffung sowie Panzerung eine wesentlich höhere Überlebensrate in Kampfeinsätzen. Während der NTF-Rebelion desertierten viele der Zeus-Staffeln und liefen zur NTF über.');
INSERT INTO `portal_facts` (`id`, `class`, `title`, `text`) VALUES (7, 'groups', 'GTVA', 'Die Galaktische Terranisch-Vasudanische Allianz (GTVA) wurde im Jahre 2345 gegründet, zehn Jahre nach dem Grossen Krieg. Dieses Vertragsbündnis erkannte die Unabhängigkeit seiner Signatarstaaten an und bildete das Gerüst für den Ausbau des Handels und die gegenseitige Verteidigung. Der Grosse Krieg hatte die Feindschaft zwischen Terranern und Vasudanern in ein stabile und dauerhafte Freundschaft verwandelt.\r\n\r\nNachdem sich Industrie und Wirtschaft des Terranisch-Vasudanischen Systems wieder erholt hatten, gewann auch die Idee einer noch mächtigeren GVTA an Bedeutung. Im Jahr 2358 unterzeichneten Delegierte beider Seiten die Beta-Aquilae-Konvention (BETAK), benannt nach dem System, in dem die Übereinkunft geschlossen und ratifiziert worden war. Die BETAK trat an die Stelle der terranischen Blöcke und erkannte die Generalversammlung, den Sicherheitsrat und das Vasudanische Imperium als höchste Autoritäten im terranisch-vasudanischen Raum an.\r\n\r\nIm Rahmen der BETAK unterhalten Terraner und Vasudaner voneinander getrennte Flotten, die allerdings einer gemeinsamen Befehlsstruktur unterliegen. Die Kriegsschiffe sind entweder mit einem GT oder einem GV gekennzeichnet, je nachdem, ob sie galaktisch-terranischer oder galaktisch-vasudanischer Herkunft sind. Die beiden Spezies tauschen offen Informationen und Technologien aus, und die unlängst entwickelten Jäger und Bomber lassen sich so modifizieren, dass sie von den Piloten beider Rassen gesteuert werden können.\r\n\r\nFormal gesehen ist die BETAK nur ein Abkommen unter vielen, das die Abläufe innerhalb der GTVA als politischer, militärischer und wirtschaftlicher Einheit regelt. Dennoch wird die BETAK zu Recht als Meilenstein der terranisch-vasudanischen Allianz angesehen und genau das macht sie zum bevorzugten Angriffsziel der NTF-Propagandisten.');
INSERT INTO `portal_facts` (`id`, `class`, `title`, `text`) VALUES (8, 'history', 'Der Große Krieg (2335)', 'Im Jahre 2335 jährte sich der terranisch-vasudanische Krieg zum 14. Mal. Die Galaktische Terranische Allianz (GTA) und die Parlamentarische Vasudanische Flotte (PVF) waren ausgelaugt und demoralisiert. Sie standen beide kurz vor dem endgültigen Zusammenbruch.\r\n\r\nDoch die Invasion einer geheimnisvollen Spezies, die sich Shivaner nannte, zwang die Führer der Terraner und Vasudaner, einen Waffenstillstand zu unterzeichnen und ein neues Bündnis zu schmieden. Aufgrund ihrer fortgeschrittenen Waffen- und Schutzschildtechnologie waren die Shivaner in der Lage, ohne Provokation und ohne jede Vorwarnung anzugreifen.\r\n\r\nDie neuen Verbündeten erwarben und entwickelten ihrerseits neue Technologien, um der unerwarteten Bedrohung zu begegnen. Die Vorhut der shivanischen Flotte bestand aus der SD Lucifer, einem riesigen Superzerstörer. Auf Vasuda Prime hatte die Lucifer schon alles Leben ausgelöscht und nahm nun Kurs auf die Erde. Die Alliierten besassen kein Waffensystem in ihrem Arsenal, das die Schilde der Lucifer hätte durchdringen können.\r\n\r\nDoch im Altair-System entdeckten vasudanische Wissenschaftler die Ruinen einer ausgestorbenen Zivilisation, die vor 8000 Jahren von den Shivanern zerstört worden war. Die Artefakte gaben Aufschluss darüber, wie man die Lucifer bis in den Subraum verfolgen konnte, wo ihre Schilde nicht funktionierten. Nur dort war die Lucifer verwundbar.\r\n\r\nIm Delta-Serpentis-System startete von der GTD Bastion aus ein Geschwader, das der Lucifer in den Sol-Sprungknoten folgte. Die Jäger und Bomber hatten nur einen Auftrag: die Reaktoren der Lucifer auszuschalten, bevor der Superzerstörer auch das Leben auf der Erde auslöschen konnte.\r\n\r\nDen zur Veröffentlichung freigegebenen Funkprotokollen zufolge muss der Einsatz erfolgreich verlaufen sein. Doch die Explosion der Lucifer verursachte im Subraum einen Kataklysmus, der alle Kontakte zwischen der Erde und den anderen Systemen des terranisch-vasudanischen Raums durchtrennte.');
