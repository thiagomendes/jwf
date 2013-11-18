package br.com.tm.dev.lib.jwf.app.controller;

import br.com.tm.dev.lib.jwf.route.ApplicationLoggedController;

public class TestLoggedController extends ApplicationLoggedController
{

	@Override
	protected void execute(Object user) throws Exception
	{
		forward("/pages/SecureView.jsp");
	}

}
