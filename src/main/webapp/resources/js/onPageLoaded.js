function onPageLoaded()
{
    let circles = new LoadingCircles()
    let aiChat = new AiChat()
    loadingAiChat(aiChat, circles)

    chatForm.appendChild(aiChat.chat())

    getFirstResponse(aiChat)
}

function getFirstResponse(aiChat)
{
    $.ajax({
        url: "/aimage/chat/getFirstResponse",
        type: "GET",
        success: function (data)
        {
            console.log(data)
            
            aiChat.remove()
            aiChat.message(data)
        }
    })
}

$(document).ready(onPageLoaded())