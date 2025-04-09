/*
 * Copyright 2025 SlingFramework by aim_41tt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.aim_41tt.sling.tools.dialogs;

import javax.swing.JOptionPane;

import dev.aim_41tt.sling.config.ui.TypeDialog;

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
