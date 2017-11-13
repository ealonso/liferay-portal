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

package com.liferay.video.embedder.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author arthurchan35
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "VideoEmbedderConfiguration", localization = "content/Language",
	name = "videoembedder-configuration-name"
)
public interface VideoEmbedderConfiguration {

	// ACFun

	public static final String ACFUN = "acfun";

	public static final String ACFUN_I_P =
		"cdn.aixifan.com/player/ACFlashPlayer.out.swf?vid=";

	public static final String ACFUN_V_P = "^.*?ac([0-9]+).*$";

	// Bilibili

	public static final String BILIBILI = "bilibili";

	public static final String BILIBILI_I_P =
		"static.hdslb.com/miniloader.swf?aid=";

	public static final String BILIBILI_V_P = "^.*?av([0-9]+).*$";

	// Delimiter

	public static final String DLM = "___";

	// QQ

	public static final String QQ = "qq";

	public static final String QQ_I_P = "v.qq.com/iframe/player.html?vid=";

	public static final String QQ_V_P =
		"^.*?cover/[a-zA-Z0-9]+/([a-zA-Z0-9]+).*$";

	public static final String QQ_V_P_2 = "^.*?page/([a-zA-Z0-9]+).*$";

	// Sohu

	public static final String SOHU = "sohu";

	public static final String SOHU_I_P =
		"tv.sohu.com/upload/public static/share/share_play.html#";

	public static final String SOHU_V_P = "^.*?pl/[0-9]+/([0-9]+).*$";

	public static final String SOHU_V_P_2 = "^.*?#/([0-9]+).*$";

	// Tudou

	public static final String TUDOU = "tudou";

	public static final String TUDOU_V_P = "^.*?v/([a-zA-Z0-9_-]+==).*$";

	// Vimeo

	public static final String VIMEO = "vimeo";

	public static final String VIMEO_I_P = "player.vimeo.com/video/";

	public static final String VIMEO_V_P = "^.*?com/([0-9]+).*$";

	//Youku

	public static final String YOUKU = "youku";

	public static final String YOUKU_TUDOU_I_P = "player.youku.com/embed/";

	public static final String YOUKU_V_P = "^.*?id_([a-zA-Z0-9_-]+==).*$";

	//YouTube

	public static final String YOUTUBE = "youtube";

	public static final String YOUTUBE_2 = "youtu.be";

	public static final String YOUTUBE_I_P = "www.youtube.com/embed/";

	public static final String YOUTUBE_V_P = "^.*?v=([a-zA-Z0-9_-]+).*$";

	public static final String YOUTUBE_V_P_2 = "^.*?be/([a-zA-Z0-9_-]+).*$";

	/**
	 * A list of prepopulated rules of video sites
	 * I_P stands for iframe prefix
	 * DLM stands for delimeter
	 * V_P stands video pattern
	 */
	@Meta.AD(
		deflt =
			YOUTUBE_I_P + DLM + YOUTUBE_V_P_2 + DLM + YOUTUBE_2 + ',' +
				BILIBILI_I_P + DLM + BILIBILI_V_P + DLM + BILIBILI + ',' +
					YOUTUBE_I_P + DLM + YOUTUBE_V_P + DLM + YOUTUBE + ',' +
						YOUKU_TUDOU_I_P + DLM + YOUKU_V_P + DLM + YOUKU + ',' +
							YOUKU_TUDOU_I_P + DLM + TUDOU_V_P + DLM + TUDOU + ',' +
								VIMEO_I_P + DLM + VIMEO_V_P + DLM + VIMEO + ',' +
									ACFUN_I_P + DLM + ACFUN_V_P + DLM + ACFUN + ',' +
										SOHU_I_P + DLM + SOHU_V_P_2 + DLM + SOHU + ',' +
											SOHU_I_P + DLM + SOHU_V_P + DLM + SOHU + ',' +
												QQ_I_P + DLM + QQ_V_P_2 + DLM + QQ + ',' +
													QQ_I_P + DLM + QQ_V_P + DLM + QQ,
		description = "iframeurl-descriptions", required = false
	)
	public abstract String[] iframeURLs();

}