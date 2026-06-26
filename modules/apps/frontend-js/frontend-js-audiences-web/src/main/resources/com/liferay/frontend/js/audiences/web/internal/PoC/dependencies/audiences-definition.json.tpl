{
	"audiences": [
		{
			"conjunction": "AND",
			"id": "the_audience",
			"rules": [
				{
					"attribute": "browser_name",
					"operator": "eq",
					"value": "Firefox"
				}
			]
		},
		{
			"conjunction": "AND",
			"id": "signed_in_user",
			"rules": [
				{
					"attribute": "custom:/o/frontend-js-audiences-web/__liferay__/custom-attributes.js#signed_in",
					"operator": "eq",
					"value": true
				}
			]
		},
		{
			"conjunction": "AND",
			"id": "desktop_user",
			"rules": [
				{
					"attribute":
						"custom:/o/liferay-sample-audiences-custom-attributes/custom-attributes.9f1591bbef36426c91319ea8ecdf3533b4a1bd31.js#touchDevice",
					"operator": "eq",
					"value": false
				}
			]
		}
	]
}