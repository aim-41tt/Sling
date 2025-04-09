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

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public final class App {
	private final JFrame frame;
	private final Map<Class<? extends Page>, Page> pages = new HashMap<>();
	private Class<? extends Page> mainPage;

	public App(String title) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
	}

	public App(String title, int x, int y) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(x, y);
	}

	public App(int x, int y) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(x, y);
	}

	public void setFrameSize(int x, int y) {
		frame.setSize(x, y);
	}
	
	public void setMainScreen(Class<? extends Page> loginPage) {
		this.mainPage = loginPage;
		addPage(loginPage);
	}

	public void addPage(Class<? extends Page> pageClass) {
		try {
			Page page = pageClass.getDeclaredConstructor().newInstance();
			page.setApp(this);
			page.onCreate();
			pages.put(pageClass, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		SwingUtilities.invokeLater(() -> {
			navigateTo(mainPage);
			frame.setVisible(true);
		});
		frame.setVisible(true);
	}

	public void navigateTo(Class<? extends Page> pageClass) {
		frame.getContentPane().removeAll();
		Page page = pages.get(pageClass);
		System.out.println(page);
		if (page != null) {
			frame.setTitle(page.getTitle());
			frame.add(page.getPanel());
		}
		frame.revalidate();
		frame.repaint();
	}

}
