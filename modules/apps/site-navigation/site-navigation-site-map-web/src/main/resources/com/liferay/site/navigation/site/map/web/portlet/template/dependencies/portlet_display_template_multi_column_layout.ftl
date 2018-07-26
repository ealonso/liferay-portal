<#if entries?has_content>
	<@liferay_aui.row>
		<#list entries as entry>
			<@liferay_aui.col width=25>
				<div class="results-header">
					<h3>
						<a

						<#assign siteNavigationMenuItemType = entry.getSiteNavigationMenuItemType() />

						<#if siteNavigationMenuItemType.isBrowsable(entry)>
							href="${siteNavigationMenuItemType.getRegularURL(request, entry)}"
						</#if>

						>${siteNavigationMenuItemType.getTitle(entry, locale)}</a>
					</h3>
				</div>

				<@displayMenuItems
					depth=1
					items=entry.getChildren()
				/>
			</@liferay_aui.col>
		</#list>
	</@liferay_aui.row>
</#if>

<#macro displayMenuItems
	depth
	items
>
	<#if pages?has_content && ((depth < displayDepth?number) || (displayDepth?number == 0))>
		<ul class="child-pages">
			<#list items as item>
				<li>
					<a

					<#assign siteNavigationMenuItemType = entry.getSiteNavigationMenuItemType() />

					<#if siteNavigationMenuItemType.isBrowsable(item)>
						href="${siteNavigationMenuItemType.getRegularURL(request, item)}"
					</#if>

					>${siteNavigationMenuItemType.getTitle(item, locale)}</a>

					<@displayMenuItems
						depth=depth + 1
						items=item.getChildren()
					/>
				</li>
			</#list>
		</ul>
	</#if>
</#macro>