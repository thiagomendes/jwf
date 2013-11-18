package br.com.tm.dev.lib.jwf.app.controller;

import br.com.tm.dev.lib.jwf.route.ApplicationController;

public class TestController extends ApplicationController
{

	@Override
	protected void execute() throws Exception
	{
		String message = "Hello World";
		request.setAttribute("message", message);
		forward("/pages/TestView.jsp");
	}

}
