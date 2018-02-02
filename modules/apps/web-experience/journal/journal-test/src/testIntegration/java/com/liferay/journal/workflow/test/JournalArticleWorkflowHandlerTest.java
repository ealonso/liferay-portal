/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.journal.workflow.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLinkLocalServiceUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMTemplateTestUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Pavel Savinov
 */
@RunWith(Arquillian.class)
public class JournalArticleWorkflowHandlerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		List<WorkflowDefinition> workflowDefinitions =
			WorkflowDefinitionManagerUtil.getActiveWorkflowDefinitions(
				_group.getCompanyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				WorkflowComparatorFactoryUtil.getDefinitionNameComparator(
					true));

		Assert.assertTrue(ListUtil.isNotEmpty(workflowDefinitions));

		WorkflowDefinition workflowDefinition = workflowDefinitions.get(0);

		_workflowDefinitionName =
			workflowDefinition.getName() + StringPool.AT +
				workflowDefinition.getVersion();

		Assert.assertNotNull(_workflowDefinitionName);

		_workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(
			JournalArticle.class.getName());

		Assert.assertNotNull(_workflowHandler);
	}

	@Test
	public void testAllStructuresWorkflow() throws Exception {
		JournalFolder folder = JournalTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		folder.setRestrictionType(
			JournalFolderConstants.
				RESTRICTION_TYPE_DDM_STRUCTURES_AND_WORKFLOW);

		JournalFolderLocalServiceUtil.updateJournalFolder(folder);

		DDMForm ddmForm = DDMFormTestUtil.createDDMForm(
			Collections.singleton(LocaleUtil.getDefault()), LocaleUtil.US);

		DDMFormField ddmFormField = DDMFormTestUtil.createTextDDMFormField(
			"test", false, false, false);

		ddmForm.addDDMFormField(ddmFormField);

		DDMStructure ddmStructure1 = DDMStructureTestUtil.addStructure(
			_group.getGroupId(), JournalArticle.class.getName(), ddmForm);

		DDMStructureLinkLocalServiceUtil.addStructureLink(
			PortalUtil.getClassNameId(JournalFolder.class),
			folder.getFolderId(), ddmStructure1.getStructureId());

		DDMTemplate ddmTemplate1 = DDMTemplateTestUtil.addTemplate(
			_group.getGroupId(), ddmStructure1.getStructureId(),
			PortalUtil.getClassNameId(JournalArticle.class));

		DDMStructure ddmStructure2 = DDMStructureTestUtil.addStructure(
			_group.getGroupId(), JournalArticle.class.getName(), ddmForm);

		DDMStructureLinkLocalServiceUtil.addStructureLink(
			PortalUtil.getClassNameId(JournalFolder.class),
			folder.getFolderId(), ddmStructure2.getStructureId());

		DDMTemplate ddmTemplate2 = DDMTemplateTestUtil.addTemplate(
			_group.getGroupId(), ddmStructure2.getStructureId(),
			PortalUtil.getClassNameId(JournalArticle.class));

		Element element = SAXReaderUtil.createElement("content");

		Document document = SAXReaderUtil.createDocument(element);

		JournalArticle article1 = JournalTestUtil.addArticleWithXMLContent(
			_group.getGroupId(), folder.getFolderId(),
			PortalUtil.getClassNameId(JournalArticle.class), document.asXML(),
			ddmStructure1.getStructureKey(), ddmTemplate1.getTemplateKey());

		JournalArticle article2 = JournalTestUtil.addArticleWithXMLContent(
			_group.getGroupId(), folder.getFolderId(),
			PortalUtil.getClassNameId(JournalArticle.class), document.asXML(),
			ddmStructure2.getStructureKey(), ddmTemplate2.getTemplateKey());

		WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
			_group.getCreatorUserId(), _group.getCompanyId(),
			_group.getGroupId(), JournalFolder.class.getName(),
			folder.getFolderId(), JournalArticleConstants.DDM_STRUCTURE_ID_ALL,
			_workflowDefinitionName);

		WorkflowDefinitionLink workflowDefinitionLink1 =
			_workflowHandler.getWorkflowDefinitionLink(
				_group.getCompanyId(), _group.getGroupId(), article1.getId());

		WorkflowDefinitionLink workflowDefinitionLink2 =
			_workflowHandler.getWorkflowDefinitionLink(
				_group.getCompanyId(), _group.getGroupId(), article2.getId());

		Assert.assertNotNull(workflowDefinitionLink1);
		Assert.assertNotNull(workflowDefinitionLink2);

		Assert.assertEquals(workflowDefinitionLink1, workflowDefinitionLink2);
	}

	@Test
	public void testDDMStructureWorkflow() throws Exception {
		JournalFolder folder = JournalTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		folder.setRestrictionType(
			JournalFolderConstants.
				RESTRICTION_TYPE_DDM_STRUCTURES_AND_WORKFLOW);

		JournalFolderLocalServiceUtil.updateJournalFolder(folder);

		DDMForm ddmForm = DDMFormTestUtil.createDDMForm(
			Collections.singleton(LocaleUtil.getDefault()), LocaleUtil.US);

		DDMFormField ddmFormField = DDMFormTestUtil.createTextDDMFormField(
			"test", false, false, false);

		ddmForm.addDDMFormField(ddmFormField);

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			_group.getGroupId(), JournalArticle.class.getName(), ddmForm);

		DDMStructureLinkLocalServiceUtil.addStructureLink(
			PortalUtil.getClassNameId(JournalFolder.class),
			folder.getFolderId(), ddmStructure.getStructureId());

		DDMTemplate ddmTemplate = DDMTemplateTestUtil.addTemplate(
			_group.getGroupId(), ddmStructure.getStructureId(),
			PortalUtil.getClassNameId(JournalArticle.class));

		Element element = SAXReaderUtil.createElement("content");

		Document document = SAXReaderUtil.createDocument(element);

		JournalArticle article = JournalTestUtil.addArticleWithXMLContent(
			_group.getGroupId(), folder.getFolderId(),
			PortalUtil.getClassNameId(JournalArticle.class), document.asXML(),
			ddmStructure.getStructureKey(), ddmTemplate.getTemplateKey());

		WorkflowDefinitionLink workflowDefinitionLink =
			_workflowHandler.getWorkflowDefinitionLink(
				article.getCompanyId(), article.getGroupId(), article.getId());

		Assert.assertNull(workflowDefinitionLink);

		WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
			_group.getCreatorUserId(), _group.getCompanyId(),
			_group.getGroupId(), JournalFolder.class.getName(),
			folder.getFolderId(), ddmStructure.getStructureId(),
			_workflowDefinitionName);

		workflowDefinitionLink = _workflowHandler.getWorkflowDefinitionLink(
			article.getCompanyId(), article.getGroupId(), article.getId());

		Assert.assertNotNull(workflowDefinitionLink);
	}

	@Test
	public void testNoWorkflowInheritance() throws Exception {
		JournalFolder folder = JournalTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		JournalFolder subfolder = JournalTestUtil.addFolder(
			_group.getGroupId(), folder.getFolderId(),
			RandomTestUtil.randomString());

		subfolder.setRestrictionType(
			JournalFolderConstants.RESTRICTION_TYPE_WORKFLOW);

		JournalFolderLocalServiceUtil.updateJournalFolder(subfolder);

		JournalArticle folderArticle = JournalTestUtil.addArticle(
			_group.getGroupId(), folder.getFolderId());

		JournalArticle subfolderArticle = JournalTestUtil.addArticle(
			_group.getGroupId(), subfolder.getFolderId());

		WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
			_group.getCreatorUserId(), _group.getCompanyId(),
			_group.getGroupId(), JournalArticle.class.getName(), 0, 0,
			_workflowDefinitionName);

		WorkflowDefinitionLink folderArticleWorkflowDefinitionLink =
			_workflowHandler.getWorkflowDefinitionLink(
				folderArticle.getCompanyId(), folderArticle.getGroupId(),
				folderArticle.getId());

		Assert.assertNotNull(folderArticleWorkflowDefinitionLink);

		WorkflowDefinitionLink subfolderArticleWorkflowDefinitionLink =
			_workflowHandler.getWorkflowDefinitionLink(
				subfolderArticle.getCompanyId(), subfolderArticle.getGroupId(),
				subfolderArticle.getId());

		Assert.assertNull(subfolderArticleWorkflowDefinitionLink);

		WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
			_group.getCreatorUserId(), _group.getCompanyId(),
			_group.getGroupId(), JournalArticle.class.getName(), 0, 0,
			StringPool.BLANK);
	}

	@Test
	public void testRootFolderWorkflowInheritance() throws Exception {
		JournalArticle article = JournalTestUtil.addArticle(
			_group.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		WorkflowDefinitionLink workflowDefinitionLink =
			_workflowHandler.getWorkflowDefinitionLink(
				article.getCompanyId(), article.getGroupId(), article.getId());

		Assert.assertNull(workflowDefinitionLink);

		WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
			_group.getCreatorUserId(), _group.getCompanyId(),
			_group.getGroupId(), JournalArticle.class.getName(), 0, 0,
			_workflowDefinitionName);

		workflowDefinitionLink = _workflowHandler.getWorkflowDefinitionLink(
			article.getCompanyId(), article.getGroupId(), article.getId());

		Assert.assertNotNull(workflowDefinitionLink);

		WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
			_group.getCreatorUserId(), _group.getCompanyId(),
			_group.getGroupId(), JournalArticle.class.getName(), 0, 0,
			StringPool.BLANK);
	}

	@Test
	public void testSubfolderWorkflowInheritance() throws Exception {
		JournalFolder folder = JournalTestUtil.addFolder(
			_group.getGroupId(), RandomTestUtil.randomString());

		JournalFolder subfolder = JournalTestUtil.addFolder(
			_group.getGroupId(), folder.getFolderId(),
			RandomTestUtil.randomString());

		JournalArticle article = JournalTestUtil.addArticle(
			_group.getGroupId(), subfolder.getFolderId());

		WorkflowDefinitionLink workflowDefinitionLink =
			_workflowHandler.getWorkflowDefinitionLink(
				article.getCompanyId(), article.getGroupId(), article.getId());

		Assert.assertNull(workflowDefinitionLink);

		WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
			_group.getCreatorUserId(), _group.getCompanyId(),
			_group.getGroupId(), JournalArticle.class.getName(), 0, 0,
			_workflowDefinitionName);

		workflowDefinitionLink = _workflowHandler.getWorkflowDefinitionLink(
			article.getCompanyId(), article.getGroupId(), article.getId());

		Assert.assertNotNull(workflowDefinitionLink);

		WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(
			_group.getCreatorUserId(), _group.getCompanyId(),
			_group.getGroupId(), JournalArticle.class.getName(), 0, 0,
			StringPool.BLANK);
	}

	@DeleteAfterTestRun
	private Group _group;

	private String _workflowDefinitionName;
	private WorkflowHandler _workflowHandler;

}