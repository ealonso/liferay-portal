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

package com.liferay.asset.kernel.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.tags.exception.DuplicateTagException}
 */
@Deprecated
public class DuplicateTagException extends PortalException {

	public DuplicateTagException() {
	}

	public DuplicateTagException(String msg) {
		super(msg);
	}

	public DuplicateTagException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DuplicateTagException(Throwable cause) {
		super(cause);
	}

}