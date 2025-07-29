/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package override.layout;

import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	property = {
		"jakarta.portlet.name=" + LayoutAdminPortletKeys.GROUP_PAGES,
		"mvc.command.name=/layout_admin/delete_layout",
		"service.ranking:Integer=200"
	},
	service = MVCActionCommand.class
)
public class CustomDeleteLayoutMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long selPlid = ParamUtil.getLong(actionRequest, "selPlid");

		long[] selPlids = ParamUtil.getLongValues(actionRequest, "rowIds");

		if ((selPlid > 0) && ArrayUtil.isEmpty(selPlids)) {
			selPlids = new long[] {selPlid};
		}

		if (!_canDeleteLayout(selPlids)) {
			SessionMessages.add(
				_portal.getHttpServletRequest(actionRequest),
				"parentLayoutDeletionException");
			SessionErrors.add(actionRequest, "parentLayoutDeletionException2");

			return;
		}

		_mvcActionCommand.processAction(actionRequest, actionResponse);
	}

	private boolean _canDeleteLayout(long[] selPlids) throws Exception {
		for (long curSelPlid : selPlids) {
			Layout layout = LayoutLocalServiceUtil.getLayout(curSelPlid);

			if (ListUtil.isEmpty(layout.getChildren())) {
				continue;
			}

			return false;
		}

		return true;
	}

	@Reference(
		target = "(component.name=com.liferay.layout.admin.web.internal.portlet.action.DeleteLayoutMVCActionCommand)"
	)
	private MVCActionCommand _mvcActionCommand;

	@Reference
	private Portal _portal;

}