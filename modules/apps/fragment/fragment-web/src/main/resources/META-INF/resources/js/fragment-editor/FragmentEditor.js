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

import ClayIcon from '@clayui/icon';
import React, {useState} from 'react';

const FragmentEditor = ({
	allowedStatus = {
		approved: false,
		draft: false,
	},
	cacheable,
	propagationEnabled,
	readOnly,
	status,
}) => {
	const [isCacheable, setIsCacheable] = useState(cacheable);
	const [isSaving, setIsSaving] = useState(false);

	const handleSaveButtonClick = () => {
		setIsSaving(true);
	};

	return (
		<div className="fragment-editor-container">
			<div className="nav-bar-container">
				<div className="navbar navbar-default">
					<div className="container">
						<div className="navbar navbar-collapse-absolute navbar-expand-md navbar-underline navigation-bar navigation-bar-light">
							<ul className="nav nav-underline" role="tablist">
								<li className="nav-item">
									<a
										aria-controls="code"
										aria-expanded="true"
										className="active nav-link"
										data-toggle="liferay-tab"
										href="#code"
										id="codeTab"
										role="tab"
									>
										{Liferay.Language.get('code')}
									</a>
								</li>

								<li className="nav-item">
									<a
										aria-controls="configuration"
										className="nav-link"
										data-toggle="liferay-tab"
										href="#configuration"
										id="configurationTab"
										role="tab"
									>
										{Liferay.Language.get('configuration')}
									</a>
								</li>
							</ul>
						</div>

						<div className="btn-group btn-group-nowrap float-right mt-1">
							{readOnly ? (
								<span className="pr-3 pt-1 text-info">
									<ClayIcon symbol="exclamation-circle" />

									<span>
										{Liferay.Language.get('read-only-view')}
									</span>
								</span>
							) : (
								<>
									{propagationEnabled && (
										<span
											className="lfr-portal-tooltip pr-3 pt-1 text-info"
											data-title={Liferay.Language.get(
												'automatic-propagation-enabled-help'
											)}
										>
											<ClayIcon symbol="exclamation-circle" />

											<span>
												{Liferay.Language.get(
													'automatic-propagation-enabled'
												)}
											</span>
										</span>
									)}

									<div className="btn-group-item custom-checkbox custom-control ml-2 mr-4 mt-1">
										<label
											className="lfr-portal-tooltip"
											data-title={Liferay.Language.get(
												'cacheable-fragment-help'
											)}
										>
											<input
												checked={isCacheable}
												className="custom-control-input toggle-switch-check"
												name="cacheable"
												onChange={event =>
													setIsCacheable(
														event.currentTarget
															.checked
													)
												}
												type="checkbox"
												value="true"
											/>

											<span className="custom-control-label">
												<span className="custom-control-label-text">
													{Liferay.Language.get(
														'cacheable'
													)}
												</span>
											</span>
										</label>
									</div>

									{status === allowedStatus.draft && (
										<div className="btn-group-item">
											<button
												className="btn btn-secondary btn-sm"
												disabled={isSaving}
												onClick={handleSaveButtonClick}
												type="button"
												value={allowedStatus.draft}
											>
												<span className="lfr-btn-label">
													{Liferay.Language.get(
														'save-as-draft'
													)}
												</span>
											</button>
										</div>
									)}

									<div className="btn-group-item">
										<button
											className="btn btn-primary btn-sm"
											disabled={isSaving}
											onClick={handleSaveButtonClick}
											type="button"
											value={allowedStatus.approved}
										>
											<span className="lfr-btn-label">
												{Liferay.Language.get(
													'publish'
												)}
											</span>
										</button>
									</div>
								</>
							)}
						</div>
					</div>
				</div>
			</div>

			<div className="tab-content">
				<div
					aria-labelledby="codeTab"
					className="active fade show tab-pane"
					id="code"
					role="tabpanel"
				>
					<div className="fragment-editor"></div>
				</div>

				<div
					aria-labelledby="configurationTab"
					className="fade tab-pane"
					id="configuration"
					role="tabpanel"
				>
					<div class="fragment-editor"></div>
				</div>
			</div>
		</div>
	);
};

export default function({
	props: {allowedStatus, cacheable, propagationEnabled, readOnly, status},
}) {
	return (
		<FragmentEditor
			allowedStatus={allowedStatus}
			cacheable={cacheable}
			propagationEnabled={propagationEnabled}
			readOnly={readOnly}
			status={status}
		/>
	);
}
