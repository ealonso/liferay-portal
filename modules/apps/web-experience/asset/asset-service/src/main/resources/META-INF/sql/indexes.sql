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

create unique index IX_DBD111AA on AssetCategoryProperty (categoryId, key_[$COLUMN_LENGTH:75$]);
create index IX_52340033 on AssetCategoryProperty (companyId, key_[$COLUMN_LENGTH:75$]);

create index IX_A188F560 on AssetEntries_AssetCategories (categoryId);
create index IX_38A65B55 on AssetEntries_AssetCategories (companyId);
create index IX_E119938A on AssetEntries_AssetCategories (entryId);

create index IX_112337B8 on AssetEntries_AssetTags (companyId);
create index IX_2ED82CAD on AssetEntries_AssetTags (entryId);
create index IX_B2A61B55 on AssetEntries_AssetTags (tagId);

create unique index IX_1E9D371D on AssetEntry (classNameId, classPK);
create index IX_7306C60 on AssetEntry (companyId);
create index IX_75D42FF9 on AssetEntry (expirationDate);
create index IX_6418BB52 on AssetEntry (groupId, classNameId, publishDate, expirationDate);
create index IX_82C4BEF6 on AssetEntry (groupId, classNameId, visible);
create index IX_1EBA6821 on AssetEntry (groupId, classUuid[$COLUMN_LENGTH:75$]);
create index IX_FEC4A201 on AssetEntry (layoutUuid[$COLUMN_LENGTH:75$]);
create index IX_2E4E3885 on AssetEntry (publishDate);
create index IX_9029E15A on AssetEntry (visible);

create unique index IX_8F542794 on AssetLink (entryId1, entryId2, type_);
create index IX_14D5A20D on AssetLink (entryId1, type_);
create index IX_91F132C on AssetLink (entryId2, type_);

create unique index IX_D63322F9 on AssetTag (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_84C501E4 on AssetTag (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B6ACB166 on AssetTag (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_50702693 on AssetTagStats (classNameId);
create unique index IX_56682CC4 on AssetTagStats (tagId, classNameId);

create index IX_B22D908C on AssetVocabulary (companyId);
create unique index IX_C0AAD74D on AssetVocabulary (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_C4E6FD10 on AssetVocabulary (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1B2B8792 on AssetVocabulary (uuid_[$COLUMN_LENGTH:75$], groupId);