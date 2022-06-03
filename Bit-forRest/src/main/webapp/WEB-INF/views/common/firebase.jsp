<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>

  <script src="https://www.gstatic.com/firebasejs/8.6.5/firebase-app.js"></script>
  <script src="https://www.gstatic.com/firebasejs/8.6.5/firebase-auth.js"></script>
  <script src="https://www.gstatic.com/firebasejs/8.6.5/firebase-firestore.js"></script>
  <script src="https://www.gstatic.com/firebasejs/8.6.5/firebase-storage.js"></script>
  <script src="https://www.gstatic.com/firebasejs/8.6.5/firebase-messaging.js"></script>  
  
  <script>
      var firebaseConfig = {
  		    apiKey: "AIzaSyAhJBes0hcVA79lL_GRWETlB_tJb37eWQk",
		    authDomain: "bit-project-runrunfunfun.firebaseapp.com",
		    projectId: "bit-project-runrunfunfun",
		    storageBucket: "bit-project-runrunfunfun.appspot.com",
		    messagingSenderId: "983824607774",
		    appId: "1:983824607774:web:839fd7b2b2c2f37f555c0f",
		    measurementId: "G-DF7YL14NV8"
		  };
      
      
      firebase.initializeApp(firebaseConfig) 
      
      const messaging = firebase.messaging();
      messaging.requestPermission()
      .then(function(){
          console.log("Have permission");
          console.log(messaging.getToken());
          return messaging.getToken();
      })
      .then(function(token){
          console.log(token);
      })
      .catch(function(arr){
          console.log("Error Occured");
      });
  </script>

  
</body>
</html>