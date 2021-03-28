
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

	
	<!-- Welcome Message -->
	<div>
		<div class="float-left">
			<div class="left-centered">
				<h1 id="title-welcome"> <b>Repair Shop Name</b> </h1>
				<h3 id="body-welcome"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus 
mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. </h3>
			</div>
		</div>
		<div class="float-right">
			<img src="../../assets/landing-hero.png" />
		</div>
	</div>

	<!-- About Us -->
	<div> 
		<div class="float-left">
			<img  class="img-about" src="../../assets/paint job 1.jpg"/>		
		</div>
		<div class="float-right">
			<div class="right-centered">
			<h1 id="title-about"> <b> About Us </b> </h1>
			<h3 id="body-about"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus 
mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. </h3>
			</div>
		</div>
	</div>

	<!-- Service -->
	<div> 
		<!--<div class="float-right">
			<img class="img-services" src="../../assets/towing.png"/>
		</div> -->
		<div class="float-left">
			<div class="left-centered-service">
			    <h1 id="title-about"> <b> Services  </b> </h1>
			    <h3 id="body-services">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus  </h3>
		       <h3 id="body-services"> mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. </h3>
		    </div>
		</div>
		<div class="float-right">
			<img class="img-services"  src="../../assets/towing.png"/>
		</div>
	</div>






	<!--<div class="welcome">
		<img class ="img-welcome" src="../../assets/landing-hero.png" />
		<div class="left-centered">
				<h1 id="title-welcome"> <b>Repair Shop Name</b> </h1>
				<h3 id="body-welcome"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus 
mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. </h3>
			</div>
	
	 LANDING PAGE WELCOME MESSAGE -->
	<!--<div class="container-1">
		<img class ="img-welcome" src="../../assets/landing-hero.png" />
			<div class="left-centered">
				<h1 id="title"> <b>Repair Shop Name</b> </h1>
				<h3 id="body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus 
mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. </h3>
			</div>
			
	</div>-->
	
	<!--</div>
	<div class="about">
		<img src="../../assets/paint job.jpg"/> 
		<div class="a-container">
			<div class="right-centered-welcome">
			<h1 id="about-title"> About Us </h1>
			<h3> body </h3>
			</div>
			<img src="../../assets/paint job.jpg"/>
		</div>
	</div>
	<div>
		<h1> Services </h1>
	</div>
	<div>
		<h1> Opening Hours </h1>
	</div>
	<div>
		<h1> Contact Us </h1>
	</div>
	<div>
		<h1> Footer </h1>
	</div>
-->

	
</div>
</template>


<script>
export default {
	name:'landing',
}
</script>

<style scoped>

	/** NAV- BAR CSS */
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

	/* ///////////////////////// */
	/** WELCOME CSS */
	/* ///////////////////////// */
	.welcome{
 	 position: relative;
 	 text-align: center;
	  margin-bottom:20px;
	}
	.left-centered {	
		position: absolute;
		float:left;
  		top: 50%;
  		left: 50%;
		transform: translate(-600px, -20px);
  		
	}

	.img-welcome{
		float:right;
		justify-content: end;
		
	}

	#title-welcome{
		margin-bottom:10px;
		color:rgb(51 41 134);
		font-size:50px;
		font-family:Roboto;
	}

	#body-welcome{
		margin-top:10px;
		text-align:left;
		line-height: 30px;
		max-width:600px;
		font-size:18px;
		color: rgba(80, 95, 152, 1)
	}
	
	/* ///////////////////////// */
	/** ABOUT US CSS */
	/* ///////////////////////// */
	.img-about{
		margin-top:50px;
		max-height:400px;
		transform: translateY(700px);
	}
	
	.right-centered
	{
		position: absolute;
		float:right;
  		top: 50%;
  		left: 50%;
		transform: translateY(600px);
		text-align:  center;
		  
	}

	#title-about{
		margin-bottom:10px;
		color:rgb(51 41 134);
		font-size:36px;
		font-family:Roboto;
	}

	#body-about
	{
		margin-top:10px;
		text-align:left;
		line-height: 30px;
		max-width:600px;
		font-size:16px;
		color: rgba(80, 95, 152, 1)
	}

	/* ///////////////////////// */
	/** Service  CSS */
	/* ///////////////////////// */
	.img-services
	{
		margin-top:50px;
		max-height: 400px;
		float:right;
		transform: translate(900px,800px);
	}
     
	.left-centered-service
	{
		position: absolute;
		float:right;
  		top: 50%;
		left:10%;
		transform: translateY(1000px);
		text-align:  center;

	}

	#body-services
	{
		margin-top:10px;
		text-align:left;
		line-height: 30px;
		max-width:400px;
		font-size:16px;
		color: rgba(80, 95, 152, 1)
	}


	
</style>