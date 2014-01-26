var tl, resizeTimerID = null;
var meetMeTheme = Timeline.ClassicTheme.create();
meetMeTheme.firstDayOfWeek = 1;
meetMeTheme.mouseWheel = 'zoom';
meetMeTheme.event.track.height = 30;
meetMeTheme.event.track.gap = 10;
meetMeTheme.event.tape.height = 15;
var user;
var pagesCache = {};
var debug = true;

var pages = {
    main: {
        name: '#',
        url: 'main.html',
        todo: function (data) {
            $('#view').html(data);
            populateTimeline();
            $('.startButton').on('click', function () {
                console.log('on start');
                $.get('api/userinfo', function(userinfo){
                    console.log('userinfo:');
                    console.log(userinfo);
                    if (userinfo.loggedIn) {
                        user = userinfo;
                        navigate('meeting');
                    } else {
                        navigate('login');
                    }
                });
            });
        }
    },
    login: {
        name: '#login',
        url: 'login.html',
        todo: function (data) {
            $('#view').html(data);
            $.get('api/providers', function(providers){
                var box = $('#loginProviders');
                $.each(providers, function(index, item){
                    var rec = $('<li class="list-group-item">'+item.adapterInfo.name+'<span class="login"><a href="'+item.adapterInfo.ouathUrl+'"><img src="'+item.adapterInfo.loginButtonUrl+'"></a></li>');
                    box.append(rec);
                });
            });
        }
    },
    meeting: {
        name: '#meeting',
        url: 'meeting.html',
        todo: function (data) {
            $('#view').html(data);
            $('#tab a').click(function (e) {
                e.preventDefault()
                $(this).tab('show')
            });
        }
    }
};

function navigate(page) {
    console.log("navigate to:");
    console.log(page);
    var item = pages[page];
    console.log(item);
    var todo = function (data) {
        var l = window.location;
        var protocol = l.protocol;
        var host = l.hostname;
        var port = l.port;
        var path = l.pathname;
        window.location = protocol+'//'+host+':'+port+path+item.name;
        item.todo(data);
    };
    var data = pagesCache[page];
    if (data && !debug) {
        todo(data);
    } else {
        $.get(item.url, todo);
    }
}


$(document).ready(function () {
    console.log("MeetMe::onLoad");
    navigate('main');
});

$(window).resize(function () {
    if (resizeTimerID == null) {
        resizeTimerID = window.setTimeout(function () {
            resizeTimerID = null;
            tl.layout();
        }, 500);
    }
});

function populateTimeline() {
    var eventSource = new Timeline.DefaultEventSource(0);
    var bandInfos = [
        Timeline.createBandInfo({
            theme: meetMeTheme,
            eventSource: eventSource,
            date: "Jun 29 2013 15:00:00 GMT",
            width: "70%",
            intervalUnit: Timeline.DateTime.DAY,
            intervalPixels: 100,
            zoomIndex: 10,
            zoomSteps: [
                {pixelsPerInterval: 280, unit: Timeline.DateTime.HOUR},
                {pixelsPerInterval: 140, unit: Timeline.DateTime.HOUR},
                {pixelsPerInterval: 70, unit: Timeline.DateTime.HOUR},
                {pixelsPerInterval: 35, unit: Timeline.DateTime.HOUR},
                {pixelsPerInterval: 400, unit: Timeline.DateTime.DAY},
                {pixelsPerInterval: 200, unit: Timeline.DateTime.DAY},
                {pixelsPerInterval: 100, unit: Timeline.DateTime.DAY},
                {pixelsPerInterval: 50, unit: Timeline.DateTime.DAY}
            ]
        }),
        Timeline.createBandInfo({
            overview: true,
            eventSource: eventSource,
            date: "Jun 29 2013 15:00:00 GMT",
            width: "15%",
            intervalUnit: Timeline.DateTime.MONTH,
            intervalPixels: 100
        }),
        Timeline.createBandInfo({
            overview: true,
            eventSource: eventSource,
            date: "Jun 29 2013 15:00:00 GMT",
            width: "15%",
            intervalUnit: Timeline.DateTime.YEAR,
            intervalPixels: 200
        })
    ];
    bandInfos[1].syncWith = 0;
    bandInfos[1].highlight = true;
    bandInfos[2].syncWith = 0;
    bandInfos[2].highlight = true;
    tl = Timeline.create(document.getElementById("my-timeline"), bandInfos);
    var json = {
        'dateTimeFormat': 'Gregorian',
        'wikiURL': "http://simile.mit.edu/shelf/",
        'wikiSection': "Simile Cubism Timeline",

        'events': [

            {'start': 'Jun 28 2013 14:00:00 GMT',
                'end': 'Jun 28 2013 21:00:00 GMT',
                'title': 'Винни'
//                'description': 'by Kasimir Malevich, Ukrainian Painter, 1878-1935',
//                'image': 'http://images.allposters.com/images/BRGPOD/75857_b.jpg',
//                'link': 'http://www.allposters.com/-sp/Three-Figures-1913-28-Posters_i1349989_.htm'
            },

            {'start': 'Jun 28 2013 00:00:00 GMT',
                'end': 'Jun 29 2013 00:00:00 GMT',
                'title': 'Ослик',
//                'description': 'by Albert Gleizes, French Painter, 1881-1953',
//                'image': 'http://images.allposters.com/images/mer/1336_b.jpg',
//                'link': 'http://www.allposters.com/-sp/Landschaft-bei-Montreuil-Posters_i339007_.htm',
                //'isDuration' : true,
                'color': 'red',
                'textColor': 'green'},

            {'start': 'Jun 28 2013 08:00:00 GMT',
                'end': 'Jun 28 2013 10:00:00 GMT',
                'title': 'Сова',
//                'description': 'by Georges Braque, French Painter, 1882-1963',
//                'image': 'http://images.allposters.com/images/SHD/S1041_b.jpg',
//                'link': 'http://www.allposters.com/-sp/Jour-Posters_i126663_.htm',
                'color': 'green'
            },

            {'start': 'Jun 28 2013 20:00:00 GMT',
                'end': 'Jun 28 2013 22:00:00 GMT',
                'title': 'Пятачок'
//                'description': 'by Roger de la Fresnaye, French Painter, 1885-1925',
//                'image': 'http://images.allposters.com/images/CORPOD/IX001463_b.jpg',
//                'link': 'http://www.allposters.com/-sp/Castor-Et-Pollux-Posters_i831718_.htm',
//                'tapeImage': 'blue_stripes.png',
//                'tapeRepeat': 'repeat-x',
//                'caption': "This is the event's caption attribute.",
//                'classname': 'hot_event'
            }
        ]
    };
    eventSource.loadJSON(json, "js/events.js");
}
