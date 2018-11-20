package com.frank.e4.dialog.dlg;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PasswordDialog extends Dialog {

	@Inject
	private MApplication app;

	private Text txtUser;
	private Text txtPassword;
	private String user = "";
	private String password = "";

//	@Inject
//	Shell shell;

	@PostConstruct
	private void initialize() {
		System.out.println("Dialog is initialized");
	}

	@Inject
	public PasswordDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void cancelPressed() {
		super.cancelPressed();
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Login", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginLeft = 10;
		layout.marginRight = 5;

		container.setLayout(layout);

		Label lblUser = new Label(container, SWT.NONE);
		lblUser.setText("User");

		txtUser = new Text(container, SWT.BORDER);
		txtUser.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtUser.setText(user);
		txtUser.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				String userText = textWidget.getText();
				user = userText;
			}
		});

		Label lblPassword = new Label(container, SWT.NONE);
		lblPassword.setText(password);

		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.horizontalIndent = 1;
		lblPassword.setLayoutData(gd_lblNewLabel);

		txtPassword = new Text(container, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtPassword.setText(password);
		txtPassword.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Text txtWidget = (Text) e.getSource();
				String pwText = txtWidget.getText();
				password = pwText;
			}
		});


		return container;
	}

	private final class ActiveShellListener extends ShellAdapter {

		@Override
		public void shellActivated(ShellEvent e) {
			super.shellActivated(e);
		}
	}

	public void doShellActivated() {
		IEclipseContext context = getEclipseContext();
		EContextService contextService = context.get(EContextService.class);
//		contextService.activateContext(id_con);
	}

//	@Override
//	protected ShellListener getShellListener() {
//		return new active
//	}

	public String getPassword() {
		return password;
	}

	public void setUser(String user1) {
		user = user1;
	}

	public String getUser() {
		return user;
	}

	@Override
	protected void okPressed() {
		user = txtUser.getText();
		password = txtPassword.getText();
		super.okPressed();
	}

	@Override
	protected Point getInitialSize() {
		return new Point(450,  300);
	}


	@Override
	public Shell getShell() {
		// TODO Auto-generated method stub
		return super.getShell();
	}

	private IEclipseContext getEclipseContext() {
		return app.getContext().getActiveLeaf();
	}
}
