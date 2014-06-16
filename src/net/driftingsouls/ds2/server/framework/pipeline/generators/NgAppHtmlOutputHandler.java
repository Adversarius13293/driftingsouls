package net.driftingsouls.ds2.server.framework.pipeline.generators;

/**
 * Spezieller HTML-Outputhandler fuer Angular-Seiten.
 */
public class NgAppHtmlOutputHandler extends HtmlOutputHandler
{
	public NgAppHtmlOutputHandler()
	{
		super();
		setAttribute("bodyParameters", "ng-app=\"ds.application\"");
	}
}
