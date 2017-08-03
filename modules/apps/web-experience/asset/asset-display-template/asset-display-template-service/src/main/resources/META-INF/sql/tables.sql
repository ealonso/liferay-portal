create table AssetDisplayTemplate (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	assetDisplayTemplateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	classNameId LONG,
	plid LONG,
	templateId LONG,
	main BOOLEAN
);