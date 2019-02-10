<!DOCTYPE html>
<html>
<body>

<h2>Add Bidding Type</h2>

<form action="/admin/biddingType/add" method="POST">
	Bidding Type:<br>
	<br />
	<select name="biddingType">
		<#list biddingTypeValues as biddingTypeValue>
  			<option value="${biddingTypeValue.name()}">${biddingTypeValue.description}</option>
  		</#list>
	</select>
	<input type="submit" value="Submit">
</form> 


</body>
</html>
