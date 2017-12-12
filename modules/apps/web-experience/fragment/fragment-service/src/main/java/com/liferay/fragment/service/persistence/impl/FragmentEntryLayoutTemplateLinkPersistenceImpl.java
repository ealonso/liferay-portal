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

import com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException;
import com.liferay.fragment.model.FragmentEntryLayoutTemplateLink;
import com.liferay.fragment.model.impl.FragmentEntryLayoutTemplateLinkImpl;
import com.liferay.fragment.model.impl.FragmentEntryLayoutTemplateLinkModelImpl;
import com.liferay.fragment.service.persistence.FragmentEntryLayoutTemplateLinkPersistence;

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
 * The persistence implementation for the fragment entry layout template link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntryLayoutTemplateLinkPersistence
 * @see com.liferay.fragment.service.persistence.FragmentEntryLayoutTemplateLinkUtil
 * @generated
 */
@ProviderType
public class FragmentEntryLayoutTemplateLinkPersistenceImpl
	extends BasePersistenceImpl<FragmentEntryLayoutTemplateLink>
	implements FragmentEntryLayoutTemplateLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FragmentEntryLayoutTemplateLinkUtil} to access the fragment entry layout template link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FragmentEntryLayoutTemplateLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			FragmentEntryLayoutTemplateLinkModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupId", new String[] { Long.class.getName() });

	/**
	 * Returns all the fragment entry layout template links where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment entry layout template links where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @return the range of matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByGroupId(long groupId,
		int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment entry layout template links where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment entry layout template links where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
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

		List<FragmentEntryLayoutTemplateLink> list = null;

		if (retrieveFromCache) {
			list = (List<FragmentEntryLayoutTemplateLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink : list) {
					if ((groupId != fragmentEntryLayoutTemplateLink.getGroupId())) {
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

			query.append(_SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FragmentEntryLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<FragmentEntryLayoutTemplateLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FragmentEntryLayoutTemplateLink>)QueryUtil.list(q,
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
	 * Returns the first fragment entry layout template link in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink findByGroupId_First(long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = fetchByGroupId_First(groupId,
				orderByComparator);

		if (fragmentEntryLayoutTemplateLink != null) {
			return fragmentEntryLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchEntryLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the first fragment entry layout template link in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink fetchByGroupId_First(long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		List<FragmentEntryLayoutTemplateLink> list = findByGroupId(groupId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment entry layout template link in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink findByGroupId_Last(long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (fragmentEntryLayoutTemplateLink != null) {
			return fragmentEntryLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchEntryLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the last fragment entry layout template link in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink fetchByGroupId_Last(long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<FragmentEntryLayoutTemplateLink> list = findByGroupId(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment entry layout template links before and after the current fragment entry layout template link in the ordered set where groupId = &#63;.
	 *
	 * @param fragmentEntryLayoutTemplateLinkId the primary key of the current fragment entry layout template link
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink[] findByGroupId_PrevAndNext(
		long fragmentEntryLayoutTemplateLinkId, long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = findByPrimaryKey(fragmentEntryLayoutTemplateLinkId);

		Session session = null;

		try {
			session = openSession();

			FragmentEntryLayoutTemplateLink[] array = new FragmentEntryLayoutTemplateLinkImpl[3];

			array[0] = getByGroupId_PrevAndNext(session,
					fragmentEntryLayoutTemplateLink, groupId,
					orderByComparator, true);

			array[1] = fragmentEntryLayoutTemplateLink;

			array[2] = getByGroupId_PrevAndNext(session,
					fragmentEntryLayoutTemplateLink, groupId,
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

	protected FragmentEntryLayoutTemplateLink getByGroupId_PrevAndNext(
		Session session,
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink,
		long groupId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
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

		query.append(_SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE);

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
			query.append(FragmentEntryLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(fragmentEntryLayoutTemplateLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FragmentEntryLayoutTemplateLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment entry layout template links where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(fragmentEntryLayoutTemplateLink);
		}
	}

	/**
	 * Returns the number of fragment entry layout template links where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fragment entry layout template links
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "fragmentEntryLayoutTemplateLink.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_F = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_F",
			new String[] { Long.class.getName(), Long.class.getName() },
			FragmentEntryLayoutTemplateLinkModelImpl.GROUPID_COLUMN_BITMASK |
			FragmentEntryLayoutTemplateLinkModelImpl.FRAGMENTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_F = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_F",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @return the matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId) {
		return findByG_F(groupId, fragmentEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @return the range of matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end) {
		return findByG_F(groupId, fragmentEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return findByG_F(groupId, fragmentEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByG_F(long groupId,
		long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
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

		List<FragmentEntryLayoutTemplateLink> list = null;

		if (retrieveFromCache) {
			list = (List<FragmentEntryLayoutTemplateLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink : list) {
					if ((groupId != fragmentEntryLayoutTemplateLink.getGroupId()) ||
							(fragmentEntryId != fragmentEntryLayoutTemplateLink.getFragmentEntryId())) {
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

			query.append(_SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE);

			query.append(_FINDER_COLUMN_G_F_GROUPID_2);

			query.append(_FINDER_COLUMN_G_F_FRAGMENTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FragmentEntryLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
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
					list = (List<FragmentEntryLayoutTemplateLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FragmentEntryLayoutTemplateLink>)QueryUtil.list(q,
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
	 * Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink findByG_F_First(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = fetchByG_F_First(groupId,
				fragmentEntryId, orderByComparator);

		if (fragmentEntryLayoutTemplateLink != null) {
			return fragmentEntryLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", fragmentEntryId=");
		msg.append(fragmentEntryId);

		msg.append("}");

		throw new NoSuchEntryLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink fetchByG_F_First(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		List<FragmentEntryLayoutTemplateLink> list = findByG_F(groupId,
				fragmentEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink findByG_F_Last(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = fetchByG_F_Last(groupId,
				fragmentEntryId, orderByComparator);

		if (fragmentEntryLayoutTemplateLink != null) {
			return fragmentEntryLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", fragmentEntryId=");
		msg.append(fragmentEntryId);

		msg.append("}");

		throw new NoSuchEntryLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink fetchByG_F_Last(long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		int count = countByG_F(groupId, fragmentEntryId);

		if (count == 0) {
			return null;
		}

		List<FragmentEntryLayoutTemplateLink> list = findByG_F(groupId,
				fragmentEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment entry layout template links before and after the current fragment entry layout template link in the ordered set where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param fragmentEntryLayoutTemplateLinkId the primary key of the current fragment entry layout template link
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink[] findByG_F_PrevAndNext(
		long fragmentEntryLayoutTemplateLinkId, long groupId,
		long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = findByPrimaryKey(fragmentEntryLayoutTemplateLinkId);

		Session session = null;

		try {
			session = openSession();

			FragmentEntryLayoutTemplateLink[] array = new FragmentEntryLayoutTemplateLinkImpl[3];

			array[0] = getByG_F_PrevAndNext(session,
					fragmentEntryLayoutTemplateLink, groupId, fragmentEntryId,
					orderByComparator, true);

			array[1] = fragmentEntryLayoutTemplateLink;

			array[2] = getByG_F_PrevAndNext(session,
					fragmentEntryLayoutTemplateLink, groupId, fragmentEntryId,
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

	protected FragmentEntryLayoutTemplateLink getByG_F_PrevAndNext(
		Session session,
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink,
		long groupId, long fragmentEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
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

		query.append(_SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE);

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
			query.append(FragmentEntryLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(fragmentEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(fragmentEntryLayoutTemplateLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FragmentEntryLayoutTemplateLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 */
	@Override
	public void removeByG_F(long groupId, long fragmentEntryId) {
		for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink : findByG_F(
				groupId, fragmentEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(fragmentEntryLayoutTemplateLink);
		}
	}

	/**
	 * Returns the number of fragment entry layout template links where groupId = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param fragmentEntryId the fragment entry ID
	 * @return the number of matching fragment entry layout template links
	 */
	@Override
	public int countByG_F(long groupId, long fragmentEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_F;

		Object[] finderArgs = new Object[] { groupId, fragmentEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE);

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

	private static final String _FINDER_COLUMN_G_F_GROUPID_2 = "fragmentEntryLayoutTemplateLink.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_F_FRAGMENTENTRYID_2 = "fragmentEntryLayoutTemplateLink.fragmentEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_L = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_L",
			new String[] { Long.class.getName(), Long.class.getName() },
			FragmentEntryLayoutTemplateLinkModelImpl.GROUPID_COLUMN_BITMASK |
			FragmentEntryLayoutTemplateLinkModelImpl.LAYOUTPAGETEMPLATEENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_L = new FinderPath(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_L",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @return the matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId) {
		return findByG_L(groupId, layoutPageTemplateEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @return the range of matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end) {
		return findByG_L(groupId, layoutPageTemplateEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return findByG_L(groupId, layoutPageTemplateEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findByG_L(long groupId,
		long layoutPageTemplateEntryId, int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
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

		List<FragmentEntryLayoutTemplateLink> list = null;

		if (retrieveFromCache) {
			list = (List<FragmentEntryLayoutTemplateLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink : list) {
					if ((groupId != fragmentEntryLayoutTemplateLink.getGroupId()) ||
							(layoutPageTemplateEntryId != fragmentEntryLayoutTemplateLink.getLayoutPageTemplateEntryId())) {
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

			query.append(_SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE);

			query.append(_FINDER_COLUMN_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_L_LAYOUTPAGETEMPLATEENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FragmentEntryLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
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
					list = (List<FragmentEntryLayoutTemplateLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FragmentEntryLayoutTemplateLink>)QueryUtil.list(q,
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
	 * Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink findByG_L_First(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = fetchByG_L_First(groupId,
				layoutPageTemplateEntryId, orderByComparator);

		if (fragmentEntryLayoutTemplateLink != null) {
			return fragmentEntryLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", layoutPageTemplateEntryId=");
		msg.append(layoutPageTemplateEntryId);

		msg.append("}");

		throw new NoSuchEntryLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the first fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink fetchByG_L_First(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		List<FragmentEntryLayoutTemplateLink> list = findByG_L(groupId,
				layoutPageTemplateEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink findByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = fetchByG_L_Last(groupId,
				layoutPageTemplateEntryId, orderByComparator);

		if (fragmentEntryLayoutTemplateLink != null) {
			return fragmentEntryLayoutTemplateLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", layoutPageTemplateEntryId=");
		msg.append(layoutPageTemplateEntryId);

		msg.append("}");

		throw new NoSuchEntryLayoutTemplateLinkException(msg.toString());
	}

	/**
	 * Returns the last fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fragment entry layout template link, or <code>null</code> if a matching fragment entry layout template link could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink fetchByG_L_Last(long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		int count = countByG_L(groupId, layoutPageTemplateEntryId);

		if (count == 0) {
			return null;
		}

		List<FragmentEntryLayoutTemplateLink> list = findByG_L(groupId,
				layoutPageTemplateEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fragment entry layout template links before and after the current fragment entry layout template link in the ordered set where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param fragmentEntryLayoutTemplateLinkId the primary key of the current fragment entry layout template link
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink[] findByG_L_PrevAndNext(
		long fragmentEntryLayoutTemplateLinkId, long groupId,
		long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator)
		throws NoSuchEntryLayoutTemplateLinkException {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = findByPrimaryKey(fragmentEntryLayoutTemplateLinkId);

		Session session = null;

		try {
			session = openSession();

			FragmentEntryLayoutTemplateLink[] array = new FragmentEntryLayoutTemplateLinkImpl[3];

			array[0] = getByG_L_PrevAndNext(session,
					fragmentEntryLayoutTemplateLink, groupId,
					layoutPageTemplateEntryId, orderByComparator, true);

			array[1] = fragmentEntryLayoutTemplateLink;

			array[2] = getByG_L_PrevAndNext(session,
					fragmentEntryLayoutTemplateLink, groupId,
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

	protected FragmentEntryLayoutTemplateLink getByG_L_PrevAndNext(
		Session session,
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink,
		long groupId, long layoutPageTemplateEntryId,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
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

		query.append(_SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE);

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
			query.append(FragmentEntryLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(layoutPageTemplateEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(fragmentEntryLayoutTemplateLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FragmentEntryLayoutTemplateLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 */
	@Override
	public void removeByG_L(long groupId, long layoutPageTemplateEntryId) {
		for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink : findByG_L(
				groupId, layoutPageTemplateEntryId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(fragmentEntryLayoutTemplateLink);
		}
	}

	/**
	 * Returns the number of fragment entry layout template links where groupId = &#63; and layoutPageTemplateEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param layoutPageTemplateEntryId the layout page template entry ID
	 * @return the number of matching fragment entry layout template links
	 */
	@Override
	public int countByG_L(long groupId, long layoutPageTemplateEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_L;

		Object[] finderArgs = new Object[] { groupId, layoutPageTemplateEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE);

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

	private static final String _FINDER_COLUMN_G_L_GROUPID_2 = "fragmentEntryLayoutTemplateLink.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_L_LAYOUTPAGETEMPLATEENTRYID_2 = "fragmentEntryLayoutTemplateLink.layoutPageTemplateEntryId = ?";

	public FragmentEntryLayoutTemplateLinkPersistenceImpl() {
		setModelClass(FragmentEntryLayoutTemplateLink.class);
	}

	/**
	 * Caches the fragment entry layout template link in the entity cache if it is enabled.
	 *
	 * @param fragmentEntryLayoutTemplateLink the fragment entry layout template link
	 */
	@Override
	public void cacheResult(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		entityCache.putResult(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			fragmentEntryLayoutTemplateLink.getPrimaryKey(),
			fragmentEntryLayoutTemplateLink);

		fragmentEntryLayoutTemplateLink.resetOriginalValues();
	}

	/**
	 * Caches the fragment entry layout template links in the entity cache if it is enabled.
	 *
	 * @param fragmentEntryLayoutTemplateLinks the fragment entry layout template links
	 */
	@Override
	public void cacheResult(
		List<FragmentEntryLayoutTemplateLink> fragmentEntryLayoutTemplateLinks) {
		for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink : fragmentEntryLayoutTemplateLinks) {
			if (entityCache.getResult(
						FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
						FragmentEntryLayoutTemplateLinkImpl.class,
						fragmentEntryLayoutTemplateLink.getPrimaryKey()) == null) {
				cacheResult(fragmentEntryLayoutTemplateLink);
			}
			else {
				fragmentEntryLayoutTemplateLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all fragment entry layout template links.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FragmentEntryLayoutTemplateLinkImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the fragment entry layout template link.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		entityCache.removeResult(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			fragmentEntryLayoutTemplateLink.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<FragmentEntryLayoutTemplateLink> fragmentEntryLayoutTemplateLinks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink : fragmentEntryLayoutTemplateLinks) {
			entityCache.removeResult(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
				FragmentEntryLayoutTemplateLinkImpl.class,
				fragmentEntryLayoutTemplateLink.getPrimaryKey());
		}
	}

	/**
	 * Creates a new fragment entry layout template link with the primary key. Does not add the fragment entry layout template link to the database.
	 *
	 * @param fragmentEntryLayoutTemplateLinkId the primary key for the new fragment entry layout template link
	 * @return the new fragment entry layout template link
	 */
	@Override
	public FragmentEntryLayoutTemplateLink create(
		long fragmentEntryLayoutTemplateLinkId) {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = new FragmentEntryLayoutTemplateLinkImpl();

		fragmentEntryLayoutTemplateLink.setNew(true);
		fragmentEntryLayoutTemplateLink.setPrimaryKey(fragmentEntryLayoutTemplateLinkId);

		return fragmentEntryLayoutTemplateLink;
	}

	/**
	 * Removes the fragment entry layout template link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	 * @return the fragment entry layout template link that was removed
	 * @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink remove(
		long fragmentEntryLayoutTemplateLinkId)
		throws NoSuchEntryLayoutTemplateLinkException {
		return remove((Serializable)fragmentEntryLayoutTemplateLinkId);
	}

	/**
	 * Removes the fragment entry layout template link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the fragment entry layout template link
	 * @return the fragment entry layout template link that was removed
	 * @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink remove(Serializable primaryKey)
		throws NoSuchEntryLayoutTemplateLinkException {
		Session session = null;

		try {
			session = openSession();

			FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = (FragmentEntryLayoutTemplateLink)session.get(FragmentEntryLayoutTemplateLinkImpl.class,
					primaryKey);

			if (fragmentEntryLayoutTemplateLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryLayoutTemplateLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(fragmentEntryLayoutTemplateLink);
		}
		catch (NoSuchEntryLayoutTemplateLinkException nsee) {
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
	protected FragmentEntryLayoutTemplateLink removeImpl(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		fragmentEntryLayoutTemplateLink = toUnwrappedModel(fragmentEntryLayoutTemplateLink);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fragmentEntryLayoutTemplateLink)) {
				fragmentEntryLayoutTemplateLink = (FragmentEntryLayoutTemplateLink)session.get(FragmentEntryLayoutTemplateLinkImpl.class,
						fragmentEntryLayoutTemplateLink.getPrimaryKeyObj());
			}

			if (fragmentEntryLayoutTemplateLink != null) {
				session.delete(fragmentEntryLayoutTemplateLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (fragmentEntryLayoutTemplateLink != null) {
			clearCache(fragmentEntryLayoutTemplateLink);
		}

		return fragmentEntryLayoutTemplateLink;
	}

	@Override
	public FragmentEntryLayoutTemplateLink updateImpl(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		fragmentEntryLayoutTemplateLink = toUnwrappedModel(fragmentEntryLayoutTemplateLink);

		boolean isNew = fragmentEntryLayoutTemplateLink.isNew();

		FragmentEntryLayoutTemplateLinkModelImpl fragmentEntryLayoutTemplateLinkModelImpl =
			(FragmentEntryLayoutTemplateLinkModelImpl)fragmentEntryLayoutTemplateLink;

		Session session = null;

		try {
			session = openSession();

			if (fragmentEntryLayoutTemplateLink.isNew()) {
				session.save(fragmentEntryLayoutTemplateLink);

				fragmentEntryLayoutTemplateLink.setNew(false);
			}
			else {
				fragmentEntryLayoutTemplateLink = (FragmentEntryLayoutTemplateLink)session.merge(fragmentEntryLayoutTemplateLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!FragmentEntryLayoutTemplateLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					fragmentEntryLayoutTemplateLinkModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					fragmentEntryLayoutTemplateLinkModelImpl.getGroupId(),
					fragmentEntryLayoutTemplateLinkModelImpl.getFragmentEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_F, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F,
				args);

			args = new Object[] {
					fragmentEntryLayoutTemplateLinkModelImpl.getGroupId(),
					fragmentEntryLayoutTemplateLinkModelImpl.getLayoutPageTemplateEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_L, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((fragmentEntryLayoutTemplateLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						fragmentEntryLayoutTemplateLinkModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						fragmentEntryLayoutTemplateLinkModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((fragmentEntryLayoutTemplateLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						fragmentEntryLayoutTemplateLinkModelImpl.getOriginalGroupId(),
						fragmentEntryLayoutTemplateLinkModelImpl.getOriginalFragmentEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_F, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F,
					args);

				args = new Object[] {
						fragmentEntryLayoutTemplateLinkModelImpl.getGroupId(),
						fragmentEntryLayoutTemplateLinkModelImpl.getFragmentEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_F, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_F,
					args);
			}

			if ((fragmentEntryLayoutTemplateLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						fragmentEntryLayoutTemplateLinkModelImpl.getOriginalGroupId(),
						fragmentEntryLayoutTemplateLinkModelImpl.getOriginalLayoutPageTemplateEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_L, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L,
					args);

				args = new Object[] {
						fragmentEntryLayoutTemplateLinkModelImpl.getGroupId(),
						fragmentEntryLayoutTemplateLinkModelImpl.getLayoutPageTemplateEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_L, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L,
					args);
			}
		}

		entityCache.putResult(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
			FragmentEntryLayoutTemplateLinkImpl.class,
			fragmentEntryLayoutTemplateLink.getPrimaryKey(),
			fragmentEntryLayoutTemplateLink, false);

		fragmentEntryLayoutTemplateLink.resetOriginalValues();

		return fragmentEntryLayoutTemplateLink;
	}

	protected FragmentEntryLayoutTemplateLink toUnwrappedModel(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		if (fragmentEntryLayoutTemplateLink instanceof FragmentEntryLayoutTemplateLinkImpl) {
			return fragmentEntryLayoutTemplateLink;
		}

		FragmentEntryLayoutTemplateLinkImpl fragmentEntryLayoutTemplateLinkImpl = new FragmentEntryLayoutTemplateLinkImpl();

		fragmentEntryLayoutTemplateLinkImpl.setNew(fragmentEntryLayoutTemplateLink.isNew());
		fragmentEntryLayoutTemplateLinkImpl.setPrimaryKey(fragmentEntryLayoutTemplateLink.getPrimaryKey());

		fragmentEntryLayoutTemplateLinkImpl.setFragmentEntryLayoutTemplateLinkId(fragmentEntryLayoutTemplateLink.getFragmentEntryLayoutTemplateLinkId());
		fragmentEntryLayoutTemplateLinkImpl.setGroupId(fragmentEntryLayoutTemplateLink.getGroupId());
		fragmentEntryLayoutTemplateLinkImpl.setFragmentEntryId(fragmentEntryLayoutTemplateLink.getFragmentEntryId());
		fragmentEntryLayoutTemplateLinkImpl.setLayoutPageTemplateEntryId(fragmentEntryLayoutTemplateLink.getLayoutPageTemplateEntryId());

		return fragmentEntryLayoutTemplateLinkImpl;
	}

	/**
	 * Returns the fragment entry layout template link with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the fragment entry layout template link
	 * @return the fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink findByPrimaryKey(
		Serializable primaryKey) throws NoSuchEntryLayoutTemplateLinkException {
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = fetchByPrimaryKey(primaryKey);

		if (fragmentEntryLayoutTemplateLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryLayoutTemplateLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return fragmentEntryLayoutTemplateLink;
	}

	/**
	 * Returns the fragment entry layout template link with the primary key or throws a {@link NoSuchEntryLayoutTemplateLinkException} if it could not be found.
	 *
	 * @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	 * @return the fragment entry layout template link
	 * @throws NoSuchEntryLayoutTemplateLinkException if a fragment entry layout template link with the primary key could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink findByPrimaryKey(
		long fragmentEntryLayoutTemplateLinkId)
		throws NoSuchEntryLayoutTemplateLinkException {
		return findByPrimaryKey((Serializable)fragmentEntryLayoutTemplateLinkId);
	}

	/**
	 * Returns the fragment entry layout template link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the fragment entry layout template link
	 * @return the fragment entry layout template link, or <code>null</code> if a fragment entry layout template link with the primary key could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
				FragmentEntryLayoutTemplateLinkImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = (FragmentEntryLayoutTemplateLink)serializable;

		if (fragmentEntryLayoutTemplateLink == null) {
			Session session = null;

			try {
				session = openSession();

				fragmentEntryLayoutTemplateLink = (FragmentEntryLayoutTemplateLink)session.get(FragmentEntryLayoutTemplateLinkImpl.class,
						primaryKey);

				if (fragmentEntryLayoutTemplateLink != null) {
					cacheResult(fragmentEntryLayoutTemplateLink);
				}
				else {
					entityCache.putResult(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
						FragmentEntryLayoutTemplateLinkImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
					FragmentEntryLayoutTemplateLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return fragmentEntryLayoutTemplateLink;
	}

	/**
	 * Returns the fragment entry layout template link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fragmentEntryLayoutTemplateLinkId the primary key of the fragment entry layout template link
	 * @return the fragment entry layout template link, or <code>null</code> if a fragment entry layout template link with the primary key could not be found
	 */
	@Override
	public FragmentEntryLayoutTemplateLink fetchByPrimaryKey(
		long fragmentEntryLayoutTemplateLinkId) {
		return fetchByPrimaryKey((Serializable)fragmentEntryLayoutTemplateLinkId);
	}

	@Override
	public Map<Serializable, FragmentEntryLayoutTemplateLink> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FragmentEntryLayoutTemplateLink> map = new HashMap<Serializable, FragmentEntryLayoutTemplateLink>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = fetchByPrimaryKey(primaryKey);

			if (fragmentEntryLayoutTemplateLink != null) {
				map.put(primaryKey, fragmentEntryLayoutTemplateLink);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
					FragmentEntryLayoutTemplateLinkImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(FragmentEntryLayoutTemplateLink)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE_PKS_IN);

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

			for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink : (List<FragmentEntryLayoutTemplateLink>)q.list()) {
				map.put(fragmentEntryLayoutTemplateLink.getPrimaryKeyObj(),
					fragmentEntryLayoutTemplateLink);

				cacheResult(fragmentEntryLayoutTemplateLink);

				uncachedPrimaryKeys.remove(fragmentEntryLayoutTemplateLink.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(FragmentEntryLayoutTemplateLinkModelImpl.ENTITY_CACHE_ENABLED,
					FragmentEntryLayoutTemplateLinkImpl.class, primaryKey,
					nullModel);
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
	 * Returns all the fragment entry layout template links.
	 *
	 * @return the fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fragment entry layout template links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @return the range of fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the fragment entry layout template links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findAll(int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fragment entry layout template links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FragmentEntryLayoutTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fragment entry layout template links
	 * @param end the upper bound of the range of fragment entry layout template links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of fragment entry layout template links
	 */
	@Override
	public List<FragmentEntryLayoutTemplateLink> findAll(int start, int end,
		OrderByComparator<FragmentEntryLayoutTemplateLink> orderByComparator,
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

		List<FragmentEntryLayoutTemplateLink> list = null;

		if (retrieveFromCache) {
			list = (List<FragmentEntryLayoutTemplateLink>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK;

				if (pagination) {
					sql = sql.concat(FragmentEntryLayoutTemplateLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FragmentEntryLayoutTemplateLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FragmentEntryLayoutTemplateLink>)QueryUtil.list(q,
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
	 * Removes all the fragment entry layout template links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink : findAll()) {
			remove(fragmentEntryLayoutTemplateLink);
		}
	}

	/**
	 * Returns the number of fragment entry layout template links.
	 *
	 * @return the number of fragment entry layout template links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FRAGMENTENTRYLAYOUTTEMPLATELINK);

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
		return FragmentEntryLayoutTemplateLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the fragment entry layout template link persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FragmentEntryLayoutTemplateLinkImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK = "SELECT fragmentEntryLayoutTemplateLink FROM FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink";
	private static final String _SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE_PKS_IN =
		"SELECT fragmentEntryLayoutTemplateLink FROM FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink WHERE fragmentEntryLayoutTemplateLinkId IN (";
	private static final String _SQL_SELECT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE =
		"SELECT fragmentEntryLayoutTemplateLink FROM FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink WHERE ";
	private static final String _SQL_COUNT_FRAGMENTENTRYLAYOUTTEMPLATELINK = "SELECT COUNT(fragmentEntryLayoutTemplateLink) FROM FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink";
	private static final String _SQL_COUNT_FRAGMENTENTRYLAYOUTTEMPLATELINK_WHERE =
		"SELECT COUNT(fragmentEntryLayoutTemplateLink) FROM FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "fragmentEntryLayoutTemplateLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FragmentEntryLayoutTemplateLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FragmentEntryLayoutTemplateLink exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(FragmentEntryLayoutTemplateLinkPersistenceImpl.class);
}