<!DOCTYPE html>
<html>
<body>

<h2>Add Advertisement</h2>

<form action="/admin/advertisement/add" method="POST">
	Advertisement Message:<br>
	<input type="text" name="message" value="">
	<br />
	Start Age:<br>
	<input type="number" name="startAge" value="">
	<br />
	End Age:<br>
	<input type="number" name="endAge" value="">
	<br />
	Gender:<br>
	<select name="gender">
		<#list genderList as gender>
  			<option value="${gender.name()}">${gender.description}</option>
  		</#list>
	</select>
	<br />
	Enter Bid:<br />
	<input type="number" name="bid" value="">
	<br />
	Enter Budget: <br />
	<input type="number" name="budget" value="">
	<input type="hidden" name="advertiserId" value="${advertiserId}" />
	<br />
	<input type="submit" value="Submit">
</form> 


</body>
</html>
