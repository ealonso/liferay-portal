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

package com.liferay.asset.entry.rel.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.entry.rel.exception.NoSuchEntryRelException;
import com.liferay.asset.entry.rel.model.AssetEntryRel;
import com.liferay.asset.entry.rel.model.impl.AssetEntryRelImpl;
import com.liferay.asset.entry.rel.model.impl.AssetEntryRelModelImpl;
import com.liferay.asset.entry.rel.service.persistence.AssetEntryRelPersistence;

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
 * The persistence implementation for the asset entry rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryRelPersistence
 * @see com.liferay.asset.entry.rel.service.persistence.AssetEntryRelUtil
 * @generated
 */
@ProviderType
public class AssetEntryRelPersistenceImpl extends BasePersistenceImpl<AssetEntryRel>
	implements AssetEntryRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetEntryRelUtil} to access the asset entry rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetEntryRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryRelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryRelImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRY =
		new FinderPath(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryRelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAssetEntry",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY =
		new FinderPath(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryRelImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAssetEntry", new String[] { Long.class.getName() },
			AssetEntryRelModelImpl.ASSETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETENTRY = new FinderPath(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetEntry",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the asset entry rels where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @return the matching asset entry rels
	 */
	@Override
	public List<AssetEntryRel> findByAssetEntry(long assetEntryId) {
		return findByAssetEntry(assetEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry rels where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset entry rels
	 * @param end the upper bound of the range of asset entry rels (not inclusive)
	 * @return the range of matching asset entry rels
	 */
	@Override
	public List<AssetEntryRel> findByAssetEntry(long assetEntryId, int start,
		int end) {
		return findByAssetEntry(assetEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry rels where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset entry rels
	 * @param end the upper bound of the range of asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry rels
	 */
	@Override
	public List<AssetEntryRel> findByAssetEntry(long assetEntryId, int start,
		int end, OrderByComparator<AssetEntryRel> orderByComparator) {
		return findByAssetEntry(assetEntryId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the asset entry rels where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset entry rels
	 * @param end the upper bound of the range of asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset entry rels
	 */
	@Override
	public List<AssetEntryRel> findByAssetEntry(long assetEntryId, int start,
		int end, OrderByComparator<AssetEntryRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY;
			finderArgs = new Object[] { assetEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRY;
			finderArgs = new Object[] {
					assetEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AssetEntryRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetEntryRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetEntryRel assetEntryRel : list) {
					if ((assetEntryId != assetEntryRel.getAssetEntryId())) {
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

			query.append(_SQL_SELECT_ASSETENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRY_ASSETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntryRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntryId);

				if (!pagination) {
					list = (List<AssetEntryRel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetEntryRel>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset entry rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry rel
	 * @throws NoSuchEntryRelException if a matching asset entry rel could not be found
	 */
	@Override
	public AssetEntryRel findByAssetEntry_First(long assetEntryId,
		OrderByComparator<AssetEntryRel> orderByComparator)
		throws NoSuchEntryRelException {
		AssetEntryRel assetEntryRel = fetchByAssetEntry_First(assetEntryId,
				orderByComparator);

		if (assetEntryRel != null) {
			return assetEntryRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append("}");

		throw new NoSuchEntryRelException(msg.toString());
	}

	/**
	 * Returns the first asset entry rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	 */
	@Override
	public AssetEntryRel fetchByAssetEntry_First(long assetEntryId,
		OrderByComparator<AssetEntryRel> orderByComparator) {
		List<AssetEntryRel> list = findByAssetEntry(assetEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry rel
	 * @throws NoSuchEntryRelException if a matching asset entry rel could not be found
	 */
	@Override
	public AssetEntryRel findByAssetEntry_Last(long assetEntryId,
		OrderByComparator<AssetEntryRel> orderByComparator)
		throws NoSuchEntryRelException {
		AssetEntryRel assetEntryRel = fetchByAssetEntry_Last(assetEntryId,
				orderByComparator);

		if (assetEntryRel != null) {
			return assetEntryRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append("}");

		throw new NoSuchEntryRelException(msg.toString());
	}

	/**
	 * Returns the last asset entry rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	 */
	@Override
	public AssetEntryRel fetchByAssetEntry_Last(long assetEntryId,
		OrderByComparator<AssetEntryRel> orderByComparator) {
		int count = countByAssetEntry(assetEntryId);

		if (count == 0) {
			return null;
		}

		List<AssetEntryRel> list = findByAssetEntry(assetEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entry rels before and after the current asset entry rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryRelId the primary key of the current asset entry rel
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry rel
	 * @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetEntryRel[] findByAssetEntry_PrevAndNext(long assetEntryRelId,
		long assetEntryId, OrderByComparator<AssetEntryRel> orderByComparator)
		throws NoSuchEntryRelException {
		AssetEntryRel assetEntryRel = findByPrimaryKey(assetEntryRelId);

		Session session = null;

		try {
			session = openSession();

			AssetEntryRel[] array = new AssetEntryRelImpl[3];

			array[0] = getByAssetEntry_PrevAndNext(session, assetEntryRel,
					assetEntryId, orderByComparator, true);

			array[1] = assetEntryRel;

			array[2] = getByAssetEntry_PrevAndNext(session, assetEntryRel,
					assetEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntryRel getByAssetEntry_PrevAndNext(Session session,
		AssetEntryRel assetEntryRel, long assetEntryId,
		OrderByComparator<AssetEntryRel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRYREL_WHERE);

		query.append(_FINDER_COLUMN_ASSETENTRY_ASSETENTRYID_2);

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
			query.append(AssetEntryRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntryRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntryRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry rels where assetEntryId = &#63; from the database.
	 *
	 * @param assetEntryId the asset entry ID
	 */
	@Override
	public void removeByAssetEntry(long assetEntryId) {
		for (AssetEntryRel assetEntryRel : findByAssetEntry(assetEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetEntryRel);
		}
	}

	/**
	 * Returns the number of asset entry rels where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @return the number of matching asset entry rels
	 */
	@Override
	public int countByAssetEntry(long assetEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSETENTRY;

		Object[] finderArgs = new Object[] { assetEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRY_ASSETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntryId);

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

	private static final String _FINDER_COLUMN_ASSETENTRY_ASSETENTRYID_2 = "assetEntryRel.assetEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_A_C = new FinderPath(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryRelImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByA_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetEntryRelModelImpl.ASSETENTRYID_COLUMN_BITMASK |
			AssetEntryRelModelImpl.CLASSNAMEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_C = new FinderPath(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the asset entry rel where assetEntryId = &#63; and classNameId = &#63; or throws a {@link NoSuchEntryRelException} if it could not be found.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param classNameId the class name ID
	 * @return the matching asset entry rel
	 * @throws NoSuchEntryRelException if a matching asset entry rel could not be found
	 */
	@Override
	public AssetEntryRel findByA_C(long assetEntryId, long classNameId)
		throws NoSuchEntryRelException {
		AssetEntryRel assetEntryRel = fetchByA_C(assetEntryId, classNameId);

		if (assetEntryRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assetEntryId=");
			msg.append(assetEntryId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEntryRelException(msg.toString());
		}

		return assetEntryRel;
	}

	/**
	 * Returns the asset entry rel where assetEntryId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param classNameId the class name ID
	 * @return the matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	 */
	@Override
	public AssetEntryRel fetchByA_C(long assetEntryId, long classNameId) {
		return fetchByA_C(assetEntryId, classNameId, true);
	}

	/**
	 * Returns the asset entry rel where assetEntryId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param classNameId the class name ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching asset entry rel, or <code>null</code> if a matching asset entry rel could not be found
	 */
	@Override
	public AssetEntryRel fetchByA_C(long assetEntryId, long classNameId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { assetEntryId, classNameId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_A_C,
					finderArgs, this);
		}

		if (result instanceof AssetEntryRel) {
			AssetEntryRel assetEntryRel = (AssetEntryRel)result;

			if ((assetEntryId != assetEntryRel.getAssetEntryId()) ||
					(classNameId != assetEntryRel.getClassNameId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASSETENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_A_C_ASSETENTRYID_2);

			query.append(_FINDER_COLUMN_A_C_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntryId);

				qPos.add(classNameId);

				List<AssetEntryRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_A_C, finderArgs,
						list);
				}
				else {
					AssetEntryRel assetEntryRel = list.get(0);

					result = assetEntryRel;

					cacheResult(assetEntryRel);

					if ((assetEntryRel.getAssetEntryId() != assetEntryId) ||
							(assetEntryRel.getClassNameId() != classNameId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_A_C,
							finderArgs, assetEntryRel);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_A_C, finderArgs);

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
			return (AssetEntryRel)result;
		}
	}

	/**
	 * Removes the asset entry rel where assetEntryId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param classNameId the class name ID
	 * @return the asset entry rel that was removed
	 */
	@Override
	public AssetEntryRel removeByA_C(long assetEntryId, long classNameId)
		throws NoSuchEntryRelException {
		AssetEntryRel assetEntryRel = findByA_C(assetEntryId, classNameId);

		return remove(assetEntryRel);
	}

	/**
	 * Returns the number of asset entry rels where assetEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param classNameId the class name ID
	 * @return the number of matching asset entry rels
	 */
	@Override
	public int countByA_C(long assetEntryId, long classNameId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_C;

		Object[] finderArgs = new Object[] { assetEntryId, classNameId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_A_C_ASSETENTRYID_2);

			query.append(_FINDER_COLUMN_A_C_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntryId);

				qPos.add(classNameId);

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

	private static final String _FINDER_COLUMN_A_C_ASSETENTRYID_2 = "assetEntryRel.assetEntryId = ? AND ";
	private static final String _FINDER_COLUMN_A_C_CLASSNAMEID_2 = "assetEntryRel.classNameId = ?";

	public AssetEntryRelPersistenceImpl() {
		setModelClass(AssetEntryRel.class);
	}

	/**
	 * Caches the asset entry rel in the entity cache if it is enabled.
	 *
	 * @param assetEntryRel the asset entry rel
	 */
	@Override
	public void cacheResult(AssetEntryRel assetEntryRel) {
		entityCache.putResult(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelImpl.class, assetEntryRel.getPrimaryKey(),
			assetEntryRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_A_C,
			new Object[] {
				assetEntryRel.getAssetEntryId(), assetEntryRel.getClassNameId()
			}, assetEntryRel);

		assetEntryRel.resetOriginalValues();
	}

	/**
	 * Caches the asset entry rels in the entity cache if it is enabled.
	 *
	 * @param assetEntryRels the asset entry rels
	 */
	@Override
	public void cacheResult(List<AssetEntryRel> assetEntryRels) {
		for (AssetEntryRel assetEntryRel : assetEntryRels) {
			if (entityCache.getResult(
						AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntryRelImpl.class, assetEntryRel.getPrimaryKey()) == null) {
				cacheResult(assetEntryRel);
			}
			else {
				assetEntryRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset entry rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AssetEntryRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset entry rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetEntryRel assetEntryRel) {
		entityCache.removeResult(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelImpl.class, assetEntryRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AssetEntryRelModelImpl)assetEntryRel, true);
	}

	@Override
	public void clearCache(List<AssetEntryRel> assetEntryRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetEntryRel assetEntryRel : assetEntryRels) {
			entityCache.removeResult(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntryRelImpl.class, assetEntryRel.getPrimaryKey());

			clearUniqueFindersCache((AssetEntryRelModelImpl)assetEntryRel, true);
		}
	}

	protected void cacheUniqueFindersCache(
		AssetEntryRelModelImpl assetEntryRelModelImpl) {
		Object[] args = new Object[] {
				assetEntryRelModelImpl.getAssetEntryId(),
				assetEntryRelModelImpl.getClassNameId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_A_C, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_A_C, args,
			assetEntryRelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AssetEntryRelModelImpl assetEntryRelModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					assetEntryRelModelImpl.getAssetEntryId(),
					assetEntryRelModelImpl.getClassNameId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_A_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_A_C, args);
		}

		if ((assetEntryRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_A_C.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetEntryRelModelImpl.getOriginalAssetEntryId(),
					assetEntryRelModelImpl.getOriginalClassNameId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_A_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_A_C, args);
		}
	}

	/**
	 * Creates a new asset entry rel with the primary key. Does not add the asset entry rel to the database.
	 *
	 * @param assetEntryRelId the primary key for the new asset entry rel
	 * @return the new asset entry rel
	 */
	@Override
	public AssetEntryRel create(long assetEntryRelId) {
		AssetEntryRel assetEntryRel = new AssetEntryRelImpl();

		assetEntryRel.setNew(true);
		assetEntryRel.setPrimaryKey(assetEntryRelId);

		return assetEntryRel;
	}

	/**
	 * Removes the asset entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryRelId the primary key of the asset entry rel
	 * @return the asset entry rel that was removed
	 * @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetEntryRel remove(long assetEntryRelId)
		throws NoSuchEntryRelException {
		return remove((Serializable)assetEntryRelId);
	}

	/**
	 * Removes the asset entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset entry rel
	 * @return the asset entry rel that was removed
	 * @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetEntryRel remove(Serializable primaryKey)
		throws NoSuchEntryRelException {
		Session session = null;

		try {
			session = openSession();

			AssetEntryRel assetEntryRel = (AssetEntryRel)session.get(AssetEntryRelImpl.class,
					primaryKey);

			if (assetEntryRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetEntryRel);
		}
		catch (NoSuchEntryRelException nsee) {
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
	protected AssetEntryRel removeImpl(AssetEntryRel assetEntryRel) {
		assetEntryRel = toUnwrappedModel(assetEntryRel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetEntryRel)) {
				assetEntryRel = (AssetEntryRel)session.get(AssetEntryRelImpl.class,
						assetEntryRel.getPrimaryKeyObj());
			}

			if (assetEntryRel != null) {
				session.delete(assetEntryRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetEntryRel != null) {
			clearCache(assetEntryRel);
		}

		return assetEntryRel;
	}

	@Override
	public AssetEntryRel updateImpl(AssetEntryRel assetEntryRel) {
		assetEntryRel = toUnwrappedModel(assetEntryRel);

		boolean isNew = assetEntryRel.isNew();

		AssetEntryRelModelImpl assetEntryRelModelImpl = (AssetEntryRelModelImpl)assetEntryRel;

		Session session = null;

		try {
			session = openSession();

			if (assetEntryRel.isNew()) {
				session.save(assetEntryRel);

				assetEntryRel.setNew(false);
			}
			else {
				assetEntryRel = (AssetEntryRel)session.merge(assetEntryRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AssetEntryRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					assetEntryRelModelImpl.getAssetEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRY, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((assetEntryRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntryRelModelImpl.getOriginalAssetEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRY, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY,
					args);

				args = new Object[] { assetEntryRelModelImpl.getAssetEntryId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRY, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY,
					args);
			}
		}

		entityCache.putResult(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryRelImpl.class, assetEntryRel.getPrimaryKey(),
			assetEntryRel, false);

		clearUniqueFindersCache(assetEntryRelModelImpl, false);
		cacheUniqueFindersCache(assetEntryRelModelImpl);

		assetEntryRel.resetOriginalValues();

		return assetEntryRel;
	}

	protected AssetEntryRel toUnwrappedModel(AssetEntryRel assetEntryRel) {
		if (assetEntryRel instanceof AssetEntryRelImpl) {
			return assetEntryRel;
		}

		AssetEntryRelImpl assetEntryRelImpl = new AssetEntryRelImpl();

		assetEntryRelImpl.setNew(assetEntryRel.isNew());
		assetEntryRelImpl.setPrimaryKey(assetEntryRel.getPrimaryKey());

		assetEntryRelImpl.setAssetEntryRelId(assetEntryRel.getAssetEntryRelId());
		assetEntryRelImpl.setAssetEntryId(assetEntryRel.getAssetEntryId());
		assetEntryRelImpl.setClassNameId(assetEntryRel.getClassNameId());
		assetEntryRelImpl.setClassPK(assetEntryRel.getClassPK());

		return assetEntryRelImpl;
	}

	/**
	 * Returns the asset entry rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry rel
	 * @return the asset entry rel
	 * @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetEntryRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryRelException {
		AssetEntryRel assetEntryRel = fetchByPrimaryKey(primaryKey);

		if (assetEntryRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetEntryRel;
	}

	/**
	 * Returns the asset entry rel with the primary key or throws a {@link NoSuchEntryRelException} if it could not be found.
	 *
	 * @param assetEntryRelId the primary key of the asset entry rel
	 * @return the asset entry rel
	 * @throws NoSuchEntryRelException if a asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetEntryRel findByPrimaryKey(long assetEntryRelId)
		throws NoSuchEntryRelException {
		return findByPrimaryKey((Serializable)assetEntryRelId);
	}

	/**
	 * Returns the asset entry rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry rel
	 * @return the asset entry rel, or <code>null</code> if a asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetEntryRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntryRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AssetEntryRel assetEntryRel = (AssetEntryRel)serializable;

		if (assetEntryRel == null) {
			Session session = null;

			try {
				session = openSession();

				assetEntryRel = (AssetEntryRel)session.get(AssetEntryRelImpl.class,
						primaryKey);

				if (assetEntryRel != null) {
					cacheResult(assetEntryRel);
				}
				else {
					entityCache.putResult(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntryRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntryRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetEntryRel;
	}

	/**
	 * Returns the asset entry rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetEntryRelId the primary key of the asset entry rel
	 * @return the asset entry rel, or <code>null</code> if a asset entry rel with the primary key could not be found
	 */
	@Override
	public AssetEntryRel fetchByPrimaryKey(long assetEntryRelId) {
		return fetchByPrimaryKey((Serializable)assetEntryRelId);
	}

	@Override
	public Map<Serializable, AssetEntryRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AssetEntryRel> map = new HashMap<Serializable, AssetEntryRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AssetEntryRel assetEntryRel = fetchByPrimaryKey(primaryKey);

			if (assetEntryRel != null) {
				map.put(primaryKey, assetEntryRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntryRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AssetEntryRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ASSETENTRYREL_WHERE_PKS_IN);

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

			for (AssetEntryRel assetEntryRel : (List<AssetEntryRel>)q.list()) {
				map.put(assetEntryRel.getPrimaryKeyObj(), assetEntryRel);

				cacheResult(assetEntryRel);

				uncachedPrimaryKeys.remove(assetEntryRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AssetEntryRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntryRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the asset entry rels.
	 *
	 * @return the asset entry rels
	 */
	@Override
	public List<AssetEntryRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry rels
	 * @param end the upper bound of the range of asset entry rels (not inclusive)
	 * @return the range of asset entry rels
	 */
	@Override
	public List<AssetEntryRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry rels
	 * @param end the upper bound of the range of asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entry rels
	 */
	@Override
	public List<AssetEntryRel> findAll(int start, int end,
		OrderByComparator<AssetEntryRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry rels
	 * @param end the upper bound of the range of asset entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of asset entry rels
	 */
	@Override
	public List<AssetEntryRel> findAll(int start, int end,
		OrderByComparator<AssetEntryRel> orderByComparator,
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

		List<AssetEntryRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetEntryRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSETENTRYREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETENTRYREL;

				if (pagination) {
					sql = sql.concat(AssetEntryRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetEntryRel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetEntryRel>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the asset entry rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssetEntryRel assetEntryRel : findAll()) {
			remove(assetEntryRel);
		}
	}

	/**
	 * Returns the number of asset entry rels.
	 *
	 * @return the number of asset entry rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETENTRYREL);

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
		return AssetEntryRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the asset entry rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AssetEntryRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ASSETENTRYREL = "SELECT assetEntryRel FROM AssetEntryRel assetEntryRel";
	private static final String _SQL_SELECT_ASSETENTRYREL_WHERE_PKS_IN = "SELECT assetEntryRel FROM AssetEntryRel assetEntryRel WHERE assetEntryRelId IN (";
	private static final String _SQL_SELECT_ASSETENTRYREL_WHERE = "SELECT assetEntryRel FROM AssetEntryRel assetEntryRel WHERE ";
	private static final String _SQL_COUNT_ASSETENTRYREL = "SELECT COUNT(assetEntryRel) FROM AssetEntryRel assetEntryRel";
	private static final String _SQL_COUNT_ASSETENTRYREL_WHERE = "SELECT COUNT(assetEntryRel) FROM AssetEntryRel assetEntryRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetEntryRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetEntryRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetEntryRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AssetEntryRelPersistenceImpl.class);
}