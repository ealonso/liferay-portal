create table AssetTag (
	uuid_ VARCHAR(75) null,
	tagId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	assetCount INTEGER,
	lastPublishDate DATE null
);

create table AssetTagStats (
	tagStatsId LONG not null primary key,
	companyId LONG,
	tagId LONG,
	classNameId LONG,
	assetCount INTEGER
);