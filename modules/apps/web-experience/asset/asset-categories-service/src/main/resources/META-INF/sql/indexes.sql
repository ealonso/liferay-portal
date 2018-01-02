create index IX_C7F39FCA on AssetCategory (groupId, name[$COLUMN_LENGTH:75$], vocabularyId);
create index IX_852EA801 on AssetCategory (groupId, parentCategoryId, name[$COLUMN_LENGTH:75$], vocabularyId);
create index IX_87603842 on AssetCategory (groupId, parentCategoryId, vocabularyId);
create index IX_2008FACB on AssetCategory (groupId, vocabularyId);
create index IX_D61ABE08 on AssetCategory (name[$COLUMN_LENGTH:75$], vocabularyId);
create unique index IX_BE4DF2BF on AssetCategory (parentCategoryId, name[$COLUMN_LENGTH:75$], vocabularyId);
create index IX_B185E980 on AssetCategory (parentCategoryId, vocabularyId);
create index IX_BBAF6928 on AssetCategory (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E8D019AA on AssetCategory (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_287B1F89 on AssetCategory (vocabularyId);

create index IX_B22D908C on AssetVocabulary (companyId);
create unique index IX_C0AAD74D on AssetVocabulary (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_C4E6FD10 on AssetVocabulary (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1B2B8792 on AssetVocabulary (uuid_[$COLUMN_LENGTH:75$], groupId);