console.log("누르긴 했는데");

let socket;

document.addEventListener("DOMContentLoaded", ()=>{
        socket = new SockJS("/echo");

    socket.onmessage = onMessage();

    // 데이터를 전달받았을 때 토스트를 생성
})

// 메세지를 받았을 때
onMessage = function(e) {
    const msg = JSON.parse(e.data);
	console.log("dsjkskgjsalasblkbe");

    let data = e.data;
    const alarmBox = document.getElementById("alarmBox");
    alarmBox.innerText = data;
}

// socket.onmessage = function (event) {
//     console.log(event.data);
//   }

// 소켓에 전달할 때
const clickButton = document.getElementById("clickButton");

clickButton.addEventListener("click", () => {
    console.log("누르긴 했는데");
        const sendMessageText = document.getElementById("sendMessageBox").value;
        console.log(sendMessageText);
    sendMessage();
})

function sendMessage() {
    const sendMessageText = document.getElementById("sendMessageBox").value;
    console.log("sendMessage 실행은 됐는데");
    socket.send("관리자,"+sendMessageText);
    console.log(socket.send);

    socket.onmessage = onMessage;
}
