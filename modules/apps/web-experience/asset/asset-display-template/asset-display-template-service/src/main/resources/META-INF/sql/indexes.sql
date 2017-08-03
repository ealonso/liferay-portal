create index IX_C9B7F75C on AssetDisplayTemplate (classNameId);
create index IX_1DFADA96 on AssetDisplayTemplate (groupId, classNameId);
create index IX_570E1CA6 on AssetDisplayTemplate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B2A194A8 on AssetDisplayTemplate (uuid_[$COLUMN_LENGTH:75$], groupId);