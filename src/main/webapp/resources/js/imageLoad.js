const images = [document.getElementById("image_1"), document.getElementById("image_2"), document.getElementById("image_3"), document.getElementById("image_4")]
const imgBodies = [document.getElementById("imgBody1"), document.getElementById("imgBody2"), document.getElementById("imgBody3"), document.getElementById("imgBody4")]
let imageLinks
let loadings = []

class Image
{
    constructor(imageLink)
    {
        let img = document.createElement('img')
        img.src = imageLink
        img.alt = 'Image'
        img.className = "object-cover w-full h-full rounded-lg transition-all duration-200 ease-in-out group-hover:opacity-70"
        img.width = '512'
        img.height = '512'
        img.style = "aspect-ratio: 512 / 512; object-fit: cover;"

        this.img = img
    }

    get()
    {
        return this.img
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

class LoadingImage
{
    constructor()
    {
        let div = document.createElement('div')
        div.className = "inline-flex absolute inset-0 bg-gray-200 rounded-lg items-center justify-center"

        let circleshome = document.createElement('div')
        circleshome.className = "h-10 w-20 items-center justify-center"

        this.circles = new LoadingCircles()

        circleshome.appendChild(this.circles.get())
        div.appendChild(circleshome)

        this.div = div
    }

    setOpacity(index, value)
    {
        this.circles.setOpacity(index, value)
    }

    get()
    {
        return this.div
    }
}

async function loadingAiChat(circles)
{
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

async function sleep(milsec) {
    return new Promise(resolve => setTimeout(resolve, milsec))
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

function onImagePageLoaded()
{
    for(var i = 0; i < 4; i ++)
    {
        loadings[i] = new LoadingImage()
        loadingAiChat(loadings[i].circles)

        images[i].appendChild(loadings[i].get())
    }

    makeImageRequest()
}

function makeImageRequest() {
    $.ajax({
        url: "/aimage/image/getResponse",
        type: "GET",
        success: function (data) {
            imageLinks = data
            console.log(data)
            showImage()
        }
    })
}

function showImage()
{
    var highestIntervalId = setInterval(";");
    for (var i = 0 ; i < highestIntervalId ; i++)
    {
        clearInterval(i);
    }

    for(var i = 0; i < 4; i ++)
    {
        images[i].removeChild(loadings[i].get())
        imgBodies[i].src = imageLinks['img_' + i]
    }
}

$(document).ready(onImagePageLoaded())



