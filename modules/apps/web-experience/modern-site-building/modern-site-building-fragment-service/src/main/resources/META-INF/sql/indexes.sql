create unique index IX_F0F44819 on MSBFragmentCollection (groupId, name[$COLUMN_LENGTH:75$]);

create index IX_3F5A4237 on MSBFragmentEntry (groupId, msbFragmentCollectionId);
create unique index IX_30585F1D on MSBFragmentEntry (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_8DA20B89 on MSBFragmentEntry (msbFragmentCollectionId);