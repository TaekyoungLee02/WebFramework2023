let responseLoaded = new CustomEvent("responseLoaded")
let responsedMessage = ""

async function sleep(milsec) {
return new Promise(resolve => setTimeout(resolve, milsec))
}

class MyChat
{
    constructor(message)
    {
        let myChat = document.createElement('div')
        myChat.className = "flex items-end justify-end space-x-2"

        let div = document.createElement('div')
        div.className = "p-2 bg-yellow-300 rounded-xl"

        let p = document.createElement('p')
        p.className = "text-sm"
        p.append(message)

        div.appendChild(p)
        myChat.appendChild(div)

        this.myChat = myChat
    }

    chat() {
        return this.myChat
    }
}

class AiChat
{
    constructor()
    {
        let aiChat = document.createElement('div')
        aiChat.className = "flex items-end space-x-2"

        let span = document.createElement('span')
        span.className = "relative flex shrink-0 overflow-hidden rounded-full w-8 h-8"

        let div = document.createElement('div')
        div.className = "p-2 bg-white rounded-xl"

        aiChat.appendChild(span)
        aiChat.appendChild(div)

        this.aiChat = aiChat
    }

    message(message)
    {
        let div = this.aiChat.children[1]

        let p = document.createElement('p')
        p.className = "text-sm"
        p.append(message)

        div.appendChild(p)
    }

    object(object)
    {
        let div = this.aiChat.children[1]
        div.appendChild(object)
    }

    remove()
    {
        let div = this.aiChat.children[1]
        div.replaceChildren()
    }

    chat()
    {
        return this.aiChat
    }
}

class Circle
{
    constructor()
    {
        let svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg')

        svg.setAttribute('width', '20')
        svg.setAttribute('height', '20')
        svg.setAttribute('fill', 'black')
        svg.setAttribute('className', 'bi bi-circle-fill')
        svg.setAttribute('viewBox', '0 0 20 20')

        let circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle')

        circle.setAttribute('cx', '10')
        circle.setAttribute('cy', '10')
        circle.setAttribute('r', '5')

        svg.appendChild(circle)

        this.svg = svg
        return this.svg
    }
}

class LoadingCircles
{
    constructor()
    {
        let circles = document.createElement('div')
        circles.className = "flex items-center"
        circles.id = 'circles'

        circles.appendChild(new Circle())
        circles.appendChild(new Circle())
        circles.appendChild(new Circle())

        this.circles = circles
    }

    setOpacity(index, value)
    {
        let circles = this.circles.children

        circles[index].setAttribute('opacity', value.toString())
    }

    get()
    {
        return this.circles
    }
}

class Button
{
    constructor(message)
    {
        let button = document.createElement('button')
        button.className = "inline-flex items-center justify-center text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 h-10 px-4 w-full py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600"
        button.setAttribute('onclick', "location.href='/aimage/image'")

        let svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg')
        svg.setAttribute('width', '24')
        svg.setAttribute('height', '24')
        svg.setAttribute('fill', 'none')
        svg.setAttribute('stroke', 'white')
        svg.setAttribute('stroke-width', '2')
        svg.setAttribute('stroke-linecap', 'round')
        svg.setAttribute('stroke-linejoin', 'round')
        svg.setAttribute('class', 'ml-2 h-4 w-4')
        svg.setAttribute('viewBox', '0 0 24 24')

        let path = document.createElementNS('http://www.w3.org/2000/svg', 'path')
        path.setAttribute('d', 'm12 5 7 7-7 7')
        svg.appendChild(path)

        button.append(message)
        button.appendChild(svg)

        this.button = button
    }

    get()
    {
        return this.button
    }
}

function makeMyChat(message)
{
    if(message.value == "") return
    
    appendMyChat(message)

    let circles = new LoadingCircles()
    let aiChat = new AiChat()
    loadingAiChat(aiChat, circles)
    chatForm.appendChild(aiChat.chat())
    makeChatRequest(message.value)
    message.value = ""

    document.addEventListener("responseLoaded", () => { makeAiChat(aiChat) }, { once: true })
}

function appendMyChat(message)
{
    if(message.value == "") return
    let myChat = new MyChat(message.value)
    chatForm.appendChild(myChat.chat())
}

async function loadingAiChat(aiChat, circles)
{
    aiChat.object(circles.get())

    circles.setOpacity(0, 0)
    circles.setOpacity(1, 0)
    circles.setOpacity(2, 0)

    circleOpacityUp(circles, 0)
    setInterval(circleOpacityUp, 3000, circles, 0)
    await sleep(300)
    circleOpacityUp(circles, 1)
    setInterval(circleOpacityUp, 3000, circles, 1)
    await sleep(300)
    circleOpacityUp(circles, 2)
    setInterval(circleOpacityUp, 3000, circles, 2)
    await sleep(700)

    circleOpacityDown(circles, 0)
    setInterval(circleOpacityDown, 3000, circles, 0)
    await sleep(300)
    circleOpacityDown(circles, 1)
   setInterval(circleOpacityDown, 3000, circles, 1)
    await sleep(300)
    circleOpacityDown(circles, 2)
    setInterval(circleOpacityDown, 3000, circles, 2)
}

async function circleOpacityUp(circles, index)
{
    for(var i = 0.0; i <= 1; i += 0.01)
    {
        circles.setOpacity(index, i)
        await sleep(10)
    }
}

async function circleOpacityDown(circles, index)
{
    for(var i = 1.0; i >= 0; i -= 0.01)
    {
        circles.setOpacity(index, i)
        await sleep(10)
    }
}

function makeChatRequest(message)
{
    console.log(message)

    $.ajax({
        url: "/aimage/chat/getResponse",
        data: {"message" : message},
        type: "POST",
        success: function (data)
        {
            console.log(data)
            responsedMessage = data
            document.dispatchEvent(responseLoaded)
        }
    })
}

function makeAiChat(aiChat)
{
    var highestIntervalId = setInterval(";");
    for (var i = 0 ; i < highestIntervalId ; i++)
    {
        clearInterval(i);
    }

    if(isEndResponse(responsedMessage))
    {
        responsedMessage = subEndChat(responsedMessage)
        onChatEnd()
    }

    aiChat.remove()
    aiChat.message(responsedMessage)

}

function isEndResponse(response)
{
    if (response.includes('End') || response.includes('end'))
        return true;
    else
        return false;
}

function subEndChat(response)
{
    return response.substr(0, response.length - 3)
}

function onChatEnd()
{
    let circles = new LoadingCircles()
    let aiChat = new AiChat()
    loadingAiChat(aiChat, circles)

    chatForm.appendChild(aiChat.chat())

    endRequest(aiChat)
}

function endRequest(aiChat)
{
    $.ajax({
        url: "/aimage/chat/getKeyWord",
        type: "GET",
        success: function (data)
        {
            console.log(data)

            let button = new Button('View Images')

            aiChat.remove()
            aiChat.object(button.get())
        }
    })
}