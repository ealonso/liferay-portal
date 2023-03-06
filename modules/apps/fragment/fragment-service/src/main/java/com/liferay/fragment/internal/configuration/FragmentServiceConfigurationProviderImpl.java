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

package com.liferay.fragment.internal.configuration;

import com.liferay.fragment.configuration.FragmentServiceConfiguration;
import com.liferay.fragment.configuration.FragmentServiceConfigurationProvider;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import java.io.IOException;

import java.util.Dictionary;
import java.util.Map;

import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	configurationPid = "com.liferay.fragment.configuration.FragmentServiceConfiguration",
	service = FragmentServiceConfigurationProvider.class
)
public class FragmentServiceConfigurationProviderImpl
	implements FragmentServiceConfigurationProvider {

	@Override
	public boolean isFragmentServiceConfigurationDefined(long companyId)
		throws ConfigurationException {

		if (_getFragmentServiceConfiguration(companyId) != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isPropagateChanges(long companyId) {
		return _fragmentServiceConfiguration.propagateChanges();
	}

	@Override
	public boolean isPropagateContributedFragmentChanges(long companyId) {
		return _fragmentServiceConfiguration.
			propagateContributedFragmentChanges();
	}

	@Override
	public void updateConfiguration(
			long companyId, boolean propagateChanges,
			boolean propagateContributedFragmentsChanges)
		throws ConfigurationException {

		Dictionary<String, Object> properties = null;

		Configuration configuration = _getFragmentServiceConfiguration(
			companyId);

		if (configuration == null) {
			try {
				configuration = _configurationAdmin.createFactoryConfiguration(
					FragmentServiceConfiguration.class.getName() + ".scoped",
					StringPool.QUESTION);
			}
			catch (IOException ioException) {
				throw new ConfigurationException(ioException);
			}

			properties = HashMapDictionaryBuilder.<String, Object>put(
				ExtendedObjectClassDefinition.Scope.COMPANY.getPropertyKey(),
				companyId
			).build();
		}
		else {
			properties = configuration.getProperties();
		}

		properties.put("propagateChanges", propagateChanges);
		properties.put(
			"propagateContributedFragmentsChanges",
			propagateContributedFragmentsChanges);

		try {
			configuration.update(properties);
		}
		catch (IOException ioException) {
			throw new ConfigurationException(ioException);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_fragmentServiceConfiguration = ConfigurableUtil.createConfigurable(
			FragmentServiceConfiguration.class, properties);
	}

	private Configuration _getFragmentServiceConfiguration(long companyId)
		throws ConfigurationException {

		try {
			String filterString = StringBundler.concat(
				"(&(", ConfigurationAdmin.SERVICE_FACTORYPID, StringPool.EQUAL,
				FragmentServiceConfiguration.class.getName(), ".scoped",
				")(companyId=", companyId, "))");

			Configuration[] configuration =
				_configurationAdmin.listConfigurations(filterString);

			if (configuration != null) {
				return configuration[0];
			}

			return null;
		}
		catch (InvalidSyntaxException | IOException exception) {
			throw new ConfigurationException(exception);
		}
	}

	@Reference
	private ConfigurationAdmin _configurationAdmin;

	@Reference
	private ConfigurationProvider _configurationProvider;

	private volatile FragmentServiceConfiguration _fragmentServiceConfiguration;

}