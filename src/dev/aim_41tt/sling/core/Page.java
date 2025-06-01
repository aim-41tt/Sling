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
package dev.aim_41tt.sling.core;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public abstract class Page {

	private final Map<String, JComponent> componentsById = new HashMap<>();
	private final JPanel panel = new JPanel();
	protected App app;
	private String title = "";

	public Page() {
		panel.setLayout(new GridLayout(3, 2, 10, 10));
	}

	public void setApp(App app) {
		this.app = app;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JTextField addTextField(String placeholder) {
		JTextField textField = new JTextField(20);
		panel.add(new JLabel(placeholder));
		panel.add(textField);
		return textField;
	}

	public JPasswordField addPasswordField(String placeholder) {
		JPasswordField passwordField = new JPasswordField(20);
		panel.add(new JLabel(placeholder));
		panel.add(passwordField);
		return passwordField;
	}

	public JButton addButton(String text, Runnable action) {
		JButton button = new JButton(text);
		button.addActionListener(e -> action.run());
		panel.add(button);
		return button;
	}

	public JLabel addLabel(String text) {
		JLabel label = new JLabel(text);
		panel.add(label);
		return label;
	}

	public JScrollPane addTableScrol(Object[][] data, Object[] columnNames, String type) {
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		panel.add(pane, type);
		return pane;
	}

	public void navigateTo(Class<? extends Page> pageClass) {
		app.navigateTo(pageClass);
	}

	public abstract void onCreate();

	public void repaint() {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}

	// Добавить компонент по ID, если не существует
	public <T extends JComponent> T addOrGetComponent(String id, Class<T> type, Supplier<T> factory) {
		JComponent existing = componentsById.get(id);
		if (existing != null) {
			if (type.isInstance(existing)) {
				return type.cast(existing);
			} else {
				throw new IllegalStateException(
						"Компонент с id='" + id + "' не соответствует ожидаемому типу " + type.getSimpleName());
			}
		}
		T component = factory.get();
		componentsById.put(id, component);
		panel.add(component);
		repaint();
		return component;
	}

	@SuppressWarnings("unchecked")
	public <T extends JComponent> T addOrGetComponent(String id, Supplier<T> factory) {
		JComponent existing = componentsById.get(id);
		if (existing != null) {
			return (T) existing;
		}
		T component = factory.get();
		componentsById.put(id, component);
		panel.add(component);
		repaint();
		return component;
	}

	public <T extends JComponent> T addOrGetComponent(Supplier<T> factory) {
		String id = String.valueOf(factory.get().hashCode()) + title.getClass();
		System.out.println(id);
		return addOrGetComponent(id, factory);
	}

	// Получить компонент по ID
	@SuppressWarnings("unchecked")
	public <T extends JComponent> T getComponent(String id) {
		return (T) componentsById.get(id);
	}

	public <T extends JComponent> T getComponent(String id, Class<T> type) {
		JComponent component = componentsById.get(id);
		if (component == null)
			return null;

		if (!type.isInstance(component)) {
			throw new IllegalStateException("Компонент с id='" + id + "' не является типом " + type.getSimpleName());
		}
		return type.cast(component);
	}

	// Удалить компонент по ID
	public void removeComponent(String id) {
		JComponent comp = componentsById.remove(id);
		if (comp != null) {
			panel.remove(comp);
			repaint();
		}
	}
}