<div class="row" style="margin-top: 100px;">
	<div class="col-xs-4"></div>
	<div class="col-xs-4">
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input id="username" type="text" class="form-control" placeholder="Username">
				</div>
				<div class="input-group top10">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-asterisk"></span>
					</span>
					<input id="password" type="password" class="form-control" placeholder="Password">
				</div>
				<div id="login-fail" class="alert alert-danger top10 hide" role="alert" style="margin-bottom: 0px;">Invalid Username or Password.</div>
				<button id="loginBtn" type="button" class="btn btn-primary col-xs-4 top10" style="float: right;">Login</button>
			</div>
		</div>
	</div>
</div>
<script>
	initLogin();
</script>