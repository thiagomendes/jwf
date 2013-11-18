package br.com.tm.dev.lib.jwf.route;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tm.dev.lib.jwf.util.JWFConstants;



public class ApplicationRouter extends HttpServlet
{

	private static final long serialVersionUID = 642575118977286587L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doExec(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doExec(req, resp);
	}

	protected void doExec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String controllerName = req.getParameter("Controller");
		PrintWriter writer = resp.getWriter();

		if (controllerName == null || controllerName.equals(""))
		{
			writer.println(JWFConstants.ERROR_CONTROLLER_NULL_0001);
			return;
		}

		try
		{
			routeController(controllerName, req, resp);
		}

		catch (InstantiationException e)
		{
			writeLoadControllerError(writer, e);
		}

		catch (IllegalAccessException e)
		{
			writeLoadControllerError(writer, e);
		}

		catch (ClassNotFoundException e)
		{
			writeLoadControllerError(writer, e);
		}

		catch (Exception e)
		{
			writeGenericApplicationError(writer, e);
		}

	}

	private void routeController(String controllerName, HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		ApplicationController applicationController = (ApplicationController) Class.forName(getFullPathController(controllerName)).newInstance();
		applicationController.load(req, resp, getServletConfig(), getServletContext());
	}

	private String getTrace(Exception exception)
	{
		StringWriter sw = new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	private void writeLoadControllerError(PrintWriter writer, Exception e)
	{
		writer.println(JWFConstants.ERROR_CONTROLLER_LOAD_0002);
	}

	private void writeGenericApplicationError(PrintWriter writer, Exception e)
	{
		writer.println(JWFConstants.ERROR_GENERIC_APPLICATION_0003);
		writer.println(getTrace(e));
	}

	private String getFullPathController(String controllerName)
	{
		String path = getServletConfig().getInitParameter(JWFConstants.PARAM_CONTROLLER_PATH);
		String fullPath = path + "." + controllerName;
		return fullPath;
	}
}
