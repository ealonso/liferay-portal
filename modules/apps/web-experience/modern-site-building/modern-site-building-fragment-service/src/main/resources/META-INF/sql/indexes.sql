create unique index IX_536510F5 on FragmentCollection (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_9A228268 on FragmentCollection (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DFB882EA on FragmentCollection (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2095DA5 on FragmentEntry (fragmentCollectionId);
create index IX_19A0A97F on FragmentEntry (groupId, fragmentCollectionId);
create unique index IX_CACC7CC1 on FragmentEntry (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_C65BF31C on FragmentEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_553E909E on FragmentEntry (uuid_[$COLUMN_LENGTH:75$], groupId);