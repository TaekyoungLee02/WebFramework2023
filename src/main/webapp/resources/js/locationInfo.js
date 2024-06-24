var weather

function getLocation() {
    if ("geolocation" in navigator) {	/* geolocation 사용 가능 */
        navigator.geolocation.getCurrentPosition(function (data) {
            var latitude = data.coords.latitude;
            var longitude = data.coords.longitude;

            $.ajax({
                url: "/aimage/getLoc",
                data: { "lati": latitude, "longi": longitude },
                type: "POST",
                success: function (data) {
                    console.log(data)
                    weather = data
                }
            })

        }, function (error) {
            console.log(error)
        }, {
            enableHighAccuracy: true,
            timeout: Infinity,
            maximumAge: 0
        })
    } else {	/* geolocation 사용 불가능 */
        console.log("geolocation is not available")
    }
}

$(document).ready(getLocation());