create table AssetEntryAssetCategoryRel (
	assetEntryAssetCategoryRelId LONG not null primary key,
	assetEntryId LONG,
	assetCategoryId LONG,
	priority INTEGER
);

create table AssetEntryRel (
	assetEntryRelId LONG not null primary key,
	assetEntryId LONG,
	classNameId LONG,
	classPK LONG
);