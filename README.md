# APT Project
Repo Advanced Programming Topics Eindproject (groepsproject).\
Dit project bevat een applicatie die gebruik maakt van microservices in Java Spring Boot, unit & integration tests, Dockerfiles, Kubernetes

Belangrijk:
- Een song heeft een ISRC (International Standard Recording Code) code (handig: https://www.isrcfinder.com/)
- Een album heeft een MAID code (Music Album Identifier)  (based on: https://musicbrainz.org/doc/Release_Group)
- Een artist heeft een MBID code (Music Band Identifier) (based on: https://musicbrainz.org/doc/MusicBrainz_Identifier)

## Table of contents

- [Project](#project)
  - [Team](#team)
  - [Thema](#thema)
- [Services](#services)
  - [BRANK-EDGE-SERVICE](#brank-edge-service)
    - [Brank-edge-service repository](https://github.com/RobinDeClerck/brank-edge-service) |
      [Okteto-link](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/) |
      [Sonarcloud](https://sonarcloud.io/project/overview?id=RobinDeClerck_brank-edge-service)
  - [ARTIST-SERVICE](#brank-edge-service)
    - [Artist-service repository](https://github.com/RobinDeClerck/artist-service) |
      [Okteto](https://artist-service-server-robindeclerck.cloud.okteto.net) |
      [Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/artist-service) |
      [Sonarcloud](https://sonarcloud.io/project/overview?id=RobinDeClerck_artist-service)
  - [ALBUM-SERVICE](#album-service)
    - [Album-service repository](https://github.com/RobinDeClerck/album-service) |
      [Okteto](https://album-service-server-robindeclerck.cloud.okteto.net) |
      [Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/album-service) |
      [Sonarcloud](https://sonarcloud.io/project/overview?id=RobinDeClerck_album-service)
  - [SONG-SERVICE](#song-service)
    - [Song-service repository](https://github.com/anthonydecap/service-song) |
      [Okteto](https://song-service-server-robindeclerck.cloud.okteto.net/) |
      [Dockerhub](https://hub.docker.com/r/realnigel/song-service) |
      [Sonarcloud](https://sonarcloud.io/project/overview?id=anthonydecap_service-song)
  - [GENRE-SERVICE](#genre-service)
    - [Genre-service repository](https://github.com/JoNaulaerts/genre-service) |
      [Okteto](https://genre-service-server-robindeclerck.cloud.okteto.net/) |
      [Dockerhub](https://hub.docker.com/r/jonaulaerts/genre-service) |
      [Sonarcloud](https://sonarcloud.io/project/overview?id=JoNaulaerts_genre-service)
  - [FRONTEND](#frontend)
    - [Frontend repository](https://github.com/RobinDeClerck/music-frontend) |
      [Okteto FRONTEND LINK](https://frontend-server-robindeclerck.cloud.okteto.net/) |
      [Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/music-frontend) |
      [Sonarcloud](https://sonarcloud.io/project/overview?id=RobinDeClerck_music-frontend)
- [Okteto](#okteto)


## Project

### Team

- [Robin De Clerck](https://github.com/RobinDeClerck)
- [Jo Naulaerts](https://github.com/JoNaulaerts)
- [Anthony Decap](https://github.com/anthonydecap)

### Thema

Als thema voor dit project kozen wij voor een muziekbibliotheek. Op deze manier hebben we een toepassing gemaakt waarmee we onze favoriete Albums en Songs kunnen raadplegen. In deze bibliotheek vindt u de volgende zaken (microservices) terug:

- Album (heeft een bepaalde Artist en een aantal Songs)
- Artist
- Song (wordt gemaakt door een bepaalde Artist en behoort tot een Album en een Genre)
- Genre (kan toegeschreven worden aan een Album en een Song)

Deze microservices werden samengebracht in 1 centrale service: de brank-edge-service. Vanuit de brank-edge-service kan alle informatie uit de onderliggende microservices opgevraagd en getoond worden.

## Services

### Brank-Edge-service

[Brank-edge-service repository](https://github.com/RobinDeClerck/brank-edge-service) |
[Okteto](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/) |
[Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/brank-edge-service) |
[Sonarcloud](https://sonarcloud.io/project/overview?id=RobinDeClerck_brank-edge-service)

#### Schema

![schema](https://cdn.discordapp.com/attachments/668890794882629662/928740542244880474/APT-Schema.png)

#### SwaggerUI
[Goto SwaggerUI page](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/swagger-ui.html)

![swaggerui](https://cdn.discordapp.com/attachments/668890794882629662/929066922073530448/swagger2.PNG)


#### Postman requests

Open de request door te klikken op de titel.
<details><summary>GET /albums</summary>

Used: [https://brank-edge-service-server-robindeclerck.cloud.okteto.net/albums](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/albums)

```json
[
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
        "title": "string",
        "length": 0,
        "url": "string",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2000193"
      },
      {
        "id": "61d742a0e3506c28e7334b5c",
        "genre": "Rock",
        "title": "Oblivion",
        "length": 161,
        "url": "3Ye5icBka8ODjcaEQakPvZ",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2001120"
      },
      {
        "id": "61d742a0e3506c28e7334b5d",
        "genre": "Rock",
        "title": "Typhoons",
        "length": 236,
        "url": "5aFGo8wHEntVxFI8IF7Wuj",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2001121"
      },
      {
        "id": "61d742a0e3506c28e7334b5e",
        "genre": "Rock",
        "title": "Who Needs Friends",
        "length": 190,
        "url": "7AXoSHtReIvoJPi5XKXecl",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2001122"
      },
      {
        "id": "61d742a0e3506c28e7334b5f",
        "genre": "Rock",
        "title": "Million and One",
        "length": 258,
        "url": "7AXoSHtReIvoJPi5XKXecl",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2001123"
      },
      {
        "id": "61d742a0e3506c28e7334b60",
        "genre": "Rock",
        "title": "Limbo",
        "length": 293,
        "url": "1P8BrsNLHWO5R0cK6zvyhc",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2001124"
      },
      {
        "id": "61d742a0e3506c28e7334b61",
        "genre": "Rock",
        "title": "Either You Want It",
        "length": 180,
        "url": "1P8BrsNLHWO5R0cK6zvyhc",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2001125"
      },
      {
        "id": "61d742a0e3506c28e7334b62",
        "genre": "Rock",
        "title": "Boilermaker",
        "length": 209,
        "url": "27BEATf1JFhKDmwJdpGVSk",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2001126"
      },
      {
        "id": "61d742a0e3506c28e7334b63",
        "genre": "Rock",
        "title": "Mad Visions",
        "length": 189,
        "url": "3S66ufJ1RdjOKf2azjXWjI",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2001127"
      },
      {
        "id": "61d742a1e3506c28e7334b64",
        "genre": "Rock",
        "title": "Hold On",
        "length": 194,
        "url": "5rUGbardlhPNzbHH3qOEOk",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2001128"
      },
      {
        "id": "61d742a1e3506c28e7334b65",
        "genre": "Rock",
        "title": "All We Have Is Now",
        "length": 213,
        "url": "4CUyNgMxAFKFEf1KrbAEbY",
        "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
        "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
        "isrc": "GBAHT2001129"
      }
    ],
    "release": "2021-04-30",
    "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
    "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51"
  },
  {
    "name": "The Bends",
    "image": "https://i.scdn.co/image/ab67616d00001e029293c743fa542094336c5e12",
    "genre": "Rock",
    "artist": {
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
    },
    "songs": [
      {
        "id": "61d742a1e3506c28e7334b66",
        "genre": "Rock",
        "title": "Planet Telex",
        "length": 259,
        "url": "37JISltgxizbDAyNEEqkTY",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "b8048f24-c026-3398-b23a-b5e50716cbc7",
        "isrc": "GBAYE9400059"
      },
      {
        "id": "61d742a1e3506c28e7334b68",
        "genre": "Rock",
        "title": "High and Dry",
        "length": 257,
        "url": "2a1iMaoWQ5MnvLFBDv4qkf",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "b8048f24-c026-3398-b23a-b5e50716cbc7",
        "isrc": "GBAYE9400055"
      },
      {
        "id": "61d742a1e3506c28e7334b69",
        "genre": "Rock",
        "title": "Fake Plastic Trees",
        "length": 290,
        "url": "73CKjW3vsUXRpy3NnX4H7F",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "b8048f24-c026-3398-b23a-b5e50716cbc7",
        "isrc": "GBAYE9400056"
      },
      {
        "id": "61d742a1e3506c28e7334b6a",
        "genre": "Rock",
        "title": "Bones",
        "length": 189,
        "url": "76RAlQcfuQknnQFruYDj6Q",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "b8048f24-c026-3398-b23a-b5e50716cbc7",
        "isrc": "GBAYE9400057"
      },
      {
        "id": "61d742a1e3506c28e7334b6c",
        "genre": "Rock",
        "title": "Just",
        "length": 234,
        "url": "1dyTcli07c77mtQK3ahUZR",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "b8048f24-c026-3398-b23a-b5e50716cbc7",
        "isrc": "GBAYE9400060"
      },
      {
        "id": "61d742a1e3506c28e7334b6d",
        "genre": "Rock",
        "title": "My Iron Lung",
        "length": 276,
        "url": "0jyikFM0Umv0KlnrOEKtTG",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "b8048f24-c026-3398-b23a-b5e50716cbc7",
        "isrc": "GBAYE9400065"
      },
      {
        "id": "61d742a1e3506c28e7334b6e",
        "genre": "Rock",
        "title": "Bullet Proof ... I Wish I Was",
        "length": 208,
        "url": "5XuU9htN358NTMCcqRvfDV",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "b8048f24-c026-3398-b23a-b5e50716cbc7",
        "isrc": "GBAYE9400064"
      },
      {
        "id": "61d742a1e3506c28e7334b6f",
        "genre": "Rock",
        "title": "Black Star",
        "length": 247,
        "url": "6UO72VSXEONxdfLyABihs9",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "b8048f24-c026-3398-b23a-b5e50716cbc7",
        "isrc": "GBAYE9400063"
      },
      {
        "id": "61d742a1e3506c28e7334b70",
        "genre": "Rock",
        "title": "Sulk",
        "length": 222,
        "url": "1elQc2QcyuBkI8FUIbNvcy",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "b8048f24-c026-3398-b23a-b5e50716cbc7",
        "isrc": "GBAYE9400062"
      }
    ],
    "release": "1995-03-13",
    "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
    "maid": "b8048f24-c026-3398-b23a-b5e50716cbc7"
  },
  {
    "name": "Pablo Honey",
    "image": "https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95",
    "genre": "Rock",
    "artist": {
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
    },
    "songs": [
      {
        "id": "61d742a1e3506c28e7334b72",
        "genre": "Rock",
        "title": "You",
        "length": 208,
        "url": "5KZ0qobWEFl892YjIC02SE",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9200113"
      },
      {
        "id": "61d742a1e3506c28e7334b73",
        "genre": "Rock",
        "title": "Creep",
        "length": 238,
        "url": "70LcF31zb1H0PyJoS1Sx1r",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9200070"
      },
      {
        "id": "61d742a1e3506c28e7334b74",
        "genre": "Rock",
        "title": "How Do You?",
        "length": 132,
        "url": "5qsgK2wcodYCEbgbdCpYOG",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9300105"
      },
      {
        "id": "61d742a1e3506c28e7334b75",
        "genre": "Rock",
        "title": "Stop Whispering",
        "length": 325,
        "url": "3CbAW3GjkBKfErt4LLbSzr",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9300106"
      },
      {
        "id": "61d742a1e3506c28e7334b76",
        "genre": "Rock",
        "title": "Thinking About You",
        "length": 144,
        "url": "46tfxn5lP7Qsbz7NHsj9iu",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9200114"
      },
      {
        "id": "61d742a1e3506c28e7334b77",
        "genre": "Rock",
        "title": "Anyone Can Play Guitar",
        "length": 217,
        "url": "23oUaizFBFVFI5PxJrkiO5",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9300107"
      },
      {
        "id": "61d742a1e3506c28e7334b78",
        "genre": "Rock",
        "title": "Ripcord",
        "length": 189,
        "url": "2wOvYLilnDJfuPXGHGFAWZ",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9300108"
      },
      {
        "id": "61d742a1e3506c28e7334b79",
        "genre": "Rock",
        "title": "Vegetable",
        "length": 192,
        "url": "6HbWoyinLdXPZmk6xONuKw",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9300109"
      },
      {
        "id": "61d742a1e3506c28e7334b7a",
        "genre": "Rock",
        "title": "Prove Yourself",
        "length": 145,
        "url": "0GDuL9TCO41PgsrKWBSGlm",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9200115"
      },
      {
        "id": "61d742a1e3506c28e7334b7b",
        "genre": "Rock",
        "title": "I Can't",
        "length": 253,
        "url": "13nQ70PnhDnTkYqCmdg3sy",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9300110"
      },
      {
        "id": "61d742a1e3506c28e7334b7c",
        "genre": "Rock",
        "title": "Lurgee",
        "length": 187,
        "url": "30C1FoJzEhNUILsxghioGz",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9200116"
      },
      {
        "id": "61d742a1e3506c28e7334b7d",
        "genre": "Rock",
        "title": "Blow Out",
        "length": 282,
        "url": "5XpcTQlNnfIQbiWE4hvYo7",
        "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
        "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851",
        "isrc": "GBAYE9300111"
      }
    ],
    "release": "1993-02-22",
    "mbid": "a74b1b7f-71a5-4011-9441-d0b5e4122711",
    "maid": "cd76f76b-ff15-3784-a71d-4da3078a6851"
  },
  {
    "name": "Reggatta de Blanc",
    "image": "https://i.scdn.co/image/ab67616d00001e028ec81cc654b45ade8bdf1486",
    "genre": "Rock",
    "artist": {
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
    "songs": [
      {
        "id": "61d742a1e3506c28e7334b7e",
        "genre": "Rock",
        "title": "Message In A Bottle",
        "length": 290,
        "url": "1oYYd2gnWZYrt89EBXdFiO",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201170"
      },
      {
        "id": "61d742a1e3506c28e7334b7f",
        "genre": "Rock",
        "title": "Reggatta De Blanc",
        "length": 185,
        "url": "2EEp2vTGSRDSLHWUF80EZZ",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201171"
      },
      {
        "id": "61d742a1e3506c28e7334b80",
        "genre": "Rock",
        "title": "It's Alright For You",
        "length": 192,
        "url": "5fTI7JCaMRK09WtwG8ZrRK",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201172"
      },
      {
        "id": "61d742a1e3506c28e7334b81",
        "genre": "Rock",
        "title": "Bring On The Night",
        "length": 255,
        "url": "4EkNINvDLeGgIL4zYGsYPb",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201173"
      },
      {
        "id": "61d742a2e3506c28e7334b82",
        "genre": "Rock",
        "title": "Deathwish",
        "length": 251,
        "url": "4i3SC58kB65zfKo1oOW1q9",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201174"
      },
      {
        "id": "61d742a2e3506c28e7334b83",
        "genre": "Rock",
        "title": "Walking On The Moon",
        "length": 300,
        "url": "62uLNJgVZaFiEiKV4LpoYJ",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201175"
      },
      {
        "id": "61d742a2e3506c28e7334b84",
        "genre": "Rock",
        "title": "On Any Other Day",
        "length": 176,
        "url": "6rN4PWNTD8AY1mfLslqrQG",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201176"
      },
      {
        "id": "61d742a2e3506c28e7334b85",
        "genre": "Rock",
        "title": "The Bed's Too Big Without You",
        "length": 265,
        "url": "3PfBnnkOf0LbCw2jixUCQG",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201177"
      },
      {
        "id": "61d742a2e3506c28e7334b86",
        "genre": "Rock",
        "title": "Contact",
        "length": 157,
        "url": "5MuKkqc8lnzldouHA0MwgL",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201178"
      },
      {
        "id": "61d742a2e3506c28e7334b87",
        "genre": "Rock",
        "title": "Does Everyone Stare",
        "length": 227,
        "url": "45BfHifOOhDpyPJn7El1JU",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201179"
      },
      {
        "id": "61d742a2e3506c28e7334b88",
        "genre": "Rock",
        "title": "No Time This Time",
        "length": 197,
        "url": "5qolpk9X28wwWLGE8sZ328",
        "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
        "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d",
        "isrc": "GBAAM0201180"
      }
    ],
    "release": "1979-10-02",
    "mbid": "9e0e2b01-41db-4008-bd8b-988977d6019a",
    "maid": "2b98e6d7-a521-332f-961e-d281ba33ba3d"
  },
  {
    "name": "Black Holes and Revelations",
    "image": "https://i.scdn.co/image/ab67616d00001e0228933b808bfb4cbbd0385400",
    "genre": "Rock",
    "artist": {
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
    "songs": [
      {
        "id": "61d742a2e3506c28e7334b89",
        "genre": "Rock",
        "title": "Take a Bow",
        "length": 275,
        "url": "4jrCMOG9OPe6iF4vWFxatb",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500591"
      },
      {
        "id": "61d742a2e3506c28e7334b8a",
        "genre": "Rock",
        "title": "Starlight",
        "length": 240,
        "url": "3skn2lauGk7Dx6bVIt5DVj",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500592"
      },
      {
        "id": "61d742a2e3506c28e7334b8b",
        "genre": "Rock",
        "title": "Supermassive Black Hole",
        "length": 212,
        "url": "3lPr8ghNDBLc2uZovNyLs9",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500593"
      },
      {
        "id": "61d742a2e3506c28e7334b8c",
        "genre": "Rock",
        "title": "Map of the Problematique",
        "length": 258,
        "url": "5YXr4AGfUQpLSxtFSsKUh6",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500594"
      },
      {
        "id": "61d742a2e3506c28e7334b8d",
        "genre": "Rock",
        "title": "Soldier's Poem",
        "length": 124,
        "url": "6jH5aCuXgtygWpx7BF54at",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500595"
      },
      {
        "id": "61d742a2e3506c28e7334b8e",
        "genre": "Rock",
        "title": "Invincible",
        "length": 301,
        "url": "2zmR3FG7iOGDAdwrVPzdg9",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500596"
      },
      {
        "id": "61d742a2e3506c28e7334b8f",
        "genre": "Rock",
        "title": "Assassin",
        "length": 211,
        "url": "6JnFVmPyJvjnfBag0hhIFa",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500597"
      },
      {
        "id": "61d742a2e3506c28e7334b90",
        "genre": "Rock",
        "title": "Exo-Politics",
        "length": 233,
        "url": "20vZII9Yu52czI9Fk4p39r",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500601"
      },
      {
        "id": "61d742a2e3506c28e7334b91",
        "genre": "Rock",
        "title": "City of Delusion",
        "length": 288,
        "url": "3Sno9FE8r2uz8QP0MtnTPL",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500599"
      },
      {
        "id": "61d742a2e3506c28e7334b92",
        "genre": "Rock",
        "title": "Hoodoo",
        "length": 223,
        "url": "0EkE0ripJ9OFNzvZANzo5C",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500598"
      },
      {
        "id": "61d742a2e3506c28e7334b93",
        "genre": "Rock",
        "title": "Knights of Cydonia",
        "length": 366,
        "url": "7ouMYWpwJ422jRcDASZB7P",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0500600"
      },
      {
        "id": "61d742a2e3506c28e7334b94",
        "genre": "Rock",
        "title": "Glorious",
        "length": 281,
        "url": "6IfitwQQ1Gu9g9QnLWDHRY",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd",
        "isrc": "GBAHT0600546"
      }
    ],
    "release": "2006-07-03",
    "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
    "maid": "af2e8e23-e9c3-4e67-8ad8-66387c5898fd"
  },
  {
    "name": "The Resistance",
    "image": "https://i.scdn.co/image/ab67616d00001e02b6d4566db0d12894a1a3b7a2",
    "genre": "Rock",
    "artist": {
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
    "songs": [
      {
        "id": "61d742a2e3506c28e7334b95",
        "genre": "Rock",
        "title": "Uprising",
        "length": 304,
        "url": "4VqPOruhp5EdPBeR92t6lQ",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900320"
      },
      {
        "id": "61d742a2e3506c28e7334b96",
        "genre": "Rock",
        "title": "Resistance",
        "length": 346,
        "url": "1C2QJNTmsTxCDBuIgai8QV",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900321"
      },
      {
        "id": "61d742a2e3506c28e7334b97",
        "genre": "Rock",
        "title": "Undisclosed Desires",
        "length": 235,
        "url": "0It6VJoMAare1zdV2wxqZq",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900322"
      },
      {
        "id": "61d742a2e3506c28e7334b98",
        "genre": "Rock",
        "title": "United States of Eurasia (+Collateral Damage)",
        "length": 347,
        "url": "0tHbQRjL5phd8OoYl2Bdnd",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900323"
      },
      {
        "id": "61d742a2e3506c28e7334b99",
        "genre": "Rock",
        "title": "Guiding Light",
        "length": 253,
        "url": "7jZ5A8bf63qYaUXfuGoxVk",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900324"
      },
      {
        "id": "61d742a2e3506c28e7334b9a",
        "genre": "Rock",
        "title": "Unnatural Selection",
        "length": 414,
        "url": "28FJMlLUu9NHuwlZWFKDn7",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900325"
      },
      {
        "id": "61d742a2e3506c28e7334b9b",
        "genre": "Rock",
        "title": "MK Ultra",
        "length": 246,
        "url": "0MrkZz4D3fGlEkhebjPPrh",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900326"
      },
      {
        "id": "61d742a2e3506c28e7334b9c",
        "genre": "Rock",
        "title": "I Belong to You (+Mon Coeur S'Ouvre a Ta Voix)",
        "length": 338,
        "url": "114rzL6VEy9bb3amPcY3tw",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900327"
      },
      {
        "id": "61d742a2e3506c28e7334b9d",
        "genre": "Rock",
        "title": "Exogenesis: Symphony Pt. 1 (Overture)",
        "length": 258,
        "url": "6zkhhG8iQ8waiwGkQuhoE1",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900328"
      },
      {
        "id": "61d742a2e3506c28e7334b9e",
        "genre": "Rock",
        "title": "Exogenesis: Symphony Pt. 2 (Cross-pollination)",
        "length": 236,
        "url": "39kUTBz4uJoy5VtmIybz9D",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900329"
      },
      {
        "id": "61d742a2e3506c28e7334b9f",
        "genre": "Rock",
        "title": "Exogenesis: Symphony Pt. 3 (Redemption)",
        "length": 287,
        "url": "76ZDwA8uTyMys4LIS3pBUX",
        "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
        "maid": "8411a4db-e104-4e45-995f-24b2f1849437",
        "isrc": "GBAHT0900330"
      }
    ],
    "release": "2009-09-11",
    "mbid": "9c9f1380-2516-4fc9-a3e6-f9f61941d090",
    "maid": "8411a4db-e104-4e45-995f-24b2f1849437"
  }
]
```

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
      "title": "string",
      "length": 0,
      "url": "string",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2000193"
    },
    {
      "id": "61d742a0e3506c28e7334b5c",
      "genre": "Rock",
      "title": "Oblivion",
      "length": 161,
      "url": "3Ye5icBka8ODjcaEQakPvZ",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2001120"
    },
    {
      "id": "61d742a0e3506c28e7334b5d",
      "genre": "Rock",
      "title": "Typhoons",
      "length": 236,
      "url": "5aFGo8wHEntVxFI8IF7Wuj",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2001121"
    },
    {
      "id": "61d742a0e3506c28e7334b5e",
      "genre": "Rock",
      "title": "Who Needs Friends",
      "length": 190,
      "url": "7AXoSHtReIvoJPi5XKXecl",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2001122"
    },
    {
      "id": "61d742a0e3506c28e7334b5f",
      "genre": "Rock",
      "title": "Million and One",
      "length": 258,
      "url": "7AXoSHtReIvoJPi5XKXecl",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2001123"
    },
    {
      "id": "61d742a0e3506c28e7334b60",
      "genre": "Rock",
      "title": "Limbo",
      "length": 293,
      "url": "1P8BrsNLHWO5R0cK6zvyhc",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2001124"
    },
    {
      "id": "61d742a0e3506c28e7334b61",
      "genre": "Rock",
      "title": "Either You Want It",
      "length": 180,
      "url": "1P8BrsNLHWO5R0cK6zvyhc",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2001125"
    },
    {
      "id": "61d742a0e3506c28e7334b62",
      "genre": "Rock",
      "title": "Boilermaker",
      "length": 209,
      "url": "27BEATf1JFhKDmwJdpGVSk",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2001126"
    },
    {
      "id": "61d742a0e3506c28e7334b63",
      "genre": "Rock",
      "title": "Mad Visions",
      "length": 189,
      "url": "3S66ufJ1RdjOKf2azjXWjI",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2001127"
    },
    {
      "id": "61d742a1e3506c28e7334b64",
      "genre": "Rock",
      "title": "Hold On",
      "length": 194,
      "url": "5rUGbardlhPNzbHH3qOEOk",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2001128"
    },
    {
      "id": "61d742a1e3506c28e7334b65",
      "genre": "Rock",
      "title": "All We Have Is Now",
      "length": 213,
      "url": "4CUyNgMxAFKFEf1KrbAEbY",
      "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
      "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
      "isrc": "GBAHT2001129"
    }
  ],
  "release": "2021-04-30",
  "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
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
  "id": "61d7429ce40c25722ecaf15f",
  "genreName": "Rock",
  "description": "Rock music is a broad genre of popular music that originated as \"rock and roll\" in the United States in the late 1940s and early 1950s, developing into a range of different styles in the mid-1960s and later, particularly in the United States and the United Kingdom."
}
```

</details>
<details><summary>GET /artists</summary>

used: [https://brank-edge-service-server-robindeclerck.cloud.okteto.net/artists](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/artists)

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

used: [https://brank-edge-service-server-robindeclerck.cloud.okteto.net/artists/9e0e2b01-41db-4008-bd8b-988977d6019a](https://brank-edge-service-server-robindeclerck.cloud.okteto.net/artists/9e0e2b01-41db-4008-bd8b-988977d6019a)

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

used: [http://localhost:8050/songs?isrc=GBAHT2000193&mbid=aa62b28e-b6d4-4086-91d4-e5fac1ed56f3&maid=dd7e7ced-a44d-4ce5-9654-c60a0d71fc51&genre=Rock&title=Trouble’s Coming&length=228&url=6voIJ7OWwRabSZDC77D5Hp](http://localhost:8050/songs?isrc=GBAHT2000193&mbid=aa62b28e-b6d4-4086-91d4-e5fac1ed56f3&maid=dd7e7ced-a44d-4ce5-9654-c60a0d71fc51&genre=Rock&title=Trouble’s Coming&length=228&url=6voIJ7OWwRabSZDC77D5Hp)

```json
{
  "id": "61d87cfd32f2950b5c5ca6ce",
  "genre": "Rock",
  "title": "Trouble’s Coming",
  "length": 228,
  "url": "6voIJ7OWwRabSZDC77D5Hp",
  "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
  "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
  "isrc": "GBAHT2000193"
}
```

</details>

<details><summary>PUT /songs</summary>

used: [http://localhost:8050/songs?isrc=GBAHT2000193&mbid=aa62b28e-b6d4-4086-91d4-e5fac1ed56f3&maid=dd7e7ced-a44d-4ce5-9654-c60a0d71fc51&genre=Rock&title=Trouble’s Coming&length=2281&url=6voIJ7OWwRabSZDC77D5Hp](http://localhost:8050/songs?isrc=GBAHT2000193&mbid=aa62b28e-b6d4-4086-91d4-e5fac1ed56f3&maid=dd7e7ced-a44d-4ce5-9654-c60a0d71fc51&genre=Rock&title=Trouble’s Coming&length=2281&url=6voIJ7OWwRabSZDC77D5Hp)

```json
{
  "id": "61d4a4cf0d3c1d361a649520",
  "genre": "Rock",
  "title": "Trouble’s Coming",
  "length": 2281,
  "url": "6voIJ7OWwRabSZDC77D5Hp",
  "mbid": "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3",
  "maid": "dd7e7ced-a44d-4ce5-9654-c60a0d71fc51",
  "isrc": "GBAHT2000193"
}
```

</details>

<details><summary>DELETE /songs</summary>

used: [http://localhost:8050/songs/GBAHT2000193](http://localhost:8050/songs/GBAHT2000193)

Status 200 OK

</details>

### Artist-service

[Artist-service repository](https://github.com/RobinDeClerck/artist-service) |
[Okteto](https://artist-service-server-robindeclerck.cloud.okteto.net) (for testing only!) |
[Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/artist-service) |
[Sonarcloud](https://sonarcloud.io/project/overview?id=RobinDeClerck_artist-service)

**Requests**:\
**GET** /artists\
**GET** /artists/{MBID}

### Album-service

[Album-service repository](https://github.com/RobinDeClerck/album-service) |
[Okteto](https://album-service-server-robindeclerck.cloud.okteto.net) (for testing only!) |
[Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/album-service) |
[Sonarcloud](https://sonarcloud.io/project/overview?id=RobinDeClerck_album-service)

**Requests**:\
**GET** /albums\
**GET** /albums/{MAID}

### Song-service

[Song-service repository](https://github.com/anthonydecap/service-song) |
[Okteto](https://song-service-server-robindeclerck.cloud.okteto.net/) (for testing only!) |
[Dockerhub](https://hub.docker.com/r/realnigel/song-service) |
[Sonarcloud](https://sonarcloud.io/project/overview?id=anthonydecap_service-song)

**Requests**:\
**GET** /songs/artist/{MBID}\
**GET** /songs/album/{MAID}\
**GET** /songs/genre/{genre}\
**GET** /songs/{ISRC}\
**POST** /songs\
**PUT** /songs\
**DELETE** /songs/{ISRC}

### Genre-service

[Genre-service repository](https://github.com/JoNaulaerts/genre-service) |
[Okteto](https://genre-service-server-robindeclerck.cloud.okteto.net/) (for testing only!) |
[Dockerhub](https://hub.docker.com/r/jonaulaerts/genre-service) |
[Sonarcloud](https://sonarcloud.io/project/overview?id=JoNaulaerts_genre-service)

**Requests**:\
**GET** /genres\
**GET** /genres/{genreName}

### Frontend

[Frontend repository](https://github.com/RobinDeClerck/music-frontend) |
[Okteto FRONTEND LINK](https://frontend-server-robindeclerck.cloud.okteto.net/) |
[Dockerhub](https://hub.docker.com/repository/docker/robindeclerck/music-frontend) |
[Sonarcloud](https://sonarcloud.io/project/overview?id=RobinDeClerck_music-frontend)

![frontend](https://cdn.discordapp.com/attachments/668890794882629662/928774887521288292/frontend.PNG)

## Okteto

All repositories run on Okteto with a K8s file found in the 'K8s' folder in every repository.\
On deploying Okteto will automatically read the yaml files in the 'K8s' folder and run 'kubectl apply'.
![okteto](https://cdn.discordapp.com/attachments/668890794882629662/928775961707700254/okteto-deployments.PNG)

