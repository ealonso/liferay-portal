create table AssetEntryAssetCategoryRel (
	assetEntryAssetCategoryRelId LONG not null primary key,
	assetEntryId LONG,
	assetCategoryId LONG,
	priority INTEGER
);

create table AssetEntryClassNameRel (
	assetEntryClassNameRelId LONG not null primary key,
	assetEntryId LONG,
	classNameId LONG,
	classPK LONG
);