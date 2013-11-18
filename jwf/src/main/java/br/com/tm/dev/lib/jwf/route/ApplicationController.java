package br.com.tm.dev.lib.jwf.route;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class ApplicationController
{

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected HttpSession session;

	protected ServletConfig config;

	protected ServletContext context;

	protected void load(HttpServletRequest request, HttpServletResponse response, ServletConfig config, ServletContext context) throws Exception
	{
		this.request = request;
		this.response = response;
		this.config = config;
		this.context = context;
		this.session = request.getSession();
		this.execute();
	}

	protected void redirect(String view) throws Exception
	{
		response.sendRedirect(view);
	}

	protected void redirect(ApplicationController applicationController) throws Exception
	{
		applicationController.load(request, response, config, context);
	}

	protected void forward(String view) throws Exception
	{
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected abstract void execute() throws Exception;

}
