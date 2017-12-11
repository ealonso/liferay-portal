create table LayoutFragment (
	layoutFragmentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	plid LONG,
	fragmentEntryId LONG,
	css VARCHAR(75) null,
	html VARCHAR(75) null,
	js VARCHAR(75) null,
	position INTEGER,
	typeSettings VARCHAR(75) null
);