<!DOCTYPE html>
<html>
<body>

<h2>List of Advertisements</h2>

<table border="1">
	<tr>
		<th>
			Message
		</th>
		<th>
			Target Age
		</th>
		<th>
			Target Gender
		</th>
		<th>
			Bid
		</th>
		<th>
			Budget
		</th>
		<th>
			Advertiser
		</th>
	</tr>
	<#list advertisementList as advertisement>
		<tr>
			<td>
				${advertisement.message}
			</td>
			<td>
				${advertisement.startAge}-${advertisement.endAge}
			</td>
			
			<td>
				${advertisement.gender.description}
			</td>
			<td>
				${advertisement.bid?c}
			</td>
			<td>
				${advertisement.budget?c}
			</td>
			<td>
				${advertisement.advertiser.name!}
			</td>
		</tr>
	</#list>

</table>


</body>
</html>
