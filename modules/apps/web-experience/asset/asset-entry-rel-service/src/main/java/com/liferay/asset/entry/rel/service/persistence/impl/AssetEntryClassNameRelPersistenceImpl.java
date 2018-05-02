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

import com.liferay.asset.entry.rel.exception.NoSuchEntryClassNameRelException;
import com.liferay.asset.entry.rel.model.AssetEntryClassNameRel;
import com.liferay.asset.entry.rel.model.impl.AssetEntryClassNameRelImpl;
import com.liferay.asset.entry.rel.model.impl.AssetEntryClassNameRelModelImpl;
import com.liferay.asset.entry.rel.service.persistence.AssetEntryClassNameRelPersistence;

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
 * The persistence implementation for the asset entry class name rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryClassNameRelPersistence
 * @see com.liferay.asset.entry.rel.service.persistence.AssetEntryClassNameRelUtil
 * @generated
 */
@ProviderType
public class AssetEntryClassNameRelPersistenceImpl extends BasePersistenceImpl<AssetEntryClassNameRel>
	implements AssetEntryClassNameRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetEntryClassNameRelUtil} to access the asset entry class name rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetEntryClassNameRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryClassNameRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryClassNameRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRY =
		new FinderPath(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryClassNameRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetEntry",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY =
		new FinderPath(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryClassNameRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetEntry",
			new String[] { Long.class.getName() },
			AssetEntryClassNameRelModelImpl.ASSETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETENTRY = new FinderPath(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetEntry",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the asset entry class name rels where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @return the matching asset entry class name rels
	 */
	@Override
	public List<AssetEntryClassNameRel> findByAssetEntry(long assetEntryId) {
		return findByAssetEntry(assetEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry class name rels where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset entry class name rels
	 * @param end the upper bound of the range of asset entry class name rels (not inclusive)
	 * @return the range of matching asset entry class name rels
	 */
	@Override
	public List<AssetEntryClassNameRel> findByAssetEntry(long assetEntryId,
		int start, int end) {
		return findByAssetEntry(assetEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry class name rels where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset entry class name rels
	 * @param end the upper bound of the range of asset entry class name rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry class name rels
	 */
	@Override
	public List<AssetEntryClassNameRel> findByAssetEntry(long assetEntryId,
		int start, int end,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator) {
		return findByAssetEntry(assetEntryId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the asset entry class name rels where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset entry class name rels
	 * @param end the upper bound of the range of asset entry class name rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset entry class name rels
	 */
	@Override
	public List<AssetEntryClassNameRel> findByAssetEntry(long assetEntryId,
		int start, int end,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator,
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

		List<AssetEntryClassNameRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetEntryClassNameRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetEntryClassNameRel assetEntryClassNameRel : list) {
					if ((assetEntryId != assetEntryClassNameRel.getAssetEntryId())) {
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

			query.append(_SQL_SELECT_ASSETENTRYCLASSNAMEREL_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRY_ASSETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntryClassNameRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntryId);

				if (!pagination) {
					list = (List<AssetEntryClassNameRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetEntryClassNameRel>)QueryUtil.list(q,
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
	 * Returns the first asset entry class name rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry class name rel
	 * @throws NoSuchEntryClassNameRelException if a matching asset entry class name rel could not be found
	 */
	@Override
	public AssetEntryClassNameRel findByAssetEntry_First(long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator)
		throws NoSuchEntryClassNameRelException {
		AssetEntryClassNameRel assetEntryClassNameRel = fetchByAssetEntry_First(assetEntryId,
				orderByComparator);

		if (assetEntryClassNameRel != null) {
			return assetEntryClassNameRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append("}");

		throw new NoSuchEntryClassNameRelException(msg.toString());
	}

	/**
	 * Returns the first asset entry class name rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry class name rel, or <code>null</code> if a matching asset entry class name rel could not be found
	 */
	@Override
	public AssetEntryClassNameRel fetchByAssetEntry_First(long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator) {
		List<AssetEntryClassNameRel> list = findByAssetEntry(assetEntryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry class name rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry class name rel
	 * @throws NoSuchEntryClassNameRelException if a matching asset entry class name rel could not be found
	 */
	@Override
	public AssetEntryClassNameRel findByAssetEntry_Last(long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator)
		throws NoSuchEntryClassNameRelException {
		AssetEntryClassNameRel assetEntryClassNameRel = fetchByAssetEntry_Last(assetEntryId,
				orderByComparator);

		if (assetEntryClassNameRel != null) {
			return assetEntryClassNameRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append("}");

		throw new NoSuchEntryClassNameRelException(msg.toString());
	}

	/**
	 * Returns the last asset entry class name rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry class name rel, or <code>null</code> if a matching asset entry class name rel could not be found
	 */
	@Override
	public AssetEntryClassNameRel fetchByAssetEntry_Last(long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator) {
		int count = countByAssetEntry(assetEntryId);

		if (count == 0) {
			return null;
		}

		List<AssetEntryClassNameRel> list = findByAssetEntry(assetEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entry class name rels before and after the current asset entry class name rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryClassNameRelId the primary key of the current asset entry class name rel
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry class name rel
	 * @throws NoSuchEntryClassNameRelException if a asset entry class name rel with the primary key could not be found
	 */
	@Override
	public AssetEntryClassNameRel[] findByAssetEntry_PrevAndNext(
		long assetEntryClassNameRelId, long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator)
		throws NoSuchEntryClassNameRelException {
		AssetEntryClassNameRel assetEntryClassNameRel = findByPrimaryKey(assetEntryClassNameRelId);

		Session session = null;

		try {
			session = openSession();

			AssetEntryClassNameRel[] array = new AssetEntryClassNameRelImpl[3];

			array[0] = getByAssetEntry_PrevAndNext(session,
					assetEntryClassNameRel, assetEntryId, orderByComparator,
					true);

			array[1] = assetEntryClassNameRel;

			array[2] = getByAssetEntry_PrevAndNext(session,
					assetEntryClassNameRel, assetEntryId, orderByComparator,
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

	protected AssetEntryClassNameRel getByAssetEntry_PrevAndNext(
		Session session, AssetEntryClassNameRel assetEntryClassNameRel,
		long assetEntryId,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator,
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

		query.append(_SQL_SELECT_ASSETENTRYCLASSNAMEREL_WHERE);

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
			query.append(AssetEntryClassNameRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntryClassNameRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntryClassNameRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry class name rels where assetEntryId = &#63; from the database.
	 *
	 * @param assetEntryId the asset entry ID
	 */
	@Override
	public void removeByAssetEntry(long assetEntryId) {
		for (AssetEntryClassNameRel assetEntryClassNameRel : findByAssetEntry(
				assetEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetEntryClassNameRel);
		}
	}

	/**
	 * Returns the number of asset entry class name rels where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @return the number of matching asset entry class name rels
	 */
	@Override
	public int countByAssetEntry(long assetEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSETENTRY;

		Object[] finderArgs = new Object[] { assetEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETENTRYCLASSNAMEREL_WHERE);

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

	private static final String _FINDER_COLUMN_ASSETENTRY_ASSETENTRYID_2 = "assetEntryClassNameRel.assetEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_A_C = new FinderPath(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryClassNameRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByA_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetEntryClassNameRelModelImpl.ASSETENTRYID_COLUMN_BITMASK |
			AssetEntryClassNameRelModelImpl.CLASSNAMEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_C = new FinderPath(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the asset entry class name rel where assetEntryId = &#63; and classNameId = &#63; or throws a {@link NoSuchEntryClassNameRelException} if it could not be found.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param classNameId the class name ID
	 * @return the matching asset entry class name rel
	 * @throws NoSuchEntryClassNameRelException if a matching asset entry class name rel could not be found
	 */
	@Override
	public AssetEntryClassNameRel findByA_C(long assetEntryId, long classNameId)
		throws NoSuchEntryClassNameRelException {
		AssetEntryClassNameRel assetEntryClassNameRel = fetchByA_C(assetEntryId,
				classNameId);

		if (assetEntryClassNameRel == null) {
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

			throw new NoSuchEntryClassNameRelException(msg.toString());
		}

		return assetEntryClassNameRel;
	}

	/**
	 * Returns the asset entry class name rel where assetEntryId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param classNameId the class name ID
	 * @return the matching asset entry class name rel, or <code>null</code> if a matching asset entry class name rel could not be found
	 */
	@Override
	public AssetEntryClassNameRel fetchByA_C(long assetEntryId, long classNameId) {
		return fetchByA_C(assetEntryId, classNameId, true);
	}

	/**
	 * Returns the asset entry class name rel where assetEntryId = &#63; and classNameId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param classNameId the class name ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching asset entry class name rel, or <code>null</code> if a matching asset entry class name rel could not be found
	 */
	@Override
	public AssetEntryClassNameRel fetchByA_C(long assetEntryId,
		long classNameId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { assetEntryId, classNameId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_A_C,
					finderArgs, this);
		}

		if (result instanceof AssetEntryClassNameRel) {
			AssetEntryClassNameRel assetEntryClassNameRel = (AssetEntryClassNameRel)result;

			if ((assetEntryId != assetEntryClassNameRel.getAssetEntryId()) ||
					(classNameId != assetEntryClassNameRel.getClassNameId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASSETENTRYCLASSNAMEREL_WHERE);

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

				List<AssetEntryClassNameRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_A_C, finderArgs,
						list);
				}
				else {
					AssetEntryClassNameRel assetEntryClassNameRel = list.get(0);

					result = assetEntryClassNameRel;

					cacheResult(assetEntryClassNameRel);

					if ((assetEntryClassNameRel.getAssetEntryId() != assetEntryId) ||
							(assetEntryClassNameRel.getClassNameId() != classNameId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_A_C,
							finderArgs, assetEntryClassNameRel);
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
			return (AssetEntryClassNameRel)result;
		}
	}

	/**
	 * Removes the asset entry class name rel where assetEntryId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param classNameId the class name ID
	 * @return the asset entry class name rel that was removed
	 */
	@Override
	public AssetEntryClassNameRel removeByA_C(long assetEntryId,
		long classNameId) throws NoSuchEntryClassNameRelException {
		AssetEntryClassNameRel assetEntryClassNameRel = findByA_C(assetEntryId,
				classNameId);

		return remove(assetEntryClassNameRel);
	}

	/**
	 * Returns the number of asset entry class name rels where assetEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param classNameId the class name ID
	 * @return the number of matching asset entry class name rels
	 */
	@Override
	public int countByA_C(long assetEntryId, long classNameId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_C;

		Object[] finderArgs = new Object[] { assetEntryId, classNameId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETENTRYCLASSNAMEREL_WHERE);

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

	private static final String _FINDER_COLUMN_A_C_ASSETENTRYID_2 = "assetEntryClassNameRel.assetEntryId = ? AND ";
	private static final String _FINDER_COLUMN_A_C_CLASSNAMEID_2 = "assetEntryClassNameRel.classNameId = ?";

	public AssetEntryClassNameRelPersistenceImpl() {
		setModelClass(AssetEntryClassNameRel.class);
	}

	/**
	 * Caches the asset entry class name rel in the entity cache if it is enabled.
	 *
	 * @param assetEntryClassNameRel the asset entry class name rel
	 */
	@Override
	public void cacheResult(AssetEntryClassNameRel assetEntryClassNameRel) {
		entityCache.putResult(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelImpl.class,
			assetEntryClassNameRel.getPrimaryKey(), assetEntryClassNameRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_A_C,
			new Object[] {
				assetEntryClassNameRel.getAssetEntryId(),
				assetEntryClassNameRel.getClassNameId()
			}, assetEntryClassNameRel);

		assetEntryClassNameRel.resetOriginalValues();
	}

	/**
	 * Caches the asset entry class name rels in the entity cache if it is enabled.
	 *
	 * @param assetEntryClassNameRels the asset entry class name rels
	 */
	@Override
	public void cacheResult(
		List<AssetEntryClassNameRel> assetEntryClassNameRels) {
		for (AssetEntryClassNameRel assetEntryClassNameRel : assetEntryClassNameRels) {
			if (entityCache.getResult(
						AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntryClassNameRelImpl.class,
						assetEntryClassNameRel.getPrimaryKey()) == null) {
				cacheResult(assetEntryClassNameRel);
			}
			else {
				assetEntryClassNameRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset entry class name rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AssetEntryClassNameRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset entry class name rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetEntryClassNameRel assetEntryClassNameRel) {
		entityCache.removeResult(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelImpl.class,
			assetEntryClassNameRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AssetEntryClassNameRelModelImpl)assetEntryClassNameRel,
			true);
	}

	@Override
	public void clearCache(List<AssetEntryClassNameRel> assetEntryClassNameRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetEntryClassNameRel assetEntryClassNameRel : assetEntryClassNameRels) {
			entityCache.removeResult(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntryClassNameRelImpl.class,
				assetEntryClassNameRel.getPrimaryKey());

			clearUniqueFindersCache((AssetEntryClassNameRelModelImpl)assetEntryClassNameRel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		AssetEntryClassNameRelModelImpl assetEntryClassNameRelModelImpl) {
		Object[] args = new Object[] {
				assetEntryClassNameRelModelImpl.getAssetEntryId(),
				assetEntryClassNameRelModelImpl.getClassNameId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_A_C, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_A_C, args,
			assetEntryClassNameRelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AssetEntryClassNameRelModelImpl assetEntryClassNameRelModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					assetEntryClassNameRelModelImpl.getAssetEntryId(),
					assetEntryClassNameRelModelImpl.getClassNameId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_A_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_A_C, args);
		}

		if ((assetEntryClassNameRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_A_C.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					assetEntryClassNameRelModelImpl.getOriginalAssetEntryId(),
					assetEntryClassNameRelModelImpl.getOriginalClassNameId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_A_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_A_C, args);
		}
	}

	/**
	 * Creates a new asset entry class name rel with the primary key. Does not add the asset entry class name rel to the database.
	 *
	 * @param assetEntryClassNameRelId the primary key for the new asset entry class name rel
	 * @return the new asset entry class name rel
	 */
	@Override
	public AssetEntryClassNameRel create(long assetEntryClassNameRelId) {
		AssetEntryClassNameRel assetEntryClassNameRel = new AssetEntryClassNameRelImpl();

		assetEntryClassNameRel.setNew(true);
		assetEntryClassNameRel.setPrimaryKey(assetEntryClassNameRelId);

		return assetEntryClassNameRel;
	}

	/**
	 * Removes the asset entry class name rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryClassNameRelId the primary key of the asset entry class name rel
	 * @return the asset entry class name rel that was removed
	 * @throws NoSuchEntryClassNameRelException if a asset entry class name rel with the primary key could not be found
	 */
	@Override
	public AssetEntryClassNameRel remove(long assetEntryClassNameRelId)
		throws NoSuchEntryClassNameRelException {
		return remove((Serializable)assetEntryClassNameRelId);
	}

	/**
	 * Removes the asset entry class name rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset entry class name rel
	 * @return the asset entry class name rel that was removed
	 * @throws NoSuchEntryClassNameRelException if a asset entry class name rel with the primary key could not be found
	 */
	@Override
	public AssetEntryClassNameRel remove(Serializable primaryKey)
		throws NoSuchEntryClassNameRelException {
		Session session = null;

		try {
			session = openSession();

			AssetEntryClassNameRel assetEntryClassNameRel = (AssetEntryClassNameRel)session.get(AssetEntryClassNameRelImpl.class,
					primaryKey);

			if (assetEntryClassNameRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryClassNameRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetEntryClassNameRel);
		}
		catch (NoSuchEntryClassNameRelException nsee) {
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
	protected AssetEntryClassNameRel removeImpl(
		AssetEntryClassNameRel assetEntryClassNameRel) {
		assetEntryClassNameRel = toUnwrappedModel(assetEntryClassNameRel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetEntryClassNameRel)) {
				assetEntryClassNameRel = (AssetEntryClassNameRel)session.get(AssetEntryClassNameRelImpl.class,
						assetEntryClassNameRel.getPrimaryKeyObj());
			}

			if (assetEntryClassNameRel != null) {
				session.delete(assetEntryClassNameRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetEntryClassNameRel != null) {
			clearCache(assetEntryClassNameRel);
		}

		return assetEntryClassNameRel;
	}

	@Override
	public AssetEntryClassNameRel updateImpl(
		AssetEntryClassNameRel assetEntryClassNameRel) {
		assetEntryClassNameRel = toUnwrappedModel(assetEntryClassNameRel);

		boolean isNew = assetEntryClassNameRel.isNew();

		AssetEntryClassNameRelModelImpl assetEntryClassNameRelModelImpl = (AssetEntryClassNameRelModelImpl)assetEntryClassNameRel;

		Session session = null;

		try {
			session = openSession();

			if (assetEntryClassNameRel.isNew()) {
				session.save(assetEntryClassNameRel);

				assetEntryClassNameRel.setNew(false);
			}
			else {
				assetEntryClassNameRel = (AssetEntryClassNameRel)session.merge(assetEntryClassNameRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AssetEntryClassNameRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					assetEntryClassNameRelModelImpl.getAssetEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRY, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((assetEntryClassNameRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntryClassNameRelModelImpl.getOriginalAssetEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRY, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY,
					args);

				args = new Object[] {
						assetEntryClassNameRelModelImpl.getAssetEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRY, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRY,
					args);
			}
		}

		entityCache.putResult(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryClassNameRelImpl.class,
			assetEntryClassNameRel.getPrimaryKey(), assetEntryClassNameRel,
			false);

		clearUniqueFindersCache(assetEntryClassNameRelModelImpl, false);
		cacheUniqueFindersCache(assetEntryClassNameRelModelImpl);

		assetEntryClassNameRel.resetOriginalValues();

		return assetEntryClassNameRel;
	}

	protected AssetEntryClassNameRel toUnwrappedModel(
		AssetEntryClassNameRel assetEntryClassNameRel) {
		if (assetEntryClassNameRel instanceof AssetEntryClassNameRelImpl) {
			return assetEntryClassNameRel;
		}

		AssetEntryClassNameRelImpl assetEntryClassNameRelImpl = new AssetEntryClassNameRelImpl();

		assetEntryClassNameRelImpl.setNew(assetEntryClassNameRel.isNew());
		assetEntryClassNameRelImpl.setPrimaryKey(assetEntryClassNameRel.getPrimaryKey());

		assetEntryClassNameRelImpl.setAssetEntryClassNameRelId(assetEntryClassNameRel.getAssetEntryClassNameRelId());
		assetEntryClassNameRelImpl.setAssetEntryId(assetEntryClassNameRel.getAssetEntryId());
		assetEntryClassNameRelImpl.setClassNameId(assetEntryClassNameRel.getClassNameId());
		assetEntryClassNameRelImpl.setClassPK(assetEntryClassNameRel.getClassPK());

		return assetEntryClassNameRelImpl;
	}

	/**
	 * Returns the asset entry class name rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry class name rel
	 * @return the asset entry class name rel
	 * @throws NoSuchEntryClassNameRelException if a asset entry class name rel with the primary key could not be found
	 */
	@Override
	public AssetEntryClassNameRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryClassNameRelException {
		AssetEntryClassNameRel assetEntryClassNameRel = fetchByPrimaryKey(primaryKey);

		if (assetEntryClassNameRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryClassNameRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetEntryClassNameRel;
	}

	/**
	 * Returns the asset entry class name rel with the primary key or throws a {@link NoSuchEntryClassNameRelException} if it could not be found.
	 *
	 * @param assetEntryClassNameRelId the primary key of the asset entry class name rel
	 * @return the asset entry class name rel
	 * @throws NoSuchEntryClassNameRelException if a asset entry class name rel with the primary key could not be found
	 */
	@Override
	public AssetEntryClassNameRel findByPrimaryKey(
		long assetEntryClassNameRelId) throws NoSuchEntryClassNameRelException {
		return findByPrimaryKey((Serializable)assetEntryClassNameRelId);
	}

	/**
	 * Returns the asset entry class name rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry class name rel
	 * @return the asset entry class name rel, or <code>null</code> if a asset entry class name rel with the primary key could not be found
	 */
	@Override
	public AssetEntryClassNameRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntryClassNameRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AssetEntryClassNameRel assetEntryClassNameRel = (AssetEntryClassNameRel)serializable;

		if (assetEntryClassNameRel == null) {
			Session session = null;

			try {
				session = openSession();

				assetEntryClassNameRel = (AssetEntryClassNameRel)session.get(AssetEntryClassNameRelImpl.class,
						primaryKey);

				if (assetEntryClassNameRel != null) {
					cacheResult(assetEntryClassNameRel);
				}
				else {
					entityCache.putResult(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntryClassNameRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntryClassNameRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetEntryClassNameRel;
	}

	/**
	 * Returns the asset entry class name rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetEntryClassNameRelId the primary key of the asset entry class name rel
	 * @return the asset entry class name rel, or <code>null</code> if a asset entry class name rel with the primary key could not be found
	 */
	@Override
	public AssetEntryClassNameRel fetchByPrimaryKey(
		long assetEntryClassNameRelId) {
		return fetchByPrimaryKey((Serializable)assetEntryClassNameRelId);
	}

	@Override
	public Map<Serializable, AssetEntryClassNameRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AssetEntryClassNameRel> map = new HashMap<Serializable, AssetEntryClassNameRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AssetEntryClassNameRel assetEntryClassNameRel = fetchByPrimaryKey(primaryKey);

			if (assetEntryClassNameRel != null) {
				map.put(primaryKey, assetEntryClassNameRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntryClassNameRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AssetEntryClassNameRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ASSETENTRYCLASSNAMEREL_WHERE_PKS_IN);

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

			for (AssetEntryClassNameRel assetEntryClassNameRel : (List<AssetEntryClassNameRel>)q.list()) {
				map.put(assetEntryClassNameRel.getPrimaryKeyObj(),
					assetEntryClassNameRel);

				cacheResult(assetEntryClassNameRel);

				uncachedPrimaryKeys.remove(assetEntryClassNameRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AssetEntryClassNameRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntryClassNameRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the asset entry class name rels.
	 *
	 * @return the asset entry class name rels
	 */
	@Override
	public List<AssetEntryClassNameRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry class name rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry class name rels
	 * @param end the upper bound of the range of asset entry class name rels (not inclusive)
	 * @return the range of asset entry class name rels
	 */
	@Override
	public List<AssetEntryClassNameRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry class name rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry class name rels
	 * @param end the upper bound of the range of asset entry class name rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entry class name rels
	 */
	@Override
	public List<AssetEntryClassNameRel> findAll(int start, int end,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset entry class name rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryClassNameRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry class name rels
	 * @param end the upper bound of the range of asset entry class name rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of asset entry class name rels
	 */
	@Override
	public List<AssetEntryClassNameRel> findAll(int start, int end,
		OrderByComparator<AssetEntryClassNameRel> orderByComparator,
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

		List<AssetEntryClassNameRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetEntryClassNameRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSETENTRYCLASSNAMEREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETENTRYCLASSNAMEREL;

				if (pagination) {
					sql = sql.concat(AssetEntryClassNameRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetEntryClassNameRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetEntryClassNameRel>)QueryUtil.list(q,
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
	 * Removes all the asset entry class name rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssetEntryClassNameRel assetEntryClassNameRel : findAll()) {
			remove(assetEntryClassNameRel);
		}
	}

	/**
	 * Returns the number of asset entry class name rels.
	 *
	 * @return the number of asset entry class name rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETENTRYCLASSNAMEREL);

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
		return AssetEntryClassNameRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the asset entry class name rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AssetEntryClassNameRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ASSETENTRYCLASSNAMEREL = "SELECT assetEntryClassNameRel FROM AssetEntryClassNameRel assetEntryClassNameRel";
	private static final String _SQL_SELECT_ASSETENTRYCLASSNAMEREL_WHERE_PKS_IN = "SELECT assetEntryClassNameRel FROM AssetEntryClassNameRel assetEntryClassNameRel WHERE assetEntryClassNameRelId IN (";
	private static final String _SQL_SELECT_ASSETENTRYCLASSNAMEREL_WHERE = "SELECT assetEntryClassNameRel FROM AssetEntryClassNameRel assetEntryClassNameRel WHERE ";
	private static final String _SQL_COUNT_ASSETENTRYCLASSNAMEREL = "SELECT COUNT(assetEntryClassNameRel) FROM AssetEntryClassNameRel assetEntryClassNameRel";
	private static final String _SQL_COUNT_ASSETENTRYCLASSNAMEREL_WHERE = "SELECT COUNT(assetEntryClassNameRel) FROM AssetEntryClassNameRel assetEntryClassNameRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetEntryClassNameRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetEntryClassNameRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetEntryClassNameRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AssetEntryClassNameRelPersistenceImpl.class);
}