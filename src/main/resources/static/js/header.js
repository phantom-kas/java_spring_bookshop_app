const headerHtml = `
<div class="nav">
  <input type="checkbox" id="nav-check">
  <div class="nav-header">
    <a href='/' class="nav-title">
      Book Store
    </a>
  </div>
  <div class="nav-btn">
    <label for="nav-check">
      <span></span>
      <span></span>
      <span></span>
    </label>
  </div>
  
  <div class="nav-links">
    <a href="/create_book" >Add book</a>
    <a href="/books">View All book</a>
    <a href="/books?category=3">Sic-fi</a>
    <a href="/books?category=1">Comedy</a>
    <a href="/books?category=2">Romance</a>
    <a href="/books?category=4">Biography</a>
    <a href="/login">Login</a>
    
  </div>
</div>
<div id='logger'></div>
`;

const headerStyle = `
<style>
header .nav {
  height: 50px;
  width: 100%;
  background-color: #4d4d4d;
  position: relative;
  z-index:10000;
}

header .nav > .nav-header {
  display: inline;
}

header .nav > .nav-header > .nav-title {
  display: inline-block;
  font-size: 22px;
  color: #fff;
  padding: 10px 10px 10px 10px;
}

header .nav > .nav-btn {
  display: none;
}

header .nav > .nav-links {
  display: inline;
  float: right;
  font-size: 18px;
}

header .nav > .nav-links > a {
  display: inline-block;
  padding: 13px 10px 13px 10px;
  text-decoration: none;
  color: #efefef;
}

header .nav > .nav-links > a:hover {
  background-color: rgba(0, 0, 0, 0.3);
}

header .nav > #nav-check {
  display: none;
}

@media (max-width:800px) {
  header .nav > .nav-btn {
    display: inline-block;
    position: absolute;
    right: 0px;
    top: 0px;
  }
  header .nav > .nav-btn > label {
    display: inline-block;
    width: 50px;
    height: 50px;
    padding: 13px;
  }
  header .nav > .nav-btn > label:hover,.nav  #nav-check:checked ~ .nav-btn > label {
    background-color: rgba(0, 0, 0, 0.3);
  }
  header .nav > .nav-btn > label > span {
    display: block;
    width: 25px;
    height: 10px;
    border-top: 2px solid #eee;
  }
  header .nav > .nav-links {
    position: absolute;
    display: block;
    width: 100%;
    background-color: #333;
    height: 0px;
    transition: all 0.3s ease-in;
    overflow-y: hidden;
    top: 50px;
    left: 0px;
  }
  header .nav > .nav-links > a {
    display: block;
    width: 100%;
  }
  header .nav > #nav-check:not(:checked) ~ .nav-links {
    height: 0px;
  }
  header .nav > #nav-check:checked ~ .nav-links {
    height: calc(100vh - 50px);
    overflow-y: auto;
  }
}
</style>
`;
const headerJs = `
<script>

</script>
`



const header = `${headerStyle} ${headerHtml} ${headerJs}`
// function test(){
// 	var tabsNewAnim = $('#navbarSupportedContent');
// 	var selectorNewAnim = $('#navbarSupportedContent').find('li').length;
// 	var activeItemNewAnim = tabsNewAnim.find('.active');
// 	var activeWidthNewAnimHeight = activeItemNewAnim.innerHeight();
// 	var activeWidthNewAnimWidth = activeItemNewAnim.innerWidth();
// 	var itemPosNewAnimTop = activeItemNewAnim.position();
// 	var itemPosNewAnimLeft = activeItemNewAnim.position();
// 	$(".hori-selector").css({
// 		"top":itemPosNewAnimTop.top + "px", 
// 		"left":itemPosNewAnimLeft.left + "px",
// 		"height": activeWidthNewAnimHeight + "px",
// 		"width": activeWidthNewAnimWidth + "px"
// 	});
// 	$("#navbarSupportedContent").on("click","li",function(e){
// 		$('#navbarSupportedContent ul li').removeClass("active");
// 		$(this).addClass('active');
// 		var activeWidthNewAnimHeight = $(this).innerHeight();
// 		var activeWidthNewAnimWidth = $(this).innerWidth();
// 		var itemPosNewAnimTop = $(this).position();
// 		var itemPosNewAnimLeft = $(this).position();
// 		$(".hori-selector").css({
// 			"top":itemPosNewAnimTop.top + "px", 
// 			"left":itemPosNewAnimLeft.left + "px",
// 			"height": activeWidthNewAnimHeight + "px",
// 			"width": activeWidthNewAnimWidth + "px"
// 		});
// 	});
// }
// $(document).ready(function(){
// 	setTimeout(function(){ test(); });
// });
// $(window).on('resize', function(){
// 	setTimeout(function(){ test(); }, 500);
// });


// $(".navbar-toggler").click(function(){
// 	$(".navbar-collapse").slideToggle(300);
// 	setTimeout(function(){ test(); });
// });




// // --------------add active class-on another-page move----------
// jQuery(document).ready(function($){
// 	// Get current path and find target link
// 	var path = window.location.pathname.split("/").pop();

// 	// Account for home page with empty path
// 	if ( path == '' ) {
// 		path = 'index.html';
// 	}
// //alert('path: ' + path);
// 	var target = $('#navbarSupportedContent ul li a[href="'+path+'"]');
// 	// Add active class to target link
// 	target.parent().addClass('active');
// });

const setHeader = () =>{
   document.getElementById('header').innerHTML = header;
  // alert('setting header');
  }


  setHeader();


