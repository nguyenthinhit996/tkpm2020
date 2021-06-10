import firebase from 'firebase/app'
import 'firebase/storage'
import 'firebase/analytics'
 
  // Your web app's Firebase configuration
  // For Firebase JS SDK v7.20.0 and later, measurementId is optional
  var firebaseConfig = {
    apiKey: "AIzaSyCR61PGoDH4vznpAaF2gth4h4sRbxX3dgE",
    authDomain: "readmepeter.firebaseapp.com",
    databaseURL: "https://readmepeter.firebaseio.com",
    projectId: "readmepeter",
    storageBucket: "readmepeter.appspot.com",
    messagingSenderId: "305907811778",
    appId: "1:305907811778:web:518dc4b2662ff9da13c25b",
    measurementId: "G-EK576H3VQV"
  };
  // {/* // Initialize Firebase */}
  firebase.initializeApp(firebaseConfig);
  firebase.analytics();
  const storage = firebase.storage();
  export  {
    storage, firebase as default
  }