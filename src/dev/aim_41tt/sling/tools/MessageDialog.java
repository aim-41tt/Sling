package dev.aim_41tt.sling.tools;

import javax.swing.JOptionPane;

import dev.aim_41tt.sling.config.TypeDialog;

public final class MessageDialog {

	public static void message(String text, String title, int TMD) {
		JOptionPane.showMessageDialog(null, text, title, TMD);
	}

	public static void info(String text, String title) {
		JOptionPane.showMessageDialog(null, text, title, TypeDialog.INFO);
	}

	public static void warn(String text, String title) {
		JOptionPane.showMessageDialog(null, text, title, TypeDialog.WARN);
	}

	public static void error(String text, String title) {
		JOptionPane.showMessageDialog(null, text, title, TypeDialog.ERROR);
	}

	public static boolean question(String question, String title) {
		int result = JOptionPane.showConfirmDialog(null, question, title, JOptionPane.YES_NO_OPTION,
				TypeDialog.QUESTION);

		return result == JOptionPane.YES_OPTION;
	}
	
}
