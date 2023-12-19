// alert(message)
axios.defaults.baseURL = "http://localhost:8080";
let tkn;
const getAToken =  ()=>{
  return ''
}
axios.interceptors.request.use((req)=>{
  console.log(req)
  console.log('begiggfgfn loading')
if(tkn != undefined){
  req.headers.Authorization =
          "Bearer "+getAToken
}
if(req._load !== undefined){
  if(req._load){
    console.log('begin loading')
    startLoad()
   // window.alert(message)
    // const loader = useLoaderStore()
    // loader.start(); 

    //----loading_code_here
  }
}
return req;
})

let refresh = false;
let c= 0;
axios.interceptors.response.use(response => {
// Do something before response is sent
if(response.data ){
  if(response.config._load != undefined){
    if(response.config._load){
      console.log('end loading')
      stopLoad()
      
      //-----------end_load_-code
    //  const loader = useLoaderStore()
    //  loader.stop();
    }
  }
  if(Object.hasOwn(response.data , 'alerts')){
  //  const alerts = useAlertStore()
 //---------  alert_code_here
    // response.data.alerts.forEach(a => {
    //   alerts.addAlert(a.message ,a.status ,'s')
    // });

    multiLog(response.data.alerts)
  }
}


// check if its is loading and stop it on successfull response

return response;
}
,async function (error) {
  // check if its is loading and stop it on error response
  console.log(error)
  if(error.config._load != undefined){
    if(error.config._load){
      console.log('loadend on success');
      stopLoad()
      //-----------end_load_-code
    //  const loader = useLoaderStore()
    //  loader.stop();
    //   console.log('loadend on error');
    //   const loader = useLoaderStore()
    // loader.stop(); 
    }
  }
 
  // const originalRequest = error.config;
  // const user = useUserStore()
  // const router1 = router;
  // const alerts = useAlertStore()
  
if(window.location.pathname == 'login' ){
  // alerts.addAlert(error.response.data.message ,error.response.data.status ,'s')
  throw error;
}
//  else if(error.response.status === 401 && !refresh){

//  // window.alert(c + ' ' + refresh);
//     originalRequest._retry = true;

//    c = c + 1
 
   
//    refresh = true;
   
    
   
//      return axios({
//       url: "user/getNewToken",
//       method: "POST",
//       data: {
//         refresh_token: localStorage.getItem('rtoken')
//       },
     
//     }).finally(()=>{
     
//     // window.alert('1')
     
//     }).then(function(res){
//      // window.alert('2'+ res.request.responseURL)
//       // alerts.addAlert('New token generated ' + c,'info' ,'s')
     
      
      
//         return res
//     }).then(res =>{
//       user.SetTokens(res.data.refreshToken, res.data.accessToken)
//      // window.alert(res.data.accessToken)
//       axios.defaults.headers.common['Authorization'] = "Bearer " + res.data.accessToken    
//        originalRequest.headers.Authorization = axios.defaults.headers.common['Authorization'] 
//        refresh = false
//        return res
//       })
//       //.then((res)=>{
//         // if(res.status == 200 && !user.isAdmin){
       
       
//         //   const { getDateTime} = useDateStore();
  
//         //   if(!user.lastCheckPDate || user.lastCheckPDate != getDateTime() && !user.isAdmin){
//         //   return  axios.post('payments/check_all_overdue')
//         //     .then(res => {
//         //   //    window.alert('asda')
//         //       if(res.data.status == 'success'){user.lastCheckPDate = getDateTime()}
//         //       return
//         //       }
              
//         //     );
//         //   }
//         //   return res
//         //   }
//     //  })
//       .
//       then(() =>{
//      //   window.alert('d' + originalRequest.headers.Authorization)
//       return  axiosApiInstance(originalRequest);
//     })
//     .catch((error)=>{
//       router1.push({name:'login'})  
//    ///   console.log(error) 
//     //  window.alert(error.request.responseURL)
//       alerts.addAlert(error.response.data.message + c,error.response.data.status ,'s')     
//       return
//     })
      
   
//   }
  else if(error.response.status === 401){
    window.location.href = "/login";
    return
  }

  else if(error.response.status === 400){
    window.alert('Unknown requsest');
  }
    //    alerts.addAlert(error.response.data.message ,error.response.data.status ,'s')
    // }




  // else if(error.response.status == 402 ){
  //   const router1 = router;
  //   const alerts = useAlertStore()
  //   alerts.addAlert(error.response.data.message,error.response.data.status,'s');
  //   router1.push({path:"/active_payments"})
  //   return
  // }
  // else if(error.response.status === 400){
  //   alerts.addAlert(error.response.data.message ,error.response.data.status ,'s')
  // }
  // else {
  //   const alerts = useAlertStore()
  //   alerts.addAlert(error.response.data.message,error.response.data.status,'s');
  //   return error.response
  // }




 
  return Promise.reject(error);
}
)


