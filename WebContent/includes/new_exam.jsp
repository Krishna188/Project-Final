<div class="col-sm-12">
	<form method="POST" action="Schedule_Exam" id="new_exam">
		<section class="col-sm-8">
			<fieldset>
				<legend>Add Exam Details</legend>
				<!-- Display courses that is being taught by teacher or learned by students -->
				${courses} 
				<!-- Display all rooms available within organization  -->
				${rooms}
				<div class="form-group">
					<label class="label label-info form-control">Date</label><br>
					<input type="date" name="date" placeholder="YYYY-MM-DD"
						style="text-transform: uppercase" required="required"
						pattern="\d{4}-\d{2}-\d{2}">
				</div>
				<div class="form-group">
					<label class="label label-info form-control">Start Time</label><br>
					<input type="time" name="start_time" placeholder="HH:MM"
						style="text-transform: uppercase" required="required"
						pattern="^([01]?[0-9]|2[0-3]):[0-5][0-9]$">
				</div>
				<div class="form-group">
					<label class="label label-info form-control">End Time</label><br>
					<input type="time" name="end_time" placeholder="HH:MM"
						style="text-transform: uppercase" required="required"
						pattern="^([01]?[0-9]|2[0-3]):[0-5][0-9]$">
				</div>
				<div class="form-group">
					<button type="submit" class="btn-primary">Add to Schedule</button>
					<button type="reset"
						onclick="document.getElementById('new_exam').reset();"
						class="btn-warning">Clear</button>
				</div>
			</fieldset>
		</section>
	</form>
</div>