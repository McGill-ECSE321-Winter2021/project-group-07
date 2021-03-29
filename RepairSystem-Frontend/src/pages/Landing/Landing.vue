
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
					<a class="btn btn-primary nav-btn" href="/login"><b> Log In </b></a>
				</li>
		</ul>

		<ul class="navbar-nav justify-content-end">
				<li class="nav-item">
					<a class="btn btn-secondary nav-btn" href="/signup"><b> Sign Up </b></a>
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
		<div class="float-right">
			<img class="img-services" src="../../assets/towing.png"/>
		</div> 
		<div class="float-left">
			
			<div class="left-centered-service">
			    <h1 id="title-about"> <b> Services  </b> </h1>
			    <h3 id="body-services">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus  </h3>
		    	<!--	<div class="row">
				<div class="col-md-2">
					<img class="service-images" src="../../assets/landingPage/car-repairService.png"/>
					<p> Repairs </p>
				</div>
				<div class="col">
					<img class="service-images" src="../../assets/landingPage/car-wash.png"/>
				</div>
				<div class="col">
					<img class="service-images" src="../../assets/landingPage/inspection.png"/>
				</div>
				<div class="col">
					<img class="service-images" src="../../assets/landingPage/oil.png"/>
				</div>
				<div class="col">
					<img class="service-images" src="../../assets/landingPage/roadsideAssistance.png"/>
				</div>
				<div class="col">
					<img class="service-images" src="../../assets/landingPage/tire.png"/>
				</div>
				<div class="col">
					<img class="service-images" src="../../assets/landingPage/tow-truck.png"/>
				</div>
			</div> -->
		    </div>
		</div>	
	</div>

	<div >
		<div>
		<img  class= "rectangle" src="../../assets/landingPage/Rectangle.png"/>
		</div>
		<div class="footer">
			<div class = "float-right">
				<table class="table table-bordered">
  
  				<tbody>
    				<tr>
     					 <th scope="row">Monday</th>
     					 <td> 9:00AM- 9:00 PM</td>
   					</tr>
					<tr>
     					 <th scope="row">Tuesday</th>
     					 <td> 9:00AM- 9:00 PM</td>
   					</tr>
					<tr>
     					 <th scope="row">Wednesday</th>
     					 <td> 9:00AM- 9:00 PM</td>
   					</tr>
					<tr>
     					 <th scope="row">Thursdayy</th>
     					 <td> 9:00AM- 9:00 PM</td>
   					</tr>
					<tr>
     					 <th scope="row">Friday</th>
     					 <td> 9:00AM- 9:00 PM</td>
   					</tr>
					<tr>
     					 <th scope="row">Saturdayy</th>
     					 <td> 9:00AM- 9:00 PM</td>
   					</tr>
 					<tr>
     					 <th scope="row">Sunday</th>
     					 <td> 9:00AM- 9:00 PM</td>
   					</tr>   
    
 				 </tbody>
			</table>
			</div>
		</div>
	</div>
	<!-- Opening Hours 
	<div> 
		<div class="float-left">
			<img class="img-hours" src="../../assets/landingPage/openingHours.png"/>		
		</div>
		<div class="float-right">
			<div class="right-centered-hours">
			<h1 id="title-hours"> <b> Opening Hours</b> </h1>
		
			
			
			</div>
		</div>
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
	
	}
	.btn-secondary{
		background-color:rgb(51 41 134) !important;
		border-color: rgb(51 41 134);
		color:white;
		font-size:14px;
		font-family: Roboto;
		text-align:top;
	
	}
	
	.btn btn-primary nav-btn, .btn .btn-sucess nav-btn{
		float:right;
		font-family: Roboto;
		font-size: 14px;
	}
	.btn btn-secondary nav-btn, .btn .btn-sucess nav-btn{
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

	.service-images{
		max-height:30px;
	}


	/* ///////////////////////// */
	/** Opening Hours  CSS */
	/* ///////////////////////// */
	.rectangle{
	
 	
		width:100%;
		background-repeat: repeat;
		transform:translateY(1000px) 
	}
	.img-hours{
		max-height: 500px;
		transform: translate(100px, 1000px);
	}

	.right-centered-hours{
		position: absolute;
		float:right;
  		top: 50%;
  		left: 50%;
		transform: translateY(1500px);
		text-align:  center;
	}
	#title-hours{
		margin-bottom:10px;
		color:rgb(51 41 134);
		font-size:36px;
		font-family:Roboto;
	}
	
	.footer{
		height: 500px;
		background-color: rgb(231,236,255);
		transform:translateY(800px);
	}
</style>