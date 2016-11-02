<div class="col-sm-12">
	${add_result}
	<% session.setAttribute("add_result", ""); %>
	<form method="post" action="AddUser" id="add_user">
		<section class="col-sm-8">
			<fieldset>
				<div class="form-group">
					<label for="" class="label label-info form-control">Role</label> <select
						name="role">
						<option value="ADMIN">ADMIN</option>
						<option value="TEACHER">TEACHER</option>
						<option value="STUDENT">STUDENT</option>
					</select>
				</div>
				<div class="form-group">
					<label class="label label-info form-control">Username</label><br>
					<input type="text" name="username" placeholder="Username"
						style="text-transform: uppercase" required="required">
				</div>
				<div class="form-group">
					<label class="label label-info form-control">Password</label><br>
					<input type="password" name="password" placeholder="Password"
						style="text-transform: none" required="required">
				</div>
				<div class="form-group">
					<label class="label label-info form-control">First Name</label><br>
					<input type="text" name="fname" placeholder="First name"
						style="text-transform: uppercase" required="required">
				</div>
				<div class="form-group">
					<label class="label label-info form-control">Last Name</label><br>
					<input type="text" name="lname" placeholder="Last name"
						style="text-transform: uppercase" required="required">
				</div>
				<div class="form-group">
					<button type="submit" class="btn-primary">Add User</button>
					<button type="reset"
						onclick="document.getElementById('add_user').reset();"
						class="btn-warning">Clear</button>
				</div>
			</fieldset>
		</section>
	</form>
</div>