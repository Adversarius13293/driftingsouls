CREATE TABLE `smilies` (
  `id` smallint(6) NOT NULL auto_increment,
  `tag` varchar(10) NOT NULL default '',
  `image` varchar(40) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Smilies'; 

INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (1, ':D', 'icon_biggrin.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (2, ':-D', 'icon_biggrin.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (3, ':grin:', 'icon_biggrin.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (4, ':)', 'icon_smile.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (5, ':-)', 'icon_smile.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (6, ':smile:', 'icon_smile.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (7, ':(', 'icon_sad.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (8, ':-(', 'icon_sad.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (9, ':sad:', 'icon_sad.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (10, ':o', 'icon_surprised.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (11, ':-o', 'icon_surprised.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (12, ':eek:', 'icon_surprised.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (13, ':shock:', 'icon_eek.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (14, ':?', 'icon_confused.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (15, ':-?', 'icon_confused.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (16, ':???:', 'icon_confused.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (17, '8)', 'icon_cool.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (18, '8-)', 'icon_cool.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (19, ':cool:', 'icon_cool.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (20, ':lol:', 'icon_lol.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (21, ':x', 'icon_mad.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (22, ':-x', 'icon_mad.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (23, ':mad:', 'icon_mad.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (24, ':P', 'icon_razz.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (25, ':-P', 'icon_razz.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (26, ':razz:', 'icon_razz.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (27, ':oops:', 'icon_redface.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (28, ':cry:', 'icon_cry.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (29, ':evil:', 'icon_evil.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (30, ':twisted:', 'icon_twisted.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (31, ':roll:', 'icon_rolleyes.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (32, ':wink:', 'icon_wink.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (33, ';)', 'icon_wink.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (34, ';-)', 'icon_wink.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (35, ':!:', 'icon_exclaim.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (36, ':?:', 'icon_question.gif');
INSERT INTO `smilies` (`id`, `tag`, `image`) VALUES (37, ':idea:', 'icon_idea.gif');