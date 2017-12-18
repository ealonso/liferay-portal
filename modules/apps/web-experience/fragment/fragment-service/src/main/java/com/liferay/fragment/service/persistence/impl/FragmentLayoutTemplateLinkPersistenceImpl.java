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

package com.liferay.fragment.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException;
import com.liferay.fragment.model.FragmentLayoutTemplateLink;
import com.liferay.fragment.model.impl.FragmentLayoutTemplateLinkImpl;
import com.liferay.fragment.model.impl.FragmentLayoutTemplateLinkModelImpl;
import com.liferay.fragment.service.persistence.FragmentLayoutTemplateLinkPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the fragment layout template link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FragmentLayoutTemplateLinkPersistence
 * @see com.liferay.fragment.service.persistence.FragmentLayoutTemplateLinkUtil
 * @generated
 */
@ProviderType
public class FragmentLayoutTemplateLinkPersistenceImpl
	extends BasePersistenceImpl<FragmentLayoutTemplateLink>
	implements FragmentLayoutTemplateLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FragmentLayoutTemplateLinkUtil} to access the fragment layout template link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FragmentLayoutTemplateLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			FragmentLayoutTemplateLinkModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupId", new String[] { Long.class.getName() });

	/**
	 * Returns all the fragment layout template links where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment layout template links where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @return the range of matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByGroupId(long groupId,
		int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment layout template links where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment layout template links where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
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

		List<FragmentLayoutTemplateLink> list = null;

		if (retrieveFromCache) {
			list = (List<FragmentLayoutTemplateLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink : list) {
					if ((groupId != fragmentLayoutTemplateLink.getGroupId())) {
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

			query.append(_SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FragmentLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<FragmentLayoutTemplateLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FragmentLayoutTemplateLink>)QueryUtil.list(q,
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
	 * Returns the first fragment layout template link in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink findByGroupId_First(long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = fetchByGroupId_First(groupId,
				orderByComparator);

		if (fragmentLayoutTemplateLink != null) {
			return fragmentLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the first fragment layout template link in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink fetchByGroupId_First(long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		List<FragmentLayoutTemplateLink> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment layout template link in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink findByGroupId_Last(long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (fragmentLayoutTemplateLink != null) {
			return fragmentLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the last fragment layout template link in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink fetchByGroupId_Last(long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<FragmentLayoutTemplateLink> list = findByGroupId(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment layout template links before and after the current fragment layout template link in the ordered set where groupId = &#63;.
	 *
	 * @param fragmentLayoutTemplateLinkId the primary key of the current fragment layout template link
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink[] findByGroupId_PrevAndNext(
		long fragmentLayoutTemplateLinkId, long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = findByPrimaryKey(fragmentLayoutTemplateLinkId);

		Session session = null;

		try {
			session = openSession();

			FragmentLayoutTemplateLink[] array = new FragmentLayoutTemplateLinkImpl[3];

			array[0] = getByGroupId_PrevAndNext(session,
					fragmentLayoutTemplateLink, groupId, orderByComparator, true);

			array[1] = fragmentLayoutTemplateLink;

			array[2] = getByGroupId_PrevAndNext(session,
					fragmentLayoutTemplateLink, groupId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FragmentLayoutTemplateLink getByGroupId_PrevAndNext(
		Session session, FragmentLayoutTemplateLink fragmentLayoutTemplateLink,
		long groupId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
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

		query.append(_SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK_WHERE);

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
			query.append(FragmentLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(fragmentLayoutTemplateLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FragmentLayoutTemplateLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment layout template links where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(fragmentLayoutTemplateLink);
		}
	}

	/**
	 * Returns the number of fragment layout template links where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fragment layout template links
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FRAGMENTLAYOUTTEMPLATELINK_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "fragmentLayoutTemplateLink.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_F = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_F",
			new String[] { Long.class.getName(), Long.class.getName() },
			FragmentLayoutTemplateLinkModelImpl.GROUPID_COLUMN_BITMASK |
			FragmentLayoutTemplateLinkModelImpl.FRAGMENTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_F = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_F",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @return the matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId) {
		return findByG_F(groupId, fragmentEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @return the range of matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end) {
		return findByG_F(groupId, fragmentEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return findByG_F(groupId, fragmentEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F;
			finderArgs = new Object[] { groupId, fragmentEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_F;
			finderArgs = new Object[] {
					groupId, fragmentEntryId,
					
					start, end, orderByComparator
				};
		}

		List<FragmentLayoutTemplateLink> list = null;

		if (retrieveFromCache) {
			list = (List<FragmentLayoutTemplateLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink : list) {
					if ((groupId != fragmentLayoutTemplateLink.getGroupId()) ||
							(fragmentEntryId != fragmentLayoutTemplateLink.getFragmentEntryId())) {
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

			query.append(_SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK_WHERE);

			query.append(_FINDER_COLUMN_G_F_GROUPID_2);

			query.append(_FINDER_COLUMN_G_F_FRAGMENTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FragmentLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(fragmentEntryId);

				if (!pagination) {
					list = (List<FragmentLayoutTemplateLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FragmentLayoutTemplateLink>)QueryUtil.list(q,
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
	 * Returns the first fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink findByG_F_First(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = fetchByG_F_First(groupId,
				fragmentEntryId, orderByComparator);

		if (fragmentLayoutTemplateLink != null) {
			return fragmentLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", fragmentEntryId=");
		msg.append(fragmentEntryId);

		msg.append("}");

		throw new NoSuchLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the first fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink fetchByG_F_First(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		List<FragmentLayoutTemplateLink> list = findByG_F(groupId,
				fragmentEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink findByG_F_Last(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = fetchByG_F_Last(groupId,
				fragmentEntryId, orderByComparator);

		if (fragmentLayoutTemplateLink != null) {
			return fragmentLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", fragmentEntryId=");
		msg.append(fragmentEntryId);

		msg.append("}");

		throw new NoSuchLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the last fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink fetchByG_F_Last(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		int count = countByG_F(groupId, fragmentEntryId);

		if (count == 0) {
			return null;
		}

		List<FragmentLayoutTemplateLink> list = findByG_F(groupId,
				fragmentEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment layout template links before and after the current fragment layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param fragmentLayoutTemplateLinkId the primary key of the current fragment layout template link
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink[] findByG_F_PrevAndNext(
		long fragmentLayoutTemplateLinkId, long groupId, long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = findByPrimaryKey(fragmentLayoutTemplateLinkId);

		Session session = null;

		try {
			session = openSession();

			FragmentLayoutTemplateLink[] array = new FragmentLayoutTemplateLinkImpl[3];

			array[0] = getByG_F_PrevAndNext(session,
					fragmentLayoutTemplateLink, groupId, fragmentEntryId,
					orderByComparator, true);

			array[1] = fragmentLayoutTemplateLink;

			array[2] = getByG_F_PrevAndNext(session,
					fragmentLayoutTemplateLink, groupId, fragmentEntryId,
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

	protected FragmentLayoutTemplateLink getByG_F_PrevAndNext(Session session,
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink, long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
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

		query.append(_SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK_WHERE);

		query.append(_FINDER_COLUMN_G_F_GROUPID_2);

		query.append(_FINDER_COLUMN_G_F_FRAGMENTENTRYID_2);

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
			query.append(FragmentLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(fragmentEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(fragmentLayoutTemplateLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FragmentLayoutTemplateLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment layout template links where groupId = &#63; and fragmentEntryId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 */
	@Override
	public void removeByG_F(long groupId, long fragmentEntryId) {
		for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink : findByG_F(
				groupId, fragmentEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(fragmentLayoutTemplateLink);
		}
	}

	/**
	 * Returns the number of fragment layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @return the number of matching fragment layout template links
	 */
	@Override
	public int countByG_F(long groupId, long fragmentEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_F;

		Object[] finderArgs = new Object[] { groupId, fragmentEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FRAGMENTLAYOUTTEMPLATELINK_WHERE);

			query.append(_FINDER_COLUMN_G_F_GROUPID_2);

			query.append(_FINDER_COLUMN_G_F_FRAGMENTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(fragmentEntryId);

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

	private static final String _FINDER_COLUMN_G_F_GROUPID_2 = "fragmentLayoutTemplateLink.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_F_FRAGMENTENTRYID_2 = "fragmentLayoutTemplateLink.fragmentEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_L = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_L",
			new String[] { Long.class.getName(), Long.class.getName() },
			FragmentLayoutTemplateLinkModelImpl.GROUPID_COLUMN_BITMASK |
			FragmentLayoutTemplateLinkModelImpl.LAYOUTPAGETEMPLATEENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_L = new FinderPath(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_L",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @return the matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId) {
		return findByG_L(groupId, layoutPageTemplateEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @return the range of matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end) {
		return findByG_L(groupId, layoutPageTemplateEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return findByG_L(groupId, layoutPageTemplateEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L;
			finderArgs = new Object[] { groupId, layoutPageTemplateEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_L;
			finderArgs = new Object[] {
					groupId, layoutPageTemplateEntryId,
					
					start, end, orderByComparator
				};
		}

		List<FragmentLayoutTemplateLink> list = null;

		if (retrieveFromCache) {
			list = (List<FragmentLayoutTemplateLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink : list) {
					if ((groupId != fragmentLayoutTemplateLink.getGroupId()) ||
							(layoutPageTemplateEntryId != fragmentLayoutTemplateLink.getLayoutPageTemplateEntryId())) {
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

			query.append(_SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK_WHERE);

			query.append(_FINDER_COLUMN_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_L_LAYOUTPAGETEMPLATEENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FragmentLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(layoutPageTemplateEntryId);

				if (!pagination) {
					list = (List<FragmentLayoutTemplateLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FragmentLayoutTemplateLink>)QueryUtil.list(q,
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
	 * Returns the first fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink findByG_L_First(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = fetchByG_L_First(groupId,
				layoutPageTemplateEntryId, orderByComparator);

		if (fragmentLayoutTemplateLink != null) {
			return fragmentLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", layoutPageTemplateEntryId=");
		msg.append(layoutPageTemplateEntryId);

		msg.append("}");

		throw new NoSuchLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the first fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink fetchByG_L_First(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		List<FragmentLayoutTemplateLink> list = findByG_L(groupId,
				layoutPageTemplateEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink findByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = fetchByG_L_Last(groupId,
				layoutPageTemplateEntryId, orderByComparator);

		if (fragmentLayoutTemplateLink != null) {
			return fragmentLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", layoutPageTemplateEntryId=");
		msg.append(layoutPageTemplateEntryId);

		msg.append("}");

		throw new NoSuchLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the last fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment layout template link, or <code>null</code> if a matching fragment layout template link could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink fetchByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		int count = countByG_L(groupId, layoutPageTemplateEntryId);

		if (count == 0) {
			return null;
		}

		List<FragmentLayoutTemplateLink> list = findByG_L(groupId,
				layoutPageTemplateEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment layout template links before and after the current fragment layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param fragmentLayoutTemplateLinkId the primary key of the current fragment layout template link
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink[] findByG_L_PrevAndNext(
		long fragmentLayoutTemplateLinkId, long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator)
		throws NoSuchLayoutTemplateLinkException {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = findByPrimaryKey(fragmentLayoutTemplateLinkId);

		Session session = null;

		try {
			session = openSession();

			FragmentLayoutTemplateLink[] array = new FragmentLayoutTemplateLinkImpl[3];

			array[0] = getByG_L_PrevAndNext(session,
					fragmentLayoutTemplateLink, groupId,
					layoutPageTemplateEntryId, orderByComparator, true);

			array[1] = fragmentLayoutTemplateLink;

			array[2] = getByG_L_PrevAndNext(session,
					fragmentLayoutTemplateLink, groupId,
					layoutPageTemplateEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FragmentLayoutTemplateLink getByG_L_PrevAndNext(Session session,
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink, long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
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

		query.append(_SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK_WHERE);

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LAYOUTPAGETEMPLATEENTRYID_2);

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
			query.append(FragmentLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(layoutPageTemplateEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(fragmentLayoutTemplateLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FragmentLayoutTemplateLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 */
	@Override
	public void removeByG_L(long groupId, long layoutPageTemplateEntryId) {
		for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink : findByG_L(
				groupId, layoutPageTemplateEntryId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(fragmentLayoutTemplateLink);
		}
	}

	/**
	 * Returns the number of fragment layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @return the number of matching fragment layout template links
	 */
	@Override
	public int countByG_L(long groupId, long layoutPageTemplateEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_L;

		Object[] finderArgs = new Object[] { groupId, layoutPageTemplateEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FRAGMENTLAYOUTTEMPLATELINK_WHERE);

			query.append(_FINDER_COLUMN_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_L_LAYOUTPAGETEMPLATEENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(layoutPageTemplateEntryId);

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

	private static final String _FINDER_COLUMN_G_L_GROUPID_2 = "fragmentLayoutTemplateLink.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_L_LAYOUTPAGETEMPLATEENTRYID_2 = "fragmentLayoutTemplateLink.layoutPageTemplateEntryId = ?";

	public FragmentLayoutTemplateLinkPersistenceImpl() {
		setModelClass(FragmentLayoutTemplateLink.class);
	}

	/**
	 * Caches the fragment layout template link in the entity cache if it is enabled.
	 *
	 * @param fragmentLayoutTemplateLink the fragment layout template link
	 */
	@Override
	public void cacheResult(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		entityCache.putResult(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			fragmentLayoutTemplateLink.getPrimaryKey(),
			fragmentLayoutTemplateLink);

		fragmentLayoutTemplateLink.resetOriginalValues();
	}

	/**
	 * Caches the fragment layout template links in the entity cache if it is enabled.
	 *
	 * @param fragmentLayoutTemplateLinks the fragment layout template links
	 */
	@Override
	public void cacheResult(
		List<FragmentLayoutTemplateLink> fragmentLayoutTemplateLinks) {
		for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink : fragmentLayoutTemplateLinks) {
			if (entityCache.getResult(
						FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
						FragmentLayoutTemplateLinkImpl.class,
						fragmentLayoutTemplateLink.getPrimaryKey()) == null) {
				cacheResult(fragmentLayoutTemplateLink);
			}
			else {
				fragmentLayoutTemplateLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all fragment layout template links.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FragmentLayoutTemplateLinkImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the fragment layout template link.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		entityCache.removeResult(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			fragmentLayoutTemplateLink.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<FragmentLayoutTemplateLink> fragmentLayoutTemplateLinks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink : fragmentLayoutTemplateLinks) {
			entityCache.removeResult(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
				FragmentLayoutTemplateLinkImpl.class,
				fragmentLayoutTemplateLink.getPrimaryKey());
		}
	}

	/**
	 * Creates a new fragment layout template link with the primary key. Does not add the fragment layout template link to the database.
	 *
	 * @param fragmentLayoutTemplateLinkId the primary key for the new fragment layout template link
	 * @return the new fragment layout template link
	 */
	@Override
	public FragmentLayoutTemplateLink create(long fragmentLayoutTemplateLinkId) {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = new FragmentLayoutTemplateLinkImpl();

		fragmentLayoutTemplateLink.setNew(true);
		fragmentLayoutTemplateLink.setPrimaryKey(fragmentLayoutTemplateLinkId);

		return fragmentLayoutTemplateLink;
	}

	/**
	 * Removes the fragment layout template link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	 * @return the fragment layout template link that was removed
	 * @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink remove(long fragmentLayoutTemplateLinkId)
		throws NoSuchLayoutTemplateLinkException {
		return remove((Serializable)fragmentLayoutTemplateLinkId);
	}

	/**
	 * Removes the fragment layout template link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the fragment layout template link
	 * @return the fragment layout template link that was removed
	 * @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink remove(Serializable primaryKey)
		throws NoSuchLayoutTemplateLinkException {
		Session session = null;

		try {
			session = openSession();

			FragmentLayoutTemplateLink fragmentLayoutTemplateLink = (FragmentLayoutTemplateLink)session.get(FragmentLayoutTemplateLinkImpl.class,
					primaryKey);

			if (fragmentLayoutTemplateLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLayoutTemplateLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(fragmentLayoutTemplateLink);
		}
		catch (NoSuchLayoutTemplateLinkException nsee) {
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
	protected FragmentLayoutTemplateLink removeImpl(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		fragmentLayoutTemplateLink = toUnwrappedModel(fragmentLayoutTemplateLink);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fragmentLayoutTemplateLink)) {
				fragmentLayoutTemplateLink = (FragmentLayoutTemplateLink)session.get(FragmentLayoutTemplateLinkImpl.class,
						fragmentLayoutTemplateLink.getPrimaryKeyObj());
			}

			if (fragmentLayoutTemplateLink != null) {
				session.delete(fragmentLayoutTemplateLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (fragmentLayoutTemplateLink != null) {
			clearCache(fragmentLayoutTemplateLink);
		}

		return fragmentLayoutTemplateLink;
	}

	@Override
	public FragmentLayoutTemplateLink updateImpl(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		fragmentLayoutTemplateLink = toUnwrappedModel(fragmentLayoutTemplateLink);

		boolean isNew = fragmentLayoutTemplateLink.isNew();

		FragmentLayoutTemplateLinkModelImpl fragmentLayoutTemplateLinkModelImpl = (FragmentLayoutTemplateLinkModelImpl)fragmentLayoutTemplateLink;

		Session session = null;

		try {
			session = openSession();

			if (fragmentLayoutTemplateLink.isNew()) {
				session.save(fragmentLayoutTemplateLink);

				fragmentLayoutTemplateLink.setNew(false);
			}
			else {
				fragmentLayoutTemplateLink = (FragmentLayoutTemplateLink)session.merge(fragmentLayoutTemplateLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!FragmentLayoutTemplateLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					fragmentLayoutTemplateLinkModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					fragmentLayoutTemplateLinkModelImpl.getGroupId(),
					fragmentLayoutTemplateLinkModelImpl.getFragmentEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_F, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F,
				args);

			args = new Object[] {
					fragmentLayoutTemplateLinkModelImpl.getGroupId(),
					fragmentLayoutTemplateLinkModelImpl.getLayoutPageTemplateEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_L, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((fragmentLayoutTemplateLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						fragmentLayoutTemplateLinkModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						fragmentLayoutTemplateLinkModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((fragmentLayoutTemplateLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						fragmentLayoutTemplateLinkModelImpl.getOriginalGroupId(),
						fragmentLayoutTemplateLinkModelImpl.getOriginalFragmentEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_F, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F,
					args);

				args = new Object[] {
						fragmentLayoutTemplateLinkModelImpl.getGroupId(),
						fragmentLayoutTemplateLinkModelImpl.getFragmentEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_F, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F,
					args);
			}

			if ((fragmentLayoutTemplateLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						fragmentLayoutTemplateLinkModelImpl.getOriginalGroupId(),
						fragmentLayoutTemplateLinkModelImpl.getOriginalLayoutPageTemplateEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_L, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L,
					args);

				args = new Object[] {
						fragmentLayoutTemplateLinkModelImpl.getGroupId(),
						fragmentLayoutTemplateLinkModelImpl.getLayoutPageTemplateEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_L, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L,
					args);
			}
		}

		entityCache.putResult(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentLayoutTemplateLinkImpl.class,
			fragmentLayoutTemplateLink.getPrimaryKey(),
			fragmentLayoutTemplateLink, false);

		fragmentLayoutTemplateLink.resetOriginalValues();

		return fragmentLayoutTemplateLink;
	}

	protected FragmentLayoutTemplateLink toUnwrappedModel(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		if (fragmentLayoutTemplateLink instanceof FragmentLayoutTemplateLinkImpl) {
			return fragmentLayoutTemplateLink;
		}

		FragmentLayoutTemplateLinkImpl fragmentLayoutTemplateLinkImpl = new FragmentLayoutTemplateLinkImpl();

		fragmentLayoutTemplateLinkImpl.setNew(fragmentLayoutTemplateLink.isNew());
		fragmentLayoutTemplateLinkImpl.setPrimaryKey(fragmentLayoutTemplateLink.getPrimaryKey());

		fragmentLayoutTemplateLinkImpl.setFragmentLayoutTemplateLinkId(fragmentLayoutTemplateLink.getFragmentLayoutTemplateLinkId());
		fragmentLayoutTemplateLinkImpl.setGroupId(fragmentLayoutTemplateLink.getGroupId());
		fragmentLayoutTemplateLinkImpl.setFragmentEntryId(fragmentLayoutTemplateLink.getFragmentEntryId());
		fragmentLayoutTemplateLinkImpl.setLayoutPageTemplateEntryId(fragmentLayoutTemplateLink.getLayoutPageTemplateEntryId());

		return fragmentLayoutTemplateLinkImpl;
	}

	/**
	 * Returns the fragment layout template link with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the fragment layout template link
	 * @return the fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLayoutTemplateLinkException {
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = fetchByPrimaryKey(primaryKey);

		if (fragmentLayoutTemplateLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLayoutTemplateLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return fragmentLayoutTemplateLink;
	}

	/**
	 * Returns the fragment layout template link with the primary key or throws a {@link NoSuchLayoutTemplateLinkException} if it could not be found.
	 *
	 * @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	 * @return the fragment layout template link
	 * @throws NoSuchLayoutTemplateLinkException if a fragment layout template link with the primary key could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink findByPrimaryKey(
		long fragmentLayoutTemplateLinkId)
		throws NoSuchLayoutTemplateLinkException {
		return findByPrimaryKey((Serializable)fragmentLayoutTemplateLinkId);
	}

	/**
	 * Returns the fragment layout template link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the fragment layout template link
	 * @return the fragment layout template link, or <code>null</code> if a fragment layout template link with the primary key could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
				FragmentLayoutTemplateLinkImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = (FragmentLayoutTemplateLink)serializable;

		if (fragmentLayoutTemplateLink == null) {
			Session session = null;

			try {
				session = openSession();

				fragmentLayoutTemplateLink = (FragmentLayoutTemplateLink)session.get(FragmentLayoutTemplateLinkImpl.class,
						primaryKey);

				if (fragmentLayoutTemplateLink != null) {
					cacheResult(fragmentLayoutTemplateLink);
				}
				else {
					entityCache.putResult(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
						FragmentLayoutTemplateLinkImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
					FragmentLayoutTemplateLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return fragmentLayoutTemplateLink;
	}

	/**
	 * Returns the fragment layout template link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fragmentLayoutTemplateLinkId the primary key of the fragment layout template link
	 * @return the fragment layout template link, or <code>null</code> if a fragment layout template link with the primary key could not be found
	 */
	@Override
	public FragmentLayoutTemplateLink fetchByPrimaryKey(
		long fragmentLayoutTemplateLinkId) {
		return fetchByPrimaryKey((Serializable)fragmentLayoutTemplateLinkId);
	}

	@Override
	public Map<Serializable, FragmentLayoutTemplateLink> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FragmentLayoutTemplateLink> map = new HashMap<Serializable, FragmentLayoutTemplateLink>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FragmentLayoutTemplateLink fragmentLayoutTemplateLink = fetchByPrimaryKey(primaryKey);

			if (fragmentLayoutTemplateLink != null) {
				map.put(primaryKey, fragmentLayoutTemplateLink);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
					FragmentLayoutTemplateLinkImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (FragmentLayoutTemplateLink)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK_WHERE_PKS_IN);

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

			for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink : (List<FragmentLayoutTemplateLink>)q.list()) {
				map.put(fragmentLayoutTemplateLink.getPrimaryKeyObj(),
					fragmentLayoutTemplateLink);

				cacheResult(fragmentLayoutTemplateLink);

				uncachedPrimaryKeys.remove(fragmentLayoutTemplateLink.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(FragmentLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
					FragmentLayoutTemplateLinkImpl.class, primaryKey, nullModel);
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
	 * Returns all the fragment layout template links.
	 *
	 * @return the fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment layout template links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @return the range of fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment layout template links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findAll(int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment layout template links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment layout template links
	 * @param end the upper bound of the range of fragment layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of fragment layout template links
	 */
	@Override
	public List<FragmentLayoutTemplateLink> findAll(int start, int end,
		OrderByComparator<FragmentLayoutTemplateLink> orderByComparator,
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

		List<FragmentLayoutTemplateLink> list = null;

		if (retrieveFromCache) {
			list = (List<FragmentLayoutTemplateLink>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK;

				if (pagination) {
					sql = sql.concat(FragmentLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FragmentLayoutTemplateLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FragmentLayoutTemplateLink>)QueryUtil.list(q,
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
	 * Removes all the fragment layout template links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink : findAll()) {
			remove(fragmentLayoutTemplateLink);
		}
	}

	/**
	 * Returns the number of fragment layout template links.
	 *
	 * @return the number of fragment layout template links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FRAGMENTLAYOUTTEMPLATELINK);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return FragmentLayoutTemplateLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the fragment layout template link persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FragmentLayoutTemplateLinkImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK = "SELECT fragmentLayoutTemplateLink FROM FragmentLayoutTemplateLink fragmentLayoutTemplateLink";
	private static final String _SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK_WHERE_PKS_IN =
		"SELECT fragmentLayoutTemplateLink FROM FragmentLayoutTemplateLink fragmentLayoutTemplateLink WHERE fragmentLayoutTemplateLinkId IN (";
	private static final String _SQL_SELECT_FRAGMENTLAYOUTTEMPLATELINK_WHERE = "SELECT fragmentLayoutTemplateLink FROM FragmentLayoutTemplateLink fragmentLayoutTemplateLink WHERE ";
	private static final String _SQL_COUNT_FRAGMENTLAYOUTTEMPLATELINK = "SELECT COUNT(fragmentLayoutTemplateLink) FROM FragmentLayoutTemplateLink fragmentLayoutTemplateLink";
	private static final String _SQL_COUNT_FRAGMENTLAYOUTTEMPLATELINK_WHERE = "SELECT COUNT(fragmentLayoutTemplateLink) FROM FragmentLayoutTemplateLink fragmentLayoutTemplateLink WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "fragmentLayoutTemplateLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FragmentLayoutTemplateLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FragmentLayoutTemplateLink exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(FragmentLayoutTemplateLinkPersistenceImpl.class);
}