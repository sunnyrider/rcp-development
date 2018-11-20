package com.frank.e4.dialog.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import com.frank.e4.dialog.dlg.PasswordDialog;

public class EnterCredencialsHandler {

	@Inject
	private IEclipseContext context;

	@Inject
	private IEventBroker eventBroker;

	@Execute
	public void execute(Shell parent) {

		context.set(Shell.class, parent);

		PasswordDialog dialog = ContextInjectionFactory.make(PasswordDialog.class, context);

		int result = dialog.open();

		if (result == Window.OK) {
			String user = dialog.getUser();
			String pw = dialog.getPassword();
			String out = "User: " + user + ", Password: " + pw;
			System.out.println(out);

			eventBroker.send("testInput", out);
		}
	}
}
