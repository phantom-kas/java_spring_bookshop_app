const loadingDiv = `
<div class=  'loading'>
<span class="loader_spin"></span> <h1>...loading </h1>
</div>
`;


const clearLogger = ()=>{
document.getElementById('logger').innerHTML ='';
}
const addAlert = (message ,status) => {

  document.getElementById('logger').innerHTML +=
  `<div class = ${status}>
  <h1>${message}</h1>
  </div>`;
  
}

const multiLog = (alerts)=>{

  clearLogger()
  alerts.forEach(a => {
      addAlert(a.message ,a.status )
      // window.alert(a.message)
    });
    document.documentElement.scrollTop = 0;
    document.body.scrollTop = 0;
}


const startLoad = () =>{
  document.getElementById('logger').innerHTML =loadingDiv
}
const stopLoad = () =>{
  document.getElementById('logger').innerHTML =""
}

const url = new URL(window.location.href);

const GET = (q) =>{
   return url.searchParams.get(q);
}


function getByid(idToFind ,arrayOfObjects) {
  return arrayOfObjects.find(obj => obj.id == idToFind);
}