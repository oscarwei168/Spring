$(document).ready(function(){
  $("#location6").on("click", function(e){
    e.preventDefault();
    var hrefval = $(this).attr("href");
    
    if(hrefval == "#participant") {
      var distance = $('#mainpage').css('right');
      
      if(distance == "auto" || distance == "0px") {
        $(this).addClass("open");
        openSidepage();
      } else {
        closeSidepage();
      }
    }
  }); // end click event handler
  

  $("#closebtn").on("click", function(e){
    e.preventDefault();
    closeSidepage();
  }); // end close button event handler

  function openSidepage() {
    $('#mainpage').animate({
      right: '350px'
    }, 400, 'easeOutBack'); 
  }
  
  function closeSidepage(){
    $("#navigation li a").removeClass("open");
    $('#mainpage').animate({
      right: '0px'
    }, 400, 'easeOutQuint');  
  }
}); 