<#include "init.ftl">

<#assign tempVarName = stringUtil.replace(name, ".", "_") />

<#if stringUtil.equals(language, "ftl")>
${r"<#assign"} ${tempVarName}_Data = getterUtil.getString(${variableName})>

${r"<#if"} validator.isNotNull(${tempVarName}_Data)>
	${r"<#assign"} ${tempVarName}_DateObj = dateUtil.parseDate("yyyy-MM-dd", ${tempVarName}_Data, locale)>

	${r"${"}dateUtil.getDate(${tempVarName}_DateObj, "dd MMM yyyy - HH:mm:ss", locale)}
${r"</#if>"}
<#else>
#set ($${tempVarName}_Data = $getterUtil.getString($${variableName}))

#if ($validator.isNotNull($${tempVarName}_Data))
	#set ($${tempVarName}_DateObj = $dateUtil.parseDate("yyyy-MM-dd",$${tempVarName}_Data, $locale))

	$dateUtil.getDate($${tempVarName}_DateObj, "dd MMM yyyy - HH:mm:ss", $locale)
#end
</#if>