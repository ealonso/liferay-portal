create unique index IX_1E9D371D on AssetEntry (classNameId, classPK);
create index IX_7306C60 on AssetEntry (companyId);
create index IX_75D42FF9 on AssetEntry (expirationDate);
create index IX_6418BB52 on AssetEntry (groupId, classNameId, publishDate, expirationDate);
create index IX_82C4BEF6 on AssetEntry (groupId, classNameId, visible);
create index IX_1EBA6821 on AssetEntry (groupId, classUuid[$COLUMN_LENGTH:75$]);
create index IX_FEC4A201 on AssetEntry (layoutUuid[$COLUMN_LENGTH:75$]);
create index IX_2E4E3885 on AssetEntry (publishDate);
create index IX_9029E15A on AssetEntry (visible);

create index IX_CB3E2B64 on AssetEntryAssetCategoryRel (assetEntryId);

create index IX_5AA0B2E4 on AssetEntryAssetTagRel (assetEntryId);
create index IX_878C34C on AssetEntryAssetTagRel (assetTagId);

create unique index IX_8F542794 on AssetLink (entryId1, entryId2, type_);
create index IX_14D5A20D on AssetLink (entryId1, type_);
create index IX_91F132C on AssetLink (entryId2, type_);