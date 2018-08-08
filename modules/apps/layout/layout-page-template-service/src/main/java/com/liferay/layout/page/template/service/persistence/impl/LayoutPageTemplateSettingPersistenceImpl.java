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

package com.liferay.layout.page.template.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException;
import com.liferay.layout.page.template.model.LayoutPageTemplateSetting;
import com.liferay.layout.page.template.model.impl.LayoutPageTemplateSettingImpl;
import com.liferay.layout.page.template.model.impl.LayoutPageTemplateSettingModelImpl;
import com.liferay.layout.page.template.service.persistence.LayoutPageTemplateSettingPersistence;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the layout page template setting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateSettingPersistence
 * @see com.liferay.layout.page.template.service.persistence.LayoutPageTemplateSettingUtil
 * @generated
 */
@ProviderType
public class LayoutPageTemplateSettingPersistenceImpl
	extends BasePersistenceImpl<LayoutPageTemplateSetting>
	implements LayoutPageTemplateSettingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LayoutPageTemplateSettingUtil} to access the layout page template setting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LayoutPageTemplateSettingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			LayoutPageTemplateSettingModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] { String.class.getName() });

	/**
	 * Returns all the layout page template settings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout page template settings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @return the range of matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout page template settings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByUuid(String uuid, int start,
		int end, OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout page template settings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByUuid(String uuid, int start,
		int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<LayoutPageTemplateSetting> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutPageTemplateSetting>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutPageTemplateSetting layoutPageTemplateSetting : list) {
					if (!Objects.equals(uuid,
								layoutPageTemplateSetting.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutPageTemplateSettingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<LayoutPageTemplateSetting>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutPageTemplateSetting>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first layout page template setting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting findByUuid_First(String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = fetchByUuid_First(uuid,
				orderByComparator);

		if (layoutPageTemplateSetting != null) {
			return layoutPageTemplateSetting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPageTemplateSettingException(msg.toString());
	}

	/**
	 * Returns the first layout page template setting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByUuid_First(String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		List<LayoutPageTemplateSetting> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout page template setting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting findByUuid_Last(String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = fetchByUuid_Last(uuid,
				orderByComparator);

		if (layoutPageTemplateSetting != null) {
			return layoutPageTemplateSetting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPageTemplateSettingException(msg.toString());
	}

	/**
	 * Returns the last layout page template setting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByUuid_Last(String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<LayoutPageTemplateSetting> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout page template settings before and after the current layout page template setting in the ordered set where uuid = &#63;.
	 *
	 * @param layoutPageTemplateSettingId the primary key of the current layout page template setting
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateSetting[] findByUuid_PrevAndNext(
		long layoutPageTemplateSettingId, String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = findByPrimaryKey(layoutPageTemplateSettingId);

		Session session = null;

		try {
			session = openSession();

			LayoutPageTemplateSetting[] array = new LayoutPageTemplateSettingImpl[3];

			array[0] = getByUuid_PrevAndNext(session,
					layoutPageTemplateSetting, uuid, orderByComparator, true);

			array[1] = layoutPageTemplateSetting;

			array[2] = getByUuid_PrevAndNext(session,
					layoutPageTemplateSetting, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutPageTemplateSetting getByUuid_PrevAndNext(Session session,
		LayoutPageTemplateSetting layoutPageTemplateSetting, String uuid,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LayoutPageTemplateSettingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(layoutPageTemplateSetting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutPageTemplateSetting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout page template settings where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (LayoutPageTemplateSetting layoutPageTemplateSetting : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutPageTemplateSetting);
		}
	}

	/**
	 * Returns the number of layout page template settings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching layout page template settings
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTPAGETEMPLATESETTING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "layoutPageTemplateSetting.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "layoutPageTemplateSetting.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(layoutPageTemplateSetting.uuid IS NULL OR layoutPageTemplateSetting.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			LayoutPageTemplateSettingModelImpl.UUID_COLUMN_BITMASK |
			LayoutPageTemplateSettingModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the layout page template setting where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPageTemplateSettingException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting findByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = fetchByUUID_G(uuid,
				groupId);

		if (layoutPageTemplateSetting == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPageTemplateSettingException(msg.toString());
		}

		return layoutPageTemplateSetting;
	}

	/**
	 * Returns the layout page template setting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the layout page template setting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof LayoutPageTemplateSetting) {
			LayoutPageTemplateSetting layoutPageTemplateSetting = (LayoutPageTemplateSetting)result;

			if (!Objects.equals(uuid, layoutPageTemplateSetting.getUuid()) ||
					(groupId != layoutPageTemplateSetting.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<LayoutPageTemplateSetting> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					LayoutPageTemplateSetting layoutPageTemplateSetting = list.get(0);

					result = layoutPageTemplateSetting;

					cacheResult(layoutPageTemplateSetting);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (LayoutPageTemplateSetting)result;
		}
	}

	/**
	 * Removes the layout page template setting where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the layout page template setting that was removed
	 */
	@Override
	public LayoutPageTemplateSetting removeByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = findByUUID_G(uuid,
				groupId);

		return remove(layoutPageTemplateSetting);
	}

	/**
	 * Returns the number of layout page template settings where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching layout page template settings
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LAYOUTPAGETEMPLATESETTING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "layoutPageTemplateSetting.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "layoutPageTemplateSetting.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(layoutPageTemplateSetting.uuid IS NULL OR layoutPageTemplateSetting.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "layoutPageTemplateSetting.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			LayoutPageTemplateSettingModelImpl.UUID_COLUMN_BITMASK |
			LayoutPageTemplateSettingModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the layout page template settings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout page template settings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @return the range of matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout page template settings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout page template settings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<LayoutPageTemplateSetting> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutPageTemplateSetting>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutPageTemplateSetting layoutPageTemplateSetting : list) {
					if (!Objects.equals(uuid,
								layoutPageTemplateSetting.getUuid()) ||
							(companyId != layoutPageTemplateSetting.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutPageTemplateSettingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<LayoutPageTemplateSetting>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutPageTemplateSetting>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (layoutPageTemplateSetting != null) {
			return layoutPageTemplateSetting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPageTemplateSettingException(msg.toString());
	}

	/**
	 * Returns the first layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		List<LayoutPageTemplateSetting> list = findByUuid_C(uuid, companyId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (layoutPageTemplateSetting != null) {
			return layoutPageTemplateSetting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPageTemplateSettingException(msg.toString());
	}

	/**
	 * Returns the last layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<LayoutPageTemplateSetting> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout page template settings before and after the current layout page template setting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param layoutPageTemplateSettingId the primary key of the current layout page template setting
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateSetting[] findByUuid_C_PrevAndNext(
		long layoutPageTemplateSettingId, String uuid, long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = findByPrimaryKey(layoutPageTemplateSettingId);

		Session session = null;

		try {
			session = openSession();

			LayoutPageTemplateSetting[] array = new LayoutPageTemplateSettingImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					layoutPageTemplateSetting, uuid, companyId,
					orderByComparator, true);

			array[1] = layoutPageTemplateSetting;

			array[2] = getByUuid_C_PrevAndNext(session,
					layoutPageTemplateSetting, uuid, companyId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutPageTemplateSetting getByUuid_C_PrevAndNext(
		Session session, LayoutPageTemplateSetting layoutPageTemplateSetting,
		String uuid, long companyId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LayoutPageTemplateSettingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(layoutPageTemplateSetting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutPageTemplateSetting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout page template settings where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (LayoutPageTemplateSetting layoutPageTemplateSetting : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutPageTemplateSetting);
		}
	}

	/**
	 * Returns the number of layout page template settings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching layout page template settings
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LAYOUTPAGETEMPLATESETTING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "layoutPageTemplateSetting.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "layoutPageTemplateSetting.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(layoutPageTemplateSetting.uuid IS NULL OR layoutPageTemplateSetting.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "layoutPageTemplateSetting.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			LayoutPageTemplateSettingModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupId", new String[] { Long.class.getName() });

	/**
	 * Returns all the layout page template settings where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout page template settings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @return the range of matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByGroupId(long groupId,
		int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout page template settings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout page template settings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<LayoutPageTemplateSetting> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutPageTemplateSetting>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutPageTemplateSetting layoutPageTemplateSetting : list) {
					if ((groupId != layoutPageTemplateSetting.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutPageTemplateSettingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<LayoutPageTemplateSetting>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutPageTemplateSetting>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first layout page template setting in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting findByGroupId_First(long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = fetchByGroupId_First(groupId,
				orderByComparator);

		if (layoutPageTemplateSetting != null) {
			return layoutPageTemplateSetting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchPageTemplateSettingException(msg.toString());
	}

	/**
	 * Returns the first layout page template setting in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByGroupId_First(long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		List<LayoutPageTemplateSetting> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout page template setting in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting findByGroupId_Last(long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (layoutPageTemplateSetting != null) {
			return layoutPageTemplateSetting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchPageTemplateSettingException(msg.toString());
	}

	/**
	 * Returns the last layout page template setting in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByGroupId_Last(long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<LayoutPageTemplateSetting> list = findByGroupId(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout page template settings before and after the current layout page template setting in the ordered set where groupId = &#63;.
	 *
	 * @param layoutPageTemplateSettingId the primary key of the current layout page template setting
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateSetting[] findByGroupId_PrevAndNext(
		long layoutPageTemplateSettingId, long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = findByPrimaryKey(layoutPageTemplateSettingId);

		Session session = null;

		try {
			session = openSession();

			LayoutPageTemplateSetting[] array = new LayoutPageTemplateSettingImpl[3];

			array[0] = getByGroupId_PrevAndNext(session,
					layoutPageTemplateSetting, groupId, orderByComparator, true);

			array[1] = layoutPageTemplateSetting;

			array[2] = getByGroupId_PrevAndNext(session,
					layoutPageTemplateSetting, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutPageTemplateSetting getByGroupId_PrevAndNext(
		Session session, LayoutPageTemplateSetting layoutPageTemplateSetting,
		long groupId,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LayoutPageTemplateSettingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(layoutPageTemplateSetting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutPageTemplateSetting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout page template settings where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (LayoutPageTemplateSetting layoutPageTemplateSetting : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutPageTemplateSetting);
		}
	}

	/**
	 * Returns the number of layout page template settings where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching layout page template settings
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTPAGETEMPLATESETTING_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "layoutPageTemplateSetting.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_C_C = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			LayoutPageTemplateSettingModelImpl.GROUPID_COLUMN_BITMASK |
			LayoutPageTemplateSettingModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LayoutPageTemplateSettingModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C_C = new FinderPath(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchPageTemplateSettingException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting findByG_C_C(long groupId,
		long classNameId, long classPK)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = fetchByG_C_C(groupId,
				classNameId, classPK);

		if (layoutPageTemplateSetting == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPageTemplateSettingException(msg.toString());
		}

		return layoutPageTemplateSetting;
	}

	/**
	 * Returns the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByG_C_C(long groupId,
		long classNameId, long classPK) {
		return fetchByG_C_C(groupId, classNameId, classPK, true);
	}

	/**
	 * Returns the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching layout page template setting, or <code>null</code> if a matching layout page template setting could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByG_C_C(long groupId,
		long classNameId, long classPK, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_C_C,
					finderArgs, this);
		}

		if (result instanceof LayoutPageTemplateSetting) {
			LayoutPageTemplateSetting layoutPageTemplateSetting = (LayoutPageTemplateSetting)result;

			if ((groupId != layoutPageTemplateSetting.getGroupId()) ||
					(classNameId != layoutPageTemplateSetting.getClassNameId()) ||
					(classPK != layoutPageTemplateSetting.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<LayoutPageTemplateSetting> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_C_C,
						finderArgs, list);
				}
				else {
					LayoutPageTemplateSetting layoutPageTemplateSetting = list.get(0);

					result = layoutPageTemplateSetting;

					cacheResult(layoutPageTemplateSetting);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_C_C, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (LayoutPageTemplateSetting)result;
		}
	}

	/**
	 * Removes the layout page template setting where groupId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the layout page template setting that was removed
	 */
	@Override
	public LayoutPageTemplateSetting removeByG_C_C(long groupId,
		long classNameId, long classPK)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = findByG_C_C(groupId,
				classNameId, classPK);

		return remove(layoutPageTemplateSetting);
	}

	/**
	 * Returns the number of layout page template settings where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching layout page template settings
	 */
	@Override
	public int countByG_C_C(long groupId, long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_C_C;

		Object[] finderArgs = new Object[] { groupId, classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LAYOUTPAGETEMPLATESETTING_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_C_C_GROUPID_2 = "layoutPageTemplateSetting.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_CLASSNAMEID_2 = "layoutPageTemplateSetting.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_CLASSPK_2 = "layoutPageTemplateSetting.classPK = ?";

	public LayoutPageTemplateSettingPersistenceImpl() {
		setModelClass(LayoutPageTemplateSetting.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("settings", "settings_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the layout page template setting in the entity cache if it is enabled.
	 *
	 * @param layoutPageTemplateSetting the layout page template setting
	 */
	@Override
	public void cacheResult(LayoutPageTemplateSetting layoutPageTemplateSetting) {
		entityCache.putResult(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			layoutPageTemplateSetting.getPrimaryKey(), layoutPageTemplateSetting);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				layoutPageTemplateSetting.getUuid(),
				layoutPageTemplateSetting.getGroupId()
			}, layoutPageTemplateSetting);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_C_C,
			new Object[] {
				layoutPageTemplateSetting.getGroupId(),
				layoutPageTemplateSetting.getClassNameId(),
				layoutPageTemplateSetting.getClassPK()
			}, layoutPageTemplateSetting);

		layoutPageTemplateSetting.resetOriginalValues();
	}

	/**
	 * Caches the layout page template settings in the entity cache if it is enabled.
	 *
	 * @param layoutPageTemplateSettings the layout page template settings
	 */
	@Override
	public void cacheResult(
		List<LayoutPageTemplateSetting> layoutPageTemplateSettings) {
		for (LayoutPageTemplateSetting layoutPageTemplateSetting : layoutPageTemplateSettings) {
			if (entityCache.getResult(
						LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
						LayoutPageTemplateSettingImpl.class,
						layoutPageTemplateSetting.getPrimaryKey()) == null) {
				cacheResult(layoutPageTemplateSetting);
			}
			else {
				layoutPageTemplateSetting.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all layout page template settings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LayoutPageTemplateSettingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the layout page template setting.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LayoutPageTemplateSetting layoutPageTemplateSetting) {
		entityCache.removeResult(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			layoutPageTemplateSetting.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LayoutPageTemplateSettingModelImpl)layoutPageTemplateSetting,
			true);
	}

	@Override
	public void clearCache(
		List<LayoutPageTemplateSetting> layoutPageTemplateSettings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LayoutPageTemplateSetting layoutPageTemplateSetting : layoutPageTemplateSettings) {
			entityCache.removeResult(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
				LayoutPageTemplateSettingImpl.class,
				layoutPageTemplateSetting.getPrimaryKey());

			clearUniqueFindersCache((LayoutPageTemplateSettingModelImpl)layoutPageTemplateSetting,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		LayoutPageTemplateSettingModelImpl layoutPageTemplateSettingModelImpl) {
		Object[] args = new Object[] {
				layoutPageTemplateSettingModelImpl.getUuid(),
				layoutPageTemplateSettingModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			layoutPageTemplateSettingModelImpl, false);

		args = new Object[] {
				layoutPageTemplateSettingModelImpl.getGroupId(),
				layoutPageTemplateSettingModelImpl.getClassNameId(),
				layoutPageTemplateSettingModelImpl.getClassPK()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_C_C, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_C_C, args,
			layoutPageTemplateSettingModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LayoutPageTemplateSettingModelImpl layoutPageTemplateSettingModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					layoutPageTemplateSettingModelImpl.getUuid(),
					layoutPageTemplateSettingModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((layoutPageTemplateSettingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					layoutPageTemplateSettingModelImpl.getOriginalUuid(),
					layoutPageTemplateSettingModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					layoutPageTemplateSettingModelImpl.getGroupId(),
					layoutPageTemplateSettingModelImpl.getClassNameId(),
					layoutPageTemplateSettingModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_C_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_C_C, args);
		}

		if ((layoutPageTemplateSettingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_C_C.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					layoutPageTemplateSettingModelImpl.getOriginalGroupId(),
					layoutPageTemplateSettingModelImpl.getOriginalClassNameId(),
					layoutPageTemplateSettingModelImpl.getOriginalClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_C_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_C_C, args);
		}
	}

	/**
	 * Creates a new layout page template setting with the primary key. Does not add the layout page template setting to the database.
	 *
	 * @param layoutPageTemplateSettingId the primary key for the new layout page template setting
	 * @return the new layout page template setting
	 */
	@Override
	public LayoutPageTemplateSetting create(long layoutPageTemplateSettingId) {
		LayoutPageTemplateSetting layoutPageTemplateSetting = new LayoutPageTemplateSettingImpl();

		layoutPageTemplateSetting.setNew(true);
		layoutPageTemplateSetting.setPrimaryKey(layoutPageTemplateSettingId);

		String uuid = PortalUUIDUtil.generate();

		layoutPageTemplateSetting.setUuid(uuid);

		layoutPageTemplateSetting.setCompanyId(companyProvider.getCompanyId());

		return layoutPageTemplateSetting;
	}

	/**
	 * Removes the layout page template setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPageTemplateSettingId the primary key of the layout page template setting
	 * @return the layout page template setting that was removed
	 * @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateSetting remove(long layoutPageTemplateSettingId)
		throws NoSuchPageTemplateSettingException {
		return remove((Serializable)layoutPageTemplateSettingId);
	}

	/**
	 * Removes the layout page template setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the layout page template setting
	 * @return the layout page template setting that was removed
	 * @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateSetting remove(Serializable primaryKey)
		throws NoSuchPageTemplateSettingException {
		Session session = null;

		try {
			session = openSession();

			LayoutPageTemplateSetting layoutPageTemplateSetting = (LayoutPageTemplateSetting)session.get(LayoutPageTemplateSettingImpl.class,
					primaryKey);

			if (layoutPageTemplateSetting == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPageTemplateSettingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(layoutPageTemplateSetting);
		}
		catch (NoSuchPageTemplateSettingException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected LayoutPageTemplateSetting removeImpl(
		LayoutPageTemplateSetting layoutPageTemplateSetting) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(layoutPageTemplateSetting)) {
				layoutPageTemplateSetting = (LayoutPageTemplateSetting)session.get(LayoutPageTemplateSettingImpl.class,
						layoutPageTemplateSetting.getPrimaryKeyObj());
			}

			if (layoutPageTemplateSetting != null) {
				session.delete(layoutPageTemplateSetting);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (layoutPageTemplateSetting != null) {
			clearCache(layoutPageTemplateSetting);
		}

		return layoutPageTemplateSetting;
	}

	@Override
	public LayoutPageTemplateSetting updateImpl(
		LayoutPageTemplateSetting layoutPageTemplateSetting) {
		boolean isNew = layoutPageTemplateSetting.isNew();

		if (!(layoutPageTemplateSetting instanceof LayoutPageTemplateSettingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(layoutPageTemplateSetting.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(layoutPageTemplateSetting);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in layoutPageTemplateSetting proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LayoutPageTemplateSetting implementation " +
				layoutPageTemplateSetting.getClass());
		}

		LayoutPageTemplateSettingModelImpl layoutPageTemplateSettingModelImpl = (LayoutPageTemplateSettingModelImpl)layoutPageTemplateSetting;

		if (Validator.isNull(layoutPageTemplateSetting.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			layoutPageTemplateSetting.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (layoutPageTemplateSetting.getCreateDate() == null)) {
			if (serviceContext == null) {
				layoutPageTemplateSetting.setCreateDate(now);
			}
			else {
				layoutPageTemplateSetting.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!layoutPageTemplateSettingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				layoutPageTemplateSetting.setModifiedDate(now);
			}
			else {
				layoutPageTemplateSetting.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (layoutPageTemplateSetting.isNew()) {
				session.save(layoutPageTemplateSetting);

				layoutPageTemplateSetting.setNew(false);
			}
			else {
				layoutPageTemplateSetting = (LayoutPageTemplateSetting)session.merge(layoutPageTemplateSetting);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LayoutPageTemplateSettingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					layoutPageTemplateSettingModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					layoutPageTemplateSettingModelImpl.getUuid(),
					layoutPageTemplateSettingModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { layoutPageTemplateSettingModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((layoutPageTemplateSettingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutPageTemplateSettingModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { layoutPageTemplateSettingModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((layoutPageTemplateSettingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutPageTemplateSettingModelImpl.getOriginalUuid(),
						layoutPageTemplateSettingModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						layoutPageTemplateSettingModelImpl.getUuid(),
						layoutPageTemplateSettingModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((layoutPageTemplateSettingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutPageTemplateSettingModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						layoutPageTemplateSettingModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
			LayoutPageTemplateSettingImpl.class,
			layoutPageTemplateSetting.getPrimaryKey(),
			layoutPageTemplateSetting, false);

		clearUniqueFindersCache(layoutPageTemplateSettingModelImpl, false);
		cacheUniqueFindersCache(layoutPageTemplateSettingModelImpl);

		layoutPageTemplateSetting.resetOriginalValues();

		return layoutPageTemplateSetting;
	}

	/**
	 * Returns the layout page template setting with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout page template setting
	 * @return the layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateSetting findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPageTemplateSettingException {
		LayoutPageTemplateSetting layoutPageTemplateSetting = fetchByPrimaryKey(primaryKey);

		if (layoutPageTemplateSetting == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPageTemplateSettingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return layoutPageTemplateSetting;
	}

	/**
	 * Returns the layout page template setting with the primary key or throws a {@link NoSuchPageTemplateSettingException} if it could not be found.
	 *
	 * @param layoutPageTemplateSettingId the primary key of the layout page template setting
	 * @return the layout page template setting
	 * @throws NoSuchPageTemplateSettingException if a layout page template setting with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateSetting findByPrimaryKey(
		long layoutPageTemplateSettingId)
		throws NoSuchPageTemplateSettingException {
		return findByPrimaryKey((Serializable)layoutPageTemplateSettingId);
	}

	/**
	 * Returns the layout page template setting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout page template setting
	 * @return the layout page template setting, or <code>null</code> if a layout page template setting with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
				LayoutPageTemplateSettingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LayoutPageTemplateSetting layoutPageTemplateSetting = (LayoutPageTemplateSetting)serializable;

		if (layoutPageTemplateSetting == null) {
			Session session = null;

			try {
				session = openSession();

				layoutPageTemplateSetting = (LayoutPageTemplateSetting)session.get(LayoutPageTemplateSettingImpl.class,
						primaryKey);

				if (layoutPageTemplateSetting != null) {
					cacheResult(layoutPageTemplateSetting);
				}
				else {
					entityCache.putResult(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
						LayoutPageTemplateSettingImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
					LayoutPageTemplateSettingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return layoutPageTemplateSetting;
	}

	/**
	 * Returns the layout page template setting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param layoutPageTemplateSettingId the primary key of the layout page template setting
	 * @return the layout page template setting, or <code>null</code> if a layout page template setting with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateSetting fetchByPrimaryKey(
		long layoutPageTemplateSettingId) {
		return fetchByPrimaryKey((Serializable)layoutPageTemplateSettingId);
	}

	@Override
	public Map<Serializable, LayoutPageTemplateSetting> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LayoutPageTemplateSetting> map = new HashMap<Serializable, LayoutPageTemplateSetting>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LayoutPageTemplateSetting layoutPageTemplateSetting = fetchByPrimaryKey(primaryKey);

			if (layoutPageTemplateSetting != null) {
				map.put(primaryKey, layoutPageTemplateSetting);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
					LayoutPageTemplateSettingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LayoutPageTemplateSetting)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (LayoutPageTemplateSetting layoutPageTemplateSetting : (List<LayoutPageTemplateSetting>)q.list()) {
				map.put(layoutPageTemplateSetting.getPrimaryKeyObj(),
					layoutPageTemplateSetting);

				cacheResult(layoutPageTemplateSetting);

				uncachedPrimaryKeys.remove(layoutPageTemplateSetting.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LayoutPageTemplateSettingModelImpl.ENTITY_CACHE_ENABLED,
					LayoutPageTemplateSettingImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the layout page template settings.
	 *
	 * @return the layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout page template settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @return the range of layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout page template settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findAll(int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout page template settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPageTemplateSettingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout page template settings
	 * @param end the upper bound of the range of layout page template settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of layout page template settings
	 */
	@Override
	public List<LayoutPageTemplateSetting> findAll(int start, int end,
		OrderByComparator<LayoutPageTemplateSetting> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<LayoutPageTemplateSetting> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutPageTemplateSetting>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LAYOUTPAGETEMPLATESETTING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LAYOUTPAGETEMPLATESETTING;

				if (pagination) {
					sql = sql.concat(LayoutPageTemplateSettingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LayoutPageTemplateSetting>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutPageTemplateSetting>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the layout page template settings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LayoutPageTemplateSetting layoutPageTemplateSetting : findAll()) {
			remove(layoutPageTemplateSetting);
		}
	}

	/**
	 * Returns the number of layout page template settings.
	 *
	 * @return the number of layout page template settings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LAYOUTPAGETEMPLATESETTING);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LayoutPageTemplateSettingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the layout page template setting persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LayoutPageTemplateSettingImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LAYOUTPAGETEMPLATESETTING = "SELECT layoutPageTemplateSetting FROM LayoutPageTemplateSetting layoutPageTemplateSetting";
	private static final String _SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE_PKS_IN =
		"SELECT layoutPageTemplateSetting FROM LayoutPageTemplateSetting layoutPageTemplateSetting WHERE layoutPageTemplateSettingId IN (";
	private static final String _SQL_SELECT_LAYOUTPAGETEMPLATESETTING_WHERE = "SELECT layoutPageTemplateSetting FROM LayoutPageTemplateSetting layoutPageTemplateSetting WHERE ";
	private static final String _SQL_COUNT_LAYOUTPAGETEMPLATESETTING = "SELECT COUNT(layoutPageTemplateSetting) FROM LayoutPageTemplateSetting layoutPageTemplateSetting";
	private static final String _SQL_COUNT_LAYOUTPAGETEMPLATESETTING_WHERE = "SELECT COUNT(layoutPageTemplateSetting) FROM LayoutPageTemplateSetting layoutPageTemplateSetting WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "layoutPageTemplateSetting.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LayoutPageTemplateSetting exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LayoutPageTemplateSetting exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LayoutPageTemplateSettingPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "settings"
			});
}