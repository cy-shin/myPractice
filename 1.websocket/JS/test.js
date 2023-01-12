
document.getElementById('chkbx').addEventListener('click', (e)=>{
    if(e.target.checked) {
        console.log('체크댐')
    } else {
        console.log('안댐..')
    }
})