create unique index IX_D63322F9 on AssetTag (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_84C501E4 on AssetTag (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B6ACB166 on AssetTag (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_50702693 on AssetTagStats (classNameId);
create unique index IX_56682CC4 on AssetTagStats (tagId, classNameId);