<div class="col-sm-12">
	<form method="post" action="Add_room" id="add_room">
		<section class="col-sm-8">
			<fieldset>
				<div class="form-group">
					<label class="label label-info form-control">Room No</label><br>
					<input type="text" name="room_no" placeholder="Room No"
						style="text-transform: uppercase" required="required">
				</div>
				<div class="form-group">
					<label class="label label-info form-control">Type</label><br>
					<input type="text" name="type" placeholder="Type"
						style="text-transform: uppercase" required="required">
				</div>
			
				<div class="form-group">
					<button type="submit" class="btn-primary">Add Room</button>
					<button type="reset"
						onclick="document.getElementById('add_room').reset();"
						class="btn-warning">Clear</button>
				</div>
			</fieldset>
		</section>
	</form>
</div>