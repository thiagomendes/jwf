package br.com.tm.dev.lib.jwf.route;

import javax.servlet.http.HttpSession;

import br.com.tm.dev.lib.jwf.util.JWFConstants;

public abstract class ApplicationLoggedController extends ApplicationController
{

	@Override
	protected void execute() throws Exception
	{
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");

		if (user == null)
		{
			String unauthorizedPage = config.getInitParameter(JWFConstants.PARAM_UNAUTHORIZED_PAGE);
			forward(unauthorizedPage);
		}

		else
		{
			execute(user);
		}
	}

	protected abstract void execute(Object user) throws Exception;
}
