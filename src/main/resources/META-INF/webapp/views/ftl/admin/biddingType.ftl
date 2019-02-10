<!DOCTYPE html>
<html>
<body>

<#if biddingType?has_content>
	<form action="/admin/biddingType/update" method="POST">
		<h3> Current Bidding Type</h3>
		<select name="biddingType">
			<#list biddingTypeValues as biddingTypeValue>
				<#if biddingType.biddingType == biddingTypeValue>
					<option value="${biddingTypeValue.name()}" selected>${biddingTypeValue.description}</option>
				<#else>
	  				<option value="${biddingTypeValue.name()}">${biddingTypeValue.description}</option>
	  			</#if>
	  		</#list>
		</select>
		<input type="hidden" name="biddingId" value="${biddingType.id}" />
		<br />
		<br />
	<input type="submit" value="Update">
</form> 
<#else>
	<a href="/admin/biddingType/add">Add Bidding Type Here</a>
</#if>

</body>
</html>
