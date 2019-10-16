<#include "init.ftl">

<#assign encodedName = stringUtil.replace(name, ".", "_")  />

<#if stringUtil.equals(language, "ftl")>
	${r"<#assign"} latitude = 0>
	${r"<#assign"} longitude = 0>

	${r"<#if"} (${variableName} != "")>
		${r"<#assign"} geolocationJSONObject = jsonFactoryUtil.createJSONObject(${variableName})>

		${r"<#assign"} latitude = geolocationJSONObject.getDouble("latitude")>
		${r"<#assign"} longitude = geolocationJSONObject.getDouble("longitude")>

		${r"<@liferay_map"}["map-display"]
			geolocation=true
			latitude=latitude
			longitude=longitude
			name="${encodedName}${r"${randomizer.nextInt()}"}"
		/>
	${r"</#if>"}
<#else>
	${getVariableReferenceCode(variableName)}
</#if>