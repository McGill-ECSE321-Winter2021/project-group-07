<template>
<div>
	<nav class="navbar navbar-expand-sm navbar-fixed-top">
		<div class="navbar-header">
			<a class="navbar-brand" href="/">
				<img src="../../assets/admin.png" style="max-width:25px; max-height:25px" />
			</a>
		</div>

		<div class="collapse navbar-collapse justify-content-center">
  			<ul class="navbar-nav">
   			 	<li class="nav-item">
   			   		<a class="nav-link" href="#">About</a>
   				</li>
   				<li class="nav-item">
      				<a class="nav-link" href="#">Services</a>
    			</li>
    			<li class="nav-item">
     				<a class="nav-link" href="#">Contacts</a>
    			</li>
  			</ul>
		</div>

		<ul class="navbar-nav justify-content-end">
				<li class="nav-item">
					<b class="btn btn-primary nav-btn"> Log In </b>
				</li>
		</ul>

		
</nav>



	<div class = "container">
		<img  class ="img" src="../../assets/landing-hero.png" />
		<div class="left-centered">
			<h1 id="title"> <b>Repair Shop Name</b> </h1>
			<h3 id="body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus 
mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. </h3>
		</div>
	

	</div>
</div>
</template>


<script>
export default {
	name:'landing',
}
</script>

<style scoped>

	.btn-sucess
	{
		background-color: transparent;
		
	}
	.btn-primary{
		background-color:rgb(51 41 134) !important;
		border-color: rgb(51 41 134);
		color:white;
		font-size:14px;
		font-family: Roboto;
		text-align:top;
		width:80px;
		height:25px;
	
	}
	
	.btn btn-primary nav-btn, .btn .btn-sucess nav-btn{
		float:right;
		font-family: Roboto;
		font-size: 14px;
	}
	.nav-link{
		color:rgba(80, 95, 152, 1);
		font-family: Roboto;
		font-size: 14px;
	}
	.container {
 	 position: relative;
 	 text-align: center;
	}
	.left-centered {
  		position: absolute;
		text-align: center;
  		top: 50%;
  		left: 50%;
  		transform: translate(-100%, 80%);
	}

	#title{
		text-align:center;
		margin-bottom:10px;
		color:rgb(51 41 134);
		font-size:50px;
		font-family:Roboto;
	}

	#body{
		margin-top:10px;
		text-align:left;
		line-height: 30px;
		max-width:600px;
		font-size:18px;
		color: rgba(80, 95, 152, 1)
	}
	.img{
		position:absolute;
		overflow:hidden;
		margin-right: 0px;
		float:right;
		
	}
	
</style>