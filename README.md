# APT Project
Repo Advanced Programming Topics Eindproject (groepsproject).\
Dit project bevat een aplicatie die gebruik maakt van microservices in Java Spring Boot, unit & integration tests, Dockerfiles, Kubernetes

Belangrijk:
- Een song heeft een ISRC code (handig: https://www.isrcfinder.com/)
- Een album heeft een MAID code (Music Album Identifier)
- Een artist heeft een MBID code (Music Band Identifier)

##Table of contents

- [Project](#project)
  - [Team](#team)
  - [Thema](#thema)
- [Services](#services)
  - [BRANK-EDGE-SERVICE](#brank-edge-service)
    - [Repository-link](https://github.com/RobinDeClerck/brank-edge-service)
    - [Okteto-link](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/)
  - [ARTIST-SERVICE](#brank-edge-service)
    - [Artist-service repository](https://github.com/RobinDeClerck/artist-service) |
      [Okteto](https://artist-service-server-robindeclerck.cloud.okteto.net) |
      [Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/artist-service)
  - [ALBUM-SERVICE](#album-service)
    - [Album-service repository](https://github.com/RobinDeClerck/album-service) |
      [Okteto](https://album-service-server-robindeclerck.cloud.okteto.net) |
      [Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/album-service)
  - [SONG-SERVICE](#song-service)
    - [Song-service repository](https://github.com/anthonydecap/service-song) |
      [Okteto](https://song-service-server-robindeclerck.cloud.okteto.net/) |
      [Dockerhub](https://hub.docker.com/r/realnigel/song-service)
  - [GENRE-SERVICE](#genre-service)
    - [Genre-service repository](https://github.com/JoNaulaerts/genre-service) |
      [Okteto](https://genre-service-server-robindeclerck.cloud.okteto.net/) |
      [Dockerhub](https://hub.docker.com/r/jonaulaerts/genre-service)
  - [FRONTEND](#frontend)
    - [Frontend repository](https://github.com/RobinDeClerck/music-frontend) |
      [Okteto](https://frontend-server-robindeclerck.cloud.okteto.net/) |
      [Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/music-frontend)
- [Okteto](#okteto)


##Project

### Team

- [Robin De Clerck](https://github.com/RobinDeClerck)
- [Jo Naulaerts](https://github.com/JoNaulaerts)
- [Anthony Decap](https://github.com/anthonydecap)

###Thema

Onze applicatie bestaat uit muziek waarbij we de song hebben opgedeeld in 4 verschillende services.
- Song
- Genre
- Album
- Artist
 
Al deze services werken samen om 1 grote applicatie te creëren.

##Services

###Brank-Edge-service

[Brank-Edge-service repository](https://github.com/RobinDeClerck/brank-edge-service) |
[Okteto](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/) |
[Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/brank-edge-service)

####Schema

![schema](https://cdn.discordapp.com/attachments/668890794882629662/928740542244880474/APT-Schema.png)

####SwaggerUI NEEDS UPDATE!!!

![swaggerui](https://cdn.discordapp.com/attachments/668890794882629662/928743967879745547/swaggerui.PNG)


####Postman requests

Open de request door te klikken op de titel.
<details><summary>GET /albums</summary>

Used: [https://brank-edge-service-server-robindeclerck.cloud.okteto.net/albums](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/albums)



</details>

<details><summary>GET /albums/{MAID}</summary>

Used: [https://brank-edge-service-server-robindeclerck.cloud.okteto.net/albums/dd7e7ced-a44d-4ce5-9654-c60a0d71fc51](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/albums/dd7e7ced-a44d-4ce5-9654-c60a0d71fc51) 

```json
{
    "name": "Typhoons",
    "image": "https://i.scdn.co/image/ab67616d00001e02712b9c0f9a8d380e26a95c1c",
    "genre": "Rock",
    "artist": {
        "id": "61d742963c4cd92feb017481",
        "name": "Royal Blood",
        "type": "Rock duo",
        "originCountry": "United Kingdom",
        "members": [
            "Mike Kerr",
            "Ben Thatcher"
        ],
        "bannerImage": "https://i.scdn.co/image/ab676186000010164ecf014fa786e9c5dfffe37c",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3"
    },
    "songs": [
        {
            "id": "61d742a0e3506c28e7334b5b",
            "genre": "Rock",
            "title": "Trouble’s Coming",
            "length": 228,
            "url": "6voIJ7OWwRabSZDC77D5Hp",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2000193"
        },
        {
            "id": "61d742a0e3506c28e7334b5c",
            "genre": "Rock",
            "title": "Oblivion",
            "length": 161,
            "url": "3Ye5icBka8ODjcaEQakPvZ",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2001120"
        },
        {
            "id": "61d742a0e3506c28e7334b5d",
            "genre": "Rock",
            "title": "Typhoons",
            "length": 236,
            "url": "5aFGo8wHEntVxFI8IF7Wuj",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2001121"
        },
        {
            "id": "61d742a0e3506c28e7334b5e",
            "genre": "Rock",
            "title": "Who Needs Friends",
            "length": 190,
            "url": "7AXoSHtReIvoJPi5XKXecl",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2001122"
        },
        {
            "id": "61d742a0e3506c28e7334b5f",
            "genre": "Rock",
            "title": "Million and One",
            "length": 258,
            "url": "7AXoSHtReIvoJPi5XKXecl",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2001123"
        },
        {
            "id": "61d742a0e3506c28e7334b60",
            "genre": "Rock",
            "title": "Limbo",
            "length": 293,
            "url": "1P8BrsNLHWO5R0cK6zvyhc",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2001124"
        },
        {
            "id": "61d742a0e3506c28e7334b61",
            "genre": "Rock",
            "title": "Either You Want It",
            "length": 180,
            "url": "1P8BrsNLHWO5R0cK6zvyhc",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2001125"
        },
        {
            "id": "61d742a0e3506c28e7334b62",
            "genre": "Rock",
            "title": "Boilermaker",
            "length": 209,
            "url": "27BEATf1JFhKDmwJdpGVSk",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2001126"
        },
        {
            "id": "61d742a0e3506c28e7334b63",
            "genre": "Rock",
            "title": "Mad Visions",
            "length": 189,
            "url": "3S66ufJ1RdjOKf2azjXWjI",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2001127"
        },
        {
            "id": "61d742a1e3506c28e7334b64",
            "genre": "Rock",
            "title": "Hold On",
            "length": 194,
            "url": "5rUGbardlhPNzbHH3qOEOk",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2001128"
        },
        {
            "id": "61d742a1e3506c28e7334b65",
            "genre": "Rock",
            "title": "All We Have Is Now",
            "length": 213,
            "url": "4CUyNgMxAFKFEf1KrbAEbY",
            "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
            "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
            "isrc": "GBAHT2001129"
        }
    ],
    "release": "2021-04-30",
    "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51"
}
```

</details>

<details><summary>GET /genres</summary>

Used: [https://brank-edge-service-server-robindeclerck.cloud.okteto.net/genres](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/genres)

```json
[
  {
    "id": "61d7429ce40c25722ecaf15f",
    "genreName": "Rock",
    "description": "Rock music is a broad genre of popular music that originated as \"rock and roll\" in the United States in the late 1940s and early 1950s, developing into a range of different styles in the mid-1960s and later, particularly in the United States and the United Kingdom."
  },
  {
    "id": "61d7429ce40c25722ecaf160",
    "genreName": "Heavy Metal",
    "description": "Heavy metal (or simply metal) is a genre of rock music that developed in the late 1960s and early 1970s, largely in the United Kingdom and the United States."
  },
  {
    "id": "61d7429de40c25722ecaf161",
    "genreName": "Pop",
    "description": "Pop is a genre of popular music that originated in its modern form during the mid-1950s in the United States and the United Kingdom."
  },
  {
    "id": "61d7429de40c25722ecaf162",
    "genreName": "Blues",
    "description": "Blues is a music genre and musical form which was originated in the Deep South of the United States around the 1860s by African-Americans from roots in African-American work songs and spirituals."
  },
  {
    "id": "61d7429de40c25722ecaf163",
    "genreName": "Punk rock",
    "description": "Punk rock (or simply punk) is a music genre that emerged in the mid-1970s. Rooted in 1960s garage rock, punk bands rejected the perceived excesses of mainstream 1970s rock."
  },
  {
    "id": "61d7429de40c25722ecaf164",
    "genreName": "Jazz",
    "description": "Jazz is a music genre that originated in the African-American communities of New Orleans, Louisiana, United States, in the late 19th and early 20th centuries, with its roots in blues and ragtime."
  },
  {
    "id": "61d7429de40c25722ecaf165",
    "genreName": "Grunge",
    "description": "Grunge (sometimes referred to as the Seattle sound) is an alternative rock genre and subculture that emerged during the mid-1980s in the American Pacific Northwest state of Washington, particularly in Seattle and nearby towns."
  },
  {
    "id": "61d7429de40c25722ecaf166",
    "genreName": "New wave",
    "description": "New wave is a broad music genre that encompasses numerous pop-oriented styles from the late 1970s and the 1980s. It was originally used as a catch-all for the music that emerged after punk rock, including punk itself, but may be viewed retrospectively as a more accessible counterpart of post-punk."
  }
]
```

</details>
<details><summary>GET /genres/{genreName}</summary>

Used: [https://brank-edge-service-server-robindeclerck.cloud.okteto.net/genres/Rock](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/genres/Rock)

```json
{
  "id": null,
  "genreName": "Rock",
  "description": "Rock music is a broad genre of popular music that originated as \"rock and roll\" in the United States in the late 1940s and early 1950s, developing into a range of different styles in the mid-1960s and later, particularly in the United States and the United Kingdom."
}
```

</details>
<details><summary>GET /artists</summary>

```json
[
  {
    "id": "61d742963c4cd92feb017480",
    "name": "The Police",
    "type": "Rock band",
    "originCountry": "United Kingdom",
    "members": [
      "Sting",
      "Stewart Copeland",
      "Andy Summers",
      "Henry Padovani"
    ],
    "bannerImage": "https://i.scdn.co/image/ab67618600001016af496a5f2377f1149d2a5cf3",
    "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a"
  },
  {
    "id": "61d742963c4cd92feb017481",
    "name": "Royal Blood",
    "type": "Rock duo",
    "originCountry": "United Kingdom",
    "members": [
      "Mike Kerr",
      "Ben Thatcher"
    ],
    "bannerImage": "https://i.scdn.co/image/ab676186000010164ecf014fa786e9c5dfffe37c",
    "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3"
  },
  {
    "id": "61d742963c4cd92feb017482",
    "name": "Muse",
    "type": "Rock band",
    "originCountry": "United Kingdom",
    "members": [
      "Matt Bellamy",
      "Chris Wolstenholme",
      "Dominic Howard"
    ],
    "bannerImage": "https://i.scdn.co/image/ab67618600001016ef59f1c62339f247d38ded80",
    "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090"
  },
  {
    "id": "61d742963c4cd92feb017483",
    "name": "Red Hot Chili Peppers",
    "type": "Rock band",
    "originCountry": "California",
    "members": [
      "Anthony Kiedis",
      "John Frusciante",
      "Dave Navarro",
      "Chad Smith",
      "Flea",
      "Josh Klinghoffer",
      "Hillel Slovak",
      "Jack Irons",
      "Jack Sherman",
      "Cliff Martinez",
      "Arik Marshall",
      "D.H. Peligro",
      "Jesse Tobias",
      "DeWayne McKnight"
    ],
    "bannerImage": "https://i.scdn.co/image/ab676186000010168de7d477c0febe421ea84332",
    "mbid": "8bfac288-ccc5-448d-9573-c33ea2aa5c30"
  },
  {
    "id": "61d742963c4cd92feb017484",
    "name": "R.E.M.",
    "type": "Rock band",
    "originCountry": "Georgia",
    "members": [
      "Michael Stipe",
      "Peter Buck",
      "Bill Berry",
      "Mike Mills"
    ],
    "bannerImage": "https://i.scdn.co/image/ab67618600001016c210c5b1c9b555891662e79f",
    "mbid": "ea4dfa26-f633-4da6-a52a-f49ea4897b58"
  },
  {
    "id": "61d742973c4cd92feb017485",
    "name": "Radiohead",
    "type": "Rock band",
    "originCountry": "United Kingdom",
    "members": [
      "Thom Yorke",
      "Jonny Greenwood",
      "Ed O'Brien",
      "Colin Greenwood",
      "Philip Selway"
    ],
    "bannerImage": "https://i.scdn.co/image/ab676186000010161802a4cbec82e078cc15cbb0",
    "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711"
  }
]
```

</details>

<details><summary>GET /artists/{MBID}</summary>

```json
{
  "id": "61d742963c4cd92feb017480",
  "name": "The Police",
  "type": "Rock band",
  "originCountry": "United Kingdom",
  "members": [
    "Sting",
    "Stewart Copeland",
    "Andy Summers",
    "Henry Padovani"
  ],
  "bannerImage": "https://i.scdn.co/image/ab67618600001016af496a5f2377f1149d2a5cf3",
  "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a"
}
```

</details>

<details><summary>POST /songs</summary>

</details>

<details><summary>PUT /songs</summary>

</details>

<details><summary>DELETE /songs</summary>

</details>

###Artist-service

[Artist-service repository](https://github.com/RobinDeClerck/artist-service) |
[Okteto](https://artist-service-server-robindeclerck.cloud.okteto.net) (testing only!) |
[Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/artist-service)

###Album-service

[Album-service repository](https://github.com/RobinDeClerck/album-service) |
[Okteto](https://album-service-server-robindeclerck.cloud.okteto.net) (testing only!) |
[Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/album-service)

###Song-service

[Song-service repository](https://github.com/anthonydecap/service-song) |
[Okteto](https://song-service-server-robindeclerck.cloud.okteto.net/) (testing only!) |
[Dockerhub](https://hub.docker.com/r/realnigel/song-service)

###Genre-service

[Genre-service repository](https://github.com/JoNaulaerts/genre-service) |
[Okteto](https://genre-service-server-robindeclerck.cloud.okteto.net/) (testing only!) |
[Dockerhub](https://hub.docker.com/r/jonaulaerts/genre-service)

###Frontend

[Frontend repository](https://github.com/RobinDeClerck/music-frontend) |
[Okteto](https://frontend-server-robindeclerck.cloud.okteto.net/) |
[Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/music-frontend)

![frontend](https://cdn.discordapp.com/attachments/668890794882629662/928774887521288292/frontend.PNG)

##Okteto

All repositories run on Okteto with a K8s file found in the 'K8s' folder in every repository.\
On deploying Okteto will automatically read the yaml files in the 'K8s' folder and run 'kubectl apply'.
![okteto](https://cdn.discordapp.com/attachments/668890794882629662/928775961707700254/okteto-deployments.PNG)

