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

package com.liferay.asset.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.exception.NoSuchEntryAssetTagRelException;
import com.liferay.asset.model.AssetEntryAssetTagRel;
import com.liferay.asset.model.impl.AssetEntryAssetTagRelImpl;
import com.liferay.asset.model.impl.AssetEntryAssetTagRelModelImpl;
import com.liferay.asset.service.persistence.AssetEntryAssetTagRelPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
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
 * The persistence implementation for the asset entry asset tag rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryAssetTagRelPersistence
 * @see com.liferay.asset.service.persistence.AssetEntryAssetTagRelUtil
 * @generated
 */
@ProviderType
public class AssetEntryAssetTagRelPersistenceImpl extends BasePersistenceImpl<AssetEntryAssetTagRel>
	implements AssetEntryAssetTagRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetEntryAssetTagRelUtil} to access the asset entry asset tag rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetEntryAssetTagRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryAssetTagRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryAssetTagRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRYID =
		new FinderPath(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryAssetTagRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID =
		new FinderPath(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryAssetTagRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetEntryId",
			new String[] { Long.class.getName() },
			AssetEntryAssetTagRelModelImpl.ASSETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETENTRYID = new FinderPath(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the asset entry asset tag rels where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @return the matching asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findByAssetEntryId(long assetEntryId) {
		return findByAssetEntryId(assetEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry asset tag rels where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset entry asset tag rels
	 * @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	 * @return the range of matching asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findByAssetEntryId(long assetEntryId,
		int start, int end) {
		return findByAssetEntryId(assetEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry asset tag rels where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset entry asset tag rels
	 * @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findByAssetEntryId(long assetEntryId,
		int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return findByAssetEntryId(assetEntryId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the asset entry asset tag rels where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset entry asset tag rels
	 * @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findByAssetEntryId(long assetEntryId,
		int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID;
			finderArgs = new Object[] { assetEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRYID;
			finderArgs = new Object[] {
					assetEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AssetEntryAssetTagRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetEntryAssetTagRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetEntryAssetTagRel assetEntryAssetTagRel : list) {
					if ((assetEntryId != assetEntryAssetTagRel.getAssetEntryId())) {
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

			query.append(_SQL_SELECT_ASSETENTRYASSETTAGREL_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntryAssetTagRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntryId);

				if (!pagination) {
					list = (List<AssetEntryAssetTagRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetEntryAssetTagRel>)QueryUtil.list(q,
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
	 * Returns the first asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry asset tag rel
	 * @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	 */
	@Override
	public AssetEntryAssetTagRel findByAssetEntryId_First(long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException {
		AssetEntryAssetTagRel assetEntryAssetTagRel = fetchByAssetEntryId_First(assetEntryId,
				orderByComparator);

		if (assetEntryAssetTagRel != null) {
			return assetEntryAssetTagRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryAssetTagRelException(msg.toString());
	}

	/**
	 * Returns the first asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	 */
	@Override
	public AssetEntryAssetTagRel fetchByAssetEntryId_First(long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		List<AssetEntryAssetTagRel> list = findByAssetEntryId(assetEntryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry asset tag rel
	 * @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	 */
	@Override
	public AssetEntryAssetTagRel findByAssetEntryId_Last(long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException {
		AssetEntryAssetTagRel assetEntryAssetTagRel = fetchByAssetEntryId_Last(assetEntryId,
				orderByComparator);

		if (assetEntryAssetTagRel != null) {
			return assetEntryAssetTagRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryAssetTagRelException(msg.toString());
	}

	/**
	 * Returns the last asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	 */
	@Override
	public AssetEntryAssetTagRel fetchByAssetEntryId_Last(long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		int count = countByAssetEntryId(assetEntryId);

		if (count == 0) {
			return null;
		}

		List<AssetEntryAssetTagRel> list = findByAssetEntryId(assetEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entry asset tag rels before and after the current asset entry asset tag rel in the ordered set where assetEntryId = &#63;.
	 *
	 * @param entryId the primary key of the current asset entry asset tag rel
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry asset tag rel
	 * @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	 */
	@Override
	public AssetEntryAssetTagRel[] findByAssetEntryId_PrevAndNext(
		long entryId, long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException {
		AssetEntryAssetTagRel assetEntryAssetTagRel = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			AssetEntryAssetTagRel[] array = new AssetEntryAssetTagRelImpl[3];

			array[0] = getByAssetEntryId_PrevAndNext(session,
					assetEntryAssetTagRel, assetEntryId, orderByComparator, true);

			array[1] = assetEntryAssetTagRel;

			array[2] = getByAssetEntryId_PrevAndNext(session,
					assetEntryAssetTagRel, assetEntryId, orderByComparator,
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

	protected AssetEntryAssetTagRel getByAssetEntryId_PrevAndNext(
		Session session, AssetEntryAssetTagRel assetEntryAssetTagRel,
		long assetEntryId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
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

		query.append(_SQL_SELECT_ASSETENTRYASSETTAGREL_WHERE);

		query.append(_FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2);

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
			query.append(AssetEntryAssetTagRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntryAssetTagRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntryAssetTagRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry asset tag rels where assetEntryId = &#63; from the database.
	 *
	 * @param assetEntryId the asset entry ID
	 */
	@Override
	public void removeByAssetEntryId(long assetEntryId) {
		for (AssetEntryAssetTagRel assetEntryAssetTagRel : findByAssetEntryId(
				assetEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetEntryAssetTagRel);
		}
	}

	/**
	 * Returns the number of asset entry asset tag rels where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @return the number of matching asset entry asset tag rels
	 */
	@Override
	public int countByAssetEntryId(long assetEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSETENTRYID;

		Object[] finderArgs = new Object[] { assetEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETENTRYASSETTAGREL_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2);

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

	private static final String _FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2 = "assetEntryAssetTagRel.assetEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETTAGID =
		new FinderPath(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryAssetTagRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetTagId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETTAGID =
		new FinderPath(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelModelImpl.FINDER_CACHE_ENABLED,
			AssetEntryAssetTagRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetTagId",
			new String[] { Long.class.getName() },
			AssetEntryAssetTagRelModelImpl.ASSETTAGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETTAGID = new FinderPath(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetTagId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the asset entry asset tag rels where assetTagId = &#63;.
	 *
	 * @param assetTagId the asset tag ID
	 * @return the matching asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findByAssetTagId(long assetTagId) {
		return findByAssetTagId(assetTagId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry asset tag rels where assetTagId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetTagId the asset tag ID
	 * @param start the lower bound of the range of asset entry asset tag rels
	 * @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	 * @return the range of matching asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findByAssetTagId(long assetTagId,
		int start, int end) {
		return findByAssetTagId(assetTagId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry asset tag rels where assetTagId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetTagId the asset tag ID
	 * @param start the lower bound of the range of asset entry asset tag rels
	 * @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findByAssetTagId(long assetTagId,
		int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return findByAssetTagId(assetTagId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset entry asset tag rels where assetTagId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetTagId the asset tag ID
	 * @param start the lower bound of the range of asset entry asset tag rels
	 * @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findByAssetTagId(long assetTagId,
		int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETTAGID;
			finderArgs = new Object[] { assetTagId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETTAGID;
			finderArgs = new Object[] { assetTagId, start, end, orderByComparator };
		}

		List<AssetEntryAssetTagRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetEntryAssetTagRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetEntryAssetTagRel assetEntryAssetTagRel : list) {
					if ((assetTagId != assetEntryAssetTagRel.getAssetTagId())) {
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

			query.append(_SQL_SELECT_ASSETENTRYASSETTAGREL_WHERE);

			query.append(_FINDER_COLUMN_ASSETTAGID_ASSETTAGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntryAssetTagRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetTagId);

				if (!pagination) {
					list = (List<AssetEntryAssetTagRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetEntryAssetTagRel>)QueryUtil.list(q,
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
	 * Returns the first asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	 *
	 * @param assetTagId the asset tag ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry asset tag rel
	 * @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	 */
	@Override
	public AssetEntryAssetTagRel findByAssetTagId_First(long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException {
		AssetEntryAssetTagRel assetEntryAssetTagRel = fetchByAssetTagId_First(assetTagId,
				orderByComparator);

		if (assetEntryAssetTagRel != null) {
			return assetEntryAssetTagRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetTagId=");
		msg.append(assetTagId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryAssetTagRelException(msg.toString());
	}

	/**
	 * Returns the first asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	 *
	 * @param assetTagId the asset tag ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	 */
	@Override
	public AssetEntryAssetTagRel fetchByAssetTagId_First(long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		List<AssetEntryAssetTagRel> list = findByAssetTagId(assetTagId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	 *
	 * @param assetTagId the asset tag ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry asset tag rel
	 * @throws NoSuchEntryAssetTagRelException if a matching asset entry asset tag rel could not be found
	 */
	@Override
	public AssetEntryAssetTagRel findByAssetTagId_Last(long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException {
		AssetEntryAssetTagRel assetEntryAssetTagRel = fetchByAssetTagId_Last(assetTagId,
				orderByComparator);

		if (assetEntryAssetTagRel != null) {
			return assetEntryAssetTagRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetTagId=");
		msg.append(assetTagId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryAssetTagRelException(msg.toString());
	}

	/**
	 * Returns the last asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	 *
	 * @param assetTagId the asset tag ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry asset tag rel, or <code>null</code> if a matching asset entry asset tag rel could not be found
	 */
	@Override
	public AssetEntryAssetTagRel fetchByAssetTagId_Last(long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		int count = countByAssetTagId(assetTagId);

		if (count == 0) {
			return null;
		}

		List<AssetEntryAssetTagRel> list = findByAssetTagId(assetTagId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entry asset tag rels before and after the current asset entry asset tag rel in the ordered set where assetTagId = &#63;.
	 *
	 * @param entryId the primary key of the current asset entry asset tag rel
	 * @param assetTagId the asset tag ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry asset tag rel
	 * @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	 */
	@Override
	public AssetEntryAssetTagRel[] findByAssetTagId_PrevAndNext(long entryId,
		long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator)
		throws NoSuchEntryAssetTagRelException {
		AssetEntryAssetTagRel assetEntryAssetTagRel = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			AssetEntryAssetTagRel[] array = new AssetEntryAssetTagRelImpl[3];

			array[0] = getByAssetTagId_PrevAndNext(session,
					assetEntryAssetTagRel, assetTagId, orderByComparator, true);

			array[1] = assetEntryAssetTagRel;

			array[2] = getByAssetTagId_PrevAndNext(session,
					assetEntryAssetTagRel, assetTagId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntryAssetTagRel getByAssetTagId_PrevAndNext(
		Session session, AssetEntryAssetTagRel assetEntryAssetTagRel,
		long assetTagId,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
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

		query.append(_SQL_SELECT_ASSETENTRYASSETTAGREL_WHERE);

		query.append(_FINDER_COLUMN_ASSETTAGID_ASSETTAGID_2);

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
			query.append(AssetEntryAssetTagRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetTagId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntryAssetTagRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntryAssetTagRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry asset tag rels where assetTagId = &#63; from the database.
	 *
	 * @param assetTagId the asset tag ID
	 */
	@Override
	public void removeByAssetTagId(long assetTagId) {
		for (AssetEntryAssetTagRel assetEntryAssetTagRel : findByAssetTagId(
				assetTagId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetEntryAssetTagRel);
		}
	}

	/**
	 * Returns the number of asset entry asset tag rels where assetTagId = &#63;.
	 *
	 * @param assetTagId the asset tag ID
	 * @return the number of matching asset entry asset tag rels
	 */
	@Override
	public int countByAssetTagId(long assetTagId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSETTAGID;

		Object[] finderArgs = new Object[] { assetTagId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETENTRYASSETTAGREL_WHERE);

			query.append(_FINDER_COLUMN_ASSETTAGID_ASSETTAGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetTagId);

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

	private static final String _FINDER_COLUMN_ASSETTAGID_ASSETTAGID_2 = "assetEntryAssetTagRel.assetTagId = ?";

	public AssetEntryAssetTagRelPersistenceImpl() {
		setModelClass(AssetEntryAssetTagRel.class);
	}

	/**
	 * Caches the asset entry asset tag rel in the entity cache if it is enabled.
	 *
	 * @param assetEntryAssetTagRel the asset entry asset tag rel
	 */
	@Override
	public void cacheResult(AssetEntryAssetTagRel assetEntryAssetTagRel) {
		entityCache.putResult(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelImpl.class,
			assetEntryAssetTagRel.getPrimaryKey(), assetEntryAssetTagRel);

		assetEntryAssetTagRel.resetOriginalValues();
	}

	/**
	 * Caches the asset entry asset tag rels in the entity cache if it is enabled.
	 *
	 * @param assetEntryAssetTagRels the asset entry asset tag rels
	 */
	@Override
	public void cacheResult(List<AssetEntryAssetTagRel> assetEntryAssetTagRels) {
		for (AssetEntryAssetTagRel assetEntryAssetTagRel : assetEntryAssetTagRels) {
			if (entityCache.getResult(
						AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntryAssetTagRelImpl.class,
						assetEntryAssetTagRel.getPrimaryKey()) == null) {
				cacheResult(assetEntryAssetTagRel);
			}
			else {
				assetEntryAssetTagRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset entry asset tag rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AssetEntryAssetTagRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset entry asset tag rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetEntryAssetTagRel assetEntryAssetTagRel) {
		entityCache.removeResult(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelImpl.class,
			assetEntryAssetTagRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetEntryAssetTagRel> assetEntryAssetTagRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetEntryAssetTagRel assetEntryAssetTagRel : assetEntryAssetTagRels) {
			entityCache.removeResult(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntryAssetTagRelImpl.class,
				assetEntryAssetTagRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset entry asset tag rel with the primary key. Does not add the asset entry asset tag rel to the database.
	 *
	 * @param entryId the primary key for the new asset entry asset tag rel
	 * @return the new asset entry asset tag rel
	 */
	@Override
	public AssetEntryAssetTagRel create(long entryId) {
		AssetEntryAssetTagRel assetEntryAssetTagRel = new AssetEntryAssetTagRelImpl();

		assetEntryAssetTagRel.setNew(true);
		assetEntryAssetTagRel.setPrimaryKey(entryId);

		assetEntryAssetTagRel.setCompanyId(companyProvider.getCompanyId());

		return assetEntryAssetTagRel;
	}

	/**
	 * Removes the asset entry asset tag rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the asset entry asset tag rel
	 * @return the asset entry asset tag rel that was removed
	 * @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	 */
	@Override
	public AssetEntryAssetTagRel remove(long entryId)
		throws NoSuchEntryAssetTagRelException {
		return remove((Serializable)entryId);
	}

	/**
	 * Removes the asset entry asset tag rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset entry asset tag rel
	 * @return the asset entry asset tag rel that was removed
	 * @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	 */
	@Override
	public AssetEntryAssetTagRel remove(Serializable primaryKey)
		throws NoSuchEntryAssetTagRelException {
		Session session = null;

		try {
			session = openSession();

			AssetEntryAssetTagRel assetEntryAssetTagRel = (AssetEntryAssetTagRel)session.get(AssetEntryAssetTagRelImpl.class,
					primaryKey);

			if (assetEntryAssetTagRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryAssetTagRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetEntryAssetTagRel);
		}
		catch (NoSuchEntryAssetTagRelException nsee) {
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
	protected AssetEntryAssetTagRel removeImpl(
		AssetEntryAssetTagRel assetEntryAssetTagRel) {
		assetEntryAssetTagRel = toUnwrappedModel(assetEntryAssetTagRel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetEntryAssetTagRel)) {
				assetEntryAssetTagRel = (AssetEntryAssetTagRel)session.get(AssetEntryAssetTagRelImpl.class,
						assetEntryAssetTagRel.getPrimaryKeyObj());
			}

			if (assetEntryAssetTagRel != null) {
				session.delete(assetEntryAssetTagRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetEntryAssetTagRel != null) {
			clearCache(assetEntryAssetTagRel);
		}

		return assetEntryAssetTagRel;
	}

	@Override
	public AssetEntryAssetTagRel updateImpl(
		AssetEntryAssetTagRel assetEntryAssetTagRel) {
		assetEntryAssetTagRel = toUnwrappedModel(assetEntryAssetTagRel);

		boolean isNew = assetEntryAssetTagRel.isNew();

		AssetEntryAssetTagRelModelImpl assetEntryAssetTagRelModelImpl = (AssetEntryAssetTagRelModelImpl)assetEntryAssetTagRel;

		Session session = null;

		try {
			session = openSession();

			if (assetEntryAssetTagRel.isNew()) {
				session.save(assetEntryAssetTagRel);

				assetEntryAssetTagRel.setNew(false);
			}
			else {
				assetEntryAssetTagRel = (AssetEntryAssetTagRel)session.merge(assetEntryAssetTagRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AssetEntryAssetTagRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					assetEntryAssetTagRelModelImpl.getAssetEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID,
				args);

			args = new Object[] { assetEntryAssetTagRelModelImpl.getAssetTagId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETTAGID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETTAGID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((assetEntryAssetTagRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntryAssetTagRelModelImpl.getOriginalAssetEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID,
					args);

				args = new Object[] {
						assetEntryAssetTagRelModelImpl.getAssetEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID,
					args);
			}

			if ((assetEntryAssetTagRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETTAGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntryAssetTagRelModelImpl.getOriginalAssetTagId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETTAGID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETTAGID,
					args);

				args = new Object[] {
						assetEntryAssetTagRelModelImpl.getAssetTagId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETTAGID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETTAGID,
					args);
			}
		}

		entityCache.putResult(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntryAssetTagRelImpl.class,
			assetEntryAssetTagRel.getPrimaryKey(), assetEntryAssetTagRel, false);

		assetEntryAssetTagRel.resetOriginalValues();

		return assetEntryAssetTagRel;
	}

	protected AssetEntryAssetTagRel toUnwrappedModel(
		AssetEntryAssetTagRel assetEntryAssetTagRel) {
		if (assetEntryAssetTagRel instanceof AssetEntryAssetTagRelImpl) {
			return assetEntryAssetTagRel;
		}

		AssetEntryAssetTagRelImpl assetEntryAssetTagRelImpl = new AssetEntryAssetTagRelImpl();

		assetEntryAssetTagRelImpl.setNew(assetEntryAssetTagRel.isNew());
		assetEntryAssetTagRelImpl.setPrimaryKey(assetEntryAssetTagRel.getPrimaryKey());

		assetEntryAssetTagRelImpl.setEntryId(assetEntryAssetTagRel.getEntryId());
		assetEntryAssetTagRelImpl.setCompanyId(assetEntryAssetTagRel.getCompanyId());
		assetEntryAssetTagRelImpl.setAssetEntryId(assetEntryAssetTagRel.getAssetEntryId());
		assetEntryAssetTagRelImpl.setAssetTagId(assetEntryAssetTagRel.getAssetTagId());

		return assetEntryAssetTagRelImpl;
	}

	/**
	 * Returns the asset entry asset tag rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry asset tag rel
	 * @return the asset entry asset tag rel
	 * @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	 */
	@Override
	public AssetEntryAssetTagRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryAssetTagRelException {
		AssetEntryAssetTagRel assetEntryAssetTagRel = fetchByPrimaryKey(primaryKey);

		if (assetEntryAssetTagRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryAssetTagRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetEntryAssetTagRel;
	}

	/**
	 * Returns the asset entry asset tag rel with the primary key or throws a {@link NoSuchEntryAssetTagRelException} if it could not be found.
	 *
	 * @param entryId the primary key of the asset entry asset tag rel
	 * @return the asset entry asset tag rel
	 * @throws NoSuchEntryAssetTagRelException if a asset entry asset tag rel with the primary key could not be found
	 */
	@Override
	public AssetEntryAssetTagRel findByPrimaryKey(long entryId)
		throws NoSuchEntryAssetTagRelException {
		return findByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns the asset entry asset tag rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry asset tag rel
	 * @return the asset entry asset tag rel, or <code>null</code> if a asset entry asset tag rel with the primary key could not be found
	 */
	@Override
	public AssetEntryAssetTagRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntryAssetTagRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AssetEntryAssetTagRel assetEntryAssetTagRel = (AssetEntryAssetTagRel)serializable;

		if (assetEntryAssetTagRel == null) {
			Session session = null;

			try {
				session = openSession();

				assetEntryAssetTagRel = (AssetEntryAssetTagRel)session.get(AssetEntryAssetTagRelImpl.class,
						primaryKey);

				if (assetEntryAssetTagRel != null) {
					cacheResult(assetEntryAssetTagRel);
				}
				else {
					entityCache.putResult(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntryAssetTagRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntryAssetTagRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetEntryAssetTagRel;
	}

	/**
	 * Returns the asset entry asset tag rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the asset entry asset tag rel
	 * @return the asset entry asset tag rel, or <code>null</code> if a asset entry asset tag rel with the primary key could not be found
	 */
	@Override
	public AssetEntryAssetTagRel fetchByPrimaryKey(long entryId) {
		return fetchByPrimaryKey((Serializable)entryId);
	}

	@Override
	public Map<Serializable, AssetEntryAssetTagRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AssetEntryAssetTagRel> map = new HashMap<Serializable, AssetEntryAssetTagRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AssetEntryAssetTagRel assetEntryAssetTagRel = fetchByPrimaryKey(primaryKey);

			if (assetEntryAssetTagRel != null) {
				map.put(primaryKey, assetEntryAssetTagRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntryAssetTagRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AssetEntryAssetTagRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ASSETENTRYASSETTAGREL_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (AssetEntryAssetTagRel assetEntryAssetTagRel : (List<AssetEntryAssetTagRel>)q.list()) {
				map.put(assetEntryAssetTagRel.getPrimaryKeyObj(),
					assetEntryAssetTagRel);

				cacheResult(assetEntryAssetTagRel);

				uncachedPrimaryKeys.remove(assetEntryAssetTagRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AssetEntryAssetTagRelModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntryAssetTagRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the asset entry asset tag rels.
	 *
	 * @return the asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry asset tag rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry asset tag rels
	 * @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	 * @return the range of asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry asset tag rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry asset tag rels
	 * @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findAll(int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset entry asset tag rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetEntryAssetTagRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry asset tag rels
	 * @param end the upper bound of the range of asset entry asset tag rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of asset entry asset tag rels
	 */
	@Override
	public List<AssetEntryAssetTagRel> findAll(int start, int end,
		OrderByComparator<AssetEntryAssetTagRel> orderByComparator,
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

		List<AssetEntryAssetTagRel> list = null;

		if (retrieveFromCache) {
			list = (List<AssetEntryAssetTagRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSETENTRYASSETTAGREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETENTRYASSETTAGREL;

				if (pagination) {
					sql = sql.concat(AssetEntryAssetTagRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetEntryAssetTagRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetEntryAssetTagRel>)QueryUtil.list(q,
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
	 * Removes all the asset entry asset tag rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssetEntryAssetTagRel assetEntryAssetTagRel : findAll()) {
			remove(assetEntryAssetTagRel);
		}
	}

	/**
	 * Returns the number of asset entry asset tag rels.
	 *
	 * @return the number of asset entry asset tag rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETENTRYASSETTAGREL);

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
		return AssetEntryAssetTagRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the asset entry asset tag rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AssetEntryAssetTagRelImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETENTRYASSETTAGREL = "SELECT assetEntryAssetTagRel FROM AssetEntryAssetTagRel assetEntryAssetTagRel";
	private static final String _SQL_SELECT_ASSETENTRYASSETTAGREL_WHERE_PKS_IN = "SELECT assetEntryAssetTagRel FROM AssetEntryAssetTagRel assetEntryAssetTagRel WHERE entryId IN (";
	private static final String _SQL_SELECT_ASSETENTRYASSETTAGREL_WHERE = "SELECT assetEntryAssetTagRel FROM AssetEntryAssetTagRel assetEntryAssetTagRel WHERE ";
	private static final String _SQL_COUNT_ASSETENTRYASSETTAGREL = "SELECT COUNT(assetEntryAssetTagRel) FROM AssetEntryAssetTagRel assetEntryAssetTagRel";
	private static final String _SQL_COUNT_ASSETENTRYASSETTAGREL_WHERE = "SELECT COUNT(assetEntryAssetTagRel) FROM AssetEntryAssetTagRel assetEntryAssetTagRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetEntryAssetTagRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetEntryAssetTagRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetEntryAssetTagRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AssetEntryAssetTagRelPersistenceImpl.class);
}