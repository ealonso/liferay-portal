/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.fragment.team.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.net.URL;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Eudaldo Alonso
 */
@RunWith(Arquillian.class)
public class TeamTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() {
		_bundle = FrameworkUtil.getBundle(getClass());

		_moneyMap = HashMapBuilder.put(
			"...", 40000000L
		).put(
			"Armeys", 40000000L
		).put(
			"Astros", 40000000L
		).put(
			"Batatas FC", 40000000L
		).put(
			"Casiano Team", 40000000L
		).put(
			"Chinijos FC", 40000000L
		).put(
			"Hakuna Matata", 40000000L
		).put(
			"Reina", 40000000L
		).put(
			"Sandro Maravilla", 40000000L
		).build();
	}

	@Test
	public void testGetTeams() throws Exception {
		_weeks();
		_transfers();

		for (Map.Entry<String, Long> entry : _moneyMap.entrySet()) {
			System.out.println(
				StringBundler.concat(
					"User: ", entry.getKey(), " money: ", entry.getValue()));
		}
	}

	private void _addMoney(String userName, String money) {
		if (_moneyMap.containsKey(userName)) {
			_moneyMap.replace(
				userName, _getMoney(money) + _moneyMap.get(userName));
		}
	}

	private void _calculateMoneyPaid(Element element) {
		for (Element teamElement : element.getElementsByTag("li")) {
			Elements userLinkElements = teamElement.getElementsByTag(
				"user-link");

			if (userLinkElements.size() <= 0) {
				continue;
			}

			Element userElement = userLinkElements.get(0);

			Element userNameElement = userElement.firstElementSibling();

			String userName = userNameElement.text();

			Elements incrementElements = teamElement.getElementsByTag(
				"increment");

			Element moneyElement = incrementElements.get(0);

			_addMoney(userName, moneyElement.text());
		}
	}

	private void _calculateTransferListMoney(Element element) {
		for (Element teamElement : element.getElementsByTag("li")) {
			Elements elements = teamElement.getElementsByTag(
				"dynamic-expression-container");

			Element operationElement = elements.get(0);

			String operation = operationElement.text();

			if (operation.contains("Cambia")) {
				if (operation.contains("de")) {
					String[] teams = StringUtil.split(operation, " a ");

					_removeMoney(_getUserName(teams[1]), operation);
					_addMoney(_getUserName(teams[0]), operation);
				}
				else {
					_removeMoney(_getUserName(operation), operation);
				}
			}
			else if (operation.contains("Vendido")) {
				_addMoney(_getUserName(operation), operation);
			}
		}
	}

	private long _getMoney(String moneyText) {
		return GetterUtil.getLong(moneyText.replaceAll("\\D+", ""));
	}

	private String _getUserName(String operation) {
		for (Map.Entry<String, Long> entry : _moneyMap.entrySet()) {
			String key = entry.getKey();

			if (operation.contains(key)) {
				return key;
			}
		}

		return StringPool.BLANK;
	}

	private boolean _isMoneyPaid(Element element) {
		Elements postTitleElements = element.getElementsByClass("post-title");

		if (postTitleElements.size() <= 0) {
			return false;
		}

		for (Element postTitleElement : postTitleElements) {
			String text = postTitleElement.text();

			if (text.contains("Fin de")) {
				return true;
			}
		}

		return false;
	}

	private void _removeMoney(String userName, String money) {
		if (_moneyMap.containsKey(userName)) {
			_moneyMap.replace(
				userName, _moneyMap.get(userName) - _getMoney(money));
		}
	}

	private void _transfers() throws Exception {
		URL resourceURL = _bundle.getEntry(_TRANSFERS_PATH);

		String html = StringUtil.read(resourceURL.openStream());

		Document document = Jsoup.parseBodyFragment(html);

		Document.OutputSettings outputSettings = new Document.OutputSettings();

		outputSettings.indentAmount(0);
		outputSettings.prettyPrint(false);

		document.outputSettings(outputSettings);

		Element bodyElement = document.body();

		// Calculate money per transfer

		for (Element element : bodyElement.getElementsByTag("transfer-list")) {
			_calculateTransferListMoney(element);
		}
	}

	private void _weeks() throws Exception {
		URL resourceURL = _bundle.getEntry(_WEEKS_PATH);

		String html = StringUtil.read(resourceURL.openStream());

		Document document = Jsoup.parseBodyFragment(html);

		Document.OutputSettings outputSettings = new Document.OutputSettings();

		outputSettings.indentAmount(0);
		outputSettings.prettyPrint(false);

		document.outputSettings(outputSettings);

		Element bodyElement = document.body();

		// Calculate money per each week

		for (Element element :
				bodyElement.getElementsByTag("league-board-post")) {

			if (_isMoneyPaid(element)) {
				_calculateMoneyPaid(element);
			}
		}
	}

	private static final String _TRANSFERS_PATH =
		"com/liferay/fragment/team/test/dependencies/transfers.html";

	private static final String _WEEKS_PATH =
		"com/liferay/fragment/team/test/dependencies/weeks.html";

	private Bundle _bundle;
	private Map<String, Long> _moneyMap;

}