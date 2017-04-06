create unique index IX_4A4B2F0F on Site_GroupFriendlyURL (companyId, friendlyURL[$COLUMN_LENGTH:75$]);
create unique index IX_7B9B2E0C on Site_GroupFriendlyURL (companyId, groupId, languageId[$COLUMN_LENGTH:75$]);
create index IX_77CA6677 on Site_GroupFriendlyURL (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6D81EB9 on Site_GroupFriendlyURL (uuid_[$COLUMN_LENGTH:75$], groupId);