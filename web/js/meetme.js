var tl, resizeTimerID = null;
var meetMeTheme = Timeline.ClassicTheme.create();
meetMeTheme.firstDayOfWeek = 1;
meetMeTheme.mouseWheel = 'zoom';
meetMeTheme.event.track.height = 30;
meetMeTheme.event.track.gap = 10;
meetMeTheme.event.tape.height = 15;


$(document).ready(function () {
    console.log("MeetMe::onLoad");
    var eventSource = new Timeline.DefaultEventSource(0);
    var bandInfos = [
        Timeline.createBandInfo({
            theme: meetMeTheme,
            eventSource: eventSource,
            date: "Jun 28 2006 00:00:00 GMT",
            width: "60%",
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
            date: "Jun 28 2006 00:00:00 GMT",
            width: "20%",
            intervalUnit: Timeline.DateTime.MONTH,
            intervalPixels: 100
        }),
        Timeline.createBandInfo({
            overview: true,
            eventSource: eventSource,
            date: "Jun 28 2006 00:00:00 GMT",
            width: "20%",
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

            {'start': 'Jun 20 2006 16:00:00 GMT',
                'end': 'Jun 20 2006 21:00:00 GMT',
                'title': 'Андрейко',
                'description': 'by Kasimir Malevich, Ukrainian Painter, 1878-1935',
                'image': 'http://images.allposters.com/images/BRGPOD/75857_b.jpg',
                'link': 'http://www.allposters.com/-sp/Three-Figures-1913-28-Posters_i1349989_.htm'
            },

            {'start': 'Jun 28 2006 00:00:00 GMT',
                'end': 'Jun 29 2006 00:00:00 GMT',
                'title': 'Никитка',
                'description': 'by Albert Gleizes, French Painter, 1881-1953',
                'image': 'http://images.allposters.com/images/mer/1336_b.jpg',
                'link': 'http://www.allposters.com/-sp/Landschaft-bei-Montreuil-Posters_i339007_.htm',
                //'isDuration' : true,
                'color': 'red',
                'textColor': 'green'},

            {'start': 'Jun 20 2006 00:00:00 GMT',
                'end': 'Jun 30 2006 00:00:00 GMT',
                'title': 'Бакдафакапка',
                'description': 'by Georges Braque, French Painter, 1882-1963',
                'image': 'http://images.allposters.com/images/SHD/S1041_b.jpg',
                'link': 'http://www.allposters.com/-sp/Jour-Posters_i126663_.htm',
                'color': 'green'
            },

            {'start': 'Jun 29 2006 20:00:00 GMT',
                'end': 'Jun 29 2006 22:00:00 GMT',
                'title': 'Фликус',
                'description': 'by Roger de la Fresnaye, French Painter, 1885-1925',
                'image': 'http://images.allposters.com/images/CORPOD/IX001463_b.jpg',
                'link': 'http://www.allposters.com/-sp/Castor-Et-Pollux-Posters_i831718_.htm',
                'tapeImage': 'blue_stripes.png',
                'tapeRepeat': 'repeat-x',
                'caption': "This is the event's caption attribute.",
                'classname': 'hot_event'
            }
        ]
    };
    eventSource.loadJSON(json, "js/events.js");
});
$(window).resize(function () {
    if (resizeTimerID == null) {
        resizeTimerID = window.setTimeout(function () {
            resizeTimerID = null;
            tl.layout();
        }, 500);
    }
});
