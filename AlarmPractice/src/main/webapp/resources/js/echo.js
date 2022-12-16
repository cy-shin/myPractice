console.log("echo");

console.log("소켓 변수 선언");
let sock;
sock = new SockJS("/echo");

// 소켓에서 수신
document.addEventListener("DOMContentLoaded", ()=>{
    console.log("DOM Loaded");

    
    console.log("/echo랑 연결");

})

sock.onmessage = function(e) {
    const alarmBox = document.getElementById("alarmBox");
    console.log(e.data)
    if(alarmBox != null) {
        alarmBox.innerText = e.data;
    }
}

// 소켓에 전송
const clickButton = document.getElementById("clickButton");

if(clickButton != null) {
    clickButton.addEventListener("click", () => {
        console.log("전송하기 누름");
        const text = document.getElementById("sendMessageBox").value;
        sock.send("관리자 : "+ text);
    })
}






