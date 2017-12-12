create unique index IX_536510F5 on FragmentCollection (groupId, name[$COLUMN_LENGTH:75$]);

create index IX_2095DA5 on FragmentEntry (fragmentCollectionId);
create index IX_18F9DFE on FragmentEntry (groupId, fragmentCollectionId, name[$COLUMN_LENGTH:75$]);
create unique index IX_CACC7CC1 on FragmentEntry (groupId, name[$COLUMN_LENGTH:75$]);

create index IX_74278EE9 on FragmentEntryLayoutTemplateLink (groupId, fragmentEntryId);
create index IX_2A4433A6 on FragmentEntryLayoutTemplateLink (groupId, layoutPageTemplateEntryId);