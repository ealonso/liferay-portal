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

package com.liferay.layout.service.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.layout.service.exception.NoSuchFragmentException;
import com.liferay.layout.service.model.LayoutFragment;
import com.liferay.layout.service.model.impl.LayoutFragmentImpl;
import com.liferay.layout.service.model.impl.LayoutFragmentModelImpl;
import com.liferay.layout.service.service.persistence.LayoutFragmentPersistence;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the layout fragment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFragmentPersistence
 * @see com.liferay.layout.service.service.persistence.LayoutFragmentUtil
 * @generated
 */
@ProviderType
public class LayoutFragmentPersistenceImpl extends BasePersistenceImpl<LayoutFragment>
	implements LayoutFragmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LayoutFragmentUtil} to access the layout fragment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LayoutFragmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED,
			LayoutFragmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED,
			LayoutFragmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED,
			LayoutFragmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED,
			LayoutFragmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			LayoutFragmentModelImpl.GROUPID_COLUMN_BITMASK |
			LayoutFragmentModelImpl.POSITION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the layout fragments where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout fragments where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @return the range of matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout fragments where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByGroupId(long groupId, int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout fragments where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByGroupId(long groupId, int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator,
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

		List<LayoutFragment> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFragment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFragment layoutFragment : list) {
					if ((groupId != layoutFragment.getGroupId())) {
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

			query.append(_SQL_SELECT_LAYOUTFRAGMENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutFragmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<LayoutFragment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFragment>)QueryUtil.list(q,
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
	 * Returns the first layout fragment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout fragment
	 * @throws NoSuchFragmentException if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment findByGroupId_First(long groupId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException {
		LayoutFragment layoutFragment = fetchByGroupId_First(groupId,
				orderByComparator);

		if (layoutFragment != null) {
			return layoutFragment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchFragmentException(msg.toString());
	}

	/**
	 * Returns the first layout fragment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment fetchByGroupId_First(long groupId,
		OrderByComparator<LayoutFragment> orderByComparator) {
		List<LayoutFragment> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout fragment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout fragment
	 * @throws NoSuchFragmentException if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment findByGroupId_Last(long groupId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException {
		LayoutFragment layoutFragment = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (layoutFragment != null) {
			return layoutFragment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchFragmentException(msg.toString());
	}

	/**
	 * Returns the last layout fragment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment fetchByGroupId_Last(long groupId,
		OrderByComparator<LayoutFragment> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<LayoutFragment> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout fragments before and after the current layout fragment in the ordered set where groupId = &#63;.
	 *
	 * @param layoutFragmentId the primary key of the current layout fragment
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout fragment
	 * @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	 */
	@Override
	public LayoutFragment[] findByGroupId_PrevAndNext(long layoutFragmentId,
		long groupId, OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException {
		LayoutFragment layoutFragment = findByPrimaryKey(layoutFragmentId);

		Session session = null;

		try {
			session = openSession();

			LayoutFragment[] array = new LayoutFragmentImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, layoutFragment,
					groupId, orderByComparator, true);

			array[1] = layoutFragment;

			array[2] = getByGroupId_PrevAndNext(session, layoutFragment,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutFragment getByGroupId_PrevAndNext(Session session,
		LayoutFragment layoutFragment, long groupId,
		OrderByComparator<LayoutFragment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTFRAGMENT_WHERE);

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
			query.append(LayoutFragmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(layoutFragment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutFragment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout fragments where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (LayoutFragment layoutFragment : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutFragment);
		}
	}

	/**
	 * Returns the number of layout fragments where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching layout fragments
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTFRAGMENT_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "layoutFragment.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED,
			LayoutFragmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED,
			LayoutFragmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P",
			new String[] { Long.class.getName(), Long.class.getName() },
			LayoutFragmentModelImpl.GROUPID_COLUMN_BITMASK |
			LayoutFragmentModelImpl.PLID_COLUMN_BITMASK |
			LayoutFragmentModelImpl.POSITION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the layout fragments where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @return the matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByG_P(long groupId, long plid) {
		return findByG_P(groupId, plid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the layout fragments where groupId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @return the range of matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByG_P(long groupId, long plid, int start,
		int end) {
		return findByG_P(groupId, plid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout fragments where groupId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByG_P(long groupId, long plid, int start,
		int end, OrderByComparator<LayoutFragment> orderByComparator) {
		return findByG_P(groupId, plid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout fragments where groupId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByG_P(long groupId, long plid, int start,
		int end, OrderByComparator<LayoutFragment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P;
			finderArgs = new Object[] { groupId, plid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P;
			finderArgs = new Object[] {
					groupId, plid,
					
					start, end, orderByComparator
				};
		}

		List<LayoutFragment> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFragment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFragment layoutFragment : list) {
					if ((groupId != layoutFragment.getGroupId()) ||
							(plid != layoutFragment.getPlid())) {
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

			query.append(_SQL_SELECT_LAYOUTFRAGMENT_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutFragmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(plid);

				if (!pagination) {
					list = (List<LayoutFragment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFragment>)QueryUtil.list(q,
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
	 * Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout fragment
	 * @throws NoSuchFragmentException if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment findByG_P_First(long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException {
		LayoutFragment layoutFragment = fetchByG_P_First(groupId, plid,
				orderByComparator);

		if (layoutFragment != null) {
			return layoutFragment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", plid=");
		msg.append(plid);

		msg.append("}");

		throw new NoSuchFragmentException(msg.toString());
	}

	/**
	 * Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment fetchByG_P_First(long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator) {
		List<LayoutFragment> list = findByG_P(groupId, plid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout fragment
	 * @throws NoSuchFragmentException if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment findByG_P_Last(long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException {
		LayoutFragment layoutFragment = fetchByG_P_Last(groupId, plid,
				orderByComparator);

		if (layoutFragment != null) {
			return layoutFragment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", plid=");
		msg.append(plid);

		msg.append("}");

		throw new NoSuchFragmentException(msg.toString());
	}

	/**
	 * Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment fetchByG_P_Last(long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator) {
		int count = countByG_P(groupId, plid);

		if (count == 0) {
			return null;
		}

		List<LayoutFragment> list = findByG_P(groupId, plid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout fragments before and after the current layout fragment in the ordered set where groupId = &#63; and plid = &#63;.
	 *
	 * @param layoutFragmentId the primary key of the current layout fragment
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout fragment
	 * @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	 */
	@Override
	public LayoutFragment[] findByG_P_PrevAndNext(long layoutFragmentId,
		long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException {
		LayoutFragment layoutFragment = findByPrimaryKey(layoutFragmentId);

		Session session = null;

		try {
			session = openSession();

			LayoutFragment[] array = new LayoutFragmentImpl[3];

			array[0] = getByG_P_PrevAndNext(session, layoutFragment, groupId,
					plid, orderByComparator, true);

			array[1] = layoutFragment;

			array[2] = getByG_P_PrevAndNext(session, layoutFragment, groupId,
					plid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutFragment getByG_P_PrevAndNext(Session session,
		LayoutFragment layoutFragment, long groupId, long plid,
		OrderByComparator<LayoutFragment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LAYOUTFRAGMENT_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PLID_2);

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
			query.append(LayoutFragmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(plid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(layoutFragment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutFragment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout fragments where groupId = &#63; and plid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 */
	@Override
	public void removeByG_P(long groupId, long plid) {
		for (LayoutFragment layoutFragment : findByG_P(groupId, plid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutFragment);
		}
	}

	/**
	 * Returns the number of layout fragments where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @return the number of matching layout fragments
	 */
	@Override
	public int countByG_P(long groupId, long plid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P;

		Object[] finderArgs = new Object[] { groupId, plid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LAYOUTFRAGMENT_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PLID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(plid);

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

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 = "layoutFragment.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_PLID_2 = "layoutFragment.plid = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_F = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED,
			LayoutFragmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_P_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_F = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED,
			LayoutFragmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			LayoutFragmentModelImpl.GROUPID_COLUMN_BITMASK |
			LayoutFragmentModelImpl.PLID_COLUMN_BITMASK |
			LayoutFragmentModelImpl.FRAGMENTENTRYID_COLUMN_BITMASK |
			LayoutFragmentModelImpl.POSITION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_F = new FinderPath(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 * @return the matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId) {
		return findByG_P_F(groupId, plid, fragmentEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @return the range of matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId, int start, int end) {
		return findByG_P_F(groupId, plid, fragmentEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId, int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return findByG_P_F(groupId, plid, fragmentEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching layout fragments
	 */
	@Override
	public List<LayoutFragment> findByG_P_F(long groupId, long plid,
		long fragmentEntryId, int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_F;
			finderArgs = new Object[] { groupId, plid, fragmentEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_F;
			finderArgs = new Object[] {
					groupId, plid, fragmentEntryId,
					
					start, end, orderByComparator
				};
		}

		List<LayoutFragment> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFragment>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutFragment layoutFragment : list) {
					if ((groupId != layoutFragment.getGroupId()) ||
							(plid != layoutFragment.getPlid()) ||
							(fragmentEntryId != layoutFragment.getFragmentEntryId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_LAYOUTFRAGMENT_WHERE);

			query.append(_FINDER_COLUMN_G_P_F_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_F_PLID_2);

			query.append(_FINDER_COLUMN_G_P_F_FRAGMENTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LayoutFragmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(plid);

				qPos.add(fragmentEntryId);

				if (!pagination) {
					list = (List<LayoutFragment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFragment>)QueryUtil.list(q,
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
	 * Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout fragment
	 * @throws NoSuchFragmentException if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment findByG_P_F_First(long groupId, long plid,
		long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException {
		LayoutFragment layoutFragment = fetchByG_P_F_First(groupId, plid,
				fragmentEntryId, orderByComparator);

		if (layoutFragment != null) {
			return layoutFragment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", plid=");
		msg.append(plid);

		msg.append(", fragmentEntryId=");
		msg.append(fragmentEntryId);

		msg.append("}");

		throw new NoSuchFragmentException(msg.toString());
	}

	/**
	 * Returns the first layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment fetchByG_P_F_First(long groupId, long plid,
		long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator) {
		List<LayoutFragment> list = findByG_P_F(groupId, plid, fragmentEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout fragment
	 * @throws NoSuchFragmentException if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment findByG_P_F_Last(long groupId, long plid,
		long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException {
		LayoutFragment layoutFragment = fetchByG_P_F_Last(groupId, plid,
				fragmentEntryId, orderByComparator);

		if (layoutFragment != null) {
			return layoutFragment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", plid=");
		msg.append(plid);

		msg.append(", fragmentEntryId=");
		msg.append(fragmentEntryId);

		msg.append("}");

		throw new NoSuchFragmentException(msg.toString());
	}

	/**
	 * Returns the last layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout fragment, or <code>null</code> if a matching layout fragment could not be found
	 */
	@Override
	public LayoutFragment fetchByG_P_F_Last(long groupId, long plid,
		long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator) {
		int count = countByG_P_F(groupId, plid, fragmentEntryId);

		if (count == 0) {
			return null;
		}

		List<LayoutFragment> list = findByG_P_F(groupId, plid, fragmentEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout fragments before and after the current layout fragment in the ordered set where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param layoutFragmentId the primary key of the current layout fragment
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout fragment
	 * @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	 */
	@Override
	public LayoutFragment[] findByG_P_F_PrevAndNext(long layoutFragmentId,
		long groupId, long plid, long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator)
		throws NoSuchFragmentException {
		LayoutFragment layoutFragment = findByPrimaryKey(layoutFragmentId);

		Session session = null;

		try {
			session = openSession();

			LayoutFragment[] array = new LayoutFragmentImpl[3];

			array[0] = getByG_P_F_PrevAndNext(session, layoutFragment, groupId,
					plid, fragmentEntryId, orderByComparator, true);

			array[1] = layoutFragment;

			array[2] = getByG_P_F_PrevAndNext(session, layoutFragment, groupId,
					plid, fragmentEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutFragment getByG_P_F_PrevAndNext(Session session,
		LayoutFragment layoutFragment, long groupId, long plid,
		long fragmentEntryId,
		OrderByComparator<LayoutFragment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_LAYOUTFRAGMENT_WHERE);

		query.append(_FINDER_COLUMN_G_P_F_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_F_PLID_2);

		query.append(_FINDER_COLUMN_G_P_F_FRAGMENTENTRYID_2);

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
			query.append(LayoutFragmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(plid);

		qPos.add(fragmentEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(layoutFragment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutFragment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 */
	@Override
	public void removeByG_P_F(long groupId, long plid, long fragmentEntryId) {
		for (LayoutFragment layoutFragment : findByG_P_F(groupId, plid,
				fragmentEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(layoutFragment);
		}
	}

	/**
	 * Returns the number of layout fragments where groupId = &#63; and plid = &#63; and fragmentEntryId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param fragmentEntryId the fragment entry ID
	 * @return the number of matching layout fragments
	 */
	@Override
	public int countByG_P_F(long groupId, long plid, long fragmentEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P_F;

		Object[] finderArgs = new Object[] { groupId, plid, fragmentEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LAYOUTFRAGMENT_WHERE);

			query.append(_FINDER_COLUMN_G_P_F_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_F_PLID_2);

			query.append(_FINDER_COLUMN_G_P_F_FRAGMENTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(plid);

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

	private static final String _FINDER_COLUMN_G_P_F_GROUPID_2 = "layoutFragment.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_F_PLID_2 = "layoutFragment.plid = ? AND ";
	private static final String _FINDER_COLUMN_G_P_F_FRAGMENTENTRYID_2 = "layoutFragment.fragmentEntryId = ?";

	public LayoutFragmentPersistenceImpl() {
		setModelClass(LayoutFragment.class);
	}

	/**
	 * Caches the layout fragment in the entity cache if it is enabled.
	 *
	 * @param layoutFragment the layout fragment
	 */
	@Override
	public void cacheResult(LayoutFragment layoutFragment) {
		entityCache.putResult(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentImpl.class, layoutFragment.getPrimaryKey(),
			layoutFragment);

		layoutFragment.resetOriginalValues();
	}

	/**
	 * Caches the layout fragments in the entity cache if it is enabled.
	 *
	 * @param layoutFragments the layout fragments
	 */
	@Override
	public void cacheResult(List<LayoutFragment> layoutFragments) {
		for (LayoutFragment layoutFragment : layoutFragments) {
			if (entityCache.getResult(
						LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
						LayoutFragmentImpl.class, layoutFragment.getPrimaryKey()) == null) {
				cacheResult(layoutFragment);
			}
			else {
				layoutFragment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all layout fragments.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LayoutFragmentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the layout fragment.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LayoutFragment layoutFragment) {
		entityCache.removeResult(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentImpl.class, layoutFragment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LayoutFragment> layoutFragments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LayoutFragment layoutFragment : layoutFragments) {
			entityCache.removeResult(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFragmentImpl.class, layoutFragment.getPrimaryKey());
		}
	}

	/**
	 * Creates a new layout fragment with the primary key. Does not add the layout fragment to the database.
	 *
	 * @param layoutFragmentId the primary key for the new layout fragment
	 * @return the new layout fragment
	 */
	@Override
	public LayoutFragment create(long layoutFragmentId) {
		LayoutFragment layoutFragment = new LayoutFragmentImpl();

		layoutFragment.setNew(true);
		layoutFragment.setPrimaryKey(layoutFragmentId);

		layoutFragment.setCompanyId(companyProvider.getCompanyId());

		return layoutFragment;
	}

	/**
	 * Removes the layout fragment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutFragmentId the primary key of the layout fragment
	 * @return the layout fragment that was removed
	 * @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	 */
	@Override
	public LayoutFragment remove(long layoutFragmentId)
		throws NoSuchFragmentException {
		return remove((Serializable)layoutFragmentId);
	}

	/**
	 * Removes the layout fragment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the layout fragment
	 * @return the layout fragment that was removed
	 * @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	 */
	@Override
	public LayoutFragment remove(Serializable primaryKey)
		throws NoSuchFragmentException {
		Session session = null;

		try {
			session = openSession();

			LayoutFragment layoutFragment = (LayoutFragment)session.get(LayoutFragmentImpl.class,
					primaryKey);

			if (layoutFragment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFragmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(layoutFragment);
		}
		catch (NoSuchFragmentException nsee) {
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
	protected LayoutFragment removeImpl(LayoutFragment layoutFragment) {
		layoutFragment = toUnwrappedModel(layoutFragment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(layoutFragment)) {
				layoutFragment = (LayoutFragment)session.get(LayoutFragmentImpl.class,
						layoutFragment.getPrimaryKeyObj());
			}

			if (layoutFragment != null) {
				session.delete(layoutFragment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (layoutFragment != null) {
			clearCache(layoutFragment);
		}

		return layoutFragment;
	}

	@Override
	public LayoutFragment updateImpl(LayoutFragment layoutFragment) {
		layoutFragment = toUnwrappedModel(layoutFragment);

		boolean isNew = layoutFragment.isNew();

		LayoutFragmentModelImpl layoutFragmentModelImpl = (LayoutFragmentModelImpl)layoutFragment;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (layoutFragment.getCreateDate() == null)) {
			if (serviceContext == null) {
				layoutFragment.setCreateDate(now);
			}
			else {
				layoutFragment.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!layoutFragmentModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				layoutFragment.setModifiedDate(now);
			}
			else {
				layoutFragment.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (layoutFragment.isNew()) {
				session.save(layoutFragment);

				layoutFragment.setNew(false);
			}
			else {
				layoutFragment = (LayoutFragment)session.merge(layoutFragment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LayoutFragmentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { layoutFragmentModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					layoutFragmentModelImpl.getGroupId(),
					layoutFragmentModelImpl.getPlid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
				args);

			args = new Object[] {
					layoutFragmentModelImpl.getGroupId(),
					layoutFragmentModelImpl.getPlid(),
					layoutFragmentModelImpl.getFragmentEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P_F, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_F,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((layoutFragmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFragmentModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { layoutFragmentModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((layoutFragmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFragmentModelImpl.getOriginalGroupId(),
						layoutFragmentModelImpl.getOriginalPlid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
					args);

				args = new Object[] {
						layoutFragmentModelImpl.getGroupId(),
						layoutFragmentModelImpl.getPlid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
					args);
			}

			if ((layoutFragmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						layoutFragmentModelImpl.getOriginalGroupId(),
						layoutFragmentModelImpl.getOriginalPlid(),
						layoutFragmentModelImpl.getOriginalFragmentEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P_F, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_F,
					args);

				args = new Object[] {
						layoutFragmentModelImpl.getGroupId(),
						layoutFragmentModelImpl.getPlid(),
						layoutFragmentModelImpl.getFragmentEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P_F, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_F,
					args);
			}
		}

		entityCache.putResult(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
			LayoutFragmentImpl.class, layoutFragment.getPrimaryKey(),
			layoutFragment, false);

		layoutFragment.resetOriginalValues();

		return layoutFragment;
	}

	protected LayoutFragment toUnwrappedModel(LayoutFragment layoutFragment) {
		if (layoutFragment instanceof LayoutFragmentImpl) {
			return layoutFragment;
		}

		LayoutFragmentImpl layoutFragmentImpl = new LayoutFragmentImpl();

		layoutFragmentImpl.setNew(layoutFragment.isNew());
		layoutFragmentImpl.setPrimaryKey(layoutFragment.getPrimaryKey());

		layoutFragmentImpl.setLayoutFragmentId(layoutFragment.getLayoutFragmentId());
		layoutFragmentImpl.setGroupId(layoutFragment.getGroupId());
		layoutFragmentImpl.setCompanyId(layoutFragment.getCompanyId());
		layoutFragmentImpl.setUserId(layoutFragment.getUserId());
		layoutFragmentImpl.setUserName(layoutFragment.getUserName());
		layoutFragmentImpl.setCreateDate(layoutFragment.getCreateDate());
		layoutFragmentImpl.setModifiedDate(layoutFragment.getModifiedDate());
		layoutFragmentImpl.setPlid(layoutFragment.getPlid());
		layoutFragmentImpl.setFragmentEntryId(layoutFragment.getFragmentEntryId());
		layoutFragmentImpl.setCss(layoutFragment.getCss());
		layoutFragmentImpl.setHtml(layoutFragment.getHtml());
		layoutFragmentImpl.setJs(layoutFragment.getJs());
		layoutFragmentImpl.setPosition(layoutFragment.getPosition());
		layoutFragmentImpl.setTypeSettings(layoutFragment.getTypeSettings());

		return layoutFragmentImpl;
	}

	/**
	 * Returns the layout fragment with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout fragment
	 * @return the layout fragment
	 * @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	 */
	@Override
	public LayoutFragment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFragmentException {
		LayoutFragment layoutFragment = fetchByPrimaryKey(primaryKey);

		if (layoutFragment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFragmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return layoutFragment;
	}

	/**
	 * Returns the layout fragment with the primary key or throws a {@link NoSuchFragmentException} if it could not be found.
	 *
	 * @param layoutFragmentId the primary key of the layout fragment
	 * @return the layout fragment
	 * @throws NoSuchFragmentException if a layout fragment with the primary key could not be found
	 */
	@Override
	public LayoutFragment findByPrimaryKey(long layoutFragmentId)
		throws NoSuchFragmentException {
		return findByPrimaryKey((Serializable)layoutFragmentId);
	}

	/**
	 * Returns the layout fragment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout fragment
	 * @return the layout fragment, or <code>null</code> if a layout fragment with the primary key could not be found
	 */
	@Override
	public LayoutFragment fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
				LayoutFragmentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LayoutFragment layoutFragment = (LayoutFragment)serializable;

		if (layoutFragment == null) {
			Session session = null;

			try {
				session = openSession();

				layoutFragment = (LayoutFragment)session.get(LayoutFragmentImpl.class,
						primaryKey);

				if (layoutFragment != null) {
					cacheResult(layoutFragment);
				}
				else {
					entityCache.putResult(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
						LayoutFragmentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
					LayoutFragmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return layoutFragment;
	}

	/**
	 * Returns the layout fragment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param layoutFragmentId the primary key of the layout fragment
	 * @return the layout fragment, or <code>null</code> if a layout fragment with the primary key could not be found
	 */
	@Override
	public LayoutFragment fetchByPrimaryKey(long layoutFragmentId) {
		return fetchByPrimaryKey((Serializable)layoutFragmentId);
	}

	@Override
	public Map<Serializable, LayoutFragment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LayoutFragment> map = new HashMap<Serializable, LayoutFragment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LayoutFragment layoutFragment = fetchByPrimaryKey(primaryKey);

			if (layoutFragment != null) {
				map.put(primaryKey, layoutFragment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
					LayoutFragmentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LayoutFragment)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LAYOUTFRAGMENT_WHERE_PKS_IN);

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

			for (LayoutFragment layoutFragment : (List<LayoutFragment>)q.list()) {
				map.put(layoutFragment.getPrimaryKeyObj(), layoutFragment);

				cacheResult(layoutFragment);

				uncachedPrimaryKeys.remove(layoutFragment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LayoutFragmentModelImpl.ENTITY_CACHE_ENABLED,
					LayoutFragmentImpl.class, primaryKey, nullModel);
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
	 * Returns all the layout fragments.
	 *
	 * @return the layout fragments
	 */
	@Override
	public List<LayoutFragment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout fragments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @return the range of layout fragments
	 */
	@Override
	public List<LayoutFragment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout fragments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of layout fragments
	 */
	@Override
	public List<LayoutFragment> findAll(int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout fragments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutFragmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout fragments
	 * @param end the upper bound of the range of layout fragments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of layout fragments
	 */
	@Override
	public List<LayoutFragment> findAll(int start, int end,
		OrderByComparator<LayoutFragment> orderByComparator,
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

		List<LayoutFragment> list = null;

		if (retrieveFromCache) {
			list = (List<LayoutFragment>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LAYOUTFRAGMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LAYOUTFRAGMENT;

				if (pagination) {
					sql = sql.concat(LayoutFragmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LayoutFragment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LayoutFragment>)QueryUtil.list(q,
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
	 * Removes all the layout fragments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LayoutFragment layoutFragment : findAll()) {
			remove(layoutFragment);
		}
	}

	/**
	 * Returns the number of layout fragments.
	 *
	 * @return the number of layout fragments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LAYOUTFRAGMENT);

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
		return LayoutFragmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the layout fragment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LayoutFragmentImpl.class.getName());
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
	private static final String _SQL_SELECT_LAYOUTFRAGMENT = "SELECT layoutFragment FROM LayoutFragment layoutFragment";
	private static final String _SQL_SELECT_LAYOUTFRAGMENT_WHERE_PKS_IN = "SELECT layoutFragment FROM LayoutFragment layoutFragment WHERE layoutFragmentId IN (";
	private static final String _SQL_SELECT_LAYOUTFRAGMENT_WHERE = "SELECT layoutFragment FROM LayoutFragment layoutFragment WHERE ";
	private static final String _SQL_COUNT_LAYOUTFRAGMENT = "SELECT COUNT(layoutFragment) FROM LayoutFragment layoutFragment";
	private static final String _SQL_COUNT_LAYOUTFRAGMENT_WHERE = "SELECT COUNT(layoutFragment) FROM LayoutFragment layoutFragment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "layoutFragment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LayoutFragment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LayoutFragment exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LayoutFragmentPersistenceImpl.class);
}