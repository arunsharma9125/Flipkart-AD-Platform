<!DOCTYPE html>
<html>
<body>

<h2>List of Advertisers</h2>

<table border="1">
	<tr>
		<th>
			Advertiser Name
		</th>
		<th>
			Add Advertisement
		</th>
	</tr>
	<#list advertiserList as advertiser>
		<tr>
			<td>
				${advertiser.name}
			</td>
			<td>
				<a href="/admin/advertisement/add/${advertiser.id}">Add Advertisement</a>
			</td>
		</tr>
	</#list>

</table>


</body>
</html>
